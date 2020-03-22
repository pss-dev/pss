<template class="c1">
  <el-table
      :data="tableData"
      border
      highlight-current-row
      @current-change="handleCurrentChange"
      @row-dblclick="doubleClick"
      height="400"
      style="width: 100%">
      <el-table-column
        v-for="title in titles"
        v-bind="title"
        :key="title.prop"
        min-width="100px">
      </el-table-column>

      <el-table-column
        v-if="showEdit"
        fixed="right"
        label="操作"
        width="80px">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row)" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
  </template>
  
  <script>
    export default {
      name: "baseInfoTabler",

      data() {
        return {
        }
      },

      methods: {
        handleCurrentChange(value) {
          this.$emit('handleCurrentChange', value);
        },

        doubleClick(value) {
          this.$emit('getChildData', value);
        },

        edit(value) {
          this.$emit('edit', value);
        }
      },

      props: ["titles", "tableData", "showEdit"]
    }
  </script>

<style>
  .c1{
    width: 200px;
  }
</style>
