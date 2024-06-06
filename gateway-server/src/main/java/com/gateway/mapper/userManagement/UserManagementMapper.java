package com.gateway.mapper.userManagement;

import com.gateway.vo.userManagement.UserManagementVO;

import java.util.List;
import java.util.Map;

public interface UserManagementMapper {

    List<UserManagementVO> queryUser(Map<String, Object> parem);
}
