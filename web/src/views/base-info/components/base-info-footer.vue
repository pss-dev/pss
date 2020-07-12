<template>
  <div class="base-info-footer">
    <el-button v-if="newInfoVisiable" @click="newInfo" size="small">空白新增</el-button>
    <el-button
      v-if="copyNewVisiable"
      @click="copyNew"
      size="small"
      :disabled="selectedInfoInvalid"
    >复制新增</el-button>
    <el-button v-if="editVisiable" @click="edit" size="small" :disabled="selectedInfoInvalid">编辑</el-button>
    <el-button
      v-if="deleteInfoVisiable"
      @click="deleteInfo"
      size="small"
      :disabled="selectedInfoInvalid"
      type="danger"
    >删除</el-button>
    <el-button
      v-if="previousVisiable"
      @click="previous"
      size="small"
      :disabled="previousDisable"
    >上一层</el-button>
    <el-button
      v-if="nextVisiable"
      @click="next"
      size="small"
      :disabled="selectedInfoInvalid || stopPurchaseDisable"
    >下一层</el-button>
    <el-button
      v-if="isProduct"
      @click="stopPurchase"
      size="small"
      :disabled="!isSelectedLeaf || stopPurchaseDisable"
    >停止采购</el-button>
  </div>
</template>

<script>
export default {
  name: "baseInfoFooter",

  props: {
    "selectedInfoInvalid": {
      type: Boolean,
      default: true
    },
    "isSelectedLeaf": {
      type: Boolean,
    },
    "previousDisable": {
      type: Boolean,
      default: true
    },
    "newInfoVisiable": {
      type: Boolean,
      default: true
    },
    "copyNewVisiable": {
      type: Boolean,
      default: true
    },
    "editVisiable": {
      type: Boolean,
      default: true
    },
    "deleteInfoVisiable": {
      type: Boolean,
      default: true
    },
    "previousVisiable": {
      type: Boolean,
      default: true
    },
    "nextVisiable": {
      type: Boolean,
      default: true
    },
    "isProduct": {
      type: Boolean,
      default: false
    },
    "stopPurchaseDisable": {
      type: Boolean,
      default: false
    }
  },

  data () {
    return {
    };
  },

  methods: {
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
    },

    stopPurchase () {
      this.$emit('stopPurchase');
    },
  },
}
</script>

<style>
.base-info-footer {
}
</style>
