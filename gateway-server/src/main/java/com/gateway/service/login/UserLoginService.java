package com.gateway.service.login;

import com.gateway.dto.login.UserTypeDTO;
import com.gateway.entity.login.UserLoginEntity;
import com.gateway.result.Result;

public interface UserLoginService {

    UserLoginEntity query();
    Result queryName(UserTypeDTO userTypeDTO);
    public Result loginOut();
}
