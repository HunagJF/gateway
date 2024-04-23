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
      label: t('userDemo.index'),
      type: 'index'
    },
    {
      field: 'meta.title',
      label: t('menu.menuName'),
    },
    {
      field: 'meta.icon',
      label: t('menu.icon'),
      slots: {
        default: (data: any) => {
          const icon = data.row.meta.icon;
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
      field: 'component',
      label: t('menu.component'),
      slots: {
        default: (data: any) => {
          const component = data.row.component;
          return component === '#' ? '顶级目录' : component === '##' ? '子目录' : component
        }
      }
    },
    {
      field: 'path',
      label: t('menu.path'),
      type: 'path'
    },
    {
      field: 'status',
      label: t('menu.status'),
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
        field: 'action',
        label: t('userDemo.action'),
    }
  ])

export const schema = reactive<FormSchema[]>([
  {
    field: 'title',
    label: '菜单名称',
    component: 'Input'
  }
])  
