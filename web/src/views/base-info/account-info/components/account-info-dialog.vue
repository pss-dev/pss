<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-form
      :model="accountData"
      ref="accountData"
      :rules="rules"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="账户名称" prop="name">
        <el-input v-model="accountData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="开户行" prop="bank">
        <el-input v-model="accountData.bank" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="持卡人" prop="cardholder">
        <el-input v-model="accountData.cardholder" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="卡号" prop="account">
        <el-input v-model="accountData.account" autocomplete="off"></el-input>
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
  name: "accountInfoDialog",
  props: {
    "title": {
      type: String
    },
    "accountData": {
      type: Object
    },
  },

  data () {
    return {
      dialogVisible: true,
      rules: {
        name: [
          { required: true, message: '请输入账户名称', trigger: 'blur' }
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
      this.$refs['accountData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.accountData);
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
