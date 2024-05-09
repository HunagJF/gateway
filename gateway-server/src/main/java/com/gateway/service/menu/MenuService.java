package com.gateway.service.menu;

import com.gateway.dto.menu.MenuDTO;
import com.gateway.result.Result;

import java.util.Map;

public interface MenuService {
    Result queryMenusByName(String Name, int page, int size);

    Result queryMenusByUsername();

    Result update(MenuDTO menuDTO);

    Result insert(MenuDTO menuDTO);
}
