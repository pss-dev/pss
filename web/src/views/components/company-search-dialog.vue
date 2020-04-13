<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
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
    ></search-base>
  </el-dialog>
</template>

<script>
import companyInfoApi from "../../api/company-info-api/companyInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info'

export default {
  name: "companyInfo",
  mixins: [searchBseInfo],
  components: {
    "search-base": searchBase
  },

  data () {
    return {
      dialogTitle: "来往单位查询",

      titleData: [
        { prop: "id", label: "编号" },
        { prop: "name", label: "名称" }],

      tableData: [],

      selectedInfo: null,

      fatherID: "",
      paths: [], // for previous

      currentPage: 1,
    }
  },

  methods: {
    handleClose () {
      this.$emit('closeDialog')
    },

    handleCurrentChange (value) {
      this.selectedInfo = value;
    },

    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getCompanyInfo(param).then(() => {
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

      this.getCompanyInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.fatherID = previousInfo.id;
      });
    },


    getcompanyInfo (params) {
      return companyInfoApi.getcompanyInfo(params).then(
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

    isSelectedInfoValid () {
      return this.selectedInfo != null;
    },

    hasFatherInfo () {
      return this.paths.length > 0;
    }
  },

  created: function () {
    var params = {
      id: this.fatherID,
    };

    this.getcompanyInfo(params);
  }
}
</script>

<style>
</style>
