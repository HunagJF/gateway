package com.gateway.mapper.menu;


import com.gateway.dto.menu.MenuDTO;
import com.gateway.entity.menu.MenuEntity;
import com.gateway.vo.menu.MenusVO;

import java.util.List;

public interface MenuMapper {

    List<MenusVO> queryMenusByName(String name);

    List<MenuEntity> queryMenusByUsername(String userName);

    int update(MenuDTO menuDTO);
}
