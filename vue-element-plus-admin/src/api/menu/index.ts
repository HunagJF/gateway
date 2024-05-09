import request from '@/axios'

const Enum = {
    queryMenusByName : '/api/menu/queryMenusByName',
    update : '/api/menu/update',
    insert : '/api/menu/insert',
}

// 菜单查询
export const queryMenusByNameApi = (params: any) => {
    return request.post({ 
        url: Enum.queryMenusByName, 
        params: params
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

// interface EditType {
//     id: string
//     parentId: string
//     path: string
//     title: string
//     component: string
//     name: string
//     sort: number
//     status: number
//     type: number
//     noTagsView: boolean
//     noCache: boolean
//     hidden: boolean
//     canTo: boolean
//     breadcrumb: boolean
//     alwaysShow: boolean
//     affix: boolean
// }
