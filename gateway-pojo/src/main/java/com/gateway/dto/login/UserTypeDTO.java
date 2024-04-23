package com.gateway.dto.login;

import lombok.Data;

/**
 * 用户请求登录
 */
@Data
public class UserTypeDTO {

    private String username;
    private String password;
    private String role;
    private String roleId;
}
