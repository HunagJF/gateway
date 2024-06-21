package com.gateway.service.dropDownBox.impl;

import com.gateway.mapper.dao.GeneralMapper;
import com.gateway.mapper.dropDownBox.ConditionPullBoxMapper;
import com.gateway.result.Result;
import com.gateway.service.dropDownBox.ConditionPullBoxService;
import com.gateway.service.util.impl.DictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConditionPullBoxServiceImpl implements ConditionPullBoxService {

    private final GeneralMapper generalMapper;

    private final DictionaryServiceImpl dictionaryService;

    private final ConditionPullBoxMapper conditionPullBoxMapper;

    @Autowired
    public ConditionPullBoxServiceImpl(GeneralMapper generalMapper, DictionaryServiceImpl dictionaryService, ConditionPullBoxMapper conditionPullBoxMapper) {
        this.generalMapper = generalMapper;
        this.dictionaryService = dictionaryService;
        this.conditionPullBoxMapper = conditionPullBoxMapper;
    }

    @Override
    public Result queryAppOrganizations() {
        return Result.success(generalMapper.querys("select id::varchar, name from app_organizations order by level"));
    }

    @Override
    public Result queryAppRoles() {
        return Result.success(generalMapper.querys("select id::varchar, name from app_roles"));
    }

    @Override
    public Result queryAppRegion() {
        return Result.success(generalMapper.querys("select id::varchar, name from app_region order by code::numeric"));
    }

    @Override
    public Result queryAccountType() {
        return Result.success(dictionaryService.queryByCodeOnNames("USER_LOGIN", "ACCOUNT_TYPE_ID"));
    }

    @Override
    public Result queryPermissionType() {
        return Result.success(dictionaryService.queryByCodeOnNames("USER_LOGIN", "PERMISSION_TYPE_ID"));
    }

    @Override
    public Result queryAppRegionTree() {
        return Result.success(conditionPullBoxMapper.queryAppRegionTree());
    }

    @Override
    public Result queryAppRolesTree() {
        return Result.success(conditionPullBoxMapper.queryAppRolesTree());
    }

    @Override
    public Result queryApps() {
        return Result.success(generalMapper.querys("select id::varchar, name from apps"));
    }
}
