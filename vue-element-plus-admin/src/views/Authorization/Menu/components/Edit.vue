<template>
   <Form :rules :schema="formSchema" @register="formRegister"/>
</template>

<script setup lang="tsx">
import { PropType, reactive, watch, ref, unref } from 'vue'

import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { useValidator } from '@/hooks/web/useValidator'

import { cloneDeep } from 'lodash-es'
import { formSchema as baseFormSchema } from './Edit'

const { required } = useValidator()
const { formRegister, formMethods } = useForm()
const { setValues, getFormData, setSchema } = formMethods

// 获取参数
const props = defineProps({
    currentRow: {
        type: Object as PropType<any>,
        default: () => null
    }
})

const formSchema = baseFormSchema.map(col => {
  if (col.field === 'type') {
    return {
      ...col,
      componentProps: {
        options: [
            {
                label: '目录',
                value: 0
            },
            {
                label: '菜单',
                value: 1
            }
        ],
        on: {
        change: async (val: number) => {
          console.log("change" + val)
          if (val === 1) {
            setSchema([
              {
                field: 'component',
                path: 'componentProps.disabled',
                value: false
              }
            ])
            setValues({
              component: unref(cacheComponent)
            })
          } else {
            setSchema([
              {
                field: 'component',
                path: 'componentProps.disabled',
                value: true
              }
            ])
          }
        }
      }
      },
    }
  }
  return col;
});

const cacheComponent = ref('')

// 监听
watch(() => props.currentRow, (value) => {
    if (!value) return
    const currentRow = cloneDeep(value)
    cacheComponent.value = currentRow.type === 1 ? currentRow.component : ''
    setSchema([
        {
          field: 'component',
          path: 'componentProps.disabled',
          value: currentRow.type === 1 ? false : true
        }
    ])
    setValues(currentRow)
}, { deep: true, immediate: true })

// 提交
const submit = async () => {
    console.log('submit')
    return await getFormData()
}

defineExpose({
  submit
})

// 必填
const rules = reactive({
  component: [required()],
  path: [required()],
  title: [required()]
})

</script>
