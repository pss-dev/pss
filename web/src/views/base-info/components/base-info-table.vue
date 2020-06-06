<template class="c1">
  <div>
    <el-table
      :data="tableData.slice((currentPage-1) * pageSize, currentPage * pageSize)"
      border
      highlight-current-row
      @current-change="handleCurrentChange"
      @row-dblclick="doubleClick"
      height="400"
      style="width: 100%"
    >
      <el-table-column v-for="title in titles" v-bind="title" :key="title.prop" min-width="100px"></el-table-column>
    </el-table>

    <el-pagination
      class="pagination"
      @size-change="handleSizeChange"
      @current-change="handlePageChange"
      :small="true"
      :current-page="currentPage"
      :page-sizes="[30, 50, 100, 200]"
      layout="sizes, jumper, prev, next, total"
      :total="totalSize"
    ></el-pagination>
  </div>
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
    "totalSize": {
      type: Number
    },
    "currentPage": {
      type: Number,
      default: 1,
    },
  },

  data () {
    return {
      pageSize: 30,
    }
  },

  methods: {
    handleCurrentChange (value) {
      this.$emit('handleCurrentChange', value);
    },

    doubleClick (value) {
      this.$emit('getChildData', value);
    },

    handlePageChange (value) {
      this.$emit('pageChange', value);
    },

    handleSizeChange (value) {
      this.pageSize = value;
    },
  },
}
</script>

<style>
.c1 {
  width: 200px;
}
.pagination {
  margin-top: 5px;
}
</style>
