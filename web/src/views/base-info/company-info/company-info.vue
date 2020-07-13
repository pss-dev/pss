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

    <company-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :companyData="dialogData"
      v-if="showDialog"
    ></company-info-dialog>
  </div>
</template>

<script>
import BaseInfoHeader from "../components/base-info-header.vue"
import BaseInfoTabler from "../components/base-info-table.vue"
import BaseInfoFooter from "../components/base-info-footer.vue"
import CompanyInfoDialog from './components/company-info-dialog'

import BseInfo from '../mixIns/base-info'
import TableBaseInfo from '@/views/mixIns/table-base-info.js'
import PermissionBase from '@/views/mixIns/permission-base.js'
import RuleTool from '@/views/constant/rule-tool.js'

import companyInfoApi from '../../../api/company-info-api/companyInfoApi.js'

export default {
  name: "companyInfo",

  mixins: [BseInfo, TableBaseInfo, PermissionBase],

  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "company-info-dialog": CompanyInfoDialog
  },

  props: {
    "companyType": {
      type: Number,
      default: 0 // customer or supplier
    }
  },

  data () {
    return {
      titData:
        [{ prop: "name", label: "名称" },
        { prop: "initials", label: "拼音码" },
        { prop: "address", label: "地址" },
        { prop: "telephone", label: "电话" },
        { prop: "contactPerson", label: "联系人" },
        { prop: "contactPhone", label: "联系人电话" },],
    }
  },

  methods: {
    getChildData (value) {
      value = value == null ? this.selectedInfo : value;
      var param = this.getParameterForNewTable(value.id);

      this.getCompanyInfo(param).then(() => {
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
        initials: '',
        telephone: '',
        address: '',
        contactPerson: '',
        contactPhone: '',
        type: this.companyType,
        parent: this.parent,
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.cloneSelectedInfoData(), true);
      this.showDialog = true;
    },

    submitData (companyData) {
      console.log("====== submitData ", companyData);

      companyInfoApi.checkCompanyDuplicate(companyData).then((res) => {
        if (res.data == true) {
          this.$message({
            message: "来往单位名称重复",
            type: "error",
            showClose: true
          });
        }
        else {
          var getInfoParams = this.getParameterForNewTable(this.getParentID());

          if (this.addInfo) {
            companyInfoApi.addCompanyInfo(companyData).then(
              () => {
                this.getCompanyInfo(getInfoParams);
              });
          }
          else {
            companyInfoApi.modifyCompanyInfo(companyData).then(
              () => {
                this.getCompanyInfo(getInfoParams);
              });
          }
        }
      });
    },

    deleteInfo () {
      companyInfoApi
        .deleteCompanyInfo(this.selectedInfo)
        .then(() => {
          var params = this.getParameterForNewTable(this.getParentID());

          this.getCompanyInfo(params);
        });
    },

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      var previousInfo = this.paths[this.paths.length - 1];
      var previousParams = this.getParameterForNewTable(this.getParentID0(previousInfo.parent));

      this.getCompanyInfo(previousParams).then(() => {
        this.setPerviousInfo();
      });
    },

    getCompanyInfo (params) {
      console.log("=========getCompanyInfo ", params);
      params.type = this.companyType;
      return companyInfoApi.getCompanyInfo(params).then(
        (res) => {
          console.log("=========rrrrr ", res);
          this.setResponseResult(res.data);
          this.closeDialog();
        });
    },

    loadData () {
      var params = this.getParameterForNewTable(this.getParentID());
      this.getCompanyInfo(params);
    }
  },

  created: function () {
    this.loadData();
    this.initPermission(RuleTool.resource.company);
  }
}
</script>

<style>
</style>
