<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose">
      <el-form :model="priceData" ref="priceData" :rules="rules" class="demo-ruleForm">
        <el-form-item label="名称" prop="priceLabel" :label-width="formLabelWidth">
          <el-input v-model="priceData.name" autocomplete="off"></el-input>
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
    name: "priceInfoDialog",
    data() {
      return {
        dialogVisible: true,
        oldID: "",
        formLabelWidth: '120px',
        rules: {
          priceLabel: [
            { required: true, message: '请输价格名称', trigger: 'blur' }
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
        this.$refs['priceData'].validate((valid) => {
          if (valid) {
            this.$emit("submitData", this.priceData);
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    },

    props: ["title", "priceData"],

    created: function() {
    }
  }
</script>

<style>
    
</style>
