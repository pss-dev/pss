<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
    append-to-body
  >
    <div id="print">
      日期：xxx
      部门：aaa
    </div>

    <el-button v-print="'#print'"></el-button>
  </el-dialog>
</template>

<script>

export default {
  name: "printDialog",
  props: {
    "orderFormData": {
      type: Object
    }
  },

  data () {
    return {
      dialogVisible: true,
    }
  },

  methods: {
    handleClose () {
      this.$emit('closeDialog')
    },
  }

}
</script>

<style>
</style>
