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
      title: "单位信息查询",

      titleData: [
        { prop: "name", label: "名称" },
        { prop: "note", label: "备注" }],

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
  },

  created: function () {
    if (!this.tableData) {
      var params = {
        id: this.fatherID,
      };

      this.getProductUnitInfo(params);
    }
  }

}
</script>

<style>
</style>
