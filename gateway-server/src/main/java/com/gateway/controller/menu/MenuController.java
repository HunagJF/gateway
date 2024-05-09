package com.gateway.controller.menu;

import com.gateway.annotation.Logger;
import com.gateway.dto.menu.MenuDTO;
import com.gateway.enums.LogSeverity;
import com.gateway.result.Result;
import com.gateway.service.menu.MenuService;
import com.gateway.utils.CommonsUtil;
import com.gateway.vo.login.UserTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result queryMenusByName(
            @RequestParam(value = "title", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return Result.success(menuService.queryMenusByName(name, page - 1, size));
    }

    @Logger(operSource = "用户登录获取菜单",
            severity = LogSeverity.MINOR,
            operName = "查询",
            isPersistence = true)
    @PostMapping(value = "/queryMenusByUsername")
    public Result queryMenusByUsername() {
        return menuService.queryMenusByUsername();
    }

    @Logger(operSource = "菜单管理",
            severity = LogSeverity.MINOR,
            operName = "更新",
            isPersistence = true)
    @PostMapping(value = "/update")
    public Result update(@RequestBody MenuDTO menuDTO) {
        return menuService.update(menuDTO);
    }

    @Logger(operSource = "菜单管理",
            severity = LogSeverity.MINOR,
            operName = "添加",
            isPersistence = true)
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody MenuDTO menuDTO) {
        return menuService.insert(menuDTO);
    }
}
