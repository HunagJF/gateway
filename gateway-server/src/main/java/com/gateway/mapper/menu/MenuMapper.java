package com.gateway.mapper.menu;


import com.gateway.dto.menu.MenuDTO;
import com.gateway.entity.menu.MenuEntity;
import com.gateway.result.Result;
import com.gateway.vo.menu.MenusVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MenuMapper {

    List<MenusVO> queryMenusByName(
            @Param("name") String name,
            @Param("page") int page,
            @Param("size") int size);

    int queryCount(String name);

    List<MenuEntity> queryMenusByUsername(String userName);

    int update(MenuDTO menuDTO);

    int insert(MenuDTO menuDTO);


}
