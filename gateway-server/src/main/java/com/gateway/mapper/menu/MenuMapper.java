package com.gateway.mapper.menu;

import com.gateway.dto.menu.MenuDTO;
import com.gateway.entity.menu.MenuEntity;
import com.gateway.vo.menu.MenusVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    List<MenusVO> queryMenusByName();

    int queryCount(String title);

    List<MenuEntity> queryTitle();

    List<MenuEntity> queryMenusByUsername(String userName);

    List<MenuEntity> queryMenus();

    int update(MenuDTO menuDTO);

    int insert(MenuDTO menuDTO);
}
