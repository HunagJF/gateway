import { reactive, h } from 'vue'
//Table 表格组件
import { TableColumn } from '@/components/Table'
// Form 表单组件
import { FormSchema } from '@/components/Form'

import { ElTag } from 'element-plus'

import {
    queryAppOrganizationsApi,
    queryAppRolesApi,
    queryAppRegionApi,
    queryAccountTypeApi,
    queryPermissionTypeApi
  } from '@/api/dropDownBox/conditionPullBox'

export const tableColumns = reactive<TableColumn[]>([
    {
        field: 'selection',
        type: 'selection'
    },
    {
        field: 'id',
        label: 'ID',
        hidden: true
    },
    {
        field: 'userName',
        label: '登录账号'
    },
    {
        field: 'name',
        label: '用户姓名'
    },
    {
        field: 'appOrganizationName',
        label: '组织机构'
    },
    {
        field: 'appOrganizationId',
        hidden: true
    },
    {
        field: 'appRolesName',
        label: '角色'
    },
    {
        field: 'appRolesIds',
        hidden: true
    },
    {
        field: 'appsName',
        label: '模块'
    },
    {
        field: 'appsIds',
        hidden: true
    },
    {
        field: 'appRegionName',
        label: '数据权限范围'
    },
    {
        field: 'appRegionIds',
        hidden: true
    },
    {
        field: 'accountTypeName',
        label: '账号类型'
    },
    {
        field: 'accountTypeId',
        hidden: true
    },
    {
        field: 'permissionTypeName',
        label: '权限类型'
    },
    {
        field: 'permissionTypeId',
        hidden: true
    },
    {
        field: 'status',
        label: '状态',
        slots: {
            default: (data: any) => {
                const status: number = data.row.status
                return h(
                    ElTag,
                    {
                        type: status === 1 ? 'success' : 'danger'
                    },
                    status === 1 ? '启用' : '停用'
                )
            }
        }
    }
])

export const searchSchema = reactive<FormSchema[]>([
    {
      field: 'userName',
      label: '登录账号',
      component: 'Input'
    },
    {
      field: 'name',
      label: '姓名',
      component: 'Input'
    },
    {
      field: 'appsId',
      label: '模块名称',
      component: 'Select',
      optionApi: async () => {}
    },
    {
      field: 'appRegionId',
      label: '数据权限范围',
      component: 'Select',
      componentProps: {
        filterable: true,
        remote: true
      },
      optionApi: async () => {
        const res = await queryAppRegionApi()
        return res.data.map((item: any) => ({
          label: item.name,
          value: item.id
        }))
      }
    },
    {
      field: 'appOrganizationId',
      label: '组织机构',
      component: 'Select',
      componentProps: {
        filterable: true,
        remote: true
      },
      optionApi: async () => {
        const res = await queryAppOrganizationsApi()
        return res.data.map((item: any) => ({
          label: item.name,
          value: item.id
        }))
      }
    },
    {
      field: 'appRolesId',
      label: '角色',
      component: 'Select',
      componentProps: {
        filterable: true,
        remote: true
      },
      optionApi: async () => {
        const res = await queryAppRolesApi()
        return res.data.map((item: any) => ({
          label: item.name,
          value: item.id
        }))
      }
    },
    {
      field: 'accountTypeId',
      label: '账号类型',
      component: 'Select',
      componentProps: {
        filterable: true,
        remote: true
      },
      optionApi: async () => {
        const res = await queryAccountTypeApi()
        return res.data.map((item: any) => ({
          label: item.description,
          value: item.id
        }))
      }
    },
    {
      field: 'permissionTypeId',
      label: '权限类型',
      component: 'Select',
      componentProps: {
        filterable: true,
        remote: true
      },
      optionApi: async () => {
        const res = await queryPermissionTypeApi()
        return res.data.map((item: any) => ({
          label: item.description,
          value: item.id
        }))
      }
    }
  ])