<template>
  <div>
    <el-container>
      <el-header height="30">
        <base-info-header :paths="getPathsLabel()" :showTypeVisiable="false"></base-info-header>
      </el-header>
      <el-main>
        <base-info-table
          :titles="titData"
          :tableData="tableData"
          @handleCurrentChange="handleCurrentChange"
          @pageChange="pageChange"
        ></base-info-table>
      </el-main>
      <el-footer>
        <base-info-footer
          :totalSize="totalSize"
          :currentPage="currentPage"
          :selectedInfoInvalid="isSelectedInfoInvalid()"
          :previousVisiable="false"
          :nextVisiable="false"
          @newInfo="newInfo"
          @copyNew="copyNew"
          @deleteInfo="deleteInfo"
        ></base-info-footer>
      </el-footer>
    </el-container>

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
  },

  data () {
    return {
      titData:
        [{ prop: "name", label: "名称" },
        { prop: "branch.name", label: "分支" },
        { prop: "department.name", label: "部门" }],
    }
  },

  methods: {
    edit () {
      this.setDialogInfo("编辑", this.cloneSelectedInfoData(), false);
      this.showDialog = true;
    },

    newInfo () {
      var emptyDialogData = {
        id: null,
        name: '',
        account: '',
        password: '',
        branch: { id: null, name: '' },
        department: { id: null, name: '' },
        ruler: {},
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.cloneSelectedInfoData(), true);
      this.showDialog = true;
    },

    submitData (employeeData) {
      if (this.addInfo) {
        employeeInfoApi.addEmployeeInfo(employeeData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
      else {
        employeeInfoApi.modifyEmployeeInfo(employeeData).then(
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
    var params = this.getParameterForNewTable(this.getParentID());

    this.getEmployeeInfo(params);
  }
}
</script>

<style>
</style>
