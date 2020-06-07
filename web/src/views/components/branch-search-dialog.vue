<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
    append-to-body
  >
    <search-base
      :titles="titleData"
      :tableData="tableData"
      :previousDisable="!hasFatherInfo()"
      :nextDisable="isSelectedInfoValid()"
      :totalSize="totalSize"
      :currentPage="currentPage"
      @handleCurrentChange="handleCurrentChange"
      @getChildData="getChildData"
      @previous="previous"
      @close="handleClose"
      @ok="handleSubmit"
    ></search-base>
  </el-dialog>
</template>

<script>
import branchInfoApi from "../../api/branch-info-api/branchInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info'

export default {
  name: "branchInfo",
  mixins: [searchBseInfo],
  components: {
    "search-base": searchBase
  },

  data () {
    return {
      title: "分支机构查询",

      titleData: [{ prop: "name", label: "名称" }],

      tableData: [],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getBranchInfo(param).then(() => {
        this.addPaths();
        this.resetCurrentPage();
        this.fatherID = value.id;
      });
    },

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      var previousInfo = this.paths[this.paths.length - 1];
      var previousParams = this.getParameterForNewTable(previousInfo.id);

      this.getBranchInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.fatherID = previousInfo.id;
      });
    },

    getBranchInfo (params) {
      return branchInfoApi.getBranchInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    var params = {
      id: this.fatherID,
    };

    this.getBranchInfo(params);
  }
}
</script>

<style>
</style>
