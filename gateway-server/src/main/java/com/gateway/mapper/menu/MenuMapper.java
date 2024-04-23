package com.gateway.mapper.menu;


import com.gateway.entity.menu.MenuEntity;

import java.util.List;

public interface MenuMapper {

    List<MenuEntity> queryMenusByName(String name);

    List<MenuEntity> queryMenusByUsername(String userName);
}
