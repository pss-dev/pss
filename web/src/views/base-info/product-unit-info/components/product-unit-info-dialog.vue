<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
    <el-form :model="productUnitData" ref="productUnitData" :rules="rules" class="demo-ruleForm">
      <el-form-item label="单位编号" prop="id" :label-width="formLabelWidth">
        <el-input v-model="productUnitData.id" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="单位名称" :label-width="formLabelWidth">
        <el-input v-model="productUnitData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="备注" :label-width="formLabelWidth">
        <el-input v-model="productUnitData.note" autocomplete="off"></el-input>
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
  name: "productUnitInfoDialog",

  props: {
    "title": {
      type: String
    },
    "productUnitData": {
      type: Object
    }
  },

  data () {
    return {
      dialogVisible: true,
      oldID: "",
      formLabelWidth: '120px',
      rules: {
        id: [
          { required: true, message: '请输单位编号', trigger: 'blur' }
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
      this.$refs['productUnitData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.productUnitData, this.oldID);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  },

  created: function () {
    if (this.productUnitData) {
      this.oldID = this.productUnitData.id;
    }
  }
}
</script>

<style>
</style>
