import request from '@/axios'

export const getMenuListApi = () => {
  // return request.get({ url: '/mock/menu/list' })
  return request.post({ url: '/api/route/findRoutes'})
}
