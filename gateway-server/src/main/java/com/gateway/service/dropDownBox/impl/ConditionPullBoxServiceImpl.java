package com.gateway.service.dropDownBox.impl;

import com.gateway.mapper.dao.GeneralMapper;
import com.gateway.result.Result;
import com.gateway.service.dropDownBox.ConditionPullBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConditionPullBoxServiceImpl implements ConditionPullBoxService {

    private final GeneralMapper generalMapper;

    @Autowired
    public ConditionPullBoxServiceImpl(GeneralMapper generalMapper) {
        this.generalMapper = generalMapper;
    }

    @Override
    public Result queryAppOrganizations() {
        return Result.success(generalMapper.querys("select id, name from app_organizations o"));
    }

    @Override
    public Result queryAppRoles() {
        return Result.success(generalMapper.querys("select id, name from app_roles"));
    }


}
