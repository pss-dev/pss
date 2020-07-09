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
          :deleteInfoVisiable="deletePermission"
          :copyNewVisiable="writePermission"
          :newInfoVisiable="writePermission"
          :editVisiable="writePermission"
          @newInfo="newInfo"
          @copyNew="copyNew"
          @edit="edit"
          @deleteInfo="deleteInfo"
          @previous="previous"
          @next="getChildData"
        ></base-info-footer>
      </el-footer>
    </el-container>

    <depot-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :depotData="dialogData"
      v-if="showDialog"
    ></depot-info-dialog>
  </div>
</template>

<script>
import BaseInfoHeader from "../components/base-info-header.vue"
import BaseInfoTabler from "../components/base-info-table.vue"
import BaseInfoFooter from "../components/base-info-footer.vue"
import DepotInfoDialog from "./components/depot-info-dialog.vue"

import BseInfo from '../mixIns/base-info'
import TableBaseInfo from '@/views/mixIns/table-base-info.js'
import PermissionBase from '@/views/mixIns/permission-base.js'
import RuleTool from '@/views/constant/rule-tool.js'

import depotInfoApi from "../../../api/depot-info-api/depotInfoApi.js"

export default {
  name: "depotInfo",
  mixins: [BseInfo, TableBaseInfo, PermissionBase],
  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "depot-info-dialog": DepotInfoDialog
  },

  data () {
    return {
      titData:
        [{ prop: "name", label: "名称" },
        { prop: "branch.name", label: "分支机构" },
        { prop: "initials", label: "拼音码" }],
    }
  },

  methods: {
    getChildData (value) {
      value = value == null ? this.selectedInfo : value;
      var param = this.getParameterForNewTable(value.id);

      this.getDepotInfo(param).then(() => {
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
        branch: { id: null, name: '' },
        initials: '',
        parent: this.parent
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.cloneSelectedInfoData(), true);
      this.showDialog = true;
    },

    submitData (depotData) {
      var getInfoParams = this.getParameterForNewTable(this.getParentID());

      if (this.addInfo) {
        depotInfoApi.addDepotInfo(depotData).then(
          () => {
            this.getDepotInfo(getInfoParams);
          });
      }
      else {
        depotInfoApi.modifyDepotInfo(depotData).then(
          () => {
            this.getDepotInfo(getInfoParams);
          });
      }
    },

    deleteInfo () {
      depotInfoApi
        .deletedeDotInfo(this.selectedInfo)
        .then(() => {
          var params = this.getParameterForNewTable(this.getParentID());

          this.getDepotInfo(params);
        });
    },

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      var previousInfo = this.paths[this.paths.length - 1];
      var previousParams = this.getParameterForNewTable(this.getParentID0(previousInfo.parent));

      this.getDepotInfo(previousParams).then(() => {
        this.setPerviousInfo();
      });
    },

    getDepotInfo (params) {
      return depotInfoApi.getDepotInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
          this.closeDialog();
        });
    },

    loadData () {
      var params = this.getParameterForNewTable(this.getParentID());
      this.getDepotInfo(params);
    }
  },

  created: function () {
    this.loadData();
    this.initPermission(RuleTool.resource.depot);
  }
}
</script>

<style>
</style>
