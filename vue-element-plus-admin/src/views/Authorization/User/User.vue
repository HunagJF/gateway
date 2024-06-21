<template>
  <ContentWrap>
    <Search
      :schema="searchSchema"
      @reset="setSearchParams"
      @search="setSearchParams"
      layout="bottom"
      buttonPosition="right"
    />
    <div class="mb-10px">
      <BaseButton type="primary" @click="AddBut">添加</BaseButton>
      <BaseButton type="success" @click="editBut">编辑</BaseButton>
      <BaseButton type="danger" @click="delBut">删除</BaseButton>
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
    <Write ref="writeRef" :current-row="currentRow" />

    <template #footer>
      <BaseButton type="primary" @click="save">保存</BaseButton>
      <BaseButton @click="dialogVisible = false">关闭</BaseButton>
    </template>
  </Dialog>
</template>

<script setup lang="tsx">
import { ref, unref } from 'vue'
import { useTable } from '@/hooks/web/useTable'
import { Table } from '@/components/Table'
import { ElMessageBox, ElMessage } from 'element-plus'
import { Search } from '@/components/Search'
import { ContentWrap } from '@/components/ContentWrap'
import { Dialog } from '@/components/Dialog'
import { BaseButton } from '@/components/Button'
import { queryLoginUserApi, saveOrUpdateLoginApi, deleteByIdsApi } from '@/api/login'
import Write from './components/Write.vue'
import { tableColumns, searchSchema } from '.'

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
const { getList, getElTableExpose } = tableMethods

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
  ElMessageBox.confirm('此操作将永久删除该用户, 是否继续?', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteByIdsApi(elTableRef?.getSelectionRows())
    getList()
  })
}

const save = async () => {
  const write = unref(writeRef)
  const formData = await write?.submit()
  console.log(formData)
  await saveOrUpdateLoginApi(formData)
  dialogVisible.value = false
  getList()
}
</script>
