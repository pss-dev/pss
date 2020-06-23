<template>
  <div>
    <el-container>
      <el-main>
        <el-card>
          <el-row class="el-row-bottom-20">
            <el-col :span="8">
              <el-date-picker
                v-model="dateRangeValue"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              ></el-date-picker>
            </el-col>
            <el-col :span="4">
              <div>
                <el-button @click="searchLogData">查询</el-button>
              </div>
            </el-col>
          </el-row>
          <el-row class="el-row-bottom-20">
            <el-col :span="4">入库结算：</el-col>
            <el-col :span="4">销售结算：</el-col>
          </el-row>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import LogApi from "@/api/log-api/logApi.js"

export default {
  name: "accountInfo",

  components: {
  },

  props: {},

  data () {
    return {
      dateRangeValue: [],
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
        startDate: this.dateRangeValue[0],
        endDate: this.dateRangeValue[1]
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
.el-row-bottom-20 {
  margin-bottom: 20px;
}
</style>
