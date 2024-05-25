<template>
  <ContentWrap>
    <Search :schema="searchSchema" @reset="setSearchParams" @search="setSearchParams" />
    <div class="mb-10px">
      <BaseButton type="primary" @click="AddBut">添加</BaseButton>
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
    />
  </ContentWrap>

  <Dialog v-model="dialogVisible" :title="dialogTitle">
    <Write ref="writeRef" :current-row="currentRow"/>

    <template #footer>
      <BaseButton type="primary" @click="save">保存</BaseButton>
      <BaseButton @click="dialogVisible = false">关闭</BaseButton>
    </template>
  </Dialog>
</template>

<script setup lang="tsx">
import { reactive, ref, unref } from 'vue'
import { useTable } from '@/hooks/web/useTable'
import { Table, TableColumn } from '@/components/Table'
import { ElTag, ElMessageBox } from 'element-plus'
import { Search } from '@/components/Search'
import { FormSchema } from '@/components/Form'
import { ContentWrap } from '@/components/ContentWrap'
import { Dialog } from '@/components/Dialog'
import { BaseButton } from '@/components/Button'
import { queryLoginUserApi, saveOrUpdateLoginApi } from '@/api/login'
import Write from './components/Write.vue'

const writeRef = ref<ComponentRef<typeof Write>>()

const { tableRegister, tableState, tableMethods } = useTable({
  fetchDataApi: async () => {
    const { currentPage, pageSize } = tableState
    const res = await queryLoginUserApi({
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
const { getList } = tableMethods

const searchParams = ref({})
const setSearchParams = (data: any) => {
  searchParams.value = data
  getList()
}

const currentRow = ref()
const dialogVisible = ref(false)
const dialogTitle = ref('')

const AddBut = () => {
  dialogTitle.value = '添加'
  currentRow.value = undefined
  dialogVisible.value = true
}

const editBut = async (row: any) => {
  dialogTitle.value = '编辑'
  currentRow.value = row
  dialogVisible.value = true
}

const deleteBut = (row: any) => {
  ElMessageBox.confirm('此操作将永久删除该用户, 是否继续?',{
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(
    async () => {
      console.log(row)
      getList()
    }
  )
}

const save = async () => {
  const write = unref(writeRef)
  const formData = await write?.submit()
  console.log(formData)
  await saveOrUpdateLoginApi(formData)
  dialogVisible.value = false
  getList()
}

const tableColumns = reactive<TableColumn[]>([
  {
    field: 'index',
    label: '序号',
    type: 'index',
  },
  {
    field: 'name',
    label: '用户名',
  },
  {
    field: 'username',
    label: '账号',
  },
  {
    field: 'rolesStr',
    label: '角色分配',
  },
  {
    field: 'createTime',
    label: '创建时间',
  },
  {
    field: 'action',
    label: '操作',
    width: 180,
    slots: {
      default: (data: any) => {
        const row = data.row
        return (
          <>
            <BaseButton type="primary" onClick={() => editBut(row)}>
              编辑
            </BaseButton>
            {/* <BaseButton type="success" onClick={() => action(row, 'detail')}>
              详情
            </BaseButton> */}
            <BaseButton type="danger" onClick={() => deleteBut(row)}>删除</BaseButton>
          </>
        )
      }
    }
  }
])

const searchSchema = reactive<FormSchema[]>([
  {
    field: 'name',
    label: '用户名',
    component: 'Input'
  },
  {
    field: 'userName',
    label: '账号',
    component: 'Input'
  },
])
</script>