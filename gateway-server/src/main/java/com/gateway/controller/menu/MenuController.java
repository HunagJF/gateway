package com.gateway.controller.menu;

import com.gateway.annotation.Logger;
import com.gateway.dto.menu.MenuDTO;
import com.gateway.enums.LogSeverity;
import com.gateway.result.Result;
import com.gateway.service.menu.MenuService;
import com.gateway.utils.CommonsUtil;
import com.gateway.vo.login.UserTypeVO;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public Result queryMenusByName(@RequestBody Map<String,Object> param) {
        return menuService.queryMenusByName(param);
    }

    @Logger(operSource = "菜单管理",
            severity = LogSeverity.MINOR,
            operName = "查询Title",
            isPersistence = true)
    @PostMapping(value = "/queryTitle")
    public Result queryTitle() {
        return menuService.queryTitle();
    }

    @Logger(operSource = "用户登录获取菜单",
            severity = LogSeverity.MINOR,
            operName = "查询",
            isPersistence = true)
    @PostMapping(value = "/queryMenusByUsername")
    public Result queryMenusByUsername() {
        return menuService.queryMenusByUsername();
    }

    @Logger(operSource = "获取所有菜单",
            severity = LogSeverity.MINOR,
            operName = "查询",
            isPersistence = true)
    @PostMapping(value = "/queryMenus")
    public Result queryMenus() {
        return menuService.queryMenus();
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

    @Logger(operSource = "菜单管理",
            severity = LogSeverity.MINOR,
            operName = "删除",
            isPersistence = true)
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody MenuDTO menuDTO) {
        return menuService.delete(menuDTO.getId());
    }
}
