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
                <el-button @click="getRevenue">查询</el-button>
              </div>
            </el-col>
          </el-row>
          <el-row class="el-row-bottom-20">
            <el-col :span="4">入库单数量：{{revenueData.purchaseCount}}</el-col>
            <el-col :span="4">入库退货数量：{{revenueData.sellCount}}</el-col>
            <el-col :span="4">销售单数量：{{revenueData.purchaseReturnCount}}</el-col>
            <el-col :span="4">销售退货数量：{{revenueData.sellReturnCount}}</el-col>
          </el-row>
          <el-row class="el-row-bottom-20">
            <el-col :span="4">入库结算：{{revenueData.expenditure}}</el-col>
            <el-col :span="4">销售结算：{{revenueData.income}}</el-col>
            <el-col :span="4">利润：{{revenueData.profit}}</el-col>
          </el-row>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import RevenueApi from "@/api/revenue-api/revenueApi.js"
import Tool from "../../constant/tool.js"

export default {
  name: "accountInfo",

  data () {
    return {
      dateRangeValue: [],
      revenueData: {
        purchaseCount: '',
        sellCount: '',
        purchaseReturnCount: '',
        sellReturnCount: '',
        expenditure: '',
        income: '',
        profit: '',
      }
    }

  },

  methods: {
    getRevenue () {
      if (this.dateRangeValue.length != 2) {
        this.$message({
          message: `请先时间区域！`,
          showClose: true
        });
        return;
      }

      let searchModelData = {
        orderFormType: null,
        orderFormStatus: Tool.orderFormStatus.verify,
        startDate: this.dateRangeValue[0],
        endDate: this.dateRangeValue[1],
        branchID: null,
        companyID: null,
        employeeID: null,
        departmentID: null,
        depotID: null,
        accountID: null,
      };

      RevenueApi.getRevenue(searchModelData).then((res) => {
        console.log(res);
        this.revenueData = res.data;
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
