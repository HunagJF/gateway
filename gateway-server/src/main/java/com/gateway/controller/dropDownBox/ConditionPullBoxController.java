package com.gateway.controller.dropDownBox;

import com.gateway.annotation.Logger;
import com.gateway.enums.LogSeverity;
import com.gateway.result.Result;
import com.gateway.service.dropDownBox.impl.ConditionPullBoxServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询添加下拉框值查询
 */
@Slf4j
@RestController
@RequestMapping(value = "/conditionPullBoxController")
public class ConditionPullBoxController {

    private final ConditionPullBoxServiceImpl conditionPullBoxService;

    @Autowired
    public ConditionPullBoxController(ConditionPullBoxServiceImpl conditionPullBoxService) {
        this.conditionPullBoxService = conditionPullBoxService;
    }

    @Logger(operSource = "下拉框",
            severity = LogSeverity.MINOR,
            operName = "组织机构下拉框",
            isPersistence = true)
    @PostMapping(value = "/queryAppOrganizations")
    public Result queryAppOrganizations() {
        return conditionPullBoxService.queryAppOrganizations();
    }

    @Logger(operSource = "下拉框",
            severity = LogSeverity.MINOR,
            operName = "角色下拉框",
            isPersistence = true)
    @PostMapping(value = "/queryAppRoles")
    public Result queryAppRoles() {
        return conditionPullBoxService.queryAppRoles();
    }

    @Logger(operSource = "下拉框",
            severity = LogSeverity.MINOR,
            operName = "数据权限范围下拉框",
            isPersistence = true)
    @PostMapping(value = "queryAppRegion")
    public Result queryAppRegion() {
        return conditionPullBoxService.queryAppRegion();
    }

    @Logger(operSource = "下拉框",
            severity = LogSeverity.MINOR,
            operName = "账号类型下拉框",
            isPersistence = true)
    @PostMapping(value = "queryAccountType")
    public Result queryAccountType() {
        return conditionPullBoxService.queryAccountType();
    }

    @Logger(operSource = "下拉框",
            severity = LogSeverity.MINOR,
            operName = "权限类型下拉框",
            isPersistence = true)
    @PostMapping(value = "queryPermissionType")
    public Result queryPermissionType() {
        return conditionPullBoxService.queryPermissionType();
    }

    @Logger(operSource = "下拉框",
            severity = LogSeverity.MINOR,
            operName = "数据权限范围下拉框树形图",
            isPersistence = true)
    @PostMapping(value = "queryAppRegionTree")
    public Result queryAppRegionTree() {
        return conditionPullBoxService.queryAppRegionTree();
    }

    @Logger(operSource = "下拉框",
            severity = LogSeverity.MINOR,
            operName = "角色下拉框树形图",
            isPersistence = true)
    @PostMapping(value = "queryAppRolesTree")
    public Result queryAppRolesTree() {
        return conditionPullBoxService.queryAppRolesTree();
    }
}
