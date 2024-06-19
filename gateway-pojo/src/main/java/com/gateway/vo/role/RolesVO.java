package com.gateway.vo.role;

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
@ApiModel(description = "角色返回的数据格式")
public class RolesVO {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("菜单分配")
    private List menu;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("菜单分配")
    private String menusStr;

    @ApiModelProperty("创建时间")
    private String createTime;
}
