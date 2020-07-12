<template class="c1">
  <el-container>
    <el-header v-if="tableHeaderVisiable" height="30">
      <base-info-header :paths="paths" @showTypeChange="showTypeChange"></base-info-header>
    </el-header>
    <el-main>
      <base-info-table
        :titles="titles"
        :tableData="tableData"
        @handleCurrentChange="handleCurrentChange"
        @getChildData="getChildData"
        @pageChange="handlePageChange"
        @pageSizeChange="handleSizeChange"
      ></base-info-table>
    </el-main>
    <div class="footer">
      <el-button
        v-if="previousVisiable"
        @click="previous"
        size="small"
        :disabled="previousDisable"
      >上一层</el-button>
      <el-button
        v-if="nextVisiable"
        @click="getChildData"
        size="small"
        :disabled="previousDisable"
      >下一层</el-button>
      <el-button @click="ok" size="small" type="primary">确定</el-button>
      <el-button @click="close" size="small">关闭</el-button>
    </div>
  </el-container>
</template>
  
<script>
import BaseInfoHeader from "../base-info/components/base-info-header.vue"
import BaseInfoTabler from "../base-info/components/base-info-table.vue"

export default {
  name: "searchBase",

  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
  },

  props: {
    "titles": {
      type: Array
    },
    "tableData": {
      type: Array
    },
    "totalSize": {
      type: Number,
      default: 1
    },
    "currentPage": {
      type: Number,
      default: 1
    },
    "tableHeaderVisiable": {
      type: Boolean,
      default: true,
    },
    "previousVisiable": {
      type: Boolean,
      default: true,
    },
    "previousDisable": {
      type: Boolean,
      default: true,
    },
    "nextDisable": {
      type: Boolean,
      default: true,
    },
    "nextVisiable": {
      type: Boolean,
      default: true,
    },
    "paths": {
      type: String,
      default: ""
    }
  },


  data () {
    return {
      search: '',
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
      this.$emit('pageSizeChange', value);
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

    showTypeChange () {
      this.$emit('showTypeChange');
    }
  },
}
  </script>

<style>
.c1 {
  width: 200px;
}

.footer {
  height: 30px;
  text-align: center;
}
</style>
