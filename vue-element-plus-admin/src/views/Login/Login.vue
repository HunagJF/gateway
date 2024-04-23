<script setup lang="ts">
// 导入登录和注册表单组件
import { LoginForm, RegisterForm } from './components'
// 导入主题切换组件
import { ThemeSwitch } from '@/components/ThemeSwitch'
// 导入语言选择下拉菜单组件
import { LocaleDropdown } from '@/components/LocaleDropdown'
// 导入国际化Hook
import { useI18n } from '@/hooks/web/useI18n'
// 导入工具函数
import { getCssVar, underlineToHump } from '@/utils'
// 导入Vuex的app模块
import { useAppStore } from '@/store/modules/app'
// 导入设计相关的Hook
import { useDesign } from '@/hooks/web/useDesign'
import { ref } from 'vue'
// 导入Element Plus的滚动条组件
import { ElScrollbar } from 'element-plus'
// 使用设计Hook获取样式前缀函数
const { getPrefixCls } = useDesign()
// 定义样式的前缀
const prefixCls = getPrefixCls('login')
// 获取app模块的实例
const appStore = useAppStore()
// 使用国际化Hook，获取翻译函数
const { t } = useI18n()
// 定义一个响应式变量来控制显示登录或注册表单
const isLogin = ref(true)

// 注册页面切换到登录页
const toRegister = () => {
  isLogin.value = false
}

// 登录页面切换到注册页
const toLogin = () => {
  isLogin.value = true
}

// 主题颜色改变时的处理函数
const themeChange = () => {
  const color = getCssVar('--el-bg-color')
  appStore.setMenuTheme(color)
  appStore.setHeaderTheme(color)
}
</script>

<template>
  <!-- 页面布局基础样式 -->
  <div
    :class="prefixCls"
    class="h-[100%] relative lt-xl:bg-[var(--login-bg-color)] lt-sm:px-10px lt-xl:px-10px lt-md:px-10px"
  >
    <ElScrollbar class="h-full">
      <!-- 使用Element Plus滚动条组件包裹内容 -->
      <div class="relative flex mx-auto min-h-100vh">
        <!-- 页面内容区 -->
        <!-- 左侧信息展示区域 -->
        <div
          :class="`${prefixCls}__left flex-1 bg-gray-500 bg-opacity-20 relative p-30px lt-xl:hidden`"
        >
          <!-- 应用logo和标题 -->
          <div class="flex items-center relative text-white">
            <img src="@/assets/imgs/logo.png" alt="" class="w-48px h-48px mr-10px" />
            <span class="text-20px font-bold">{{ underlineToHump(appStore.getTitle) }}</span>
          </div>
          <!-- 登录/注册表单动画区域 -->
          <div class="flex justify-center items-center h-[calc(100%-60px)]">
            <!-- 使用动画效果切换展示的图片和文字 -->
            <TransitionGroup
              appear
              tag="div"
              enter-active-class="animate__animated animate__bounceInLeft"
            >
              <img src="@/assets/svgs/login-box-bg.svg" key="1" alt="" class="w-350px" />
              <div class="text-3xl text-white" key="2">{{ t('login.welcome') }}</div>
              <div class="mt-5 font-normal text-white text-14px" key="3">
                {{ t('login.message') }}
              </div>
            </TransitionGroup>
          </div>
        </div>
        <!-- 右侧登录/注册表单展示区 -->
        <div class="flex-1 p-30px lt-sm:p-10px dark:bg-[var(--login-bg-color)] relative">
          <!-- 顶部的logo、主题切换和语言选择 -->
          <div
            class="flex justify-between items-center text-white at-2xl:justify-end at-xl:justify-end"
          >
            <div class="flex items-center at-2xl:hidden at-xl:hidden">
              <img src="@/assets/imgs/logo.png" alt="" class="w-48px h-48px mr-10px" />
              <span class="text-20px font-bold">{{ underlineToHump(appStore.getTitle) }}</span>
            </div>

            <div class="flex justify-end items-center space-x-10px">
              <ThemeSwitch @change="themeChange" />
              <LocaleDropdown class="lt-xl:text-white dark:text-white" />
            </div>
          </div>
          <!-- 包裹登录和注册表单，切换表单时使用动画效果 -->
          <Transition appear enter-active-class="animate__animated animate__bounceInRight">
            <div
              class="h-auto flex items-center m-auto w-[100%] at-2xl:max-w-500px at-xl:max-w-500px at-md:max-w-500px at-lg:max-w-500px"
            >
              <LoginForm
                v-if="isLogin"
                class="p-20px h-auto m-auto lt-xl:rounded-3xl lt-xl:light:bg-white"
                @to-register="toRegister"
              />
              <RegisterForm
                v-else
                class="p-20px h-auto m-auto lt-xl:rounded-3xl lt-xl:light:bg-white"
                @to-login="toLogin"
              />
            </div>
          </Transition>
        </div>
      </div>
    </ElScrollbar>
  </div>
</template>

<style lang="less" scoped>
@prefix-cls: ~'@{namespace}-login';

.@{prefix-cls} {
  overflow: auto;

  &__left {
    &::before {
      position: absolute;
      top: 0;
      left: 0;
      z-index: -1;
      width: 100%;
      height: 100%;
      background-image: url('@/assets/svgs/login-bg.svg');
      background-position: center;
      background-repeat: no-repeat;
      content: '';
    }
  }
}
</style>
