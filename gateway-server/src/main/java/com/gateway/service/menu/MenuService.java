package com.gateway.service.menu;

import com.gateway.dto.menu.MenuDTO;
import com.gateway.result.Result;

import java.util.Map;

public interface MenuService {
    Map<String, Object> queryMenusByName(String Name);

    Result queryMenusByUsername();

    Result update(MenuDTO menuDTO);
}
