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
//import productInfoApi from "../../api/product-info-api/productInfoApi.js"
import DepotInfoApi from "../../api/depot-info-api/depotInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info'
import tableBaseInfo from '../mixIns/table-base-info.js'

export default {
  name: "productInfo",
  mixins: [searchBseInfo, tableBaseInfo],
  components: {
    "search-base": searchBase
  },

  props: {
    "depot": {
      type: Number,
    }
  },

  data () {
    return {
      title: "商品查询",

      titleData: [
        { prop: "product.identifier", label: "编号" },
        { prop: "product.name", label: "名称" },
        { prop: "productCount", label: "库存" }],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.product.id);
      param.depot = this.depot;

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
      var previousParams = this.getParameterForNewTable(this.getParentID0(previousInfo.parent.product));
      previousParams.depot = this.depot;

      this.getProductInfo(previousParams).then(() => {
        this.setPerviousInfo();
      });
    },

    getProductInfo (params) {
      return DepotInfoApi.getDepotProducts(params).then(
        (res) => {
          console.log("========== getProductInfo ", res);
          this.setResponseResult(res.data);
        });
    },

    loadData () {
      var params = this.getParameterForNewTable(this.getParentID());
      params.depot = this.depot;

      this.getProductInfo(params);
    }
  },

  created: function () {
    this.loadData();
  }
}
</script>

<style>
</style>
