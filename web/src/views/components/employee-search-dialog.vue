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
import EmployeeInfoApi from "../../api/employee-info-api/employeeInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info'

export default {
  name: "employeeSearchDialog",
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
      title: "员工信息查询",

      titData:
        [{ prop: "name", label: "名称" },
        { prop: "branch.name", label: "分支" },
        { prop: "department.name", label: "部门" }],

      selectedInfo: null,
    }
  },

  methods: {
    geEmployeeInfo (params) {
      return EmployeeInfoApi.geEmployeeInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    if (!this.tableData) {
      var params = this.getParameterForNewTable(this.getParentID());

      this.geEmployeeInfo(params);
    }
  }

}
</script>

<style>
</style>
