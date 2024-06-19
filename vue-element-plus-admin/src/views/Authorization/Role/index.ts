import { reactive, h } from 'vue'
//Table 表格组件
import { TableColumn } from '@/components/Table'
// Form 表单组件
import { FormSchema } from '@/components/Form'

import { ElTag } from 'element-plus'

export const tableColumns = reactive<TableColumn[]>([
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
        field: 'roleName',
        label: '角色名称',
        width: 200,
    },
    {
        field: 'status',
        label: '状态',
        width: 80,
        slots: {
            default: (data: any) => {
                const status: number = data.row.status
                return h(
                    ElTag,
                    {
                        type: status === 1 ? 'success' : 'danger'
                    },
                    status === 1 ? '启用' : '停用'
                )
            }
        }
    },
    {
        field: 'remark',
        label: '备注',
        width: 200,
    },
    {
        field: 'menusStr',
        label: '菜单分配'
    },
    {
        field: 'createTime',
        label: '创建时间',
        width: 200,
    },
])

export const searchSchema = reactive<FormSchema[]>([
    {
        field: 'roleName',
        label: '角色名称',
        component: 'Input'
    }
])