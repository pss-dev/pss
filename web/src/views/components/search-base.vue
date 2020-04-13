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
      @row-dblclick="getChildData"
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
      :page-sizes="[30, 50, 100, 200]"
      layout="sizes, jumper, prev, next, total"
      :total="totalSize"
    ></el-pagination>

    <el-button @click="previous" size="small" :disabled="previousDisable">上一层</el-button>
    <el-button @click="getChildData" size="small" :disabled="previousDisable">下一层</el-button>
    <el-button @click="ok" size="small">确定</el-button>
    <el-button @click="close" size="small">关闭</el-button>
  </div>
</template>
  
<script>
export default {
  name: "searchBase",

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
    "previousDisable": {
      type: Boolean,
      default: true,
    },
    "nextDisable": {
      type: Boolean,
      default: true,
    },
  },


  data () {
    return {
      search: '',

      pageSize: 30,
    }
  },

  methods: {
    handleCurrentChange (value) {
      this.$emit('handleCurrentChange', value)
    },

    handlePageChange (value) {
      this.$emit('pageChange', value)
    },

    handleSizeChange (value) {
      this.pageSize = value;
    },

    getChildData (value) {
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
}
  </script>

<style>
.c1 {
  width: 200px;
}
</style>
