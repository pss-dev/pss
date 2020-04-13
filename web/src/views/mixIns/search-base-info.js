export default {
  data () {
    return {
      selectedInfo: null,

      dialogVisible: false,

      currentPage: 1,

      fatherID: "",
      paths: [], // for previous
    }
  },

  methods: {
    handleClose () {
      this.$emit('closeDialog')
    },

    handleCurrentChange (value) {
      this.selectedInfo = value;
    },

    isSelectedInfoValid () {
      return this.selectedInfo != null;
    },

    hasFatherInfo () {
      return this.paths.length > 0;
    },

    getParameterForNewTable (tableID) {
      var params = {
        id: tableID
      };

      return params;
    },

    pageChange (value) {
      this.currentPage = value;
    },

    resetCurrentPage () {
      this.currentPage = 1;
    },

    addPaths () {
      this.paths.push({
        tableID: this.fatherID,
        currentPage: this.currentPage
      });
    },
  }
}