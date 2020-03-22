<template>
  <div>
    <base-info-header></base-info-header>

    <base-info-table 
      :titles="titData"
      :tableData="tableData"
      :showEdit="true"
      @edit="edit"
      @handleCurrentChange="handleCurrentChange"
      @getChildData="getChildData">
    </base-info-table>

    <base-info-footer 
      :totalSize="totalSize"
      :page="currentPage"
      :copyNewDisable="isSelectedInfoInvalid()"
      :deleteDisable="isSelectedInfoInvalid()"
      :previousDisable="!hasFatherInfo()"
      @pageChange="pageChange"
      @pageSizeChange="pageSizeChange"
      @newInfo="newInfo"
      @copyNew="copyNew"
      @deleteInfo="deleteInfo"
      @previous="previous">
    </base-info-footer>

    <department-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :departmentData="dialogData"
      v-if="showDialog">
    </department-info-dialog>
  </div>
</template>

<script>
  import baseInfoHeader from "../components/base-info-header.vue"
  import baseInfoTabler from "../components/base-info-table.vue"
  import baseInfoFooter from "../components/base-info-footer.vue"
  import departmentInfoDialog from "./components/department-info-dialog.vue"

  import departmentInfoApi from "../../../api/department-info-api/departmentInfoApi.js"

  export default {
    name: "departmentInfo",

    components: {
        "base-info-header": baseInfoHeader,
        "base-info-table": baseInfoTabler,
        "base-info-footer": baseInfoFooter,
        "department-info-dialog": departmentInfoDialog
    },

    data() {
      return {
        showDialog: false,
        addInfo: false,
        fatherID: "",
        selectedInfo: null,
        dialogTitle: "",
        dialogData: {},
        currentPage: 1,
        pageSize: 30,
        totalSize: 1024,
        titData: [{prop:"departmentID", label:"编号"},
                  {prop:"departmentName", label:"名称"},
                  {prop:"initials", label:"拼音码"}],
        tableData: [],
        paths: [],
        }
    },
    
    methods: {
      pageChange(value) {
        var pageParams = {
          departmentID: this.fatherID,
          currentPage: value,
          pageSize: this.pageSize
        };
        
        this.getDepartmentInfo(pageParams).then(() => {
          this.currentPage = value;
        });
      },

      pageSizeChange(value) {
        this.pageSize = value;
      },

      handleCurrentChange(value) {
        this.selectedInfo = value;
      },

      getChildData(value) {
        var param = {
          departmentID: value.departmentID,
          currentPage: 1,
          pageSize: this.pageSize
        };

        this.getDepartmentInfo(param).then(() => {
          this.paths.push({
            departmentID: this.fatherID,
            currentPage: this.currentPage
          });
          this.currentPage = 1;
          this.fatherID = value.departmentID;
        });
      },

      edit(value) {
        this.dialogTitle = "编辑";
        this.dialogData = value;
        this.addInfo = false;
        this.showDialog = true;
      },

      newInfo() {
        this.dialogTitle = "空白新增";
        this.addInfo = true;
        this.dialogData = {
          departmentID: '',
          departmentName: '',
          initials: ''
        };
        this.showDialog = true;
      },

      copyNew() {
        this.dialogTitle = "复制新增"
        this.addInfo = true;
        this.dialogData = this.selectedInfo;
        this.showDialog = true;
      },

      deleteInfo() {
        var deleteParams = {
          departmentID: this.selectedInfo.departmentID,
          currentPage: this.currentPage,
          pageSize: this.pageSize
        }

        departmentInfoApi
          .deleteDepartmentInfo(deleteParams)
          .then((res) => {
              this.setResponseResult(res.data);
          });
      },

      previous() {
        if(this.paths.length < 1) {
          return;
        }

        var previousInfo = this.paths.pop();
        var previousParams = {
          departmentID: previousInfo.departmentID,
          currentPage: previousInfo.page,
          pageSize: this.pageSize
        };
        
        this.getDepartmentInfo(previousParams).then(() => {
          this.currentPage = previousInfo.page;
          this.fatherID = previousInfo.departmentID;
        });
      },
      
      submitData(departmentData, oldID) {
        var params = {
          departmentID: this.fatherID,
          currentPage: this.currentPage,
          pageSize: this.pageSize
        };

        if(this.addInfo) {
          departmentInfoApi.addDepartmentInfo(params, departmentData).then(
            (res) => {
              this.setResponseResult(res.data);
          });
        }
        else {
          params.oldID = oldID;
          departmentInfoApi.modifyDepartmentInfo(params, departmentData).then(
            (res) => {
              this.setResponseResult(res.data);
          });
        }
      },

      closeDialog() {
        this.showDialog = false;
      },

      getDepartmentInfo(params) {
        return departmentInfoApi.getDepartmentInfo(params).then(
          (res) => {
            this.setResponseResult(res.data);
        });
      },

      setResponseResult(data) {
        if(data.totalSize && data.result) {
          this.totalSize = data.totalSize;
          this.tableData = data.result;

          this.showDialog = false;
        }
      },
      
      isSelectedInfoInvalid() {
        return this.selectedInfo == null;
      },
      
      hasFatherInfo() {
        return this.paths.length > 0;
      }
    },

    created: function() {
      var params = {
        departmentID: this.fatherID,
        currentPage: this.currentPage,
        pageSize: this.pageSize
      };

      this.getDepartmentInfo(params);
    }
  }
</script>

<style>
</style>
