<script setup lang="ts">
import Detail from './components/Detail.vue'
import { ContentDetailWrap } from '@/components/ContentDetailWrap'
import { ref } from 'vue'
import { useI18n } from '@/hooks/web/useI18n'
import { useRouter, useRoute } from 'vue-router'
import { getTableDetApi } from '@/api/model/table'
import { TableData } from '@/api/model/table/types'

const { push, go } = useRouter()

const { query } = useRoute()

const { t } = useI18n()

const currentRow = ref<Nullable<TableData>>(null)

const getTableDet = async () => {
  const res = await getTableDetApi(query.id as string)
  if (res) {
    currentRow.value = res.data
  }
}

getTableDet()
</script>

<template>
  <ContentDetailWrap :title="t('exampleDemo.detail')" @back="push('/example/example-page')">
    <template #header>
      <BaseButton @click="go(-1)">
        {{ t('common.back') }}
      </BaseButton>
    </template>
    <Detail :current-row="currentRow" />
  </ContentDetailWrap>
</template>
