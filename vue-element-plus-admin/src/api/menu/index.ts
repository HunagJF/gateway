import request from '@/axios'

const Enum = {
    queryMenusByName : '/api/menu/queryMenusByName',
    update : '/api/menu/update',
    insert : '/api/menu/insert',
    queryTitle: '/api/menu/queryTitle',
    delete: '/api/menu/delete',
}

// 菜单查询
export const queryMenusByNameApi = (data: any) => {
    return request.post({ 
        url: Enum.queryMenusByName, 
        data
    })
}

export const updateApi = (data: any) => {
    return request.post({ 
        url: Enum.update, 
        data
    })
}

export const insertApi = (data: any) => {
    return request.post({ 
        url: Enum.insert, 
        data
    })
}

export const queryTitleApi = () => {
    return request.post({ 
        url: Enum.queryTitle
    })
}

export const deleteApi = (data: any) => {
    return request.post({ 
        url: Enum.delete, 
        data
    })
}