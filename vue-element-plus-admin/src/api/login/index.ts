import request from '@/axios'
import type { UserType } from './types'

const Enum = {
  login: '/api/login',
  loginOut: '/api/loginOut',
  queryMenusByUsername: '/api/menu/queryMenusByUsername'
}

export const loginApi = (data: UserType): Promise<IResponse<UserType>> => {
  return request.post({ url: Enum.login, data })
}

export const loginOutApi = (): Promise<IResponse> => {
  return request.post({ url: Enum.loginOut })
}

export const queryMenusByUsernameApi = (
  params: MenuParams
): Promise<IResponse<AppCustomRouteRecordRaw[]>> => {
  return request.post({ 
    url: Enum.queryMenusByUsername, 
    params 
  })
}

interface MenuParams {
  userName: string
}









// export const loginOutApi = (): Promise<IResponse> => {
//   return request.get({ url: '/mock/user/loginOut' })
// }

export const getUserListApi = ({ params }: AxiosConfig) => {
  return request.get<{
    code: string
    data: {
      list: UserType[]
      total: number
    }
  }>({ url: '/mock/user/list', params })
}
