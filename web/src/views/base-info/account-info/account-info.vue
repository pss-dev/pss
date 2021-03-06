<template>
  <div>
    <el-container>
      <el-header height="30">
        <base-info-header :paths="getPathsLabel()" @showTypeChange="showTypeChange"></base-info-header>
      </el-header>
      <el-main>
        <base-info-table
          :titles="titleData"
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
import AccountInfoDialog from './components/account-info-dialog'

import BseInfo from '../mixIns/base-info'
import TableBaseInfo from '@/views/mixIns/table-base-info.js'
import PermissionBase from '@/views/mixIns/permission-base.js'

import accountInfoApi from '../../../api/account-info-api/accountInfoApi'
import RuleTool from '@/views/constant/rule-tool.js'

export default {
  name: "accountInfo",

  mixins: [BseInfo, TableBaseInfo, PermissionBase],

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
      titleData: [
        { prop: "name", label: "账户名称" },
        { prop: "bank", label: "银行名称" },
        { prop: "cardholder", label: "持卡人" },
        { prop: "account", label: "卡号" }],
    }
  },

  methods: {
    getChildData (value) {
      value = value == null ? this.selectedInfo : value;
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
      this.setDialogInfo("复制新增", this.cloneSelectedInfoData(true), true);
      this.showDialog = true;
    },

    submitData (accountData) {
      console.log("=========accountData ", accountData);
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
        this.setPerviousInfo();
      });
    },

    getAccountInfo (params) {
      return accountInfoApi.getAccountInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
          this.closeDialog();
        });
    },

    loadData () {
      var params = this.getParameterForNewTable(this.getParentID());
      this.getAccountInfo(params);
    }
  },

  created: function () {
    this.loadData();
    this.initPermission(RuleTool.resource.account);
  }
}
</script>

<style>
</style>
