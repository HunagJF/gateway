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
      <BaseButton type="success" @click="editBut">编辑</BaseButton>
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
import { ref, unref } from 'vue'
import { useTable } from '@/hooks/web/useTable'
import { Table } from '@/components/Table'
import { Search } from '@/components/Search'
import { ContentWrap } from '@/components/ContentWrap'
import { BaseButton } from '@/components/Button'
import { queryUserApi, updateApi } from '@/api/userManagement'
import { Dialog } from '@/components/Dialog'
import Write from './components/Write.vue'
import { tableColumns, searchSchema } from '.' 
import { ElMessage } from 'element-plus'

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
</script>
