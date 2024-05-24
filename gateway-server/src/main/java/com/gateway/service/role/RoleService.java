package com.gateway.service.role;

import com.gateway.result.Result;

import java.util.Map;

public interface RoleService {

    Result queryRolesByName(Map<String,Object> param);

    Result saveOrUpdateRole(Map<String,Object> param);

    Result deleteById(Map<String,Object> param);
}
