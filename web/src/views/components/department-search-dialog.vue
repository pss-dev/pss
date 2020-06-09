<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="dialogTitle"
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
      @pageChange="pageChange"
      @getChildData="getChildData"
      @previous="previous"
      @ok="handleSubmit"
    ></search-base>
  </el-dialog>
</template>

<script>
import departmentInfoApi from "../../api/department-info-api/departmentInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info'

export default {
  name: "departmentInfo",
  mixins: [searchBseInfo],
  components: {
    "search-base": searchBase
  },

  data () {
    return {
      dialogTitle: "部门查询",

      titleData: [{ prop: "name", label: "名称" }],

      tableData: [],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getDepartmentInfo(param).then(() => {
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

      this.getDepartmentInfo(previousParams).then(() => {
        this.setPerviousInfo();
      });
    },


    getDepartmentInfo (params) {
      return departmentInfoApi.getDepartmentInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.getParentID());

    this.getDepartmentInfo(params);
  }
}
</script>

<style>
</style>
