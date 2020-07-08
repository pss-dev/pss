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
          @next="getChildData"
        ></base-info-footer>
      </el-footer>
    </el-container>

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
import unitInfoDialog from "./components/unit-info-dialog.vue"

import BseInfo from '../mixIns/base-info'
import TableBaseInfo from '@/views/mixIns/table-base-info.js'

import unitInfoApi from "../../../api/unit-info-api/unitInfoApi.js"

export default {
  name: "UnitInfo",
  mixins: [BseInfo, TableBaseInfo],
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
      value = value == null ? this.selectedInfo : value;
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
      unitInfoApi.checkUnitDuplicate(unitData).then((res) => {
        if (res.data == false) {
          this.$message({
            message: "单位名称重复",
            type: "error",
            showClose: true
          });
        }
        else {
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
        }
      });
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
        this.setPerviousInfo();
      });
    },

    getUnitInfo (params) {
      return unitInfoApi.getUnitInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
          this.closeDialog();
        });
    },

    loadData () {
      var params = this.getParameterForNewTable(this.getParentID());
      this.getUnitInfo(params);
    }
  },

  created: function () {
    this.loadData();
  }
}
</script>

<style>
</style>
