<template>
  <div>
    <base-info-header :paths="getPathsLabel()"></base-info-header>

    <base-info-table
      :titles="titData"
      :tableData="tableData"
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
      @edit="edit"
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
import BaseInfoHeader from "../components/base-info-header.vue"
import BaseInfoTabler from "../components/base-info-table.vue"
import BaseInfoFooter from "../components/base-info-footer.vue"
import BseInfo from '../mixIns/base-info'
import DepartmentInfoDialog from "./components/department-info-dialog.vue"

import departmentInfoApi from "../../../api/department-info-api/departmentInfoApi.js"

export default {
  name: "departmentInfo",
  mixins: [BseInfo],
  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "department-info-dialog": DepartmentInfoDialog
  },

  data () {
    return {
      titData:
        [{ prop: "name", label: "名称" },
        { prop: "initials", label: "拼音码" }],
    }
  },

  methods: {
    getChildData (value) {
      let param = this.getParameterForNewTable(value.id);

      this.getDepartmentInfo(param).then(() => {
        this.addPaths();
        this.resetCurrentPage();
        this.parent = value;
      });
    },

    edit () {
      this.setDialogInfo("编辑", this.selectedInfo, false);
      this.showDialog = true;
    },

    newInfo () {
      let emptyDialogData = {
        id: null,
        name: '',
        initials: '',
        parent: this.parent,
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.selectedInfo, true);
      this.showDialog = true;
    },

    submitData (departmentData) {
      let params = {};
      let getInfoParams = this.getParameterForNewTable(this.getParentID());

      if (this.addInfo) {
        departmentInfoApi.addDepartmentInfo(departmentData).then(
          () => {
            this.getDepartmentInfo(getInfoParams);
          });
      }
      else {
        departmentInfoApi.modifyDepartmentInfo(params, departmentData).then(
          () => {
            this.getDepartmentInfo(getInfoParams);
          });
      }
    },

    deleteInfo () {
      departmentInfoApi
        .deleteDepartmentInfo(this.selectedInfo)
        .then(() => {
          let params = this.getParameterForNewTable(this.getParentID());

          this.getDepartmentInfo(params);
        });
    },

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      let previousInfo = this.paths[this.paths.length - 1];
      let previousParams = this.getParameterForNewTable(previousInfo.parent.id);

      this.getDepartmentInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.parent = previousInfo.parent;
      });
    },

    getDepartmentInfo (params) {
      return departmentInfoApi.getDepartmentInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    let params = this.getParameterForNewTable(this.getParentID());

    this.getDepartmentInfo(params);
  }
}
</script>

<style>
</style>
