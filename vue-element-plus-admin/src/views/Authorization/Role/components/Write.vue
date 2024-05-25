<template>
  <Form :rules="rules" @register="formRegister" :schema="formSchema" />
</template>

<script setup lang="tsx">
import { Form, FormSchema } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { PropType, reactive, watch, ref, unref, nextTick } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { useI18n } from '@/hooks/web/useI18n'
import { ElTree, ElCheckboxGroup, ElCheckbox } from 'element-plus'
import { queryMenusApi, queryTitleApi } from '@/api/menu'
import { filter, eachTree } from '@/utils/tree'

const { t } = useI18n()

const { required } = useValidator()

const props = defineProps({
  currentRow: {
    type: Object as PropType<any>,
    default: () => null
  }
})

const treeRef = ref<typeof ElTree>()

const currentTreeData = ref()
const nodeClick = (treeData: any) => {
  currentTreeData.value = treeData
}

const rules = reactive({
  roleName: [required()],
  role: [required()],
  status: [required()]
})

const { formRegister, formMethods } = useForm()
const { setValues, getFormData, getElFormExpose } = formMethods

const treeData = ref([])
const getMenuList = async () => {
  console.log('getMenuList')
  const res = await queryTitleApi()
  if (res) {
    treeData.value = res.data
    if (!props.currentRow) return
    await nextTick()
    const checked: any[] = []
    eachTree(props.currentRow.menu, (v) => {
      checked.push({
        id: v
      })
    })
    for (const item of checked) {
      unref(treeRef)?.setChecked(item.id, true, false)
    }
  }
}
getMenuList()

const submit = async () => {
  const elForm = await getElFormExpose()
  const valid = await elForm?.validate().catch((err) => {
    console.log(err)
  })
  if (valid) {
    const formData = await getFormData()
    const checkedKeys = unref(treeRef)?.getCheckedKeys() || []
    const data = filter(unref(treeData), (item: any) => {
      return checkedKeys.includes(item.id)
    })
    formData.menu = data || []
    return formData
  }
}

watch(
  () => props.currentRow,
  (currentRow) => {
    if (!currentRow) return
    setValues(currentRow)
  },
  {
    deep: true,
    immediate: true
  }
)

defineExpose({
  submit
})

const formSchema = ref<FormSchema[]>([
  {
    field: 'roleName',
    label: '角色名称',
    component: 'Input'
  },
  {
    field: 'status',
    label: '状态',
    component: 'Select',
    componentProps: {
      options: [
        {
          label: t('userDemo.disable'),
          value: 0
        },
        {
          label: t('userDemo.enable'),
          value: 1
        }
      ]
    }
  },
  {
    field: 'remark',
    label: '备注',
    component: 'Input'
  },
  {
    field: 'menu',
    label: '菜单分配',
    colProps: {
      span: 24
    },
    formItemProps: {
      slots: {
        default: () => {
          return (
            <>
              <div class="flex w-full">
                <div class="flex-1">
                  <ElTree
                    ref={treeRef}
                    show-checkbox
                    node-key="id"
                    highlight-current
                    check-strictly
                    expand-on-click-node={false}
                    data={treeData.value}
                    onNode-click={nodeClick}
                  >
                    {{
                      default: (data) => {
                        return <span>{data.data.title}</span>
                      }
                    }}
                  </ElTree>
                </div>
              </div>
            </>
          )
        }
      }
    }
  }
])
</script>
<!-- 
ElTree 用于展示菜单的树形结构。
ref={treeRef}: 通过引用 treeRef，可以在其他地方访问 ElTree 组件的实例。
show-checkbox: 显示复选框，允许用户选择菜单项。
node-key="id": 每个节点的唯一标识符为 id。
highlight-current: 高亮显示当前选中的节点。
check-strictly: 启用严格模式，父子节点的选中状态不关联。
expand-on-click-node={false}: 点击节点不会自动展开子节点。
data={treeData.value}: 树形结构的数据来源为 treeData。
onNode-click={nodeClick}: 当节点被点击时，触发 nodeClick 函数。
 -->