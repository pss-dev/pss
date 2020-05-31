<template>
  <div>
    <el-row>
      <el-col :span="6">
        <div>
          <el-date-picker type="date" v-model="startDate" placeholder="选择日期"></el-date-picker>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
          <el-date-picker type="date" v-model="endDate" placeholder="选择日期"></el-date-picker>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
          <el-button size="small" @click="searchLogData">查询</el-button>
        </div>
      </el-col>
    </el-row>
    <base-info-table :titles="titData" :tableData="tableData"></base-info-table>
  </div>
</template>

<script>
import BaseInfoTabler from "../../base-info/components/base-info-table"

import LogApi from "@/api/log-api/logApi.js"

export default {
  name: "accountInfo",

  components: {
    "base-info-table": BaseInfoTabler,
  },

  props: {},

  data () {
    return {
      startDate: new Date(),
      endDate: new Date(),
      titData:
        [{ prop: "date", label: "时间" },
        { prop: "employee", label: "员工" },
        { prop: "action", label: "操作" }],

      tableData: []
    }
  },

  methods: {
    searchLogData () {
      var params = {
        startDate: this.startDate,
        endDate: this.endDate
      };
      console.log("====== searchLogData ", params);

      LogApi.getLogData(params).then((res) => {
        console.log(res);
        this.tableData = res;
      });
    }
  },

  created: function () {
  }
}
</script>

<style>
</style>
