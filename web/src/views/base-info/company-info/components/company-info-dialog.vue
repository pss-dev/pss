<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
    <el-form :model="companyData" ref="companyData" :rules="rules" class="demo-ruleForm">
      <el-form-item label="名称" :label-width="formLabelWidth">
        <el-input v-model="companyData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="拼音码" :label-width="formLabelWidth">
        <el-input v-model="companyData.initials" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址" :label-width="formLabelWidth">
        <el-input v-model="companyData.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="联系人" :label-width="formLabelWidth">
        <el-input v-model="companyData.contactPerson" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="联系人电话" :label-width="formLabelWidth">
        <el-input v-model="companyData.contactPhone" autocomplete="off"></el-input>
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
  name: "companyInfoDialog",

  props: {
    "title": {
      type: String
    },
    "companyData": {
      type: Object
    }
  },

  data () {
    return {
      dialogVisible: true,
      formLabelWidth: '120px',
      rules: {
        id: [
          { required: true, message: '请输编号', trigger: 'blur' }
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
      this.$refs['companyData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.companyData);
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
