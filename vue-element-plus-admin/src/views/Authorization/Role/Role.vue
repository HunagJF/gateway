<template>
    <ContentWrap>
        <Table
            :columns="columns"
            @register="tableRegister" 
            :data="dataList" 
            :loading
            default-expand-all 
            node-key="id" 
        />
    </ContentWrap>
</template>

<script setup lang="tsx">
import { h, ref, unref } from 'vue'
import { ContentWrap } from '@/components/ContentWrap'
//应用参数
import { columns } from '.'
//Table 表格组件
import { Table } from '@/components/Table'
import { useTable } from '@/hooks/web/useTable'
import { Search } from '@/components/Search'


// 列表组件
const { tableRegister, tableState, tableMethods } = useTable({

})

const { loading, dataList, total, currentPage, pageSize } = tableState

</script>