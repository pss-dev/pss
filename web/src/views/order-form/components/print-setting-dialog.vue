<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
    append-to-body
  >
    <el-form
      :model="printInfo"
      ref="printInfo"
      :rules="rules"
      label-width="100px"
      class="demo-dynamic"
    >
      <el-form-item label="标题" prop="title">
        <el-input v-model="printInfo.title" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="提示" prop="prompt">
        <el-input v-model="printInfo.prompt" autocomplete="off"></el-input>
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
  name: "printSettingDialog",
  components: {
  },

  props: {
    "printInfo": {
      type: Object
    },
  },

  data () {
    return {
      dialogVisible: true,
      title: "打印信息设置",
      rules: {
        title: [
          { required: true, message: '请输价格名称', trigger: 'blur' }
        ],
        prompt: [
          { required: true, message: '请输价格名称', trigger: 'blur' }
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
      this.$refs['printInfo'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.printInfo);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  },

  created: function () {
  }
}
</script>

<style>
</style>
