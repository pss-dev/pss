<template>
  <div>
    <base-info-header :paths="getPathsLabel()"></base-info-header>

    <base-info-table
      :titles="titData"
      :tableData="tableData"
      :showEdit="true"
      @edit="edit"
      @handleCurrentChange="handleCurrentChange"
      @getChildData="getChildData"
    ></base-info-table>

    <base-info-footer
      :totalSize="totalSize"
      :currentPage="currentPage"
      :selectedInfoInvalid="isSelectedInfoInvalid()"
      :previousDisable="!hasFatherInfo()"
      @pageChange="pageChange"
      @pageSizeChange="pageSizeChange"
      @newInfo="newInfo"
      @copyNew="copyNew"
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
import baseInfoHeader from "../components/base-info-header.vue"
import baseInfoTabler from "../components/base-info-table.vue"
import baseInfoFooter from "../components/base-info-footer.vue"
import bseInfo from '../mixIns/base-info'
import companyInfoDialog from './components/company-info-dialog'

import companyInfoApi from '../../../api/company-info-api/companyInfoApi'

export default {
  name: "companyInfo",

  mixins: [bseInfo],

  components: {
    "base-info-header": baseInfoHeader,
    "base-info-table": baseInfoTabler,
    "base-info-footer": baseInfoFooter,
    "company-info-dialog": companyInfoDialog
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
        [{ prop: "id", label: "编号" },
        { prop: "name", label: "名称" },
        { prop: "initials", label: "拼音码" },
        { prop: "address", label: "地址" },
        { prop: "contactPerson", label: "联系人" },
        { prop: "contactPhone", label: "联系人电话" },],
      tableData: [],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id, 1, this.pageSize);

      this.getCompanyInfo(param).then(() => {
        this.addPaths();
        this.resetCurrentPage();
        this.fatherID = value.id;
      });
    },

    edit (value) {
      this.setDialogInfo("编辑", value, false);
      this.showDialog = true;
    },

    newInfo () {
      var emptyDialogData = {
        id: '',
        name: '',
        initials: '',
        address: '',
        contactPerson: '',
        contactPhone: '',
        type: this.companyType
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.selectedInfo, true);
      this.showDialog = true;
    },

    submitData (companyData, oldID) {
      var params = this.getParameterForNewTable(this.fatherID, this.currentPage, this.pageSize);

      if (this.addInfo) {
        companyInfoApi.addCompanyInfo(params, companyData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
      else {
        params.oldID = oldID;
        companyInfoApi.modifyCompanyInfo(params, companyData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
    },

    deleteInfo () {
      var deleteParams = {
        id: this.selectedInfo.id,
        currentPage: this.currentPage,
        pageSize: this.pageSize
      }

      companyInfoApi
        .deleteCompanyInfo(deleteParams)
        .then((res) => {
          this.setResponseResult(res.data);
        });
    },

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      var previousInfo = this.paths[this.paths.length - 1];
      var previousParams = this.getParameterForNewTable(previousInfo.id,
        previousInfo.page, this.pageSize);

      this.getCompanyInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.fatherID = previousInfo.id;
      });
    },

    pageChange (value) {
      var params = this.getParameterForNewTable(this.fatherID, value, this.pageSize);

      this.getCompanyInfo(params).then(() => {
        this.currentPage = value;
      });

    },

    getCompanyInfo (params) {
      return companyInfoApi.getCompanyInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },

    setResponseResult (data) {
      if (data.totalSize && data.result) {
        this.totalSize = data.totalSize;
        this.tableData = data.result;

        this.showDialog = false;
      }
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.fatherID, this.currentPage, this.pageSize);

    this.getCompanyInfo(params);
  }
}
</script>

<style>
</style>
