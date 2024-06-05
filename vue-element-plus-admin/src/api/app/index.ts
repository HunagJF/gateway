import request from '@/axios'

const Enum = {
    queryHomePageApp: '/api/app/queryHomePageApp'
}

export const queryHomePageAppApi = () => {
    return request.post({
        url: Enum.queryHomePageApp,
    })
}