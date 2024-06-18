package com.gateway.vo.dropDownBox;

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
@ApiModel(description = "树形图")
public class ConditionPullBoxVO {

    @ApiModelProperty("ID")
    private String value;

    @ApiModelProperty("名称")
    private String label;

    @ApiModelProperty("下级")
    private List children;
}
