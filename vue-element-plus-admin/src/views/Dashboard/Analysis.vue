<template>
  <div class="scroll-container">
    <!-- 左滚动按钮 -->
    <button class="scroll-button left" @click="scrollLeft">‹</button>
    <div class="horizontal-scroll" ref="scrollContainer">
      <div class="row-container">
        <el-row gutter={20} class="row">
          <el-col v-for="(box, index) in visibleBoxes" :key="index" :span="6" class="box">
            <div class="box-content">
              <h1> {{ box.name }} </h1>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <!-- 右滚动按钮 -->
    <button class="scroll-button right" @click="scrollRight">›</button>
  </div>
</template>

<script setup lang="tsx">
import { ref, unref, computed, onMounted } from 'vue'
import { queryHomePageAppApi } from '@/api/app'

// 引用滚动容器
const scrollContainer = ref<HTMLDivElement | null>(null);
const currentIndex = ref(0);

// 数据源，可以根据需要调整
const boxes = ref<any>([])

// 获取数据的函数
const fetchData = async () => {
  const response = await queryHomePageAppApi()
  console.log(response.data)
  boxes.value = response.data
};

// 计算当前显示的容器
const visibleBoxes = computed(() => {
  return boxes.value.slice(unref(currentIndex), unref(currentIndex) + 6);
});

// 左滚动函数
const scrollLeft = () => {
  if (unref(currentIndex) > 0) {
    currentIndex.value -= 1;
  }
}

// 右滚动函数
const scrollRight = () => {
  if (unref(currentIndex) < unref(boxes).length - 6) {
    currentIndex.value += 1;
  }
}

// 组件挂载时获取数据
onMounted(() => {
  fetchData()
})
</script>


<style scoped>
/* 容器样式 */
.scroll-container {
  position: relative;
  width: 100%;
  display: flex;
  align-items: center;
}

/* 滚动按钮样式 */
.scroll-button {
  position: absolute;
  z-index: 10;
  background-color: rgba(0, 0, 0, 0.5);
  /* 半透明背景 */
  color: white;
  border: none;
  font-size: 2rem;
  /* 字体大小 */
  cursor: pointer;
  padding: 10px;
  top: 50%;
  transform: translateY(-50%);
}

.scroll-button.left {
  left: 0;
}

.scroll-button.right {
  right: 0;
}

/* 内层水平滚动容器 */
.horizontal-scroll {
  width: 100%;
  /* 设置容器宽度 */
  overflow-x: auto;
  /* 启用水平滚动条 */
  white-space: nowrap;
  /* 防止内容换行 */
  background-image: url('@/assets/Group.png');
  /* 使用 Group.png 作为背景 */
  background-size: cover;
  /* 宽度拉伸至整个屏幕，高度保持不变 */
  background-repeat: no-repeat;
  /* 禁止背景图片重复 */
  background-position: center;
  /* 居中对齐背景图片 */
  scrollbar-width: none;
  /* 隐藏 Firefox 的滚动条 */
}

.horizontal-scroll::-webkit-scrollbar {
  display: none;
  /* 隐藏 Chrome, Safari 和 Opera 的滚动条 */
}

/* 行容器样式 */
.row-container {
  display: flex;
  justify-content: center;
  width: 100%;
}

/* 行样式 */
.row {
  display: inline-flex;
  /* 使子元素横向排列 */
  width: max-content;
  /* 确保所有子元素在一行内显示 */
  width: max-content;
  /* 确保所有子元素在一行内显示 */
}

/* 盒子样式 */
.box {
  /* 使用本地图片作为背景 */
  background-image: url('@/assets/app-item-bg.png');
  background-size: contain;
  /* 根据盒子大小自适应缩放背景图片 */
  background-repeat: no-repeat;
  /* 禁止背景图片重复 */
  background-position: center;
  /* 居中对齐背景图片 */
  padding: 20px;
  /* 设置内边距 */
  margin: 20px;
  /* 设置外边距 */
  text-align: center;
  /* 文本居中 */
  min-width: 25vh;
  /* 设置最小宽度 */
  height: 80vh;
  /* 设置高度为视口高度的80% */
  box-sizing: border-box;
  /* 包括内边距和边框 */
}

/* 盒子内容样式 */
.box-content {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>
