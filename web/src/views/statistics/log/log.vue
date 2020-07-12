<template>
  <el-container>
    <el-header height="30">
      <div>
        <el-date-picker
          v-model="dateRangeValue"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
        <el-button
          class="search-button"
          :disabled="isDateRangeValueInvalid()"
          @click="searchLogData"
        >查询</el-button>
      </div>
    </el-header>
    <el-main>
      <base-info-table :titles="titData" :tableData="tableData"></base-info-table>
    </el-main>
  </el-container>
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
      dateRangeValue: null,
      titData:
        [{ prop: "date", label: "时间" },
        { prop: "employee", label: "员工" },
        { prop: "action", label: "操作" }],

      tableData: []
    }
  },

  methods: {
    searchLogData () {
      if (this.dateRangeValue.length != 2) {
        return;
      }

      var params = {
        startDate: this.dateRangeValue[0].getTime(),
        endDate: this.dateRangeValue[1].getTime()
      };
      console.log("====== searchLogData ", params);

      LogApi.getLogData(params).then((res) => {
        console.log(res);
        this.tableData = res;
      });
    },

    isDateRangeValueInvalid () {
      return this.dateRangeValue == null;
    },
  },

  created: function () {
  }
}
</script>

<style>
.search-row {
  margin-bottom: 5px;
}

.search-button {
  margin-left: 10px;
}
</style>
