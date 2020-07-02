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
import EmployeeInfoDialog from './components/employee-info-dialog'

import BseInfo from '../mixIns/base-info'
import TableBaseInfo from '@/views/mixIns/table-base-info.js'

import employeeInfoApi from '../../../api/employee-info-api/employeeInfoApi'

export default {
  name: "employeeInfo",

  mixins: [BseInfo, TableBaseInfo],

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
        branch: null,
        department: null,
        roles: [],
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
        console.log("====== submitData ", employeeData);
        employeeInfoApi.addEmployeeInfo(employeeData).then(
          () => {
            this.getEmployeeInfo();
          });
      }
      else {
        employeeInfoApi.modifyEmployeeInfo(employeeData).then(
          () => {
            this.getEmployeeInfo();
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

    getEmployeeInfo () {
      employeeInfoApi.getEmployeeInfo().then(
        (res) => {
          console.log("=========  getEmployeeInfo ", res);
          this.setResponseResult(res.data);
          this.closeDialog();
        });
    },
  },

  created: function () {
    this.getEmployeeInfo();
  }
}
</script>

<style>
</style>
