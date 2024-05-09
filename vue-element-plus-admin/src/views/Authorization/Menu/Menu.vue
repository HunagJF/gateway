<template>
  <ContentWrap>
    <Search :schema="schema" @reset="setSearchParams" @search="setSearchParams" />
    <BaseButton type="primary" @click="AddAction">添加</BaseButton>
    <Table 
      :maxHeight = "650"
      :pagination="{total: total}"
      :pageSize
      :currentPage
      :columns
      default-expand-all 
      node-key="id" 
      :data="dataList" 
      :loading
      @register="tableRegister" 
      />
  </ContentWrap>

  <Dialog v-model="dialogVisible" title="编辑">
    <Edit :current-row="currentRow" ref="editRef" />
    <template #footer>
      <BaseButton type="primary" @click="update">提交</BaseButton>
    </template>
  </Dialog>

  <Dialog v-model="addDialog" title="添加">
    <Add ref="addRef" />
    <template #footer>
      <BaseButton type="primary" @click="add">提交</BaseButton>
    </template>
  </Dialog>
</template>


<script setup lang="tsx">
import { h, ref, unref } from 'vue'
import { ContentWrap } from '@/components/ContentWrap'
//应用参数
import { columns as baseColumns, schema } from './Menu'
//URL地址
import { queryMenusByNameApi, updateApi, insertApi } from '@/api/menu'
//BaseButton 按钮组件
import { BaseButton } from '@/components/Button'
//Table 表格组件
import { Table } from '@/components/Table'
import { useTable } from '@/hooks/web/useTable'
import { Search } from '@/components/Search'

import Edit from './components/Edit.vue'
import Add from './components/Add.vue'
import { Dialog } from '@/components/Dialog'

// 查询条件
const searchParams = ref({})
// 列表组件
const { tableRegister, tableState, tableMethods } = useTable({
  fetchDataApi: async () => {
    const { currentPage, pageSize } = tableState
    const res = await queryMenusByNameApi({
      ...unref(searchParams),
      page: unref(currentPage),
      size: unref(pageSize)
    })
    return {
      list: res.data.data.list || [],
      total: res.data.data.total
    }
  }
})

const { getList } = tableMethods
const { loading, dataList, total, currentPage, pageSize } = tableState

const setSearchParams = (data: any) => {
  searchParams.value = data
  getList()
}

const dialogVisible = ref(false)
const addDialog = ref(false)
const currentRow = ref()

const editRef = ref<ComponentRef<typeof Edit>>()
const addRef = ref<ComponentRef<typeof Add>>()

const AddAction = () => {
  console.log("添加操作")
  addDialog.value = true
}

const handleEdit = (row) => {
  // 编辑操作的逻辑
  console.log('编辑操作', row)
  dialogVisible.value = true
  currentRow.value = row
}

const handleDelete = (row) => {
  // 删除操作的逻辑
  console.log('删除操作', row)
}

const update = async () => {
  const write = unref(editRef)
  const formData = await write?.submit()
  await updateApi(formData);
  dialogVisible.value = false
  getList()
}

const add = async () => {
  const write = unref(addRef)
  const formData = await write?.addSubmit()
  await insertApi(formData);
  addDialog.value = false
  getList()
}

// 给列表添加按钮
const columns = baseColumns.map(col => {
  if (col.field === 'action') {
    return {
      ...col,
      slots: {
        default: ({ row }) => {
          return [
            h(BaseButton, { // 将 ElButton 改为 BaseButton
              type: 'primary',
              size: 'small',
              onClick: () => handleEdit(row),
            }, '编辑'),
            h(BaseButton, { // 将 ElButton 改为 BaseButton
              type: 'danger',
              size: 'small',
              onClick: () => handleDelete(row),
            }, '删除'),
          ];
        }
      }
    };
  }
  return col;
})
</script>