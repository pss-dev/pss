<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
    <el-form :model="UnitData" ref="UnitData" :rules="rules" class="demo-ruleForm">
      <el-form-item label="单位名称" :label-width="formLabelWidth">
        <el-input v-model="unitData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="备注" :label-width="formLabelWidth">
        <el-input v-model="unitData.note" autocomplete="off"></el-input>
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
  name: "unitInfoDialog",

  props: {
    "title": {
      type: String
    },
    "unitData": {
      type: Object
    }
  },

  data () {
    return {
      dialogVisible: true,
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
      this.$refs['unitData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.unitData, this.oldID);
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
