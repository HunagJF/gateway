package com.gateway.service.login.impl;

import com.gateway.constant.JwtClaimsConstant;
import com.gateway.dto.login.UserTypeDTO;
import com.gateway.entity.login.UserLoginEntity;
import com.gateway.mapper.login.UserLoginMapper;
import com.gateway.result.Result;
import com.gateway.service.login.UserLoginService;
import com.gateway.utils.CacheUtil;
import com.gateway.utils.CommonsUtil;
import com.gateway.utils.LoginUtil;
import com.gateway.utils.SimplePasswordUtil;
import com.gateway.vo.login.UserTypeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.gateway.utils.CommonsUtil.getUserToken;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    private final UserLoginMapper userLoginMapper;
    private final CacheUtil cacheUtil;

    @Autowired
    public UserLoginServiceImpl(UserLoginMapper userLoginMapper, CacheUtil cacheUtil) {
        this.userLoginMapper = userLoginMapper;
        this.cacheUtil = cacheUtil;
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

        return Result.success(userTypeVO);
    }

    @Override
    public Result loginOut() {
        cacheUtil.evictFromCache(JwtClaimsConstant.USER, getUserToken());
        return Result.success();
    }
}
