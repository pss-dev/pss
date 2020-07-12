<template>
  <header class="index-header">
    <div class="info">
      <div class="header-left">
        <span>进销存logo</span>
        <span>进销存v0.1</span>
      </div>
      <div class="header-right">
        <el-dropdown>
          <span class="el-icon-user-solid">{{userName}}</span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item onclick="location.href='/logout'">登出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: "IndexHeader",
  props: {
    "userName": {
      type: String,
    }
  },
  data () {
    return {};
  },
  methods: {}
};
</script>

<style lang="scss" scoped>
.index-header {
  margin-top: 30px;
  padding-right: 20px;
  min-height: 60px;
  border-bottom: 1px solid #999;
  .info {
    display: flex;
    align-items: center;
    padding: 10px 0;
  }
  .header-left {
    display: flex;
    padding: 0 15px;
    justify-content: space-between;
    width: 80%;
    text-align: center;
  }
  .header-right {
    width: 20%;
    text-align: right;
    cursor: pointer;
  }
  .nav {
    padding: 10px 0;
  }
}
</style>
