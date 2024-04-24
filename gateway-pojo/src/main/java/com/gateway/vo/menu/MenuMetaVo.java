package com.gateway.vo.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuMetaVo {

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("children是否大于一")
    private boolean  alwaysShow;

    @ApiModelProperty("")
    private boolean affix;

    @ApiModelProperty("<keep-alive> 缓存(")
    private boolean noCache;

    @ApiModelProperty("是否展示")
    private boolean hidden;

    @ApiModelProperty("")
    private boolean canTo;

    @ApiModelProperty("显示高亮")
    private String activeMenu;

    @ApiModelProperty("")
    private boolean breadcrumb;

    @ApiModelProperty("")
    private boolean noTagsView;
}
