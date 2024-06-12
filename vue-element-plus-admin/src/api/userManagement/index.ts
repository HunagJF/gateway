import request from '@/axios'

const Enum = {
    queryUser: '/api/userManagement/queryUser',
}

export const queryUserApi = (data: any) => {
    return request.post({
        url: Enum.queryUser,
        data
    })
}