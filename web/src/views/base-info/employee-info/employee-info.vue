<template>
  <div>
    <base-info-header :paths="getPathsLabel()"></base-info-header>

    <base-info-table
      :titles="titData"
      :tableData="tableData"
      :showEdit="true"
      @edit="edit"
      @handleCurrentChange="handleCurrentChange"
      @getChildData="getChildData"
      @pageChange="pageChange"
    ></base-info-table>

    <base-info-footer
      :totalSize="totalSize"
      :currentPage="currentPage"
      :selectedInfoInvalid="isSelectedInfoInvalid()"
      :previousDisable="!hasFatherInfo()"
      @newInfo="newInfo"
      @copyNew="copyNew"
      @deleteInfo="deleteInfo"
      @previous="previous"
    ></base-info-footer>

    <employee-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :employeeData="dialogData"
      v-if="showDialog"
    ></employee-info-dialog>
  </div>
</template>

<script>
import baseInfoHeader from "../components/base-info-header.vue"
import baseInfoTabler from "../components/base-info-table.vue"
import baseInfoFooter from "../components/base-info-footer.vue"
import bseInfo from '../mixIns/base-info'
import employeeInfoDialog from './components/employee-info-dialog'

import employeeInfoApi from '../../../api/employee-info-api/employeeInfoApi'

export default {
  name: "employeeInfo",

  mixins: [bseInfo],

  components: {
    "base-info-header": baseInfoHeader,
    "base-info-table": baseInfoTabler,
    "base-info-footer": baseInfoFooter,
    "employee-info-dialog": employeeInfoDialog
  },

  props: {
    "employeeType": {
      type: Number,
      default: 0 // customer or supplier
    }
  },

  data () {
    return {
      titData:
        [{ prop: "id", label: "编号" },
        { prop: "name", label: "名称" }],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getemployeeInfo(param).then(() => {
        this.addPaths();
        this.resetCurrentPage();
        this.fatherID = value.id;
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
        passworld: '',
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.selectedInfo, true);
      this.showDialog = true;
    },

    submitData (employeeData, oldID) {
      var params = {};

      if (this.addInfo) {
        employeeInfoApi.addEmployeeInfo(employeeData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
      else {
        params.oldID = oldID;
        employeeInfoApi.modifyEmployeeInfo(params, employeeData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
    },

    deleteInfo () {
      employeeInfoApi
        .deleteEmployeeInfo(this.selectedInfo)
        .then((res) => {
          this.setResponseResult(res.data);
        });
    },

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      var previousInfo = this.paths[this.paths.length - 1];
      var previousParams = this.getParameterForNewTable(previousInfo.id);

      this.getEmployeeInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.fatherID = previousInfo.id;
      });
    },

    getEmployeeInfo (params) {
      employeeInfoApi.getEmployeeInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.fatherID);

    this.getEmployeeInfo(params);
  }
}
</script>

<style>
</style>
