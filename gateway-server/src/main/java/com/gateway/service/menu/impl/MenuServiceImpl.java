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
    public Result queryMenus() {
        return Result.success(menuMapper.queryMenus());
    }


    @Override
    public Result update(MenuDTO menuDTO) {
        generalService.update(SQLConverterUtil.replaceAllPlaceHolder(
                "UPDATE MENUS SET TYPE = ?, parent_id = ?, title = ?, component = ?, name = ?, path = ?, status = ?, " +
                        "sort = ?, hidden = ?, always_show = ?, no_cache = ?, breadcrumb = ?, affix = ?, no_tags_view = ?, " +
                        "can_to = ?, update_time = now(), icon = ? WHERE ID = ?",
                new Object[]{
                    menuDTO.getType(), menuDTO.getParentId(), menuDTO.getTitle(), menuDTO.getComponent(),menuDTO.getName(),
                        menuDTO.getPath(), menuDTO.getStatus(), menuDTO.getSort(), menuDTO.isHidden(), menuDTO.isAlwaysShow(),
                        menuDTO.isNoCache(), menuDTO.isBreadcrumb(), menuDTO.isAffix(), menuDTO.isNoTagsView(), menuDTO.isCanTo(),
                        menuDTO.getIcon(), menuDTO.getId()
                }
        ));
        return Result.success();
    }

    @Override
    public Result insert(MenuDTO menuDTO) {
        generalService.insert(SQLConverterUtil.replaceAllPlaceHolder(
                "insert into menus (path,name,component,redirect,parent_id,status,title,icon,always_show,affix," +
                        "no_cache,hidden,can_to,breadcrumb,no_tags_view,sort,type) values " +
                        "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                new Object[]{
                    menuDTO.getPath(), menuDTO.getName(), menuDTO.getComponent(), menuDTO.getRedirect(), menuDTO.getParentId(),
                        menuDTO.getStatus(), menuDTO.getTitle(), menuDTO.getIcon(), menuDTO.isAlwaysShow(), menuDTO.isAffix(),
                        menuDTO.isNoCache(), menuDTO.isHidden(), menuDTO.isCanTo(), menuDTO.isBreadcrumb(), menuDTO.isNoTagsView(),
                        menuDTO.getSort(), menuDTO.getType()
                }
        ));
        return Result.success();
    }

    @Override
    public Result delete(String id) {
        generalService.delete(SQLConverterUtil.replaceAllPlaceHolder(
                "DELETE FROM MENUS WHERE ID = ?",
                new Object[]{
                        id
                }
        ));
        return Result.success();
    }

    private int queryCount(String name) {
        return menuMapper.queryCount(name);
    }
}
