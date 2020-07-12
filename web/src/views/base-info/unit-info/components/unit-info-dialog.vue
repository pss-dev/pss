<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-form
      :model="unitData"
      ref="unitData"
      :rules="rules"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="单位名称" prop="name">
        <el-input v-model="unitData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="note">
        <el-input v-model="unitData.note" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <dialog-footer @closeDialog="closeDialog" @submitData="submitData"></dialog-footer>
  </el-dialog>
</template>

<script>
import BaseInfoDialogFooter from "@/views/base-info/components/base-info-dialog-footer.vue"

export default {
  name: "unitInfoDialog",
  components: {
    "dialog-footer": BaseInfoDialogFooter
  },
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
      rules: {
        name: [
          { required: true, message: '请输单位名称', trigger: 'blur' }
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
          this.$emit("submitData", this.unitData);
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
