export default {
  data () {
    return {
      addInfo: false, //add or edit
      selectedInfo: null,

      showDialog: false,
      dialogTitle: "",
      dialogData: {},

      currentPage: 1,
      pageSize: 30,
      totalSize: 1024,

      fatherID: "",
      paths: [], // for previous
    }
  },

  methods: {
    getPathsLabel () {
      if (this.paths.length > 0) {
        let pathsLabel = ""

        this.paths.forEach(element => {
          pathsLabel += element + "/";
        });

        return pathsLabel;
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
      var params = {
        fatherID: tableID
      };

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
        tableID: this.fatherID,
        currentPage: this.currentPage
      });
    },

    hasFatherInfo () {
      return this.paths.length > 0;
    },
  }
}