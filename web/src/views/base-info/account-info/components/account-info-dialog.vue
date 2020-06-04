<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-form :model="accountData" ref="accountData" :rules="rules" class="demo-ruleForm">
      <el-form-item label="账户名称" :label-width="formLabelWidth">
        <el-input v-model="accountData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="开户行" :label-width="formLabelWidth">
        <el-input v-model="accountData.bank" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="持卡人" :label-width="formLabelWidth">
        <el-input v-model="accountData.cardholder" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="卡号" :label-width="formLabelWidth">
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
  data () {
    return {
      dialogVisible: true,
      formLabelWidth: '120px',
      rules: {
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

  props: ["title", "accountData"],

  created: function () {
  }
}
</script>

<style>
</style>
