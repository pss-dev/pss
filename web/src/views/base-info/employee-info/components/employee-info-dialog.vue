<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
    <el-form :model="employeeData" ref="employeeData" :rules="rules" class="demo-ruleForm">
      <el-form-item label="员工编号" prop="employeeID" :label-width="formLabelWidth">
        <el-input v-model="employeeData.id" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="员工姓名" :label-width="formLabelWidth">
        <el-input v-model="employeeData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth">
        <el-input v-model="employeeData.passworld" autocomplete="off"></el-input>
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
  name: "employeeInfoDialog",
  data () {
    return {
      dialogVisible: true,
      oldID: "",
      formLabelWidth: '120px',
      rules: {
        employeeID: [
          { required: true, message: '请输账户编号', trigger: 'blur' }
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
      this.$refs['employeeData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.employeeData, this.oldID);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  },

  props: ["title", "employeeData"],

  created: function () {
    if (this.employeeData) {
      this.oldID = this.employeeData.id;
    }
  }
}
</script>

<style>
</style>
