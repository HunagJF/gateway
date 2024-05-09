<template>
    <Form :rules :schema="formSchema" @register="formRegister" />
</template>

<script setup lang="ts">
import { reactive, unref, ref } from 'vue'
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { formSchema as baseFormSchema } from '.'
import { useValidator } from '@/hooks/web/useValidator'

const { formRegister, formMethods } = useForm()
const { setValues, getFormData, setSchema, getElFormExpose } = formMethods
const { required } = useValidator()

const cacheComponent = ref('')

// 必填
const rules = reactive({
    component: [required()],
    path: [required()],
    title: [required()]
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
                        const formData = await getFormData()
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

                            if (formData.parentId === void 0) {
                                setValues({
                                    component: '#'
                                })
                            } else {
                                setValues({
                                    component: '##'
                                })
                            }
                        }
                    }
                }
            },
        }
    }
    return col;
});

// 提交
const addSubmit = async () => {
    const elForm = await getElFormExpose()
    const valid = await elForm?.validate()
    if (valid) {
        return await getFormData()
    }
}

defineExpose({
    addSubmit
})
</script>.