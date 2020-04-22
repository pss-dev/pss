<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
    <el-tabs v-model="activeName">
      <el-tab-pane label="基本资料" name="first">
        <el-form :model="productData" ref="productData" :rules="rules" class="demo-ruleForm">
          <el-form-item label="编号" prop="id" :label-width="formLabelWidth">
            <el-input v-model="productData.id" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="名称" :label-width="formLabelWidth">
            <el-input v-model="productData.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="拼音码" :label-width="formLabelWidth">
            <el-input v-model="productData.initials" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="规格" :label-width="formLabelWidth">
            <el-input v-model="productData.specification" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="型号" :label-width="formLabelWidth">
            <el-input v-model="productData.type" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="产地" :label-width="formLabelWidth">
            <el-input v-model="productData.address" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="单位" name="second"></el-tab-pane>
    </el-tabs>
    <el-button @click="closeDialog">取 消</el-button>
    <el-button type="primary" @click="submitData">确 定</el-button>
  </el-dialog>
</template>

<script>
export default {
  name: "productInfoDialog",
  data () {
    return {
      dialogVisible: true,
      oldID: "",
      activeName: 'first',
      formLabelWidth: '120px',
      rules: {
        id: [
          { required: true, message: '请输部门编号', trigger: 'blur' }
        ]
      },
    }
  },

  methods: {
    closeDialog () {
      console.log("===== ", this.productData);
      this.$emit('closeDialog');
    },

    handleClose () {
      this.$emit('closeDialog')
    },

    submitData () {
      this.$refs['productData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.productData, this.oldID);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  },

  props: ["title", "productData"],

  created: function () {
    if (this.productData) {
      this.oldID = this.productData.productID;
    }
  }
}
</script>

<style>
</style>
