package com.gateway.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户")
public class UserTypeVO{

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("角色")
    private String rolesStr;

    @ApiModelProperty("角色分配")
    private List roles;

    @ApiModelProperty("创建时间")
    private String createTime;

}
