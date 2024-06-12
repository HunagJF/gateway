// 查询下拉框
import request from '@/axios'

const Enum = {
    queryOrganizations: '/api/conditionPullBoxController/queryOrganizations',
    queryRoles: '/api/conditionPullBoxController/queryRoles'
}

export const queryOrganizationsApi = () => {
    return request.post({
        url: Enum.queryOrganizations,
    })
}

export const queryRolesApi = () => {
    return request.post({
        url: Enum.queryRoles,
    })
}