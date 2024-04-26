<script setup lang="tsx">
import { h, ref, unref } from 'vue'
import { ContentWrap } from '@/components/ContentWrap'
//应用参数
import { columns as baseColumns, schema } from './Menu'
//URL地址
import { queryMenusByNameApi } from '@/api/menu'
//BaseButton 按钮组件
import { BaseButton } from '@/components/Button'
//Table 表格组件
import { Table } from '@/components/Table'
import { useTable } from '@/hooks/web/useTable'
import { Search } from '@/components/Search'

// 查询条件
const searchParams = ref({})
// 列表组件
const { tableRegister, tableState, tableMethods } = useTable({
  fetchDataApi: async () => {
    const res = await queryMenusByNameApi(unref(searchParams))
    return {
      list: res.data.list || []
    }
  }
})

const { getList } = tableMethods
const { loading, dataList } = tableState

const setSearchParams = (data: any) => {
  searchParams.value = data
  getList()
}

const handleEdit = (row) => {
    // 编辑操作的逻辑
    console.log('编辑操作', row);
}

const handleDelete = (row) => {
    // 删除操作的逻辑
    console.log('删除操作', row);
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
});

</script>

<template>
  <ContentWrap>
    <Search 
    :schema="schema" 
    @reset="setSearchParams" 
    @search="setSearchParams"/>
      <Table
        :columns="columns"
        default-expand-all
        node-key="id"
        :data="dataList"
        :loading="loading"
        @register="tableRegister"
        :height="800"
        :max-height="750"
      />
  </ContentWrap>
</template>
