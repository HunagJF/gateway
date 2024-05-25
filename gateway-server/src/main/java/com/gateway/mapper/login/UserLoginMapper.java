package com.gateway.mapper.login;

import com.gateway.dto.login.UserTypeDTO;
import com.gateway.entity.login.UserLoginEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserLoginMapper {

    UserLoginEntity query();

    UserLoginEntity queryName(UserTypeDTO userTypeDTO);

    List queryLoginUser(Map<String, Object> parem);

    int queryLoginUserCount(Map<String, Object> parem);
}
