<template>
  <header class="index-header">
    <div class="header-left">进销存logo</div>
    <div class="header-right">登出</div>
  </header>
</template>

<script>
export default {
  name: 'IndexHeader',
}
</script>

<style lang="scss" scoped>
  .index-header {
    display: flex;
    align-items: center;
    padding-right: 20px;
    height: 60px;
    border-bottom: 1px solid #999;
    .header-left {
      width: 80%;
      text-align: center;
    }
    .header-right {
      width: 20%;
    }
  }  
</style>