<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { Dialog } from '@/components/Dialog'
import { useI18n } from '@/hooks/web/useI18n'
import { ref, reactive } from 'vue'
import { Form, FormSchema } from '@/components/Form'
import { useValidator } from '@/hooks/web/useValidator'
import { getDictOneApi } from '@/api/model/common'
import { useForm } from '@/hooks/web/useForm'
import Echart from './Echart.vue'

const { required } = useValidator()

const { t } = useI18n()

const dialogVisible = ref(false)

const dialogVisible2 = ref(false)

const { formRegister, formMethods } = useForm()
const { getElFormExpose } = formMethods

const schema = reactive<FormSchema[]>([
  {
    field: 'field1',
    label: t('formDemo.input'),
    component: 'Input',
    formItemProps: {
      rules: [required()]
    }
  },
  {
    field: 'field2',
    label: t('formDemo.select'),
    component: 'Select',
    // componentProps: {
    //   options: []
    // },
    optionApi: async () => {
      const res = await getDictOneApi()
      return res.data
    }
  },
  {
    field: 'field3',
    label: t('formDemo.radio'),
    component: 'RadioGroup',
    componentProps: {
      options: [
        {
          label: 'option-1',
          value: '1'
        },
        {
          label: 'option-2',
          value: '2'
        }
      ]
    }
  },
  {
    field: 'field4',
    label: t('formDemo.checkbox'),
    component: 'CheckboxGroup',
    value: [],
    componentProps: {
      options: [
        {
          label: 'option-1',
          value: '1'
        },
        {
          label: 'option-2',
          value: '2'
        }
      ]
    }
  },
  {
    field: 'field5',
    component: 'DatePicker',
    label: t('formDemo.datePicker'),
    componentProps: {
      type: 'date'
    }
  },
  {
    field: 'field6',
    component: 'TimeSelect',
    label: t('formDemo.timeSelect')
  }
])

const formSubmit = async () => {
  const elFormExpose = await getElFormExpose()
  elFormExpose?.validate((valid) => {
    if (valid) {
      console.log('submit success')
    } else {
      console.log('submit fail')
    }
  })
}
</script>

<template>
  <ContentWrap :title="t('dialogDemo.dialog')" :message="t('dialogDemo.dialogDes')">
    <BaseButton type="primary" @click="dialogVisible = !dialogVisible">
      {{ t('dialogDemo.open') }}
    </BaseButton>

    <BaseButton type="primary" @click="dialogVisible2 = !dialogVisible2">
      {{ t('dialogDemo.combineWithForm') }}
    </BaseButton>

    <Dialog v-model="dialogVisible" :title="t('dialogDemo.dialog')">
      <Echart />
      <template #footer>
        <BaseButton @click="dialogVisible = false">{{ t('dialogDemo.close') }}</BaseButton>
      </template>
    </Dialog>

    <Dialog v-model="dialogVisible2" :title="t('dialogDemo.dialog')">
      <Form :schema="schema" @register="formRegister" />
      <template #footer>
        <BaseButton type="primary" @click="formSubmit">{{ t('dialogDemo.submit') }}</BaseButton>
        <BaseButton @click="dialogVisible2 = false">{{ t('dialogDemo.close') }}</BaseButton>
      </template>
    </Dialog>
  </ContentWrap>
</template>
