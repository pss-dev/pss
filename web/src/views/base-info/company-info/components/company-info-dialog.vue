<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-form
      :model="companyData"
      ref="companyData"
      :rules="rules"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="名称" prop="name">
        <el-input v-model="companyData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="拼音码" prop="initials">
        <el-input v-model="companyData.initials" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="companyData.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话" prop="telephone">
        <el-input v-model="companyData.telephone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="联系人" prop="contactPerson">
        <el-input v-model="companyData.contactPerson" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="联系人电话" prop="contactPhone">
        <el-input v-model="companyData.contactPhone" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <dialog-footer @closeDialog="closeDialog" @submitData="submitData"></dialog-footer>
  </el-dialog>
</template>

<script>
import BaseInfoDialogFooter from "@/views/base-info/components/base-info-dialog-footer.vue"

export default {
  name: "companyInfoDialog",
  components: {
    "dialog-footer": BaseInfoDialogFooter
  },
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
      rules: {
        name: [
          { required: true, message: '请入名称', trigger: 'blur' }
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
