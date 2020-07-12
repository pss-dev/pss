<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-form
      :model="departmentData"
      ref="departmentData"
      :rules="rules"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="部门名称" prop="name">
        <el-input v-model="departmentData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="拼音码" prop="initials">
        <el-input v-model="departmentData.initials" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <dialog-footer @closeDialog="closeDialog" @submitData="submitData"></dialog-footer>
  </el-dialog>
</template>

<script>
import BaseInfoDialogFooter from "@/views/base-info/components/base-info-dialog-footer.vue"

export default {
  name: "departmentInfoDialog",
  components: {
    "dialog-footer": BaseInfoDialogFooter
  },
  props: {
    "title": {
      type: String
    },
    "departmentData": {
      type: Object
    }
  },

  data () {
    return {
      dialogVisible: true,
      rules: {
        name: [
          { required: true, message: '请输部门名称', trigger: 'blur' }
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
      this.$refs['departmentData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.departmentData);
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
