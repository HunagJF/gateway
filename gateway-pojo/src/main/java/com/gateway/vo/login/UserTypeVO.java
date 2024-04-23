package com.gateway.vo.login;

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
@ApiModel(description = "用户登录返回的数据格式")
public class UserTypeVO{

    @ApiModelProperty("用户名")
    private String username;
}
