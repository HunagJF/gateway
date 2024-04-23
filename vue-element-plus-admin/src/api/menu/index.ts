import request from '@/axios'

const Enum = {
    queryMenusByName : '/api/menu/queryMenusByName'
}

// 菜单查询
export const queryMenusByNameApi = (params: any) => {
    return request.post({ 
        url: Enum.queryMenusByName, 
        params: params
    })
}
