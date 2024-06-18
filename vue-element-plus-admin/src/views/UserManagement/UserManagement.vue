<template>
  <ContentWrap>
    <Search
      :schema="searchSchema"
      @reset="setSearchParams"
      @search="setSearchParams"
      layout="bottom"
      buttonPosition="right"
      show-expand
      expand-field="appOrganizationId"
    />
    <div class="mb-10px">
      <!-- <BaseButton type="primary" @click="AddBut">添加</BaseButton> -->
      <BaseButton type="primary" @click="editBut">编辑</BaseButton>
    </div>
    <Table
      :columns="tableColumns"
      default-expand-all
      node-key="id"
      :data="dataList"
      :loading="loading"
      @register="tableRegister"
      :pagination="{
        total: total,
        pageSizes: [20, 40, 100]
      }"
      v-model:pageSize="pageSize"
      v-model:currentPage="currentPage"
      @selection-change="handleSelectionChange"
    />
  </ContentWrap>
  <Dialog v-model="dialogVisible" :title="dialogTitle">
    <Write ref="writeRef" :current-row="currentRow" />

    <template #footer>
      <BaseButton type="primary" @click="save"> 保存 </BaseButton>
      <BaseButton @click="dialogVisible = false">关闭</BaseButton>
    </template>
  </Dialog>
</template>

<script setup lang="tsx">
import { reactive, ref, unref, onMounted } from 'vue'
import { useTable } from '@/hooks/web/useTable'
import { Table, TableColumn } from '@/components/Table'
import { ElTag, ElMessageBox } from 'element-plus'
import { Search } from '@/components/Search'
import { FormSchema } from '@/components/Form'
import { ContentWrap } from '@/components/ContentWrap'
import { BaseButton } from '@/components/Button'
import { queryUserApi, updateApi } from '@/api/userManagement'
import { Dialog } from '@/components/Dialog'
import {
  queryAppOrganizationsApi,
  queryAppRolesApi,
  queryAppRegionApi,
  queryAccountTypeApi,
  queryPermissionTypeApi
} from '@/api/dropDownBox/conditionPullBox'
import Write from './components/Write.vue'

const writeRef = ref<ComponentRef<typeof Write>>()

const { tableRegister, tableState, tableMethods } = useTable({
  fetchDataApi: async () => {
    const { currentPage, pageSize } = tableState
    const res = await queryUserApi({
      ...unref(searchParams),
      page: unref(currentPage),
      size: unref(pageSize)
    })
    return {
      list: res.data || [],
      total: res.total
    }
  }
})

const { dataList, loading, total, currentPage, pageSize } = tableState
const { getList, getElTableExpose } = tableMethods

const searchParams = ref({})
const setSearchParams = (data: any) => {
  searchParams.value = data
  console.log(data)
  getList()
}

const currentRow = ref()
const dialogVisible = ref(false)
const dialogTitle = ref('')

// const AddBut = () => {
//   dialogTitle.value = '添加'
//   currentRow.value = undefined
//   dialogVisible.value = true
// }

const editBut = async () => {
  const elTableRef = await getElTableExpose()
  dialogTitle.value = '编辑'
  currentRow.value = elTableRef?.getSelectionRows()[0]
  dialogVisible.value = true
}

const save = async () => {
  const write = unref(writeRef)
  const formData = await write?.submit()
  console.log(formData)
  await updateApi(formData)
  dialogVisible.value = false
  getList()
}

const currentSelectedId = ref(null)
const handleSelectionChange = async (selection: any[]) => {
  const datas = dataList.value.length
  const rowLen = selection.length

  if (datas === rowLen || rowLen === 0) return

  const elTableRef = await getElTableExpose()
  if (rowLen === 1) {
    currentSelectedId.value = selection[0].id
  } else if (rowLen >= 2) {
    for (let i = 0; i < rowLen; i++) {
      const id = selection[i].id
      if (id === unref(currentSelectedId)) {
        elTableRef?.toggleRowSelection(selection[i], false)
      } else {
        currentSelectedId.value = id
        elTableRef?.toggleRowSelection(selection[i], true)
      }
    }
  } else {
    currentSelectedId.value = null
  }
}

const tableColumns = reactive<TableColumn[]>([
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
        return (
          <>
            <ElTag type={data.row.status === 0 ? 'danger' : 'success'}>
              {data.row.status === 1 ? '启用' : '停用'}
            </ElTag>
          </>
        )
      }
    }
  }
])

const searchSchema = reactive<FormSchema[]>([
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
</script>
