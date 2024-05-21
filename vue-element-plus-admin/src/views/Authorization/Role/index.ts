import { reactive, h } from 'vue'
//Table 表格组件
import { TableColumn } from '@/components/Table'
// Form 表单组件
import { FormSchema } from '@/components/Form'

export const columns = reactive<TableColumn[]>([
    {
      field: 'index',
      label: '序号',
      type: 'index'
    },
    {
      field: 'id',
      label: 'ID',
      hidden: true
    },
    {
      field: 'title',
      label: '菜单名称',
      width: '100px'
    },
    {
      field: 'name',
      label: '组件名称',
      type: 'name',
      width: '150px'
    },
    {
      field: 'action',
      label: '操作',
      width: '150px'
    },
  ])