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
import UnitInfoApi from "../../api/unit-info-api/unitInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info'
import tableBaseInfo from '../mixIns/table-base-info.js'

export default {
  name: "unitSearchDialog",
  mixins: [searchBseInfo, tableBaseInfo],
  components: {
    "search-base": searchBase
  },

  props: {
    "productInfo": {
      type: Object
    },
  },

  data () {
    return {
      title: "单位信息查询",

      titleData: [
        { prop: "name", label: "名称" },
        { prop: "note", label: "备注" }],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getUnitInfo(param).then(() => {
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

      this.getUnitInfo(previousParams).then(() => {
        this.setPerviousInfo();
      });
    },

    getUnitInfo (params) {
      return UnitInfoApi.getUnitInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    console.log("=======created  ", this.productInfo);
    if (!!this.productInfo && !!this.productInfo.units) {
      console.log("=======tableData  ");

      this.productInfo.units.forEach((value) => {
        this.tableData.push(value.unit);
      });
    }
    else {
      var params = this.getParameterForNewTable(this.getParentID());

      this.getUnitInfo(params);
    }
  }

}
</script>

<style>
</style>
