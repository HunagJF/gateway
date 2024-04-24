package com.gateway.entity.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity {

    private String id;

    //路径
    private String path;

    //组件名称
    private String name;

    //组件
    private String component;

    //重定向
    private String redirect;

    //上级ID
    private String parentId;

    //状态 (1:启用,0:禁止)
    private int status;

    //菜单名称
    private String title;

    //图标
    private String icon;

    //是否一直显示(默认 false)
    private boolean  alwaysShow;

    //是否固定在标签页(默认 false)
    private boolean affix;

    //是否清除缓存(默认 false)
    private boolean noCache;

    //是否隐藏(默认 false)
    private boolean hidden;

    //是否可跳转(默认 false)
    private boolean canTo;

    //高亮菜单
    private String activeMenu;

    //是否显示面包屑(默认 true)
    private boolean breadcrumb;

    //是否隐藏标签页(默认 false)
    private boolean noTagsView;

    //排序
    private int sort;
}
