<template>
  <div class="horizontal-scroll">
    <el-row gutter={20} class="row">
      <el-col :span="6" class="box">Box 1</el-col>
      <el-col :span="6" class="box">Box 2</el-col>
      <el-col :span="6" class="box">Box 3</el-col>
      <el-col :span="6" class="box">Box 4</el-col>
      <el-col :span="6" class="box">Box 5</el-col>
    </el-row>
  </div>
</template>

<script setup lang="tsx">

</script>

<style scoped>
.horizontal-scroll {
  width: 100%;
  overflow-x: auto;
  white-space: nowrap;
}
.row {
  display: inline-flex;
  width: max-content; /* 保证子元素在一行内 */
}
.box {
  background-color: #f2f2f2;
  padding: 20px;
  margin: 10px;
  text-align: center;
  min-width: 100px; /* 设置最小宽度 */
}
</style>