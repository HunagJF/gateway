package com.gateway.service.menu;

import com.gateway.dto.menu.MenuDTO;
import com.gateway.result.Result;

import java.util.Map;

public interface MenuService {
    Result queryMenusByName();

    Result queryTitle();

    Result queryMenusByUsername();

    Result queryMenus();

    Result update(MenuDTO menuDTO);

    Result insert(MenuDTO menuDTO);

    Result delete(String id);
}
