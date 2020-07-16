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
        <el-button
          class="export-button"
          :disabled="isDateRangeValueInvalid()"
          @click="exportLogData"
        >导出</el-button>
      </div>
    </el-header>
    <el-main>
      <el-table
        :data="tableData.slice((currentPage - 1) * pageSize,currentPage * pageSize)"
        border
        highlight-current-row
        height="400"
        style="width: 100%"
      >
        <el-table-column prop="date" label="操作时间">
          <template slot-scope="scope">
            <span>{{ scope.row.date.substring(0, 10) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="employee" label="员工">
          <template slot-scope="scope">
            <span>{{ scope.row.employee }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="action" label="操作">
          <template slot-scope="scope">
            <span>{{ scope.row.action }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="resource" label="资源">
          <template slot-scope="scope">
            <span>{{ scope.row.resource }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="message" label="备注">
          <template slot-scope="scope">
            <span>{{ scope.row.message }}</span>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        class="pagination"
        @size-change="pageSizeChange"
        @current-change="pageChange"
        :small="true"
        :current-page="currentPage"
        :page-sizes="[30, 50, 100, 200]"
        layout="sizes, jumper, prev, next, total"
        :total="totalSize"
      ></el-pagination>
    </el-main>
  </el-container>
</template>

<script>
import TableBaseInfo from "@/views/mixIns/table-base-info.js"
import LogApi from "@/api/log-api/logApi.js"

export default {
  name: "accountInfo",
  mixins: [TableBaseInfo],

  components: {
  },

  props: {},

  data () {
    return {
      dateRangeValue: null,

      tableData: []
    }
  },

  methods: {
    searchLogData () {
      if (this.dateRangeValue.length != 2) {
        return;
      }

      let params = {
        startDate: this.dateRangeValue[0].getTime(),
        endDate: this.dateRangeValue[1].getTime()
      };

      LogApi.getLogData(params).then((res) => {
        this.tableData = res.data;
      });
    },

    isDateRangeValueInvalid () {
      return this.dateRangeValue == null;
    },

    exportLogData () {
      let params = "";

      if (this.dateRangeValue && this.dateRangeValue.length == 2) {
        params = "?startDate=" + this.dateRangeValue[0].getTime()
          + "&endDate=" + this.dateRangeValue[1].getTime()
      }

      window.open(LogApi.exportUrl + params);
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
