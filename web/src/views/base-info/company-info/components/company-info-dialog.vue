<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose">
      <el-form :model="companyData" ref="companyData" :rules="rules" class="demo-ruleForm">
        <el-form-item label="类型" :label-width="formLabelWidth">
          <el-select v-model="companyData.type" placeholder="类型">
            <el-option :value="0" label="供货商"></el-option>
            <el-option :value="1" label="客户"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="编号" prop="companyID" :label-width="formLabelWidth">
          <el-input v-model="companyData.id" autocomplete="off"></el-input>
        </el-form-item>
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
    data() {
      return {
        dialogVisible: true,
        oldID: "",
        formLabelWidth: '120px',
        rules: {
          companyID: [
            { required: true, message: '请输来往单位编号', trigger: 'blur' }
          ]
        },
      }
    },

    methods:{
      closeDialog() {
        console.log(this.form);
        this.$emit('closeDialog');
      },

      handleClose() {
        this.$emit('closeDialog')
      },

      submitData() {
        this.$refs['companyData'].validate((valid) => {
          if (valid) {
            this.$emit("submitData", this.companyData, this.oldID);
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    },

    props: ["title", "companyData"],

    created: function() {
      if(this.companyData) {
        this.oldID = this.companyData.id;
      }
    }
  }
</script>

<style>
    
</style>
