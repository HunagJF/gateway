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
@ApiModel(description = "菜单名称")
public class MenusTitleVO {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("子菜单")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenusTitleVO> children;
}
