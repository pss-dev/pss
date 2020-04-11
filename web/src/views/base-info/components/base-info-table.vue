<template class="c1">
  <el-table
    :data="tableData"
    border
    highlight-current-row
    @current-change="handleCurrentChange"
    @row-dblclick="doubleClick"
    height="400"
    style="width: 100%"
  >
    <el-table-column v-for="title in titles" v-bind="title" :key="title.prop" min-width="100px"></el-table-column>
  </el-table>
</template>
  
  <script>
export default {
  name: "baseInfoTabler",

  props: {
    "titles": {
      type: Array
    },
    "tableData": {
      type: Array
    },
  },

  data () {
    return {
    }
  },

  methods: {
    handleCurrentChange (value) {
      this.$emit('handleCurrentChange', value);
    },

    doubleClick (value) {
      this.$emit('getChildData', value);
    },

    edit (value) {
      this.$emit('edit', value);
    }
  },
}
  </script>

<style>
.c1 {
  width: 200px;
}
</style>
