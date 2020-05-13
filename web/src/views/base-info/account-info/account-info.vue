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
      @pageChange="pageChange"
    ></base-info-table>

    <base-info-footer
      :totalSize="totalSize"
      :currentPage="currentPage"
      :selectedInfoInvalid="isSelectedInfoInvalid()"
      :previousDisable="!hasFatherInfo()"
      @newInfo="newInfo"
      @copyNew="copyNew"
      @deleteInfo="deleteInfo"
      @previous="previous"
    ></base-info-footer>

    <account-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :accountData="dialogData"
      v-if="showDialog"
    ></account-info-dialog>
  </div>
</template>

<script>
import baseInfoHeader from "../components/base-info-header.vue"
import baseInfoTabler from "../components/base-info-table.vue"
import baseInfoFooter from "../components/base-info-footer.vue"
import bseInfo from '../mixIns/base-info'
import accountInfoDialog from './components/account-info-dialog'

import accountInfoApi from '../../../api/account-info-api/accountInfoApi'

export default {
  name: "accountInfo",

  mixins: [bseInfo],

  components: {
    "base-info-header": baseInfoHeader,
    "base-info-table": baseInfoTabler,
    "base-info-footer": baseInfoFooter,
    "account-info-dialog": accountInfoDialog
  },

  props: {
    "accountType": {
      type: Number,
      default: 0 // customer or supplier
    }
  },

  data () {
    return {
      titData:
        [{ prop: "id", label: "编号" },
        { prop: "name", label: "名称" },
        { prop: "balance", label: "余额" }],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getaccountInfo(param).then(() => {
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
        balance: '',
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.selectedInfo, true);
      this.showDialog = true;
    },

    submitData (accountData, oldID) {
      var params = {};

      if (this.addInfo) {
        accountInfoApi.addAccountInfo(accountData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
      else {
        params.oldID = oldID;
        accountInfoApi.modifyAccountInfo(params, accountData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
    },

    deleteInfo () {
      accountInfoApi
        .deleteAccountInfo(this.selectedInfo)
        .then((res) => {
          this.setResponseResult(res.data);
        });
    },

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      var previousInfo = this.paths[this.paths.length - 1];
      var previousParams = this.getParameterForNewTable(previousInfo.id);

      this.getAccountInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.fatherID = previousInfo.id;
      });
    },

    getAccountInfo (params) {
      accountInfoApi.getAccountInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.fatherID);

    this.getAccountInfo(params);
  }
}
</script>

<style>
</style>
