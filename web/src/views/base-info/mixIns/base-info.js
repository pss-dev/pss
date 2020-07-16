export default {
  data () {
    return {
      showDatasAsTree: true,

      addInfo: false, //add or edit

      showDialog: false,
      dialogTitle: "",
      dialogData: {},
    }
  },

  methods: {
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

    cloneData (data) {
      var obj = {};
      obj = JSON.parse(JSON.stringify(data));
      obj.id = null;

      return obj;
    },

    cloneSelectedInfoData () {
      return this.cloneData(this.selectedInfo);
    },
  }
}