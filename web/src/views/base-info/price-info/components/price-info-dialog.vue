<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-form
      :model="priceData"
      ref="priceData"
      :rules="rules"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="名称" prop="name">
        <el-input readonly v-model="priceData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="显示名称" prop="label">
        <el-input v-model="priceData.label" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <dialog-footer @closeDialog="closeDialog" @submitData="submitData"></dialog-footer>
  </el-dialog>
</template>

<script>
import BaseInfoDialogFooter from "@/views/base-info/components/base-info-dialog-footer.vue"

export default {
  name: "priceInfoDialog",
  components: {
    "dialog-footer": BaseInfoDialogFooter
  },
  data () {
    return {
      dialogVisible: true,
      rules: {
        label: [
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

  created: function () {
  }
}
</script>

<style>
</style>
