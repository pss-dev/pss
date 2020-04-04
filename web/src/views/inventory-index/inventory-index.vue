<template>
  <div class="inventory-index">
    <index-header></index-header>
    <el-table
      :data="tableData"
      style="width: 100%;"
    >
      <el-table-column
        v-for="item in columns"
        v-bind="item"
        :key="item.propVal"
        width="180"
      >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import indexHeader from "./components/index-header";
export default {
  components: {
    indexHeader
  },
  data() {
    return {
      columns: [
        {
          label: "日期",
          prop: "date"
        },
        {
          label: "姓名",
          prop: "name"
        },
        {
          label: "地址",
          prop: "address"
        }
      ],
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄"
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        }
      ]
    };
  },
  methods() {}
};
</script>

<style></style>
