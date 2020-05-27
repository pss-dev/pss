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
import BseInfo from '../mixIns/base-info'
import CompanyInfoDialog from './components/company-info-dialog'

import companyInfoApi from '../../../api/company-info-api/companyInfoApi'

export default {
  name: "companyInfo",

  mixins: [BseInfo],

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
        { prop: "contactPerson", label: "联系人" },
        { prop: "contactPhone", label: "联系人电话" },],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getCompanyInfo(param).then(() => {
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
        initials: '',
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
      this.setDialogInfo("复制新增", this.selectedInfo, true);
      this.showDialog = true;
    },

    submitData (companyData) {
      var params = {};
      var getInfoParams = this.getParameterForNewTable(this.getParentID());

      if (this.addInfo) {
        companyInfoApi.addCompanyInfo(companyData).then(
          () => {
            this.getCompanyInfo(getInfoParams);
          });
      }
      else {
        companyInfoApi.modifyCompanyInfo(params, companyData).then(
          () => {
            this.getCompanyInfo(getInfoParams);
          });
      }
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
      var previousParams = this.getParameterForNewTable(previousInfo.parent.id);

      this.getCompanyInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.parent = previousInfo.parent;
      });
    },

    getCompanyInfo (params) {
      return companyInfoApi.getCompanyInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.getParentID());

    this.getCompanyInfo(params);
  }
}
</script>

<style>
</style>
