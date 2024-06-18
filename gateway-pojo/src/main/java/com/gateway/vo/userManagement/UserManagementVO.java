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
    private String appOrganizationId;

    @ApiModelProperty("角色")
    private String appRolesName;
    private String appRolesIds;

    @ApiModelProperty("模块")
    private String appsName;
    private String appsIds;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("账号权限")
    private String accountTypeName;
    private String accountTypeId;

    @ApiModelProperty("权限类型")
    private String permissionTypeName;
    private String permissionTypeId;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("数据权限范围")
    private String appRegionName;
    private String appRegionIds;
}
