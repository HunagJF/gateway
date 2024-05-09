package com.gateway.service.menu.impl;

import com.gateway.dto.menu.MenuDTO;
import com.gateway.mapper.menu.MenuMapper;
import com.gateway.result.Result;
import com.gateway.service.dao.GeneralService;
import com.gateway.service.menu.MenuService;
import com.gateway.utils.CommonsUtil;
import com.gateway.utils.SQLConverterUtil;
import com.gateway.vo.login.UserTypeVO;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;
    private final GeneralService generalService;

    @Autowired(required = false)
    public MenuServiceImpl(MenuMapper menuMapper, GeneralService generalService) {
        this.menuMapper = menuMapper;
        this.generalService = generalService;
    }

    @Override
    public Result queryMenusByName(Map<String,Object> param) {
        String title = MapUtils.getString(param, "title");
        Integer page = MapUtils.getInteger(param, "page", 1) - 1;
        Integer size = MapUtils.getInteger(param, "size", 10);
        return Result.success(menuMapper.queryMenusByName(title, page, size), queryCount(title));
    }

    @Override
    public Result queryTitle() {
        return Result.success(menuMapper.queryTitle());
    }

    @Override
    public Result queryMenusByUsername() {
        UserTypeVO user = CommonsUtil.getUser(UserTypeVO.class);
        return Result.success(menuMapper.queryMenusByUsername(user.getUsername()));
    }



    @Override
    public Result update(MenuDTO menuDTO) {
        menuMapper.update(menuDTO);
        return Result.success();
    }

    @Override
    public Result insert(MenuDTO menuDTO) {
        menuMapper.insert(menuDTO);
        return Result.success();
    }

    @Override
    public Result delete(String id) {
        generalService.delete(SQLConverterUtil.appendParams(
                "delete from menus where id = ?",
                new Object[]{id}
        ));
        return Result.success();
    }

    private int queryCount(String name) {
        return menuMapper.queryCount(name);
    }

}
