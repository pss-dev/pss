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
      @close="handleClose"
      @ok="handleSubmit"
    ></search-base>
  </el-dialog>
</template>

<script>
import RulerInfoApi from "../../api/ruler-info-api/rulerInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info'

export default {
  name: "rulerSearchDialog",
  mixins: [searchBseInfo],
  components: {
    "search-base": searchBase
  },

  props: {
    "tableData": {
      type: Array
    },
  },

  data () {
    return {
      title: "权限查询",

      titleData: [
        { prop: "name", label: "名称" }],

      selectedInfo: null,
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getRulerInfo(param).then(() => {
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

      this.getRulerInfo(previousParams).then(() => {
        this.setPerviousInfo();
      });
    },

    getRulerInfo (params) {
      return RulerInfoApi.getRulerInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    if (!this.tableData) {
      var params = this.getParameterForNewTable(this.getParentID());

      this.getRulerInfo(params);
    }
  }

}
</script>

<style>
</style>
