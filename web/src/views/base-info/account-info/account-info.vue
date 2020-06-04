<template>
  <div>
    <base-info-header :paths="getPathsLabel()" @showTypeChange="showTypeChange"></base-info-header>

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
import BaseInfoHeader from "../components/base-info-header.vue"
import BaseInfoTabler from "../components/base-info-table.vue"
import BaseInfoFooter from "../components/base-info-footer.vue"
import BseInfo from '../mixIns/base-info'
import AccountInfoDialog from './components/account-info-dialog'

import accountInfoApi from '../../../api/account-info-api/accountInfoApi'

export default {
  name: "accountInfo",

  mixins: [BseInfo],

  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "account-info-dialog": AccountInfoDialog
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
        [{ prop: "name", label: "支付类型" },
        { prop: "account", label: "支付账户" }],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getaccountInfo(param).then(() => {
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
        bank: '',
        cardholder: '',
        account: '',
        parent: this.parent,
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.cloneSelectedInfoData(), true);
      this.showDialog = true;
    },

    submitData (accountData) {
      var getInfoParams = this.getParameterForNewTable(this.getParentID());

      if (this.addInfo) {
        accountInfoApi.addAccountInfo(accountData).then(
          () => {
            this.getAccountInfo(getInfoParams);
          });
      }
      else {
        accountInfoApi.modifyAccountInfo(accountData).then(
          () => {
            this.getAccountInfo(getInfoParams);
          });
      }
    },

    deleteInfo () {
      accountInfoApi
        .deleteAccountInfo(this.selectedInfo)
        .then(() => {
          var params = this.getParameterForNewTable(this.getParentID());

          this.getAccountInfo(params);
        });
    },

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      var previousInfo = this.paths[this.paths.length - 1];
      var previousParams = this.getParameterForNewTable(this.getParentID0(previousInfo.parent));

      this.getAccountInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.parent = previousInfo.parent;
      });
    },

    getAccountInfo (params) {
      return accountInfoApi.getAccountInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },

    loadData () {
      var params = this.getParameterForNewTable(this.getParentID());
      this.getAccountInfo(params);
    }
  },

  created: function () {
    this.loadData();
  }
}
</script>

<style>
</style>
