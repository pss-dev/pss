<template>
  <div class="base-info-dialog-footer">
    <el-button @click="closeDialog">取 消</el-button>
    <el-button type="primary" @click="submitData">确 定</el-button>
  </div>
</template>

<script>
export default {
  name: "baseInfoDialogFooter",

  data () {
    return {
    };
  },

  methods: {
    closeDialog () {
      this.$emit('closeDialog');
    },

    submitData () {
      this.$emit('submitData');
    },
  },
}
</script>

<style>
.base-info-dialog-footer {
  text-align: center;
}
</style>
