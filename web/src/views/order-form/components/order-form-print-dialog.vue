
<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
    :width="'80%'"
    append-to-body
  >
    <div ref="print" id="print">
      <el-row :gutter="20">
        <el-col :span="4">客户姓名：</el-col>
        <el-col :span="4">{{orderFormData.company.name}}</el-col>
        <el-col :span="4">发货仓库：</el-col>
        <el-col :span="4">{{orderFormData.depot.name}}</el-col>
        <el-col :span="4">录单日期：</el-col>
        <el-col :span="4">{{orderFormData.createDate.toDateString()}}</el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="4">联系人：</el-col>
        <el-col :span="4">{{orderFormData.company.contactPerson}}</el-col>
        <el-col :span="4">经手人：</el-col>
        <el-col :span="4">{{orderFormData.employee.name}}</el-col>
        <el-col :span="4">付款方式：</el-col>
        <el-col :span="4">{{orderFormData.account.name}}</el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="4">联系电话：</el-col>
        <el-col :span="4">{{orderFormData.company.contactPhone}}</el-col>
        <el-col :span="4">摘要：</el-col>
        <el-col :span="12">{{orderFormData.summary}}</el-col>
      </el-row>

      <el-table
        class="print-table"
        :data="orderFormData.products"
        show-summary
        border
        style="width: 100%"
      >
        <el-table-column type="index" label="行号" width="50"></el-table-column>
        <el-table-column v-for="title in titles" v-bind="title" :key="title.prop" min-width="100px"></el-table-column>
      </el-table>
      <el-row>
        <el-col :span="4">总计大写：</el-col>
        <el-col :span="12">伍佰陆十元整</el-col>
      </el-row>
      <el-row>
        <el-col :span="6">抹零金额：</el-col>
        <el-col :span="6">0</el-col>
        <el-col :span="6">抹零后金额：</el-col>
        <el-col :span="6">560</el-col>
      </el-row>
      <el-row>
        <el-col :span="4">制单人：</el-col>
        <el-col :span="4">{{orderFormData.creatUser.name}}</el-col>
        <el-col :span="4">经手人：</el-col>
        <el-col :span="4">{{orderFormData.employee.name}}</el-col>
        <el-col :span="4">打印日期：</el-col>
        <el-col :span="4">{{(new Date()).toDateString()}}</el-col>
      </el-row>
      <el-row>
        <el-col :span="2">地址：</el-col>
        <el-col :span="10">西安市西安市西安市西安市西安市西安市西安市</el-col>
        <el-col :span="2">电话：</el-col>
        <el-col :span="4">029-123123123</el-col>
        <el-col :span="2">手机：</el-col>
        <el-col :span="4">123123123123</el-col>
      </el-row>
      <el-row>
        <el-col :span="2">银行账户：</el-col>
        <el-col :span="22">建设银行建设银行建设银行建设银行建设银行</el-col>
      </el-row>
      <el-row>
        <el-col :span="2">温馨提示：</el-col>
        <el-col :span="22">如果您在收货过程中有任何质疑，请在收货及时与本公司联系，非常感谢您对我们工作的支持！</el-col>
      </el-row>
    </div>
    <el-button v-print="'#print'"></el-button>
  </el-dialog>
</template>

<script>

export default {
  name: "printDialog",
  props: {
    "orderFormData": {
      type: Object
    }
  },

  data () {
    return {
      dialogVisible: true,

      titles:
        [{ prop: "product.identifer", label: "商品编号" },
        { prop: "product.name", label: "商品名称" },
        { prop: "unit.name", label: "单位" },
        { prop: "count", label: "数量" },
        { prop: "price", label: "单价" },
        { prop: "amount", label: "金额" },
        { prop: "note", label: "备注" }],
    }
  },

  methods: {
    handleClose () {
      this.$emit('closeDialog')
    },
  }

}
</script>

<style>
.print-table {
  border: 1px solid #ebeef5;
}
</style>
