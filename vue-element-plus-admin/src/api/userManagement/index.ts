import request from '@/axios'

const Enum = {
    queryUser: '/api/userManagement/queryUser',
    update: '/api/userManagement/update'
}

export const queryUserApi = (data: any) => {
    return request.post({
        url: Enum.queryUser,
        data
    })
}

export const updateApi = (data: any) => {
    return request.post({
        url: Enum.update,
        data
    })
}