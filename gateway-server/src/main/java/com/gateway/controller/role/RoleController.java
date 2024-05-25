package com.gateway.controller.role;

import com.gateway.annotation.Logger;
import com.gateway.enums.LogSeverity;
import com.gateway.result.Result;
import com.gateway.service.role.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private final RoleServiceImpl roleService;

    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Logger(operSource = "角色管理",
            severity = LogSeverity.MINOR,
            operName = "查询",
            isPersistence = true)
    @PostMapping(value = "/queryRolesByName")
    public Result queryRolesByName(@RequestBody Map<String, Object> parem) {
     return roleService.queryRolesByName(parem);
    }

    @Logger(operSource = "角色管理",
            severity = LogSeverity.MINOR,
            operName = "编辑或添加",
            isPersistence = true)
    @PostMapping(value = "/saveOrUpdateRole")
    public Result saveOrUpdateRole(@RequestBody Map<String, Object> parem) {
        return roleService.saveOrUpdateRole(parem);
    }

    @Logger(operSource = "角色管理",
            severity = LogSeverity.MINOR,
            operName = "删除",
            isPersistence = true)
    @PostMapping(value = "/deleteById")
    public Result deleteById(@RequestBody Map<String, Object> parem){
        return roleService.deleteById(parem);
    }

    @Logger(operSource = "角色管理",
            severity = LogSeverity.MINOR,
            operName = "查询所有角色",
            isPersistence = true)
    @PostMapping(value = "/queryNames")
    public Result queryNames() {
        return roleService.queryNames();
    }
}
