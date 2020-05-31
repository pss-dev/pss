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

    <unit-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :unitData="dialogData"
      v-if="showDialog"
    ></unit-info-dialog>
  </div>
</template>

<script>
import baseInfoHeader from "../components/base-info-header.vue"
import baseInfoTabler from "../components/base-info-table.vue"
import baseInfoFooter from "../components/base-info-footer.vue"
import bseInfo from '../mixIns/base-info'
import unitInfoDialog from "./components/unit-info-dialog.vue"
import unitInfoApi from "../../../api/unit-info-api/unitInfoApi.js"

export default {
  name: "UnitInfo",
  mixins: [bseInfo],
  components: {
    "base-info-header": baseInfoHeader,
    "base-info-table": baseInfoTabler,
    "base-info-footer": baseInfoFooter,
    "unit-info-dialog": unitInfoDialog
  },

  data () {
    return {
      titData:
        [{ prop: "name", label: "名称" },
        { prop: "note", label: "备注" }],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getUnitInfo(param).then(() => {
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
        note: '',
        parent: this.parent,
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.cloneSelectedInfoData(), true);
      this.showDialog = true;
    },

    submitData (unitData) {
      var getInfoParams = this.getParameterForNewTable(this.getParentID());

      if (this.addInfo) {
        unitInfoApi.addUnitInfo(unitData).then(
          () => {
            this.getUnitInfo(getInfoParams);
          });
      }
      else {
        unitInfoApi.modifyUnitInfo(unitData).then(
          () => {
            this.getUnitInfo(getInfoParams);
          });
      }
    },

    deleteInfo () {
      unitInfoApi
        .deleteUnitInfo(this.selectedInfo)
        .then(() => {
          var params = this.getParameterForNewTable(this.getParentID());

          this.getUnitInfo(params);
        });
    },

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      var previousInfo = this.paths[this.paths.length - 1];
      var previousParams = this.getParameterForNewTable(this.getParentID0(previousInfo.parent));

      this.getUnitInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.parent = previousInfo.parent;
      });
    },

    getUnitInfo (params) {
      return unitInfoApi.getUnitInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.getParentID());

    this.getUnitInfo(params);
  }
}
</script>

<style>
</style>
