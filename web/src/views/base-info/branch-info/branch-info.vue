<template>
  <div>
    <el-container>
      <el-header height="30">
        <base-info-header :paths="getPathsLabel()" @showTypeChange="showTypeChange"></base-info-header>
      </el-header>
      <el-main>
        <base-info-table
          :titles="titData"
          :tableData="tableData"
          @handleCurrentChange="handleCurrentChange"
          @getChildData="getChildData"
          @pageChange="pageChange"
        ></base-info-table>
      </el-main>
      <el-footer>
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
      </el-footer>
    </el-container>

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
import BranchInfoDialog from "./components/branch-info-dialog.vue"

import BseInfo from '../mixIns/base-info'
import TableBaseInfo from '@/views/mixIns/table-base-info.js'

import branchInfoApi from "../../../api/branch-info-api/branchInfoApi.js"

export default {
  name: "branchInfo",
  mixins: [BseInfo, TableBaseInfo],
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
      this.setDialogInfo("编辑", this.cloneSelectedInfoData(), false);
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
      this.setDialogInfo("复制新增", this.cloneSelectedInfoData(), true);
      this.showDialog = true;
    },

    submitData (branchData) {
      console.log("====== branchData ", branchData);
      var getInfoParams = this.getParameterForNewTable(this.getParentID());

      if (this.addInfo) {
        branchInfoApi.addBranchInfo(branchData).then(
          () => {
            this.getBranchInfo(getInfoParams);
          });
      }
      else {
        branchInfoApi.modifyBranchInfo(branchData).then(
          () => {
            this.getBranchInfo(getInfoParams);
          });
      }
    },

    deleteInfo () {
      branchInfoApi
        .deleteBranchInfo(this.selectedInfo)
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
      var previousParams = this.getParameterForNewTable(this.getParentID0(previousInfo.parent));

      this.getBranchInfo(previousParams).then(() => {
        this.setPerviousInfo();
      });
    },

    getBranchInfo (params) {
      return branchInfoApi.getBranchInfo(params).then(
        (res) => {
          console.log("========res ", res);
          this.setResponseResult(res.data);
          this.closeDialog();
        });
    },

    loadData () {
      var params = this.getParameterForNewTable(this.getParentID());
      this.getBranchInfo(params);
    }
  },

  created: function () {
    this.loadData();
  }
}
</script>

<style>
</style>
