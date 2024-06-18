package com.gateway.controller.userManagement;


import com.gateway.annotation.Logger;
import com.gateway.enums.LogSeverity;
import com.gateway.mapper.dao.GeneralMapper;
import com.gateway.result.Result;
import com.gateway.service.userManagement.impl.UserManagementServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/userManagement")
public class UserManagementController {

    private final UserManagementServiceImpl userManagementService;

    private final GeneralMapper generalMapper;

    @Autowired
    public UserManagementController(UserManagementServiceImpl userManagementService, GeneralMapper generalMapper) {
        this.userManagementService = userManagementService;
        this.generalMapper = generalMapper;
    }

    @Logger(operSource = "用户管理",
            severity = LogSeverity.MINOR,
            operName = "列表查询",
            isPersistence = true)
    @PostMapping(value = "/queryUser")
    public Result queryUser(@RequestBody Map<String, Object> parem) {
        return userManagementService.queryUser(parem);
    }

    @Logger(operSource = "用户管理",
            severity = LogSeverity.MINOR,
            operName = "编辑",
            isPersistence = true)
    @PostMapping(value = "/update")
    public Result update(@RequestBody Map<String, Object> parem) {
        return userManagementService.update(parem);
    }
}
