export default {
  data () {
    return {
      selectedInfo: null,

      dialogVisible: true,

      totalSize: 1,
      currentPage: 1,
      showDatasAsTree: true,

      parent: null,
      paths: [], // for previous
    }
  },

  methods: {
    handleClose () {
      this.$emit('closeDialog')
    },

    handleSubmit () {
      this.$emit('submitData', this.selectedInfo)
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

    getParameterForNewTable (fatherID) {
      var params = {};

      if (fatherID) {
        params.fatherID = fatherID;
      }


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
        parent: this.parent,
        page: this.currentPage
      });
    },

    setResponseResult (res) {
      if (res) {
        this.tableData = res;
        this.totalSize = res.length;
      }
    },

    getParentID () {
      return this.getParentID0(this.parent);
    },

    getParentID0 (parent) {
      if (parent) {
        return parent.id;
      }

      return this.showDatasAsTree ? -1 : null;
    },

    setPerviousInfo () {
      let previousInfo = this.paths.pop();
      this.currentPage = previousInfo.page;
      this.parent = previousInfo.parent;
    }
  }
}