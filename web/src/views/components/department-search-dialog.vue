<template>
  <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" :before-close="handleClose">
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

      titleData: [{ prop: "id", label: "编号" },
      { prop: "name", label: "名称" }],

      tableData: [{ id: "aaa", name: "bbb" },
      { id: "111aaa0", name: "bbb1" },
      { id: "aaa2", name: "bbb3" },
      { id: "aaa4", name: "bbb5" },
      { id: "aaa", name: "bbb" },
      { id: "222aaa0", name: "bbb1" },
      { id: "aaa2", name: "bbb3" },
      { id: "aaa4", name: "bbb5" },
      { id: "aaa", name: "bbb" },
      { id: "333aaa0", name: "bbb1" },
      { id: "aaa2", name: "bbb3" },
      { id: "aaa4", name: "bbb5" },
      { id: "aaa", name: "bbb" },
      { id: "444aaa0", name: "bbb1" },
      { id: "aaa2", name: "bbb3" },
      { id: "aaa4", name: "bbb5" }],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getDepartmentInfo(param).then(() => {
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

      this.getDepartmentInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.fatherID = previousInfo.id;
      });
    },


    getDepartmentInfo (params) {
      return departmentInfoApi.getDepartmentInfo(params).then(
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
    var params = {
      id: this.fatherID,
    };

    this.getDepartmentInfo(params);
  }
}
</script>

<style>
</style>
