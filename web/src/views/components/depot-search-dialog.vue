<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
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
import depotInfoApi from "../../api/depot-info-api/depotInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info.js'
import tableBaseInfo from '../mixIns/table-base-info.js'

export default {
  name: "depotInfo",
  mixins: [searchBseInfo, tableBaseInfo],
  components: {
    "search-base": searchBase
  },

  props: {
    "branchID": {
      type: Number,
    }
  },

  data () {
    return {
      title: "仓库机构查询",

      titleData: [{ prop: "name", label: "名称" }],

      tableData: [],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);
      param.branchID = this.branchID;

      this.getDepotInfo(param).then(() => {
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
      previousParams.branchID = this.branchID;

      this.getDepotInfo(previousParams).then(() => {
        this.setPerviousInfo();
      });
    },

    getDepotInfo (params) {
      return depotInfoApi.getDepotInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },

    loadData () {
      var params = this.getParameterForNewTable(this.getParentID());
      params.branchID = this.branchID;

      this.getDepotInfo(params);
    }
  },

  created: function () {
    this.loadData();
  }
}
</script>

<style>
</style>
