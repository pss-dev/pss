<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose">
      <el-form :model="depotData" ref="depotData" :rules="rules" class="demo-ruleForm">
        <el-form-item label="编号" prop="depotID" :label-width="formLabelWidth">
          <el-input v-model="depotData.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="depotData.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分支" :label-width="formLabelWidth">
          <el-input v-model="depotData.branch" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="拼音码" :label-width="formLabelWidth">
          <el-input v-model="depotData.initials" autocomplete="off"></el-input>
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
    name: "depotInfoDialog",
    data() {
      return {
        dialogVisible: true,
        oldID: "",
        formLabelWidth: '120px',
        rules: {
          depotID: [
            { required: true, message: '请输仓库编号', trigger: 'blur' }
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
        this.$refs['depotData'].validate((valid) => {
          if (valid) {
            this.$emit("submitData", this.depotData, this.oldID);
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    },

    props: ["title", "depotData"],

    created: function() {
      if(this.depotData) {
        this.oldID = this.depotData.id;
      }
    }
  }
</script>

<style>
    
</style>
