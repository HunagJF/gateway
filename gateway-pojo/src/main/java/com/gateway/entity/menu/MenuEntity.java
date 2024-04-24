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

    //设置该路由在侧边栏和面包屑中展示的名字
    private String title;

    //设置该路由的图标
    private String icon;

    //当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式，
    //只有一个时，会将那个子路由当做根路由显示在侧边栏，
    //若你想不管路由下面的 children 声明的个数都显示你的根路由，
    //你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，
    //一直显示根路由(默认 false)
    private boolean  alwaysShow;

    //如果设置为true，则会一直固定在tag项中(默认 false)
    private boolean affix;

    //如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    private boolean noCache;

    //当设置 true 的时候该路由不会再侧边栏出现 (默认 false)
    private boolean hidden;

    //设置为true即使hidden为true，也依然可以进行路由跳转(默认 false)
    private boolean canTo;

    //显示高亮的路由路径
    private String activeMenu;

    //如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)
    private boolean breadcrumb;

    //如果设置为true，则不会出现在tag中(默认 false)
    private boolean noTagsView;

    //排序
    private int sort;
}
