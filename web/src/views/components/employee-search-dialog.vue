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
      :tableHeaderVisiable="false"
      @handleCurrentChange="handleCurrentChange"
      @close="handleClose"
      @ok="handleSubmit"
    ></search-base>
  </el-dialog>
</template>

<script>
import EmployeeInfoApi from "../../api/employee-info-api/employeeInfoApi.js"
import searchBase from "./search-base.vue"
import searchBseInfo from '../mixIns/search-base-info.js'
import tableBaseInfo from '../mixIns/table-base-info.js'

export default {
  name: "employeeSearchDialog",
  mixins: [searchBseInfo, tableBaseInfo],
  components: {
    "search-base": searchBase
  },

  props: {
  },

  data () {
    return {
      title: "员工信息查询",

      titleData:
        [{ prop: "name", label: "名称" },
        { prop: "branch.name", label: "分支" },
        { prop: "department.name", label: "部门" }],
    }
  },

  methods: {
    getEmployeeInfo () {
      return EmployeeInfoApi.getEmployeeInfo().then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    this.getEmployeeInfo();
  }

}
</script>

<style>
</style>
