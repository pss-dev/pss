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
import DepartmentInfoDialog from "./components/department-info-dialog.vue"

import BseInfo from '../mixIns/base-info'
import TableBaseInfo from '@/views/mixIns/table-base-info.js'
import PermissionBase from '@/views/mixIns/permission-base.js'
import RuleTool from '@/views/constant/rule-tool.js'

import departmentInfoApi from "../../../api/department-info-api/departmentInfoApi.js"

export default {
  name: "departmentInfo",
  mixins: [BseInfo, TableBaseInfo, PermissionBase],
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
      value = value == null ? this.selectedInfo : value;
      let param = this.getParameterForNewTable(value.id);

      this.getDepartmentInfo(param).then(() => {
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
      this.setDialogInfo("复制新增", this.cloneSelectedInfoData(true), true);
      this.showDialog = true;
    },

    submitData (departmentData) {
      console.log("======departmentData  ", departmentData);

      departmentInfoApi.checkDepartmentDuplicate(departmentData).then((res) => {
        if (res.data == true) {
          this.$message({
            message: "部门名称重复",
            type: "error",
            showClose: true
          });
        }
        else {
          let getInfoParams = this.getParameterForNewTable(this.getParentID());

          if (this.addInfo) {
            departmentInfoApi.addDepartmentInfo(departmentData).then(
              () => {
                this.getDepartmentInfo(getInfoParams);
              });
          }
          else {
            departmentInfoApi.modifyDepartmentInfo(departmentData).then(
              () => {
                this.getDepartmentInfo(getInfoParams);
              });
          }
        }
      });
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
      let previousParams = this.getParameterForNewTable(this.getParentID0(previousInfo.parent));

      this.getDepartmentInfo(previousParams).then(() => {
        this.setPerviousInfo();
      });
    },

    getDepartmentInfo (params) {
      return departmentInfoApi.getDepartmentInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
          this.closeDialog();
        });
    },

    loadData () {
      let params = this.getParameterForNewTable(this.getParentID());
      this.getDepartmentInfo(params);
    }
  },

  created: function () {
    this.loadData();
    this.initPermission(RuleTool.resource.department);
  }
}
</script>

<style>
</style>
