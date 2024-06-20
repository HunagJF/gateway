package com.gateway.service.login;

import com.gateway.dto.login.UserTypeDTO;
import com.gateway.entity.login.UserLoginEntity;
import com.gateway.result.Result;

import java.util.List;
import java.util.Map;

public interface UserLoginService {

    UserLoginEntity query();

    Result queryName(UserTypeDTO userTypeDTO);

    Result loginOut();

    Result queryLoginUser(Map<String, Object> parem);

    Result saveOrUpdateLogin(Map<String, Object> parem);

    Result deleteByIds(List<Map<String, Object>> parems);
}
