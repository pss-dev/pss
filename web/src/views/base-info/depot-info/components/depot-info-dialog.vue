<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
    <el-form
      :model="depotData"
      ref="depotData"
      :rules="rules"
      label-width="100px"
      class="demo-dynamic"
    >
      <el-form-item label="编号" prop="id">
        <el-input v-model="depotData.id" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="名称">
        <el-input v-model="depotData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="分支">
        <el-col :span="21">
          <el-input disabled v-model="depotData.branchName" autocomplete="off"></el-input>
        </el-col>
        <el-col :span="1">
          <el-button @click="showBranchDialog()">...</el-button>
        </el-col>
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
import branchSearchDialog from "../../../components/branch-search-dialog"

export default {
  name: "depotInfoDialog",
  components: {
    "branch-search-dialog": branchSearchDialog
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
      oldID: "",
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
      this.depotData.branchID = value.id;
      this.depotData.branchName = value.name;

      this.branchDialogVisible = false;
    }
  },

  created: function () {
    if (this.depotData) {
      this.oldID = this.depotData.id;
    }
  }
}
</script>

<style>
</style>
