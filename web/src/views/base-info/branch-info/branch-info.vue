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

    <branch-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :branchData="dialogData"
      v-if="showDialog"
    ></branch-info-dialog>
  </div>
</template>

<script>
import BaseInfoHeader from "../components/base-info-header.vue"
import BaseInfoTabler from "../components/base-info-table.vue"
import BaseInfoFooter from "../components/base-info-footer.vue"
import BseInfo from '../mixIns/base-info'
import BranchInfoDialog from "./components/branch-info-dialog.vue"

import branchInfoApi from "../../../api/branch-info-api/branchInfoApi.js"

export default {
  name: "branchInfo",
  mixins: [BseInfo],
  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "branch-info-dialog": BranchInfoDialog
  },

  data () {
    return {
      titData:
        [{ prop: "name", label: "名称" },
        { prop: "customer", label: "客户" },
        { prop: "supplier", label: "供货商" },
        { prop: "initials", label: "拼音码" }],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getbranchInfo(param).then(() => {
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
      var emptyDialogData = {
        id: null,
        name: '',
        customer: { id: null, name: '' },
        supplier: { id: null, name: '' },
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

    submitData (branchData) {
      var params = {};
      var getInfoParams = this.getParameterForNewTable(this.getParentID());

      if (this.addInfo) {
        branchInfoApi.addbranchInfo(branchData).then(
          () => {
            this.getBranchInfo(getInfoParams);
          });
      }
      else {
        branchInfoApi.modifybranchInfo(params, branchData).then(
          () => {
            this.getBranchInfo(getInfoParams);
          });
      }
    },

    deleteInfo () {
      branchInfoApi
        .deletebranchInfo(this.selectedInfo)
        .then(() => {
          var params = this.getParameterForNewTable(this.getParentID());

          this.getBranchInfo(params);
        });
    },

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      var previousInfo = this.paths[this.paths.length - 1];
      var previousParams = this.getParameterForNewTable(previousInfo.parent.id);

      this.getBranchInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.parent = previousInfo.parent;
      });
    },

    getBranchInfo (params) {
      return branchInfoApi.getBranchInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.getParentID());

    this.getBranchInfo(params);
  }
}
</script>

<style>
</style>
