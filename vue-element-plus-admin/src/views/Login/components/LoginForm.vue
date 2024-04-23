<script setup lang="tsx">
import { reactive, ref, watch, onMounted, unref } from 'vue'
import { Form, FormSchema } from '@/components/Form'
import { useI18n } from '@/hooks/web/useI18n'
import { ElCheckbox, ElLink } from 'element-plus'
import { useForm } from '@/hooks/web/useForm'
import { loginApi, queryMenusByUsernameApi } from '@/api/login'
import { useAppStore } from '@/store/modules/app'
import { usePermissionStore } from '@/store/modules/permission'
import { useRouter } from 'vue-router'
import type { RouteLocationNormalizedLoaded, RouteRecordRaw } from 'vue-router'
import { UserType } from '@/api/login/types'
import { useValidator } from '@/hooks/web/useValidator'
import { Icon } from '@/components/Icon'
import { useUserStore } from '@/store/modules/user'
import { BaseButton } from '@/components/Button'

// 获取必要的表单验证器
const { required } = useValidator()

// 定义组件能够触发的事件
const emit = defineEmits(['to-register'])

// 应用程序级别的状态管理
const appStore = useAppStore()
const userStore = useUserStore()
const permissionStore = usePermissionStore()

// 应用程序级别的状态管理
const { currentRoute, addRoute, push } = useRouter()

// 国际化
const { t } = useI18n()

// 定义表单验证规则
const rules = {
  username: [required()],
  password: [required()]
}

// 表单模式定义，用于对接Form组件
const schema = reactive<FormSchema[]>([
  {
    // 登录表单标题区域
    field: 'title',
    colProps: {
      // 该区域占满整行
      span: 24
    },
    formItemProps: {
      slots: {
        default: () => {
          // 返回一个h2标题元素，显示登录文本（支持国际化）
          return <h2 class="text-2xl font-bold text-center w-[100%]">{t('login.login')}</h2>
        }
      }
    }
  },
  {
    field: 'username',
    label: t('login.username'),
    // value: 'admin',
    component: 'Input',
    colProps: {
      span: 24
    },
    componentProps: {
      placeholder: 'admin or test'
    }
  },
  {
    field: 'password',
    label: t('login.password'),
    // value: 'admin',
    component: 'InputPassword',
    colProps: {
      span: 24
    },
    componentProps: {
      style: {
        width: '100%'
      },
      placeholder: 'admin or test'
    }
  },
  {
    // 记住我和忘记密码工具区域
    field: 'tool',
    colProps: {
      span: 24
    },
    formItemProps: {
      // 使用插槽返回复合组件
      slots: {
        default: () => {
          return (
            <>
              <div class="flex justify-between items-center w-[100%]">
                <ElCheckbox v-model={remember.value} label={t('login.remember')} size="small" />
                <ElLink type="primary" underline={false}>
                  {t('login.forgetPassword')}
                </ElLink>
              </div>
            </>
          )
        }
      }
    }
  },
  {
    // 登录和注册按钮区域
    field: 'login',
    colProps: {
      span: 24
    },
    formItemProps: {
      // 使用插槽返回按钮
      slots: {
        default: () => {
          return (
            <>
              <div class="w-[100%]">
                {/* 登录按钮，点击时调用signIn方法 */}
                <BaseButton
                  // 绑定加载状态
                  loading={loading.value}
                  type="primary"
                  class="w-[100%]"
                  onClick={signIn}
                >
                  {t('login.login')}
                </BaseButton>
              </div>
              <div class="w-[100%] mt-15px">
                <BaseButton class="w-[100%]" onClick={toRegister}>
                  {t('login.register')}
                </BaseButton>
              </div>
            </>
          )
        }
      }
    }
  },
  {
    // 分割线，用于分隔登录选项和其他登录方法
    field: 'other',
    component: 'Divider',
    label: t('login.otherLogin'),
    componentProps: {
      contentPosition: 'center'
    }
  },
  {
    // 其他登录方式图标区域
    field: 'otherIcon',
    colProps: {
      span: 24
    },
    formItemProps: {
      // 使用插槽返回一组图标
      slots: {
        default: () => {
          return (
            <>
              <div class="flex justify-between w-[100%]">
                <Icon
                  icon="ant-design:github-filled"
                  size={iconSize}
                  class="cursor-pointer ant-icon"
                  color={iconColor}
                  hoverColor={hoverColor}
                />
                <Icon
                  icon="ant-design:wechat-filled"
                  size={iconSize}
                  class="cursor-pointer ant-icon"
                  color={iconColor}
                  hoverColor={hoverColor}
                />
                <Icon
                  icon="ant-design:alipay-circle-filled"
                  size={iconSize}
                  color={iconColor}
                  hoverColor={hoverColor}
                  class="cursor-pointer ant-icon"
                />
                <Icon
                  icon="ant-design:weibo-circle-filled"
                  size={iconSize}
                  color={iconColor}
                  hoverColor={hoverColor}
                  class="cursor-pointer ant-icon"
                />
              </div>
            </>
          )
        }
      }
    }
  }
])

// 图标大小
const iconSize = 30

// 是否记住密码
const remember = ref(userStore.getRememberMe)

const initLoginInfo = () => {
  const loginInfo = userStore.getLoginInfo
  if (loginInfo) {
    const { username, password } = loginInfo
    setValues({ username, password })
  }
}

// 在组件挂载后初始化登录信息
onMounted(() => {
  initLoginInfo()
})

// 使用自定义表单hook
const { formRegister, formMethods } = useForm()
const { getFormData, getElFormExpose, setValues } = formMethods

// 表单提交时的加载状态
const loading = ref(false)

// 图标默认颜色
const iconColor = '#999'

// 图标悬停颜色
const hoverColor = 'var(--el-color-primary)'

// 登录后重定向的路径
const redirect = ref<string>('')

// 监视当前路由变化，设置重定向路径
watch(
  () => currentRoute.value,
  (route: RouteLocationNormalizedLoaded) => {
    redirect.value = route?.query?.redirect as string
  },
  {
    // 立即执行
    immediate: true
  }
)

// 登录
// 登录函数，处理表单提交逻辑
const signIn = async () => {
  const formRef = await getElFormExpose()
  await formRef?.validate(async (isValid) => {
    if (isValid) {
      loading.value = true
      const formData = await getFormData<UserType>()

      try {
        const res = await loginApi(formData)
        console.log(res)
        if (res) {
          // 是否记住我
          if (unref(remember)) {
            userStore.setLoginInfo({
              username: formData.username,
              password: formData.password
            })
          } else {
            userStore.setLoginInfo(undefined)
          }
          userStore.setRememberMe(unref(remember))
          userStore.setUserInfo(res.data)
          // 是否使用动态路由
          if (appStore.getDynamicRouter) {
            getRole()
          } else {
            await permissionStore.generateRoutes('static').catch(() => {})
            permissionStore.getAddRouters.forEach((route) => {
              addRoute(route as RouteRecordRaw) // 动态添加可访问路由表
            })
            permissionStore.setIsAddRouters(true)
            push({ path: redirect.value || permissionStore.addRouters[0].path })
          }
        }
      } finally {
        loading.value = false
      }
    }
  })
}

// 获取角色信息
const getRole = async () => {
  const formData = await getFormData<UserType>()
  const params = {
    userName: formData.username
  }
  const res = 
    appStore.getDynamicRouter && appStore.getServerDynamicRouter
      ? await queryMenusByUsernameApi(params)
      : await queryMenusByUsernameApi(params)
  if (res) {
    const routers = res.data || []
    userStore.setRoleRouters(routers)
    appStore.getDynamicRouter && appStore.getServerDynamicRouter
      ? await permissionStore.generateRoutes('server', routers).catch(() => {})
      : await permissionStore.generateRoutes('frontEnd', routers).catch(() => {})

    permissionStore.getAddRouters.forEach((route) => {
      addRoute(route as RouteRecordRaw) // 动态添加可访问路由表
    })
    permissionStore.setIsAddRouters(true)
    push({ path: redirect.value || permissionStore.addRouters[0].path })
  }
}

// 去注册页面
const toRegister = () => {
  emit('to-register')
}
</script>

<template>
  <Form
    :schema="schema"
    :rules="rules"
    label-position="top"
    hide-required-asterisk
    size="large"
    class="dark:(border-1 border-[var(--el-border-color)] border-solid)"
    @register="formRegister"
  />
</template>
