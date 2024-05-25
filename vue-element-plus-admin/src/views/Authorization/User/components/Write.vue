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
import { queryNamesApi } from '@/api/role'
import { eachTree, filter } from '@/utils/tree'

const { t } = useI18n()
const { required } = useValidator()

const props = defineProps({
  currentRow: {
    type: Object as PropType<any>,
    default: () => null
  }
})

const treeRef = ref<typeof ElTree>()
const treeData = ref([])
const currentTreeData = ref()
const nodeClick = (treeData: any) => {
  currentTreeData.value = treeData
}

const rules = reactive({
    name: [required()],
    username: [required()]
})

const { formRegister, formMethods } = useForm()
const { setValues, getFormData, getElFormExpose } = formMethods

const getRoleList = async () => {
    const res = await queryNamesApi();
    if (res) {
        treeData.value = res.data;
        if (!props.currentRow) return
        await nextTick()
        const checked: any[] = []
        eachTree(props.currentRow.roles, (v) => {
            checked.push({
                id: v
            })
        })
        for (const item of checked) {
            unref(treeRef)?.setChecked(item.id, true, false)
        }
    }
}
getRoleList()

const submit = async () => {
  const elForm = await getElFormExpose()
  const valid = await elForm?.validate()
  if (valid) {
    const formData = await getFormData()
    const checkedKeys = unref(treeRef)?.getCheckedKeys() || []
    const data = filter(unref(treeData), (item: any) => {
      return checkedKeys.includes(item.id)
    })
    formData.roles = data || []
    return formData
  }
}

defineExpose({
  submit
})

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

const formSchema = ref<FormSchema[]>([
    {
        field: 'name',
        label: '用户名',
        component: 'Input'
    },
    {
        field: 'username',
        label: '账号',
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
        field: 'rolesStr',
        label: '角色分配',
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
                                    check-strictly
                                    expand-on-click-node={false}
                                    data={treeData.value}
                                    onNode-click={nodeClick}
                                >
                                    {{
                                        default: (data) => {
                                            return <span>{data.data.name}</span>
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

    },
])
</script>