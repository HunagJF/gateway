package com.gateway.vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "菜单返回的数据格式")
public class MenusVO {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("组件名称")
    private String name;

    @ApiModelProperty("组件")
    private String component;

    @ApiModelProperty("重定向")
    private String redirect;

    @ApiModelProperty("上级ID")
    private String parentId;

    @ApiModelProperty("状态")
    private int status;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("是否一直显示")
    private boolean  alwaysShow;

    @ApiModelProperty("是否固定在标签页")
    private boolean affix;

    @ApiModelProperty("是否清除缓存")
    private boolean noCache;

    @ApiModelProperty("是否隐藏")
    private boolean hidden;

    @ApiModelProperty("是否可跳转")
    private boolean canTo;

    @ApiModelProperty("高亮菜单")
    private String activeMenu;

    @ApiModelProperty("是否显示面包屑")
    private boolean breadcrumb;

    @ApiModelProperty("是否隐藏标签页")
    private boolean noTagsView;

    @ApiModelProperty("排序")
    private int sort;
}
