import { reactive } from 'vue'
import { FormSchema } from '@/components/Form'
import { queryTitleApi } from '@/api/menu'

export const formSchema = reactive<FormSchema[]>([
    {
      field: 'id',
      hidden: false
    },
    {
      field: 'type',
      label: '菜单类型',
      component: 'RadioButton',
      value: 0,
      colProps: {
        span: 24
      }
    },
    {
      field: 'parentId',
      label: '父级菜单',
      component: 'TreeSelect',
      componentProps: {
        nodeKey: 'id',
        props: {
          label: 'title',
          value: 'id',
          children: 'children'
        },
        highlightCurrent: true,
        expandOnClickNode: false,
        checkStrictly: true,
        checkOnClickNode: true,
        clearable: true,
      },
      optionApi: async () => {
        const res = await queryTitleApi()
        console.log(res)
        return res.data || []
      }
    },
    {
      field: 'title',
      label: '菜单名称',
      component: 'Input',
    },
    {
      field: 'component',
      label: '组件',
      component: 'Input',
      value: '#',
      componentProps: {
        disabled: true,
        placeholder: '#为顶级目录，##为子目录',
      }
    },
    {
      field: 'name',
      label: '组件名称',
      component: 'Input'
    },
    {
      field: 'icon',
      label: '图标',
      component: 'IconPicker',
    },
    {
      field: 'path',
      label: '路径',
      component: 'Input'
    },
    {
      field: 'activeMenu',
      label: '高亮菜单',
      component: 'Input'
    },
    {
      field: 'status',
      label: '状态',
      component: 'Select',
      componentProps: {
        options: [
          {
            label : '禁用',
            value: 0
          },
          {
            label : '启用',
            value: 1
          }
        ]
      }
    },
    {
      field: 'sort',
      label: '排序',
      component: 'Input'
    },
    {
      field: 'hidden',
      label: '是否隐藏',
      component: 'Switch'
    },
    {
      field: 'alwaysShow',
      label: '是否一直显示',
      component: 'Switch'
    },
    {
      field: 'noCache',
      label: '是否清除缓存',
      component: 'Switch'
    },
    {
      field: 'breadcrumb',
      label: '是否显示面包屑',
      component: 'Switch'
    },
    {
      field: 'affix',
      label: '是否固定在标签页',
      component: 'Switch'
    },
    {
      field: 'noTagsView',
      label: '是否隐藏标签页',
      component: 'Switch'
    },
    {
      field: 'canTo',
      label: '是否可跳转',
      component: 'Switch'
    },
])