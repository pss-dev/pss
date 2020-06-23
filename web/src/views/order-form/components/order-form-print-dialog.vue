
<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
    :width="'80%'"
  >
    <el-card class="prind-card">
      <div ref="print" id="print">
        <div class="print-title">{{formInfo.title}}</div>
        <el-row :gutter="10">
          <el-col :span="3">客户姓名：</el-col>
          <el-col :span="5">{{orderFormData.company.name}}</el-col>
          <el-col :span="3">发货仓库：</el-col>
          <el-col :span="5">{{orderFormData.depot.name}}</el-col>
          <el-col :span="3">录单日期：</el-col>
          <el-col :span="5">{{createDate}}</el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="3">联系人：</el-col>
          <el-col :span="5">{{orderFormData.company.contactPerson}}</el-col>
          <el-col :span="3">经手人：</el-col>
          <el-col :span="5">{{orderFormData.employee.name}}</el-col>
          <el-col :span="3">付款方式：</el-col>
          <el-col :span="5">{{orderFormData.account.name}}</el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="3">联系电话：</el-col>
          <el-col :span="5">{{orderFormData.company.contactPhone}}</el-col>
          <el-col :span="3">摘要：</el-col>
          <el-col :span="13">{{orderFormData.summary}}</el-col>
        </el-row>

        <el-table
          class="print-table"
          :data="orderFormData.products"
          show-summary
          border
          style="width: 100%"
        >
          <el-table-column type="index" label="行号" width="50"></el-table-column>
          <el-table-column
            v-for="title in titles"
            v-bind="title"
            :key="title.prop"
            min-width="50px"
          ></el-table-column>
        </el-table>
        <el-row :gutter="10">
          <el-col :span="3">总计大写：</el-col>
          <el-col :span="13">{{bigMoney}}</el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="3">收款金额：</el-col>
          <el-col :span="5">{{orderFormData.money}}</el-col>
          <el-col :span="3">抹零金额：</el-col>
          <el-col :span="5">{{orderFormData.wipe}}</el-col>
          <el-col :span="4">抹零后金额：</el-col>
          <el-col :span="4">{{afterWipe}}</el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="3">制单人：</el-col>
          <el-col :span="5">{{orderFormData.creatUser.name}}</el-col>
          <el-col :span="3">经手人：</el-col>
          <el-col :span="5">{{orderFormData.employee.name}}</el-col>
          <el-col :span="3">打印日期：</el-col>
          <el-col :span="5">{{printDate}}</el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="3">地址：</el-col>
          <el-col :span="13">{{orderFormData.company.address}}</el-col>
          <el-col :span="3">电话：</el-col>
          <el-col :span="5">{{orderFormData.company.contactPhone}}</el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="3">银行账户：</el-col>
          <el-col :span="21">{{accountInfo}}</el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="3">温馨提示：</el-col>
          <el-col :span="21">{{formInfo.prompt}}</el-col>
        </el-row>
      </div>
    </el-card>

    <div class="print-footer">
      <el-button>打印设置</el-button>
      <el-button v-print="'#print'">打印</el-button>
    </div>
  </el-dialog>
</template>

<script>
function smalltoBIG (n) {
  var fraction = ['角', '分'];
  var digit = ['零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'];
  var unit = [
    ['元', '万', '亿'],
    ['', '拾', '佰', '仟']
  ];
  var head = n < 0 ? '欠' : '';
  n = Math.abs(n);

  var s = '';

  for (var k = 0; k < fraction.length; k++) {
    s += (digit[Math.floor(n * 10 * Math.pow(10, k)) % 10] + fraction[k]).replace(/零./, '');
  }
  s = s || '整';
  n = Math.floor(n);

  for (var i = 0; i < unit[0].length && n > 0; i++) {
    var p = '';
    for (var j = 0; j < unit[1].length && n > 0; j++) {
      p = digit[n % 10] + unit[1][j] + p;
      n = Math.floor(n / 10);
    }
    s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
  }
  return head + s.replace(/(零.)*零元/, '元').replace(/(零.)+/g, '零').replace(/^整$/, '零元整');
}

export default {
  name: "printDialog",
  props: {
    "orderFormData": {
      type: Object
    },
    "afterWipe": {
      type: Number
    },
    "amountMoney": {
      type: Number
    }
  },

  data () {
    return {
      dialogVisible: true,
      title: "打印预览",

      titles:
        [{ prop: "product.identifier", label: "商品编号" },
        { prop: "product.name", label: "商品名称" },
        { prop: "unit.name", label: "单位" },
        { prop: "count", label: "数量" },
        { prop: "price", label: "单价" },
        { prop: "amount", label: "金额" },
        { prop: "note", label: "备注" }],

      formInfo: {
        title: "谢谢使用",
        prompt: "如果您在收货过程中有任何质疑，请在收货及时与本公司联系，非常感谢您对我们工作的支持！"
      },

      bigMoney: '',
      accountInfo: '',
      createDate: '',
    }
  },

  methods: {
    handleClose () {
      this.$emit('closeDialog')
    },

    getAccountInfo () {
      let result = '';

      if (this.orderFormData.account.bank) {
        result += this.orderFormData.account.bank + '  ';
      }

      if (this.orderFormData.account.account) {
        result += this.orderFormData.account.account + '  ';
      }

      if (this.orderFormData.account.cardholder) {
        result += this.orderFormData.account.cardholder + '  ';
      }

      return result;
    },

    getDateLabel (date) {
      let year = date.getFullYear()
      let month = date.getMonth() + 1
      let day = date.getDate()
      if (month < 10) month = '0' + month
      if (day < 10) day = '0' + day

      return year + '-' + month + '-' + day
    }
  },

  created: function () {
    this.bigMoney = smalltoBIG(this.amountMoney);
    this.accountInfo = this.getAccountInfo();
    this.createDate = this.getDateLabel(this.orderFormData.createDate);
    this.printDate = this.getDateLabel(new Date());
  }

}
</script>

<style>
.print-table {
  border: 1px solid #ebeef5;
  margin-bottom: 10px;
}
.print-title {
  text-align: center;
  font: 2em sans-serif;
  margin-bottom: 5px;
}
.prind-card {
  width: 750px;
  margin: 0px auto;
}
.el-row {
  margin-bottom: 5px;
}
.el-col {
  text-align: left;
}

.print-footer {
  margin-top: 10px;
}
</style>
