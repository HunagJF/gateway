package com.gateway.mapper.dropDownBox;

import com.gateway.vo.dropDownBox.ConditionPullBoxVO;

import java.util.List;

public interface ConditionPullBoxMapper {

    List<ConditionPullBoxVO> queryAppRegionTree();

    List<ConditionPullBoxVO> queryAppRolesTree();
}
