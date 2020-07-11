<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App"
};
</script>

<style lang="scss">
/*滚动条样式*/
::-webkit-scrollbar {
  width: 4px;
  height: 8px;
}

::-webkit-scrollbar-thumb {
  border-radius: 10px;
  // -webkit-box-shadow: inset 0 0 2px rgba(0, 0, 0, 0.2);
  background: rgba(10, 83, 218, 0.2);
}

::-webkit-scrollbar-track {
  // -webkit-box-shadow: inset 0 0 2px rgba(0, 0, 0, 0.2);
  border-radius: 0;
  background: rgba(10, 83, 218, 0.2);
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  // text-align: center;
  color: #2c3e50;
}
</style>
