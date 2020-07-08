<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <search-base
      :paths="getPathsLabel()"
      :titles="titleData"
      :tableData="tableData"
      :previousDisable="!hasFatherInfo()"
      :nextDisable="isSelectedInfoValid()"
      :totalSize="totalSize"
      :currentPage="currentPage"
      @handleCurrentChange="handleCurrentChange"
      @pageSizeChange="pageSizeChange"
      @getChildData="getChildData"
      @previous="previous"
      @close="handleClose"
      @ok="handleSubmit"
    ></search-base>
  </el-dialog>
</template>

<script>
import accountInfoApi from "../../api/account-info-api/accountInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info.js'
import tableBaseInfo from '../mixIns/table-base-info.js'

export default {
  name: "accountInfo",
  mixins: [searchBseInfo, tableBaseInfo],
  components: {
    "search-base": searchBase
  },

  data () {
    return {
      title: "账户查询",

      titleData: [
        { prop: "name", label: "账户名称" },
        { prop: "bank", label: "银行名称" },
        { prop: "cardholder", label: "持卡人" },
        { prop: "account", label: "卡号" }],

      tableData: [],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getAccountInfo(param).then(() => {
        this.addPaths();
        this.resetCurrentPage();
        this.parent = value;
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
        });
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.getParentID());

    this.getAccountInfo(params);
  }
}
</script>

<style>
</style>
