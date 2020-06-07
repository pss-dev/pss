<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-form
      :model="departmentData"
      ref="departmentData"
      :rules="rules"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="部门名称" prop="name">
        <el-input v-model="departmentData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="拼音码" prop="initials">
        <el-input v-model="departmentData.initials" autocomplete="off"></el-input>
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
  name: "departmentInfoDialog",

  props: {
    "title": {
      type: String
    },
    "departmentData": {
      type: Object
    }
  },

  data () {
    return {
      dialogVisible: true,
      rules: {
        name: [
          { required: true, message: '请输部门名称', trigger: 'blur' }
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
      this.$refs['departmentData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.departmentData, this.oldID);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  },

  created: function () {
  }
}
</script>

<style>
</style>
