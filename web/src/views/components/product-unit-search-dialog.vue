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
import productUnitInfoApi from "../../api/productUnit-info-api/productUnitInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info'

export default {
  name: "productUnitSearchDialog",
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
      title: "商品单位信息查询",

      titleData: [
        { prop: "id", label: "编号" },
        { prop: "name", label: "名称" }],

      selectedInfo: null,
    }
  },

  methods: {
    getProductUnitInfo (params) {
      return productUnitInfoApi.getProductUnitInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },

    setResponseResult (data) {
      if (data.totalSize && data.result) {
        this.totalSize = data.totalSize;
        this.tableData = data.result;
      }
    },
  },

  created: function () {
  }
}

</script>

<style>
</style>
