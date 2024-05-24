import request from '@/axios'

const Enum = {
    queryRolesByName : '/api/role/queryRolesByName',
    saveOrUpdateRole: '/api/role/saveOrUpdateRole',
    deleteById: '/api/role/deleteById',
}

// 菜单查询
export const queryRolesByNameApi = (data: any) => {
    return request.post({ 
        url: Enum.queryRolesByName, 
        data
    })
}

// 菜单编辑
export const saveOrUpdateRoleApi = (data: any) => {
    return request.post({ 
        url: Enum.saveOrUpdateRole, 
        data
    })
}

export const deleteByIdApi = (data: any) => {
    return request.post({ 
        url: Enum.deleteById, 
        data
    })
}