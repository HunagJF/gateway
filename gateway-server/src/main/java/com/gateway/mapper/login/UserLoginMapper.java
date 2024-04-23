package com.gateway.mapper.login;

import com.gateway.dto.login.UserTypeDTO;
import com.gateway.entity.login.UserLoginEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginMapper {

    UserLoginEntity query();

    UserLoginEntity queryName(UserTypeDTO userTypeDTO);
}
