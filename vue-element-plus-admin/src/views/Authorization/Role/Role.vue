<template>
  <ContentWrap>
    <Search :schema="searchSchema" @reset="setSearchParams" @search="setSearchParams" />
    <div class="mb-10px">
      <BaseButton type="primary" @click="addBut">添加</BaseButton>
      <BaseButton type="success" @click="editBut">编辑</BaseButton>
      <BaseButton type="danger" @click="delBut">删除</BaseButton>
    </div>
    <Table :columns="tableColumns" default-expand-all node-key="id" :data="dataList" :loading="loading"
      @register="tableRegister" :pagination="{
      total: total,
      pageSizes: [20, 40, 100]
    }" v-model:pageSize="pageSize" v-model:currentPage="currentPage" />
  </ContentWrap>

  <Dialog v-model="dialogVisible" :title="dialogTitle">
    <Write ref="writeRef" :current-row="currentRow" />
    <template #footer>
      <BaseButton type="primary" @click="save">
        保存
      </BaseButton>
      <BaseButton @click="dialogVisible = false">关闭</BaseButton>
    </template>
  </Dialog>
</template>

<script setup lang="tsx">
import { ref, unref } from 'vue'
import { queryRolesByNameApi } from '@/api/role'
import { useTable } from '@/hooks/web/useTable'
import { Table } from '@/components/Table'
import { ElMessageBox, ElMessage } from 'element-plus'
import { Search } from '@/components/Search'
import { ContentWrap } from '@/components/ContentWrap'
import Write from './components/Write.vue'
// import Detail from './components/Detail.vue'
import { Dialog } from '@/components/Dialog'
import { BaseButton } from '@/components/Button'
import { saveOrUpdateRoleApi, deleteByIdApi } from '@/api/role'
import { tableColumns, searchSchema } from '.'

const writeRef = ref<ComponentRef<typeof Write>>()
const dialogVisible = ref(false)
const dialogTitle = ref('')
const currentRow = ref()

const { tableRegister, tableState, tableMethods } = useTable({
  fetchDataApi: async () => {
    const { currentPage, pageSize } = tableState
    const res = await queryRolesByNameApi({
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
  getList()
}

const addBut = async () => {
  dialogTitle.value = '添加'
  currentRow.value = undefined
  dialogVisible.value = true
}

const editBut = async () => {
  const elTableRef = await getElTableExpose()
  const len = elTableRef?.getSelectionRows().length
  if (len === 0 || len > 1) {
    ElMessage.error('请选择一条数据！')
    return
  }
  dialogTitle.value = '编辑'
  currentRow.value = elTableRef?.getSelectionRows()[0]
  dialogVisible.value = true
}

const delBut = async () => {
  const elTableRef = await getElTableExpose()
  const len = elTableRef?.getSelectionRows().length
  if (!len) {
    ElMessage.error('请选择一条数据！')
    return
  }
  ElMessageBox.confirm('此操作将永久删除该角色, 是否继续?', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(
    async () => {
      await deleteByIdApi(elTableRef?.getSelectionRows())
      getList()
    }
  )
}

const save = async () => {
  const write = unref(writeRef)
  const formData = await write?.submit()
  await saveOrUpdateRoleApi(formData)
  dialogVisible.value = false
  getList()
}
</script>
