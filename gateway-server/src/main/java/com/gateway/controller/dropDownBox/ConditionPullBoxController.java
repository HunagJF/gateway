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
    @PostMapping(value = "/queryOrganizations")
    public Result queryAppOrganizations() {
        return conditionPullBoxService.queryAppOrganizations();
    }

    @Logger(operSource = "下拉框",
            severity = LogSeverity.MINOR,
            operName = "角色下拉框",
            isPersistence = true)
    @PostMapping(value = "/queryRoles")
    public Result queryAppRoles() {
        return conditionPullBoxService.queryAppRoles();
    }
}
