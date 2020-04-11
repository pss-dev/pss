<template>
  <el-container>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handlePageChange"
      :small="true"
      :current-page="currentPage"
      :page-sizes="[30, 50, 100, 200]"
      layout="sizes, jumper, prev, next, total"
      :total="totalSize"
    ></el-pagination>
    <el-button @click="newInfo" size="small">空白新增</el-button>
    <el-button @click="copyNew" size="small" :disabled="selectedInfoInvalid">复制新增</el-button>
    <el-button @click="edit" size="small" :disabled="selectedInfoInvalid">编辑</el-button>
    <el-button @click="deleteInfo" size="small" :disabled="selectedInfoInvalid" type="danger">删除</el-button>
    <el-button @click="previous" size="small" :disabled="previousDisable">上一层</el-button>
    <el-button @click="next" size="small" :disabled="selectedInfoInvalid">下一层</el-button>
  </el-container>
</template>

<script>
export default {
  name: "baseInfoFooter",

  methods: {
    handleSizeChange (value) {
      this.$emit('pageSizeChange', value);
    },

    handlePageChange (value) {
      this.$emit('pageChange', value);
    },

    newInfo () {
      this.$emit('newInfo');
    },

    copyNew () {
      this.$emit('copyNew');
    },

    edit () {
      this.$emit('edit');
    },

    deleteInfo () {
      this.$emit('deleteInfo');
    },

    previous () {
      this.$emit('previous');
    },

    next () {
      this.$emit('next');
    }
  },

  data () {
    return {
    };
  },

  props: {
    "totalSize": {
      type: Number
    },
    "currentPage": {
      type: Number
    },
    "selectedInfoInvalid": {
      type: Boolean,
      default: true
    },
    "previousDisable": {
      type: Boolean,
      default: true
    }  }
}
</script>

<style>
</style>
