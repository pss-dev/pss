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
      console.log("============  ", this.selectedInfo);
      this.$emit('submitData', this.selectedInfo)
    },

    setResponseResult (res) {
      if (res) {
        this.tableData = res;
        this.totalSize = res.length;
      }
    },
  }
}