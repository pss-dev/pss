<template class="c1">
  <div>
    <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />

    <el-table
      :data="search ? tableData.filter(data =>
      data.id.toLowerCase().includes(search.toLowerCase()) ||
      data.name.toLowerCase().includes(search.toLowerCase())): 
      tableData.slice((currentPage-1) * pageSize, currentPage * pageSize)"
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
      @size-change="handleSizeChange"
      @current-change="handlePageChange"
      :small="true"
      :current-page="currentPage"
      :page-sizes="[1, 2, 3, 4]"
      layout="sizes, jumper, prev, next, total"
      :total="totalSize"
    ></el-pagination>

    <el-button @click="previous" size="small" :disabled="previousDisable">上一层</el-button>
    <el-button @click="ok" size="small">确定</el-button>
    <el-button @click="close" size="small">关闭</el-button>
  </div>
</template>
  
  <script>
export default {
  name: "searchBase",

  data () {
    return {
      search: '',

      currentPage: 1,
      pageSize: 1,
    }
  },

  methods: {
    handleCurrentChange (value) {
      this.$emit('handleCurrentChange', value)
    },

    handlePageChange (value) {
      this.currentPage = value;
    },

    handleSizeChange (value) {
      this.pageSize = value;
    },

    doubleClick (value) {
      this.$emit('getChildData', value);
    },

    ok () {
      this.$emit('ok');
    },

    close () {
      this.$emit('close');
    },

    previous () {
      this.$emit('previous');
    },
  },

  props: ["titles", "tableData", "totalSize", "previousDisable"]
}
  </script>

<style>
.c1 {
  width: 200px;
}
</style>
