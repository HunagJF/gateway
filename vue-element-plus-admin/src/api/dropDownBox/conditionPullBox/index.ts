// 查询下拉框
import request from '@/axios'

const Enum = {
    queryAppOrganizations: '/api/conditionPullBoxController/queryAppOrganizations',
    queryAppRoles: '/api/conditionPullBoxController/queryAppRoles',
    queryAppRegion: '/api/conditionPullBoxController/queryAppRegion',
    queryAccountType: '/api/conditionPullBoxController/queryAccountType',
    queryPermissionType: '/api/conditionPullBoxController/queryPermissionType',
    queryAppRegionTree: '/api/conditionPullBoxController/queryAppRegionTree',
    queryAppRolesTree: '/api/conditionPullBoxController/queryAppRolesTree',
}

export const queryAppOrganizationsApi = () => {
    return request.post({
        url: Enum.queryAppOrganizations,
    })
}

export const queryAppRolesApi = () => {
    return request.post({
        url: Enum.queryAppRoles,
    })
}

export const queryAppRegionApi = () => {
    return request.post({
        url: Enum.queryAppRegion,
    })
}

export const queryAccountTypeApi = () => {
    return request.post({
        url: Enum.queryAccountType,
    })
}

export const queryPermissionTypeApi = () => {
    return request.post({
        url: Enum.queryPermissionType,
    })
}

export const queryAppRegionTreeApi = () => {
    return request.post({
        url: Enum.queryAppRegionTree,
    })
}

export const queryAppRolesTreeApi = () => {
    return request.post({
        url: Enum.queryAppRolesTree,
    })
}