package com.gateway.service.userManagement.impl;

import com.gateway.mapper.dao.GeneralMapper;
import com.gateway.mapper.userManagement.UserManagementMapper;
import com.gateway.result.Result;
import com.gateway.service.userManagement.UserManagementService;
import com.gateway.utils.SQLConverterUtil;
import com.gateway.vo.userManagement.UserManagementVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final GeneralMapper generalMapper;

    private final UserManagementMapper userManagementMapper;

    @Autowired(required = false)
    public UserManagementServiceImpl(GeneralMapper generalMapper, UserManagementMapper userManagementMapper) {
        this.generalMapper = generalMapper;
        this.userManagementMapper = userManagementMapper;
    }

    @Override
    public Result queryUser(Map<String, Object> parem) {
        List<UserManagementVO> userManagementVOS = userManagementMapper.queryUser(parem);
        computeRegion(userManagementVOS);
        return Result.success(userManagementVOS, userManagementVOS.size());
    }


    @Override
    @Transactional
    public Result update(Map<String, Object> parem) {
        String id = MapUtils.getString(parem, "id");
        String userName = MapUtils.getString(parem, "userName");
        String name = MapUtils.getString(parem, "name");
        String status = MapUtils.getString(parem, "status");
        String accountTypeId = MapUtils.getString(parem, "accountTypeId");
        String appOrganizationId = MapUtils.getString(parem, "appOrganizationId");
        String permissionTypeId = MapUtils.getString(parem, "permissionTypeId");
        List<String> appRegionIds = (List<String>) MapUtils.getObject(parem, "appRegionIds");
        List<String> appRolesIds = (List<String>) MapUtils.getObject(parem, "appRolesIds");

        if (StringUtils.isNotEmpty(id)) {
            generalMapper.update(SQLConverterUtil.replaceAllPlaceHolder(
                    "update user_login set name = ?, status = ?, account_type_id = ?, permission_type_id = ?,app_organization_id = ? where id = ?",
                    new Object[]{name, status, accountTypeId, permissionTypeId, appOrganizationId, id}
            ));
        }

        updateAppRegion(appRegionIds, id);
        updateAppRoles(appRolesIds, id);
        computeUserApps(id);
        return Result.success();
    }

    /**
     * 更新用户与区域关系
     * @param appRegionIds
     * @param id
     */
    private void updateAppRegion(List<String> appRegionIds, String id) {
        generalMapper.delete(SQLConverterUtil.replaceAllPlaceHolder(
                "delete from user_app_region uap where uap.user_id = ?",
                new Object[]{id}
        ));
        String appRegionIdStr = appRegionIds.stream()
                .map(isvalue -> "'" + isvalue + "'")
                .collect(Collectors.joining(","));

        if (StringUtils.isNotEmpty(appRegionIdStr)) {
            generalMapper.insert("insert into user_app_region (user_id, app_region_id) " +
                    "select '" + id + "', ar.id from app_region ar where ar.id in (" +appRegionIdStr+ ") ");
        }
    }

    /**
     * 更新用户与角色关系
     * @param appRolesIds
     * @param id
     */
    private void updateAppRoles(List<String> appRolesIds, String id) {
        generalMapper.delete(SQLConverterUtil.replaceAllPlaceHolder(
                "delete from user_app_roles uar where uar.user_id = ?",
                new Object[]{id}
        ));
        String appRolesIdStr = appRolesIds.stream()
                .map(isvalue -> "'" + isvalue + "'")
                .collect(Collectors.joining(","));

        if (StringUtils.isNotEmpty(appRolesIdStr)) {
            generalMapper.insert("insert into user_app_roles (user_id, app_roles_id) " +
                    "select '" + id + "', ar.id from app_roles ar where ar.id in (" + appRolesIdStr + ")");
        }
    }

    /**
     * 计算区域特殊处理
     * @param userManagementVOS
     */
    private void computeRegion(List<UserManagementVO> userManagementVOS) {
        for (int i = 0; i < userManagementVOS.size(); i++) {
            UserManagementVO userManagementVO = userManagementVOS.get(i);
            String appRegionIds = userManagementVO.getAppRegionIds();
            if (StringUtils.isEmpty(appRegionIds))
                continue;
            String[] regionIds = appRegionIds.split(",");
            boolean code = true;
            //区
            String area = "";
            //营
            String camp = "";
            //营下
            String campUnder = "";
            for (int j = 0; j < regionIds.length; j++) {
                Map<String, Object> regionMap = queryRegionById(regionIds[j]);
                String parentId = MapUtils.getString(regionMap, "parent_id");
                String name = MapUtils.getString(regionMap, "name");
                if (StringUtils.isEmpty(parentId)) {
                    userManagementVO.setAppRegionName(name);
                    code = false;
                    break;
                }
                int parseInt = Integer.parseInt(parentId);
                if (parseInt == 0) {
                    area += "," + name;
                    continue;
                }
                if (parseInt >= 100 && parseInt <= 999 && !StringUtils.isNotEmpty(area)) {
                    camp += "," + name;
                    continue;
                }
                if (parseInt >= 1000 && !StringUtils.isNotEmpty(camp)) {
                    campUnder += "," + name;
                    continue;
                }
            }
            if (StringUtils.isNotEmpty(area) && code) {
                userManagementVO.setAppRegionName(area.substring(1));
                continue;
            }
            if (StringUtils.isNotEmpty(camp) && code) {
                userManagementVO.setAppRegionName(camp.substring(1));
                continue;
            }
            if (StringUtils.isNotEmpty(campUnder) && code) {
                userManagementVO.setAppRegionName(campUnder.substring(1));
                continue;
            }
            userManagementVOS.set(i, userManagementVO);
        }
    }

    /**
     * 根据id查询区域
     * @param id
     * @return
     */
    private Map<String, Object> queryRegionById(String id) {
        return generalMapper.query(SQLConverterUtil.replaceAllPlaceHolder(
                "select * from app_region where id = ?",
                new Object[]{id}));
    }

    /**
     * 用户与模块关系
     */
    private void computeUserApps(String userId) {
        generalMapper.delete(SQLConverterUtil.replaceAllPlaceHolder(
                "delete from user_apps where user_id = ?",
                new Object[]{userId}));
        generalMapper.insert(SQLConverterUtil.replaceAllPlaceHolder(
                "insert into user_apps (user_id, apps_id) " +
                        "select distinct uar.user_id, ar.app_id from user_app_roles uar, app_roles ar where uar.app_roles_id = ar.id and uar.user_id = ?",
                new Object[]{userId}));
    }
}
