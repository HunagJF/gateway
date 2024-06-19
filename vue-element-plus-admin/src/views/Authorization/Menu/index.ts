import { reactive, h } from 'vue'
//Table 表格组件
import { TableColumn } from '@/components/Table'
// Form 表单组件
import { FormSchema } from '@/components/Form'
//国际化
import Icon from '@/components/Icon/src/Icon.vue'
import { ElTag } from 'element-plus'

export const columns = reactive<TableColumn[]>([
  {
    field: 'id',
    label: 'ID',
    hidden: true
  },
  {
    field: 'title',
    label: '菜单名称',
    width: '200px'
  },
  {
    field: 'sort',
    label: '排序',
    width: '100px'
  },
  {
    field: 'name',
    label: '组件名称',
    type: 'name',
    width: '150px'
  },
  {
    field: 'component',
    label: '组件',
    width: '200px',
    slots: {
      default: (data: any) => {
        const component = data.row.component
        return component === '#' ? '顶级目录' : component === '##' ? '子目录' : component
      }
    }
  },
  {
    field: 'path',
    label: '路径',
    type: 'path',
    width: '120px'
  },
  {
    field: 'redirect',
    label: '重定向',
    type: 'redirect',
    width: '120px'
  },
  {
    field: 'icon',
    label: '图标',
    slots: {
      default: (data: any) => {
        const icon = data.row.icon
        if (icon) {
          // 使用`h`函数创建VNode
          return h(Icon, { icon: icon })
        } else {
          return null
        }
      }
    }
  },
  {
    field: 'status',
    label: '状态',
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
    field: 'action',
    label: '操作',
    width: '150px'
  },
  {
    field: 'alwaysShow',
    label: '是否一直显示',
    slots: {
      default: (data: any) => {
        const alwaysShow: boolean = data.row.alwaysShow
        return h(
          ElTag,
          {
            type: alwaysShow ? 'success' : 'danger'
          },
          alwaysShow ? '是' : '否'
        )
      }
    }
  },
  {
    field: 'affix',
    label: '是否固定在标签页',
    slots: {
      default: (data: any) => {
        const affix: boolean = data.row.affix
        return h(
          ElTag,
          {
            type: affix ? 'success' : 'danger'
          },
          affix ? '是' : '否'
        )
      }
    }
  },
  {
    field: 'noCache',
    label: '是否清除缓存',
    slots: {
      default: (data: any) => {
        const noCache: boolean = data.row.noCache
        return h(
          ElTag,
          {
            type: noCache ? 'success' : 'danger'
          },
          noCache ? '是' : '否'
        )
      }
    }
  },
  {
    field: 'hidden',
    label: '是否隐藏',
    slots: {
      default: (data: any) => {
        const hidden: boolean = data.row.hidden
        return h(
          ElTag,
          {
            type: hidden ? 'success' : 'danger'
          },
          hidden ? '是' : '否'
        )
      }
    }
  },
  {
    field: 'canTo',
    label: '是否可跳转',
    slots: {
      default: (data: any) => {
        const canTo: boolean = data.row.canTo
        return h(
          ElTag,
          {
            type: canTo ? 'success' : 'danger'
          },
          canTo ? '是' : '否'
        )
      }
    }
  },
  {
    field: 'activeMenu',
    label: '高亮菜单',
    type: 'activeMenu'
  },
  {
    field: 'meta.breadcrumb',
    label: '是否显示面包屑',
    slots: {
      default: (data: any) => {
        const breadcrumb: boolean = data.row.breadcrumb
        return h(
          ElTag,
          {
            type: breadcrumb ? 'success' : 'danger'
          },
          breadcrumb ? '是' : '否'
        )
      }
    }
  },
  {
    field: 'noTagsView',
    label: '是否隐藏标签页',
    slots: {
      default: (data: any) => {
        const noTagsView: boolean = data.row.noTagsView
        return h(
          ElTag,
          {
            type: noTagsView ? 'success' : 'danger'
          },
          noTagsView ? '是' : '否'
        )
      }
    }
  }
])

export const schema = reactive<FormSchema[]>([
  {
    field: 'title',
    label: '菜单名称',
    component: 'Input'
  }
])
