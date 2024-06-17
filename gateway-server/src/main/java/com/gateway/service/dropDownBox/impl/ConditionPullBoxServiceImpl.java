package com.gateway.service.dropDownBox.impl;

import com.gateway.mapper.dao.GeneralMapper;
import com.gateway.result.Result;
import com.gateway.service.dropDownBox.ConditionPullBoxService;
import com.gateway.service.util.impl.DictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConditionPullBoxServiceImpl implements ConditionPullBoxService {

    private final GeneralMapper generalMapper;

    private final DictionaryServiceImpl dictionaryService;

    @Autowired
    public ConditionPullBoxServiceImpl(GeneralMapper generalMapper, DictionaryServiceImpl dictionaryService) {
        this.generalMapper = generalMapper;
        this.dictionaryService = dictionaryService;
    }

    @Override
    public Result queryAppOrganizations() {
        return Result.success(generalMapper.querys("select id, name from app_organizations order by level"));
    }

    @Override
    public Result queryAppRoles() {
        return Result.success(generalMapper.querys("select id, name from app_roles"));
    }

    @Override
    public Result queryAppRegion() {
        return Result.success(generalMapper.querys("select id, name from app_region order by code::numeric"));
    }

    @Override
    public Result queryAccountType() {
        return Result.success(dictionaryService.queryByCodeOnNames("USER_LOGIN", "ACCOUNT_TYPE_ID"));
    }

    @Override
    public Result queryPermissionType() {
        return Result.success(dictionaryService.queryByCodeOnNames("USER_LOGIN", "PERMISSION_TYPE_ID"));
    }
}
