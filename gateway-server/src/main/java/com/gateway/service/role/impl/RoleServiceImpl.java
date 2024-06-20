package com.gateway.service.role.impl;

import com.gateway.mapper.dao.GeneralMapper;
import com.gateway.mapper.role.RoleMapper;
import com.gateway.result.Result;
import com.gateway.service.role.RoleService;
import com.gateway.utils.PaginationUtil;
import com.gateway.utils.SQLConverterUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {


    private final RoleMapper roleMapper;

    private final GeneralMapper generalMapper;

    @Autowired(required = false)
    public RoleServiceImpl(RoleMapper roleMapper, GeneralMapper generalMapper) {
        this.roleMapper = roleMapper;
        this.generalMapper = generalMapper;
    }

    @Override
    public Result queryRolesByName(Map<String, Object> param) {
        PaginationUtil.getPagination(param);
        return Result.success(roleMapper.queryRoelsByName(param), roleMapper.queryRoelsByNameCount(param));
    }

    @Override
    @Transactional
    public Result saveOrUpdateRole(Map<String, Object> param) {
        // 用于存储所有ID的集合
        Set<String> menuIds = new HashSet<>();
        String id = MapUtils.getString(param, "id");
        String roleName = MapUtils.getString(param, "roleName");
        String status = MapUtils.getString(param, "status");
        String remark = MapUtils.getString(param, "remark");
        List<Map<String, Object>> menus = (List) MapUtils.getObject(param, "menu");

        // 遍历List并提取所有ID
        for (Map<String, Object> menu : menus) {
            extractIds(menu, menuIds);
        }

        String menuIdStr = menuIds.stream().collect(Collectors.joining(","));

        //编辑
        if (StringUtils.isNotEmpty(id)) {
            generalMapper.update(SQLConverterUtil.replaceAllPlaceHolder(
                    "update roles set role_name = ?, remark = ?, status = ? where id = ?",
                    new Object[]{roleName, remark, status, id}));

            //删除原有关系
            generalMapper.delete(SQLConverterUtil.replaceAllPlaceHolder(
                    "delete from role_menu where role_id = ?",
                    new Object[]{id}));
        } else {
            //添加
            id = generalMapper.nextVal();
            generalMapper.insert(SQLConverterUtil.replaceAllPlaceHolder(
                    "insert into roles (id, role_name, remark, status) values (?,?,?,?)",
                    new Object[]{id, roleName, remark, status}));
        }

        if (StringUtils.isEmpty(menuIdStr))
            return Result.success();

        generalMapper.insert("insert into role_menu (role_id, menu_id) " +
                "select " + id + ",m.id from menus m where id in (" + menuIdStr + ")");
        return Result.success();
    }

    @Override
    @Transactional
    public Result deleteById(List<Map<String, Object>> params) {
        for (int i = 0; i < params.size(); i++) {
            Map<String, Object> parem = params.get(i);
            String id = MapUtils.getString(parem, "id");
            int userRoleCount = generalMapper.queryCount(SQLConverterUtil.replaceAllPlaceHolder(
                    "select count(1) from user_role where role_id = ?",
                    new Object[]{id}));

            int roleMenuCount = generalMapper.queryCount(SQLConverterUtil.replaceAllPlaceHolder(
                    "select count(1) from role_menu where role_id = ?",
                    new Object[]{id}));

            if (userRoleCount >  0 || roleMenuCount > 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.error("用户或菜单有关联禁止删除！");
            }

            generalMapper.delete(SQLConverterUtil.replaceAllPlaceHolder(
                    "delete from roles where id = ?",
                    new Object[]{id}));
        }
        return Result.success();
    }

    @Override
    public Result queryNames() {
        return Result.success(generalMapper.querys("select r.id::varchar, r.role_name as name from roles r where r.role_name is not null"));
    }

    /**
     * 获取所有的id
     * @param menu
     * @param ids
     */
    private void extractIds(Map<String, Object> menu, Set<String> ids) {
        // 提取当前菜单的ID
        String id = MapUtils.getString(menu, "id");
        if (id != null) {
            ids.add(id);
        }

        // 递归提取子菜单中的ID
        List<Map<String, Object>> subMenus = (List<Map<String, Object>>) MapUtils.getObject(menu, "children");
        if (subMenus != null) {
            for (Map<String, Object> subMenu : subMenus) {
                extractIds(subMenu, ids);
            }
        }
    }
}
