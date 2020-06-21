export default {
  data () {
    return {
      dialogVisible: true,
    }
  },

  methods: {
    handleClose () {
      this.$emit('closeDialog')
    },

    handleSubmit () {
      if (this.selectedInfo && this.selectedInfo.haveChildren == true) {
        this.getChildData(this.selectedInfo);
      }
      else {
        this.$emit('submitData', this.selectedInfo);
      }
    },

    setResponseResult (res) {
      if (res) {
        this.tableData = res;
        this.totalSize = res.length;
        this.selectedInfo = null;
      }
    },
  }
}