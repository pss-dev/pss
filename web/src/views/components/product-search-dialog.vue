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
import productInfoApi from "../../api/product-info-api/productInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info'
import tableBaseInfo from '../mixIns/table-base-info.js'

export default {
  name: "productInfo",
  mixins: [searchBseInfo, tableBaseInfo],
  components: {
    "search-base": searchBase
  },

  data () {
    return {
      title: "商品查询",

      titleData: [
        { prop: "id", label: "编号" },
        { prop: "name", label: "名称" },
        { prop: "number", label: "库存" }],

      tableData: [],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getProductInfo(param).then(() => {
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

      this.getProductInfo(previousParams).then(() => {
        this.setPerviousInfo();
      });
    },

    getProductInfo (params) {
      return productInfoApi.getProductInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.getParentID());

    this.getProductInfo(params);
  }
}
</script>

<style>
</style>
