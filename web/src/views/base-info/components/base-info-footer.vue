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
    <el-button v-if="isProduct" 
      :disabled="parentIsNull" 
      @click="generatePrice" 
      size="small">生成价格</el-button>
    <el-button v-if="uploadVisiable" size="small" @click="getDataTemplate">获取数据模板</el-button>
    <el-upload
      v-if="uploadVisiable && !parentIsNull"
      class="upload-button"
      :action="uploadAction"
      :data="uploadData"
      :multiple="false"
      :show-file-list="false"
      :before-upload="beforeUpload"
      :on-success="uploadSuccess"
    >
      <el-button size="small">上传数据</el-button>
    </el-upload>
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
    },
    "parentIsNull": {
      type: Boolean,
      default: false
    },
    "uploadVisiable": {
      type: Boolean,
      default: false
    },
    "uploadAction": {
      type: String
    },
    "uploadData": {
      type: Object
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
      this.$confirm('将执行删除操作, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$emit('deleteInfo');
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
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

    getDataTemplate () {
      this.$emit('getDataTemplate');
    },

    uploadSuccess () {
      this.$message({
        message: "上传成功!",
        showClose: true
      });
    },

    beforeUpload () {
      this.$message({
        message: "开始上传",
        showClose: true
      });
    },

    generatePrice () {
      this.$emit('generatePrice');
    }
  },
}
</script>

<style>
.base-info-footer {
}

.upload-button {
  display: inline-block;
  margin-left: 10px;
}
</style>
