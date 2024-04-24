package com.gateway.vo.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户菜单返回的数据格式")
public class UserMenusVO {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("组件")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String component;

    @ApiModelProperty("重定向")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String redirect;

    @ApiModelProperty("组件名称")
    private String name;

    @ApiModelProperty("状态")
    private int status;

    @ApiModelProperty("属性")
    private MenuMetaVo meta;

    @ApiModelProperty("子菜单")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<UserMenusVO> children;
}
