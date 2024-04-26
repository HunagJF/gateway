import { reactive, h } from 'vue'
//Table 表格组件
import { TableColumn } from '@/components/Table'
// Form 表单组件
import { FormSchema } from '@/components/Form'
//国际化
import { useI18n } from '@/hooks/web/useI18n'
import Icon from '@/components/Icon/src/Icon.vue'
import { ElTag } from 'element-plus'
const { t } = useI18n()

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
      width: '100px',
    },
    {
      field: 'name',
      label: '组件名称',
      type: 'name',
      width: '150px',
    },
    {
      field: 'component',
      label: '组件',
      width: '200px',
      slots: {
        default: (data: any) => {
          const component = data.row.component;
          return component === '#' ? '顶级目录' : component === '##' ? '子目录' : component
        }
      }
    },
    {
      field: 'path',
      label: '路径',
      type: 'path',
      width: '120px',
    },
    {
      field: 'redirect',
      label: '重定向',
      type: 'redirect',
      width: '120px',
    },
    {
      field: 'icon',
      label: '图标',
      slots: {
        default: (data: any) => {
          const icon = data.row.icon;
          if (icon) {
            // 使用`h`函数创建VNode
            return h(Icon, { icon: icon });
          } else {
            return null;
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
          if (status) {
            return h(ElTag, {
              type: status === 0 ? 'danger' : 'success'
            }, status === 1 ? t('userDemo.enable') : t('userDemo.disable')
            )
          }
        }
      }
    },
    {
      field: 'alwaysShow',
      label: '是否一直显示',
      type: 'alwaysShow',
    },
    {
      field: 'affix',
      label: '是否固定在标签页',
      type: 'affix',
    },
    {
      field: 'noCache',
      label: '是否清除缓存',
      type: 'noCache',
    },
    {
      field: 'hidden',
      label: '是否隐藏',
      type: 'hidden',
    },
    {
      field: 'canTo',
      label: '是否可跳转',
      type: 'canTo',
    },
    {
      field: 'activeMenu',
      label: '高亮菜单',
      type: 'activeMenu',
    },
    {
      field: 'breadcrumb',
      label: '是否显示面包屑',
      type: 'breadcrumb',
    },
    {
      field: 'noTagsView',
      label: '是否隐藏标签页',
      type: 'noTagsView',
    },
    {
      field: 'sort',
      label: '排序',
      type: 'sort',
    },
    {
        field: 'action',
        label: '操作',
        width: '150px'
    }
  ])

export const schema = reactive<FormSchema[]>([
  {
    field: 'title',
    label: '菜单名称',
    component: 'Input'
  }
])  
