package com.gateway.utils;

import com.gateway.dto.login.UserTypeDTO;
import com.gateway.entity.login.UserLoginEntity;
import com.gateway.result.Result;

/**
 * 登录校验
 */
public class LoginUtil {

    public static Result validateUserLogin(UserLoginEntity userLogin, UserTypeDTO userTypeDTO) {
        if (userLogin == null || userLogin.getUsername() == null) {
            return Result.error(userTypeDTO.getUsername() + ":用户不存在");
        }

        if (!SimplePasswordUtil.checkPassword(userTypeDTO.getPassword(), userLogin.getPassword())) {
            return Result.error("账号或密码错误");
        }
        return null;
    }
}
