<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
    <el-form :model="branchData" ref="branchData" :rules="rules" class="demo-ruleForm">
      <el-form-item label="分支编号" prop="id" :label-width="formLabelWidth">
        <el-input v-model="branchData.id" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="分支名称" :label-width="formLabelWidth">
        <el-input v-model="branchData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="客户" :label-width="formLabelWidth">
        <el-input v-model="branchData.customerName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="供货商" :label-width="formLabelWidth">
        <el-input v-model="branchData.supplierName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="拼音码" :label-width="formLabelWidth">
        <el-input v-model="branchData.initials" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: "branchInfoDialog",
  data () {
    return {
      dialogVisible: true,
      oldID: "",
      formLabelWidth: '120px',
      rules: {
        id: [
          { required: true, message: '请输分支编号', trigger: 'blur' }
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
      this.$refs['branchData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.branchData, this.oldID);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  },

  props: ["title", "branchData"],

  created: function () {
    if (this.branchData) {
      this.oldID = this.branchData.id;
    }
  }
}
</script>

<style>
</style>
