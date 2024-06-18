package com.gateway.service.userManagement;

import com.gateway.result.Result;

import java.util.Map;

public interface UserManagementService {

    Result queryUser(Map<String, Object> parem);

    Result update(Map<String, Object> parem);
}
