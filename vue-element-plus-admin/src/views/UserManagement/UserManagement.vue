<template>
    <ContentWrap>
        <Search :schema="searchSchema" @reset="setSearchParams" @search="setSearchParams" />
        <div class="mb-10px">
            <BaseButton type="primary" @click="AddBut">添加</BaseButton>
        </div>
        <Table :columns="tableColumns" default-expand-all node-key="id" :data="dataList" :loading="loading"
            @register="tableRegister" :pagination="{
            total: total,
            pageSizes: [20, 40, 100]
        }" v-model:pageSize="pageSize" v-model:currentPage="currentPage" />
    </ContentWrap>
</template>

<script setup lang="tsx">
import { reactive, ref, unref } from 'vue'
import { useTable } from '@/hooks/web/useTable'
import { Table, TableColumn } from '@/components/Table'
import { ElTag, ElMessageBox } from 'element-plus'
import { Search } from '@/components/Search'
import { FormSchema } from '@/components/Form'
import { ContentWrap } from '@/components/ContentWrap'
import { BaseButton } from '@/components/Button'
import { queryLoginUserApi, saveOrUpdateLoginApi } from '@/api/login'

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


const tableColumns = reactive<TableColumn[]>([
    {
        field: 'userName',
        label: '登录账号',
    },
    {
        field: 'user',
        label: '用户姓名',
    },
    {
        field: 'rolesStr',
        label: '组织机构',
    },
    {
        field: 'createTime',
        label: '角色',
    },
    {
        field: 'createTime',
        label: '模块',
    },
    {
        field: 'createTime',
        label: '数据权限范围',
    },
    {
        field: 'accountTypeName',
        label: '账号类型',
    },
    {
        field: 'permissionTypeName',
        label: '权限类型',
    },
    {
        field: 'status',
        label: '状态',
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