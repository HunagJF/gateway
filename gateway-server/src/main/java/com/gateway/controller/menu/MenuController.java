package com.gateway.controller.menu;

import com.gateway.annotation.Logger;
import com.gateway.enums.LogSeverity;
import com.gateway.result.Result;
import com.gateway.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(@Autowired MenuService menuService) {
        this.menuService = menuService;
    }

    @Logger(operSource = "菜单管理",
            severity = LogSeverity.MINOR,
            operName = "查询",
            isPersistence = true)
    @PostMapping(value = "/queryMenusByName")
    public Result queryMenusByName(@RequestParam(value = "title", required = false) String name) {
        return Result.success(menuService.queryMenusByName(name));
    }

    @Logger(operSource = "用户登录获取菜单",
            severity = LogSeverity.MINOR,
            operName = "查询",
            isPersistence = true)
    @PostMapping(value = "/queryMenusByUsername")
    public Result queryMenusByUsername(@RequestParam("userName") String userName) {
        return menuService.queryMenusByUsername(userName);
    }
}
