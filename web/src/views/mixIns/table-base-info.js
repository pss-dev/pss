export default {
  data () {
    return {
      selectedInfo: null,

      showDatasAsTree: true,

      parent: null,
      paths: [], // for previous

      tableData: [],

      totalSize: 1,
      currentPage: 1,
      pageSize: 30,
    }
  },

  methods: {
    getPathsLabel () {
      if (this.paths.length > 0) {
        let pathsLabel = ""

        this.paths.forEach(element => {
          let path = element.parent == null ? "" : element.parent.name;
          pathsLabel += path + "/";
        });

        return pathsLabel + this.parent.name;
      }

      return "root";
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

    pageSizeChange (value) {
      this.pageSize = value;
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
        page: this.currentPage,
        pageSize: this.pageSize
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
      this.pageSize = previousInfo.pageSize;
    },

    showTypeChange (value) {
      this.showDatasAsTree = value;

      this.paths = [];
      this.parent = null;

      this.loadData();
    },
  }
}