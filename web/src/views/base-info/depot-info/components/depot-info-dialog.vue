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
      <el-form-item label="名称">
        <el-input v-model="depotData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="分支">
        <el-input readonly v-model="depotData.branch.name" autocomplete="off">
          <el-button size="small" @click="showBranchDialog()" slot="append" icon="el-icon-search"></el-button>
        </el-input>
      </el-form-item>
      <el-form-item label="拼音码">
        <el-input v-model="depotData.initials" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </el-form-item>
    </el-form>
    <branch-search-dialog
      @closeDialog="closeBranchDialog"
      @submitData="submitBranchData"
      v-if="branchDialogVisible"
    ></branch-search-dialog>
  </el-dialog>
</template>

<script>
import BranchSearchDialog from "../../../components/branch-search-dialog"

export default {
  name: "depotInfoDialog",
  components: {
    "branch-search-dialog": BranchSearchDialog
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
      formLabelWidth: '120px',
      rules: {
        id: [
          { required: true, message: '请输仓库编号', trigger: 'blur' }
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
          this.$emit("submitData", this.depotData, this.oldID);
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
