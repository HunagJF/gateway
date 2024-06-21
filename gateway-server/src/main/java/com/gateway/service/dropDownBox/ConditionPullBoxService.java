package com.gateway.service.dropDownBox;

import com.gateway.result.Result;

public interface ConditionPullBoxService {

    Result queryAppOrganizations();

    Result queryAppRoles();

    Result queryAppRegion();

    Result queryAccountType();

    Result queryPermissionType();

    Result queryAppRegionTree();

    Result queryAppRolesTree();

    Result queryApps();
}
