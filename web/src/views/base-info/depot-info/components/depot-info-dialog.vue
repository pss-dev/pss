<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-form
      :model="depotData"
      ref="depotData"
      :rules="rules"
      label-width="100px"
      class="demo-dynamic"
    >
      <el-form-item label="名称" prop="name">
        <el-input v-model="depotData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="分支" prop="branch">
        <el-input readonly v-model="depotData.branch.name" autocomplete="off">
          <el-button size="small" @click="showBranchDialog()" slot="append" icon="el-icon-search"></el-button>
        </el-input>
      </el-form-item>
      <el-form-item label="拼音码" prop="initials">
        <el-input v-model="depotData.initials" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <dialog-footer @closeDialog="closeDialog" @submitData="submitData"></dialog-footer>
    <branch-search-dialog
      @closeDialog="closeBranchDialog"
      @submitData="submitBranchData"
      v-if="branchDialogVisible"
    ></branch-search-dialog>
  </el-dialog>
</template>

<script>
import BranchSearchDialog from "../../../components/branch-search-dialog"
import BaseInfoDialogFooter from "@/views/base-info/components/base-info-dialog-footer.vue"

export default {
  name: "depotInfoDialog",
  components: {
    "branch-search-dialog": BranchSearchDialog,
    "dialog-footer": BaseInfoDialogFooter
  },

  props: {
    "title": {
      type: String
    },
    "depotData": {
      type: Object
    },
  },

  data () {
    return {
      dialogVisible: true,
      branchDialogVisible: false,
      rules: {
        name: [
          { required: true, message: '请输仓库名称', trigger: 'blur' }
        ],
        branch: [
          { required: true, message: '请选择分支机构', trigger: 'blur' }
        ]
      },
    }
  },

  methods: {
    closeDialog () {
      console.log(this.form);
      this.$emit('closeDialog');
    },

    handleClose () {
      this.$emit('closeDialog')
    },

    submitData () {
      this.$refs['depotData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.depotData);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    showBranchDialog () {
      this.branchDialogVisible = true;
    },

    closeBranchDialog () {
      this.branchDialogVisible = false;
    },

    submitBranchData (value) {
      this.depotData.branch = value;

      this.branchDialogVisible = false;
    }
  },

  created: function () {
  }
}
</script>

<style>
</style>
