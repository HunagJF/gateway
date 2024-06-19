package com.gateway.service.role;

import com.gateway.result.Result;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Result queryRolesByName(Map<String,Object> param);

    Result saveOrUpdateRole(Map<String,Object> param);

    Result deleteById(List<Map<String, Object>> params);

    Result queryNames();
}
