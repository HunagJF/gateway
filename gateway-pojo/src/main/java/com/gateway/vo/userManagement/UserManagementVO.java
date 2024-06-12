package com.gateway.vo.userManagement;

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
@ApiModel(description = "用户管理返回的数据格式")
public class UserManagementVO {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("登录账号")
    private String userName;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("组织机构")
    private String appOrganizationName;

    @ApiModelProperty("角色")
    private String appRolesName;

    @ApiModelProperty("模块")
    private String appsName;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("账号权限")
    private String accountTypeName;

    @ApiModelProperty("权限类型")
    private String permissionTypeName;

    @ApiModelProperty("状态")
    private Integer status;
}
