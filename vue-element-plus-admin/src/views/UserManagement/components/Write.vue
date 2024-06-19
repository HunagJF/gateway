<template>
  <Form :rules="rules" @register="formRegister" :schema="formSchema" />
</template>

<script setup lang="tsx">
import { Form, FormSchema } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { PropType, reactive, watch, ref, unref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import {
  queryAppOrganizationsApi,
  queryAccountTypeApi,
  queryPermissionTypeApi,
  queryAppRegionTreeApi,
  queryAppRolesTreeApi,
} from '@/api/dropDownBox/conditionPullBox'

const props = defineProps({
  currentRow: {
    type: Object as PropType<any>,
    default: () => null
  }
})

const { required } = useValidator()
const rules = reactive({
  name: [required()],
  userName: [required()],
  appOrganizationId: [required()],
  appRegionIds: [required()],
  accountTypeId: [required()],
  permissionTypeId: [required()]
})

const { formRegister, formMethods } = useForm()
const { setValues, getFormData, getElFormExpose } = formMethods
const appRegionTreeData = ref([])
const appRolesTreeData = ref([])


const submit = async () => {
  const elForm = await getElFormExpose()
  const valid = await elForm?.validate()
  if (valid) {
    const formData = await getFormData()
    return formData
  }
}

defineExpose({
  submit
})

const appRegionFetchTreeData = async () => {
  const res = await queryAppRegionTreeApi()
  appRegionTreeData.value = res.data
}

const appRolesFetchTreeData = async () => {
  const res = await queryAppRolesTreeApi()
  appRolesTreeData.value = res.data.map(item => ({
    ...item,
    disabled: !item.parent_id
  }))
}

watch(
  () => props.currentRow,
  async (currentRow) => {
    if (!currentRow) return
    const modifiedRow = { ...currentRow }
    if (typeof modifiedRow.appRegionIds === 'string') {
      modifiedRow.appRegionIds = modifiedRow.appRegionIds.split(',')
    }
    if (typeof modifiedRow.appRolesIds === 'string') {
      modifiedRow.appRolesIds = modifiedRow.appRolesIds.split(',')
    }
    // 等待数据加载完毕再设置值
    await appRegionFetchTreeData()
    await appRolesFetchTreeData()
    setValues(modifiedRow)
  },
  {
    deep: true,
    immediate: true
  }
)

const formSchema = ref<FormSchema[]>([
  {
    field: 'id',
    hidden: true
  },
  {
    field: 'userName',
    label: '登录账号',
    component: 'Input'
  },
  {
    field: 'name',
    label: '用户姓名',
    component: 'Input'
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
  },
  {
    field: 'status',
    label: '状态',
    component: 'Select',
    componentProps: {
      options: [
        {
          label: '禁用',
          value: 0
        },
        {
          label: '启用',
          value: 1
        }
      ]
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
    field: 'appRegionIds',
    label: '数据权限范围',
    component: 'TreeSelect',
    componentProps: {
      renderAfterExpand: false,
      multiple: true,
      filterable: true,
      showCheckbox: true,
      checkStrictly: true,
      checkOnClickNode: true,
      filterNodeMethod: (value, data) => data.label.includes(value),
      data: unref(appRegionTreeData)
    },
    optionApi: async () => {
      return unref(appRegionTreeData)
    }
  },
  {
    field: 'appRolesIds',
    label: '角色',
    component: 'TreeSelect',
    componentProps: {
      renderAfterExpand: false,
      multiple: true,
      filterable: true,
      showCheckbox: true,
      checkStrictly: true,
      checkOnClickNode: true,
      filterNodeMethod: (value, data) => data.label.includes(value),
      data: unref(appRolesTreeData)
    },
    optionApi: async () => {
      return unref(appRolesTreeData)
    }
  }
])
</script>
