<template>
  <div>
    <base-info-header></base-info-header>

    <base-info-table
      :titles="titData"
      :tableData="tableData"
      :showEdit="true"
      @edit="edit"
      @handleCurrentChange="handleCurrentChange"
      @getChildData="getChildData"
    ></base-info-table>

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
      @previous="previous"
    ></base-info-footer>

    <department-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :departmentData="dialogData"
      v-if="showDialog"
    ></department-info-dialog>
  </div>
</template>

<script>
import baseInfoHeader from "../components/base-info-header.vue"
import baseInfoTabler from "../components/base-info-table.vue"
import baseInfoFooter from "../components/base-info-footer.vue"
import bseInfo from '../mixIns/base-info'
import departmentInfoDialog from "./components/department-info-dialog.vue"

import departmentInfoApi from "../../../api/department-info-api/departmentInfoApi.js"

export default {
  name: "departmentInfo",
  mixins: [bseInfo],
  components: {
    "base-info-header": baseInfoHeader,
    "base-info-table": baseInfoTabler,
    "base-info-footer": baseInfoFooter,
    "department-info-dialog": departmentInfoDialog
  },

  data () {
    return {
      titData: [{ prop: "id", label: "编号" },
      { prop: "name", label: "名称" },
      { prop: "initials", label: "拼音码" }],
      tableData: [],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.departmentID, 1, this.pageSize);

      this.getDepartmentInfo(param).then(() => {
        this.addPaths();
        this.resetCurrentPage();
        this.fatherID = value.departmentID;
      });
    },

    edit (value) {
      this.setDialogInfo("编辑", value, false);
      this.showDialog = true;
    },

    newInfo () {
      var emptyDialogData = {
        id: '',
        name: '',
        initials: ''
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.selectedInfo, true);
      this.showDialog = true;
    },

    submitData (departmentData, oldID) {
      var params = this.getParameterForNewTable(this.fatherID, this.currentPage, this.pageSize);

      if (this.addInfo) {
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

    deleteInfo () {
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

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      var previousInfo = this.paths[this.paths.length - 1];
      var previousParams = this.getParameterForNewTable(previousInfo.id,
        previousInfo.page, this.pageSize);

      this.getDepartmentInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.fatherID = previousInfo.departmentID;
      });
    },

    pageChange (value) {
      var params = this.getParameterForNewTable(this.fatherID, value, this.pageSize);

      this.getDepartmentInfo(params).then(() => {
        this.currentPage = value;
      });

    },

    getDepartmentInfo (params) {
      return departmentInfoApi.getDepartmentInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },

    setResponseResult (data) {
      if (data.totalSize && data.result) {
        this.totalSize = data.totalSize;
        this.tableData = data.result;

        this.showDialog = false;
      }
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.fatherID, this.currentPage, this.pageSize);

    this.getDepartmentInfo(params);
  }
}
</script>

<style>
</style>
