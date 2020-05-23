<template>
  <div>
    <base-info-header :paths="getPathsLabel()"></base-info-header>

    <base-info-table
      :titles="titData"
      :tableData="tableData"
      @handleCurrentChange="handleCurrentChange"
      @pageChange="pageChange"
    ></base-info-table>

    <base-info-footer
      :totalSize="totalSize"
      :currentPage="currentPage"
      :selectedInfoInvalid="isSelectedInfoInvalid()"
      @newInfo="newInfo"
      @copyNew="copyNew"
      @deleteInfo="deleteInfo"
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
import BaseInfoHeader from "../components/base-info-header.vue"
import BaseInfoTabler from "../components/base-info-table.vue"
import BaseInfoFooter from "../components/base-info-footer.vue"
import BseInfo from '../mixIns/base-info'
import EmployeeInfoDialog from './components/employee-info-dialog'

import employeeInfoApi from '../../../api/employee-info-api/employeeInfoApi'

export default {
  name: "employeeInfo",

  mixins: [BseInfo],

  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "employee-info-dialog": EmployeeInfoDialog
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
        [{ prop: "name", label: "名称" }],
    }
  },

  methods: {
    edit () {
      this.setDialogInfo("编辑", this.selectedInfo, false);
      this.showDialog = true;
    },

    newInfo () {
      var emptyDialogData = {
        id: -1,
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

    submitData (employeeData) {
      var params = {};

      if (this.addInfo) {
        this.setDefaultID(employeeData);

        employeeInfoApi.addEmployeeInfo(employeeData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
      else {
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
