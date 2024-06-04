package com.gateway.service.login.impl;

import com.gateway.constant.AppConstant;
import com.gateway.constant.MessageConstant;
import com.gateway.dto.login.UserTypeDTO;
import com.gateway.entity.login.UserLoginEntity;
import com.gateway.mapper.dao.GeneralMapper;
import com.gateway.mapper.login.UserLoginMapper;
import com.gateway.result.Result;
import com.gateway.service.login.UserLoginService;
import com.gateway.utils.*;
import com.gateway.vo.login.UserTypeVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gateway.utils.CommonsUtil.getUserToken;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    private final UserLoginMapper userLoginMapper;

    private final CombinedCacheUtil cacheUtil;

    private final GeneralMapper generalMapper;

    @Autowired
    public UserLoginServiceImpl(UserLoginMapper userLoginMapper, CombinedCacheUtil cacheUtil,
                                GeneralMapper generalMapper) {
        this.userLoginMapper = userLoginMapper;
        this.cacheUtil = cacheUtil;
        this.generalMapper = generalMapper;
    }

    @Override
    public UserLoginEntity query() {
        return userLoginMapper.query();
    }

    @Override
    public Result queryName(UserTypeDTO userTypeDTO) {
        String admin = SimplePasswordUtil.encodePassword("admin");
        log.info("员工登录：{}", userTypeDTO);
        UserLoginEntity userLogin = userLoginMapper.queryName(userTypeDTO);

        // 使用工具类进行用户存在性校验
        Result validationResult = LoginUtil.validateUserLogin(userLogin, userTypeDTO);
        if (validationResult != null) {
            return validationResult; // 用户不存在，返回错误信息
        }

        UserTypeVO userTypeVO = UserTypeVO.builder()
                .username(userLogin.getUsername())
                .build();

        return Result.success(MessageConstant.LOGIN_SUCCESS_MESSAGE,userTypeVO);
    }

    @Override
    public Result loginOut() {
        cacheUtil.evictFromCache(AppConstant.USER, getUserToken());
        return Result.success(MessageConstant.LOGOUT_SUCCESS_MESSAGE);
    }

    @Override
    public Result queryLoginUser(Map<String, Object> parem) {
        PaginationUtil.getPagination(parem);
        return Result.success(userLoginMapper.queryLoginUser(parem), userLoginMapper.queryLoginUserCount(parem));
    }

    @Override
    @Transactional
    public Result saveOrUpdateLogin(Map<String, Object> parem) {
        Set<String> roleIds = new HashSet<>();
        String id = MapUtils.getString(parem, "id");
        String name = MapUtils.getString(parem, "name");
        String userName = MapUtils.getString(parem, "username");
        String status = MapUtils.getString(parem, "status");
        List<Map<String, Object>> roles = (List<Map<String, Object>>) MapUtils.getObject(parem, "roles");

        for (Map<String, Object> role : roles) {
            roleIds.add(MapUtils.getString(role, "id"));
        }

        String roleIdStr = roleIds.stream()
                .map(idValue -> "'" + idValue + "'")
                .collect(Collectors.joining(","));

        if (StringUtils.isNotEmpty(id)) {
            generalMapper.update(SQLConverterUtil.replaceAllPlaceHolder(
                    "update user_login set user_name = ?, name = ?, status = ? where id = ?",
                    new Object[]{userName, name, status, id}));

            generalMapper.delete(SQLConverterUtil.replaceAllPlaceHolder(
                    "delete from user_role where user_id = ?",
                    new Object[]{id}));

        } else {
            id = generalMapper.nextVal();

            generalMapper.insert(SQLConverterUtil.replaceAllPlaceHolder(
                    "insert into user_login (id, user_name, name, status) values (?,?,?,?)",
                    new Object[]{id, userName, name, status}));
        }

        if (StringUtils.isEmpty(roleIdStr))
            return Result.success();

        generalMapper.insert("insert into user_role (user_id, role_id) " +
                "select '" + id + "',r.id from roles r where r.id in (" + roleIdStr + ")");

        return Result.success();
    }
}
