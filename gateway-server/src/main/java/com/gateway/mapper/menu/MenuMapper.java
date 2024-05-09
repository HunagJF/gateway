package com.gateway.mapper.menu;


import com.gateway.dto.menu.MenuDTO;
import com.gateway.entity.menu.MenuEntity;
import com.gateway.result.Result;
import com.gateway.vo.menu.MenusVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface MenuMapper {

    List<MenusVO> queryMenusByName(
            @Param("title") String title,
            @Param("page") int page,
            @Param("size") int size);

    int queryCount(String title);

    List<MenuEntity> queryTitle();

    List<MenuEntity> queryMenusByUsername(String userName);

    int update(MenuDTO menuDTO);

    int insert(MenuDTO menuDTO);


}
