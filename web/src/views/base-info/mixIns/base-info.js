export default {
  data () {
    return {
      tableData: [],

      addInfo: false, //add or edit
      selectedInfo: null,

      showDialog: false,
      dialogTitle: "",
      dialogData: {},

      currentPage: 1,
      pageSize: 30,
      totalSize: 1024,

      parent: null,
      paths: [], // for previous
    }
  },

  methods: {
    getPathsLabel () {
      if (this.paths.length > 0) {
        let pathsLabel = "root"

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

    isSelectedInfoInvalid () {
      return this.selectedInfo == null;
    },

    setDialogInfo (title, dialogData, addInfo) {
      this.dialogTitle = title;
      this.dialogData = dialogData;
      this.addInfo = addInfo;
    },

    closeDialog () {
      this.showDialog = false;
    },

    getParameterForNewTable (tableID) {
      var params = {};

      if (tableID) {
        params.fatherID = tableID;
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
        page: this.currentPage
      });
    },

    hasFatherInfo () {
      return this.paths.length > 0;
    },

    setResponseResult (data) {
      if (data) {
        this.tableData = data;
        this.totalSize = data.length;

        this.showDialog = false;
      }
    },

    getParentID () {
      return this.getParentID0(this.parent);
    },

    getParentID0 (parent) {
      if (parent) {
        return parent.id;
      }

      return null;
    },
  }
}