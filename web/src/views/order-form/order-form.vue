<template>
  <div>
    <el-container>
      <el-header height="30">
        <el-card shadow="never">
          <div slot="header" class="card-header">
            <span>创建者: {{getCreateUserName()}}</span>
            <span class="verify-user">审核者: {{getVerifyUserName()}}</span>
            <span v-if="showVerifyMessage" class="verified-message">已审核</span>
          </div>
          <el-row :gutter="20" class="el-row-bottom-20">
            <el-col :span="6">
              <div class="date-picker-item">
                <el-date-picker
                  class="order-form-item"
                  type="date"
                  v-model="orderFormData.createDate"
                  @change="dateChange"
                  placeholder="选择日期"
                ></el-date-picker>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="order-form-item">
                <el-input readonly placeholder="分支机构" :value="getBranchName()">
                  <el-button
                    size="small"
                    @click="showBranchDialog"
                    slot="append"
                    icon="el-icon-search"
                  ></el-button>
                </el-input>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="order-form-item">
                <el-input readonly placeholder="来往单位" :value="getCompanyName()">
                  <el-button
                    size="small"
                    @click="showCompanyDialog"
                    slot="append"
                    icon="el-icon-search"
                  ></el-button>
                </el-input>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="order-form-item">
                <el-input readonly placeholder="经手人" :value="getEmployeeName()">
                  <el-button
                    size="small"
                    @click="showEmployeeDialog"
                    slot="append"
                    icon="el-icon-search"
                  ></el-button>
                </el-input>
              </div>
            </el-col>
          </el-row>

          <el-row :gutter="20" class="el-row-second">
            <el-col :span="6">
              <div class="order-form-item">
                <el-input readonly placeholder="部门" :value="getDepartmentName()">
                  <el-button
                    size="small"
                    @click="showDepartmentDialog"
                    slot="append"
                    icon="el-icon-search"
                  ></el-button>
                </el-input>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="order-form-item">
                <el-input readonly placeholder="仓库" :value="getDepotName()">
                  <el-button
                    size="small"
                    @click="showDepotDialog"
                    slot="append"
                    icon="el-icon-search"
                  ></el-button>
                </el-input>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <el-input maxlength="30" placeholder="摘要" v-model="orderFormData.summary"></el-input>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-header>
      <el-main>
        <div>
          <el-row class="el-row-third">
            <el-col class="add-product-button" :span="12">
              <el-button :disabled="verified()" @click="showProductSelectDialog(null)">添加商品</el-button>
            </el-col>
            <el-col class="price-select" :span="12">
              <el-select v-model="defaultPriceID" placeholder="默认价格">
                <el-option
                  v-for="item in prices"
                  :key="item.id"
                  :label="item.label"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-col>
          </el-row>
        </div>

        <div>
          <el-table
            :data="orderFormData.products"
            height="400"
            style="width: 100%"
            :row-class-name="tableRowClassName"
            border
            show-summary
          >
            <el-table-column type="index" label="行号" width="50"></el-table-column>
            <el-table-column prop="product.identifier" label="商品" width="130">
              <template slot-scope="scope">
                <el-input
                  :disabled="getDisable(scope.row)"
                  readonly
                  v-model="scope.row.product.identifier"
                  placeholder="商品选择"
                >
                  <el-button
                    size="small"
                    :disabled="getDisable(scope.row)"
                    @click="showProductSelectDialog(scope.row)"
                    slot="append"
                    icon="el-icon-search"
                  ></el-button>
                </el-input>
              </template>
            </el-table-column>
            <el-table-column prop="productName" label="商品名称">
              <template slot-scope="scope">
                <span>{{scope.row.product.name}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="product.unit.name" label="单位">
              <template slot-scope="scope">
                <el-input
                  :disabled="getDisable(scope.row)"
                  readonly
                  v-model="scope.row.unit.name"
                  placeholder="单位选择"
                >
                  <el-button
                    size="small"
                    :disabled="getDisable(scope.row)"
                    @click="showProductUnitSelectDialog(scope)"
                    slot="append"
                    icon="el-icon-search"
                  ></el-button>
                </el-input>
              </template>
            </el-table-column>
            <el-table-column v-if="orderFormData.status == 1" prop="stock" label="账面库存">
              <template slot-scope="scope">
                <span>{{scope.row.stock}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="count" label="数量">
              <template slot-scope="scope">
                <el-input
                  :disabled="getDisable(scope.row)"
                  @change="countChange(scope.row)"
                  v-model="scope.row.count"
                  placeholder="数量"
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="price" label="单价">
              <template slot-scope="scope">
                <el-input
                  :disabled="getDisable(scope.row)"
                  @change="priceChange(scope.row)"
                  v-model="scope.row.price"
                  placeholder="单价"
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="amount" label="金额">
              <template slot-scope="scope">
                <el-input
                  :disabled="getDisable(scope.row)"
                  @change="amountChange(scope.row)"
                  v-model="scope.row.amount"
                  placeholder="金额"
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="note" label="备注">
              <template slot-scope="scope">
                <el-input
                  :disabled="getDisable(scope.row)"
                  v-model="scope.row.note"
                  placeholder="备注"
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="specification" label="规格">
              <template slot-scope="scope">
                <span>{{scope.row.specification}}</span>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="50">
              <template slot-scope="scope">
                <el-button
                  :disabled="getDisable(scope.row)"
                  @click.native.prevent="deleteRow(scope.row, scope.$index)"
                  type="text"
                  size="small"
                >移除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="account-pane">
          <el-row>
            <el-col :span="3">
              <el-input placeholder="账户选择" :value="getAccountName()">
                <el-button
                  size="small"
                  slot="append"
                  icon="el-icon-search"
                  @click="showAccountDialog()"
                ></el-button>
              </el-input>
            </el-col>
            <el-col :span="2" class="money-label">
              <label class="account-label">收款金额：</label>
            </el-col>
            <el-col :span="3">
              <el-input placeholder="收款金额" v-model="orderFormData.money" @change="moneyChange"></el-input>
            </el-col>
            <el-col :span="2" class="money-label">
              <label class="account-label">抹零金额：</label>
            </el-col>
            <el-col :span="3">
              <el-input placeholder="抹零金额" v-model="orderFormData.wipe" @change="moneyChange"></el-input>
            </el-col>
            <el-col :span="2" class="money-label">
              <label class="account-label">抹零后金额：</label>
            </el-col>
            <el-col :span="3">
              <el-input readonly placeholder="抹零后金额" v-model="afterWipe"></el-input>
            </el-col>
          </el-row>
        </div>
      </el-main>
      <el-footer>
        <div>
          <el-row>
            <el-col :span="2">
              <el-button v-if="writePermission" :disabled="verified()" @click="save()">保存草稿</el-button>
            </el-col>
            <el-col :span="2">
              <el-button
                v-if="verifyPermission"
                :disabled="verified()"
                @click="verifyOrderForm"
              >审核过账</el-button>
            </el-col>
            <el-col :span="2">
              <el-button @click="showPrintDialog">打印</el-button>
            </el-col>
          </el-row>
        </div>
      </el-footer>
    </el-container>

    <branch-search-dialog
      v-if="branchDialogVisiable"
      @submitData="submitBranchData"
      @closeDialog="closeBranchDialog"
    ></branch-search-dialog>
    <company-search-dialog
      v-if="companyDialogVisiable"
      :companyType="companyType"
      @submitData="submitCompanyData"
      @closeDialog="closeCompanyDialog"
    ></company-search-dialog>
    <employee-search-dialog
      v-if="employeeDialogVisiable"
      @submitData="submitEmployeeData"
      @closeDialog="closeEmployeeDialog"
    ></employee-search-dialog>
    <department-search-dialog
      v-if="departmentDialogVisiable"
      @submitData="submitDepartmentData"
      @closeDialog="closeDepartmentDialog"
    ></department-search-dialog>
    <depot-search-dialog
      v-if="depotDialogVisiable"
      :branchID="getBranchID()"
      @submitData="submitDepotData"
      @closeDialog="closeDepotDialog"
    ></depot-search-dialog>
    <product-search-dialog
      v-if="productDialogVisiable"
      :depot="getDepotID()"
      @submitData="submitProductData"
      @closeDialog="closeProductDialog"
    ></product-search-dialog>
    <unit-search-dialog
      v-if="productUnitDialogVisiable"
      :productInfo="scopeValue.product"
      @submitData="submitProductUnitData"
      @closeDialog="closeProductUnitDialog"
    ></unit-search-dialog>
    <account-search-dialog
      v-if="accountDialogVisiable"
      @submitData="submitAccountData"
      @closeDialog="closeAccountDialog"
    ></account-search-dialog>
    <print-dialog
      v-if="printDialogVisiable"
      :orderFormData="orderFormData"
      :amountMoney="getAmountMoney()"
      :afterWipe="afterWipe"
      @closeDialog="closePrintDialog"
    ></print-dialog>
  </div>
</template>

<script>
import DepartmentSearchDialog from "../components/department-search-dialog.vue"
import BranchSearchDialog from "../components/branch-search-dialog.vue"
import CompanySearchDialog from "../components/company-search-dialog.vue"
import EmployeeSearchDialog from "../components/employee-search-dialog"
import DepotSearchDialog from "../components/depot-search-dialog.vue"
import ProductSearchDialog from "../components/product-search-dialog.vue"
import UnitSearchDialog from "../components/unit-search-dialog.vue"
import AccountSearchDialog from "../components/account-search-dialog.vue"
import PrintDialog from "./components/order-form-print-dialog"

import orderFormApi from "../../api/order-form-api/orderFormApi.js"
import PriceInfoApi from "../../api/price-info-api/priceInfoApi.js"
import Tool from '@/views/constant/tool.js'
import PermissionBase from '@/views/mixIns/permission-base.js'
import RuleTool from '@/views/constant/rule-tool.js'

export default {
  name: "orderForm",
  mixins: [PermissionBase],
  components: {
    "department-search-dialog": DepartmentSearchDialog,
    "branch-search-dialog": BranchSearchDialog,
    "company-search-dialog": CompanySearchDialog,
    "employee-search-dialog": EmployeeSearchDialog,
    "depot-search-dialog": DepotSearchDialog,
    "product-search-dialog": ProductSearchDialog,
    "unit-search-dialog": UnitSearchDialog,
    "account-search-dialog": AccountSearchDialog,
    "print-dialog": PrintDialog
  },

  props: {
    "orderFormDataValue": {
      type: Object,
    },
    "orderFormType": {
      type: Number,
    }
  },

  data () {
    return {
      prices: [],
      defaultPriceID: -1,
      scopeValue: null,
      companyType: 0,
      showVerifyMessage: false,

      orderFormData: {
        id: null,
        type: 1,
        status: 1,// 用来判断是草稿还是已经审核过的
        creatUser: null, //由谁创建
        verifyUser: null, //由谁审核过账
        createDate: new Date(),
        branch: null,
        company: null,
        employee: null,
        department: null,
        depot: null,
        summary: 'asdasdasd',

        actionType: Tool.actionType.add,

        products: [
        ],

        account: null,
        money: 0, // 收款
        wipe: 0, // 抹零
      },

      afterWipe: 0,

      departmentDialogVisiable: false,
      branchDialogVisiable: false,
      companyDialogVisiable: false,
      employeeDialogVisiable: false,
      depotDialogVisiable: false,
      productDialogVisiable: false,
      productUnitDialogVisiable: false,
      accountDialogVisiable: false,
      printDialogVisiable: false
    }
  },

  methods: {

    getDepotID () {
      return this.orderFormData.depot ? this.orderFormData.depot.id : null;
    },

    getBranchID () {
      return this.orderFormData.branch ? this.orderFormData.branch.id : null;
    },

    getCreateUserName () {
      return this.orderFormData.creatUser ? this.orderFormData.creatUser.name : "";
    },

    getVerifyUserName () {
      return this.orderFormData.verifyUser ? this.orderFormData.verifyUser.name : "";
    },

    getBranchName () {
      return this.orderFormData.branch ? this.orderFormData.branch.name : "";
    },

    getCompanyName () {
      return this.orderFormData.company ? this.orderFormData.company.name : "";
    },

    getEmployeeName () {
      return this.orderFormData.employee ? this.orderFormData.employee.name : "";
    },

    getDepartmentName () {
      return this.orderFormData.department ? this.orderFormData.department.name : "";
    },

    getDepotName () {
      return this.orderFormData.depot ? this.orderFormData.depot.name : "";
    },

    getAccountName () {
      return this.orderFormData.account ? this.orderFormData.account.name : "";
    },

    dateChange (value) {
      this.orderFormData.createDate = value;
    },

    showBranchDialog () {
      this.branchDialogVisiable = true;
    },

    closeBranchDialog () {
      this.branchDialogVisiable = false;
    },

    submitBranchData (branchValue) {
      this.orderFormData.branch = branchValue;

      this.branchDialogVisiable = false;
    },

    showCompanyDialog () {
      this.companyDialogVisiable = true;
    },

    closeCompanyDialog () {
      this.companyDialogVisiable = false;
    },

    submitCompanyData (companyValue) {
      this.orderFormData.company = companyValue;

      this.companyDialogVisiable = false;
    },

    showEmployeeDialog () {
      this.employeeDialogVisiable = true;
    },

    closeEmployeeDialog () {
      this.employeeDialogVisiable = false;
    },

    submitEmployeeData (employeeValue) {
      this.orderFormData.employee = employeeValue;

      this.employeeDialogVisiable = false;
    },

    showDepartmentDialog () {
      this.departmentDialogVisiable = true;
    },

    closeDepartmentDialog () {
      this.departmentDialogVisiable = false;
    },

    submitDepartmentData (departmentValue) {
      this.orderFormData.department = departmentValue;

      this.departmentDialogVisiable = false;
    },

    showDepotDialog () {
      if (this.getBranchID() == null) {
        this.$message({
          message: `请先选择分支结构！`,
          showClose: true
        });
      }
      else {
        this.depotDialogVisiable = true;
      }
    },

    closeDepotDialog () {
      this.depotDialogVisiable = false;
    },

    submitDepotData (depotValue) {
      this.orderFormData.depot = depotValue;

      this.depotDialogVisiable = false;
    },

    showProductSelectDialog (row) {
      if (this.getDepotID() == null) {
        this.$message({
          message: `请先选择仓库！`,
          showClose: true
        });
      }
      else {
        this.productDialogVisiable = true;
        this.scopeValue = row;
      }
    },

    closeProductDialog () {
      this.productDialogVisiable = false;
    },

    submitProductData (productValue) {
      let defaultUnit = {};

      if (this.orderFormType == Tool.orderFormType.purchaseForm ||
        this.orderFormType == Tool.orderFormType.purchaseReturn) {
        defaultUnit = productValue.product.purchaseDefaultUnit;
      }
      else {
        defaultUnit = productValue.product.sellDefaultUnit;
      }

      if (this.scopeValue != null) {
        this.scopeValue.product = productValue.product;
        this.scopeValue.stock = productValue.productCount
        this.scopeValue.unit = defaultUnit;
      }
      else {
        let pvalue = {
          id: null,
          product: productValue.product,
          unit: defaultUnit,
          stock: productValue.productCount,
          count: 0,
          price: 0,
          amount: 0,
          note: '',
          actionType: Tool.actionType.add
        };
        console.log("======== pvalue ", pvalue, productValue);
        this.orderFormData.products.push(pvalue);
        this.scopeValue = pvalue;
      }

      this.setPrice();

      this.productDialogVisiable = false;
    },

    setPrice () {
      let pricesValue = [];

      //get units
      this.scopeValue.product.units.forEach((value) => {
        console.log("=====setPrice ", this.scopeValue, value);
        if (this.scopeValue.unit.id == value.unit.id) {
          pricesValue = value.prices;
        }
      });

      //get price value
      console.log("=====pricesValue ", pricesValue);
      pricesValue.forEach((value) => {
        if (value.price.id == this.defaultPriceID) {
          this.scopeValue.price = value.value;
        }
      });
    },

    showProductUnitSelectDialog (scope) {
      this.productUnitDialogVisiable = true;
      this.scopeValue = scope.row;
    },

    closeProductUnitDialog () {
      this.productUnitDialogVisiable = false;
    },

    submitProductUnitData (productUnitValue) {
      this.scopeValue.unit = productUnitValue;
      this.setPrice();

      this.productUnitDialogVisiable = false;
    },

    showAccountDialog () {
      this.accountDialogVisiable = true;
    },

    closeAccountDialog () {
      this.accountDialogVisiable = false;
    },

    submitAccountData (accountValue) {
      this.orderFormData.account = accountValue;

      this.accountDialogVisiable = false;
    },

    tableRowClassName ({ row }) {
      if (row.actionType == Tool.actionType.delete) {
        return 'warning-row';
      }

      return '';
    },

    deleteRow (row, index) {
      if (row.actionType == Tool.actionType.add) {
        this.orderFormData.products.splice(index, 1);
      }
      else {
        row.actionType = Tool.actionType.delete;
      }
    },

    getDisable (row) {
      return row.actionType == Tool.actionType.delete;
    },

    showPrintDialog () {
      this.printDialogVisiable = true;
    },

    closePrintDialog () {
      this.printDialogVisiable = false;
    },

    save () {
      console.log("=========== save ", this.orderFormData);
      if (!this.formValid()) {
        return;
      }

      if (this.orderFormData.actionType == Tool.actionType.add) {
        orderFormApi.addOrderForm(this.orderFormData).then((res) => {
          console.log(res);
          this.orderFormData.id = res.data;

          this.$message({
            message: `保存成功`,
            showClose: true
          });
        });
      }
      else {
        orderFormApi.saveOrderForm(this.orderFormData).then((res) => {
          console.log(res);
          this.$message({
            message: `保存成功`,
            showClose: true
          });
        });
      }
    },

    verifyOrderForm () {
      if (!this.formValid()) {
        return;
      }

      orderFormApi.verifyOrderForm(this.orderFormData).then((res) => {
        console.log(res);

        this.orderFormData.status = 2;
        this.showVerifyMessage = true;
        this.$message({
          message: `单据审核成功`,
          showClose: true
        });
      });
    },

    amountChange (row) {
      if (row.count != 0) {
        row.price = row.amount / row.count;
      }
    },

    countChange (row) {
      row.amount = row.price * row.count;
    },

    priceChange (row) {
      row.amount = row.price * row.count;
    },

    moneyChange () {
      this.afterWipe = this.orderFormData.money - this.orderFormData.wipe;
    },

    getPricesData () {
      PriceInfoApi.getPriceInfo().then((res) => {
        console.log("========getPricesData  ", res);
        this.prices = res.data;

        if (this.prices.length > 0) {
          this.defaultPriceID = this.prices[0].id;
        }
      });
    },

    verified () {
      return this.orderFormData.status == 2;
    },

    getAmountMoney () {
      let amount = 0;

      this.orderFormData.products.forEach((product) => {
        console.log("===== product.amount ", product.amount);
        amount += parseInt(product.amount);
      });
      console.log("=========amount  ", amount);
      return amount;
    },

    initOrderForm (ovalue) {
      if (ovalue) {
        this.orderFormData = ovalue;
        this.orderFormType = ovalue.type;
      }
      else {
        this.orderFormData.type = this.orderFormType;
      }

      if (this.orderFormType == Tool.orderFormType.purchaseForm ||
        this.orderFormType == Tool.orderFormType.purchaseReturn) {
        this.companyType = 1;
      }
      else {
        this.companyType = 0;
      }

      this.afterWipe = this.getAmountMoney() - this.orderFormData.wipe;
      this.orderFormData.createDate = new Date(this.orderFormData.createDate);
    },

    formValid () {
      let message = "";
      let valid = true;

      if (this.orderFormData.branch == null) {
        message = "请选择分支机构";
        valid = false;
      }
      else if (this.orderFormData.company == null) {
        message = "请选择来往公司";
        valid = false;
      }
      else if (this.orderFormData.employee == null) {
        message = "请选择经手人";
        valid = false;
      }
      else if (this.orderFormData.department == null) {
        message = "请选择部门";
        valid = false;
      }
      else if (this.orderFormData.depot == null) {
        message = "请选择仓库";
        valid = false;
      }
      else if (this.orderFormData.account == null) {
        message = "请选择账户";
        valid = false;
      }

      this.$message({
        message: message,
        showClose: true
      });

      return valid;
    }
  },

  created: function () {
    this.getPricesData();
    this.initPermission(RuleTool.resource.orderForm);

    if (this.orderFormDataValue && this.orderFormDataValue.id != null && this.orderFormDataValue.status == 1) {
      orderFormApi.initOrderForm(this.orderFormDataValue).then((res) => {
        console.log("========= init ", res);
        this.initOrderForm(res.data);
      });
    }
    else {
      this.initOrderForm(this.orderFormDataValue);

      if (this.orderFormData.status == 2) {
        this.showVerifyMessage = true;
      }
    }
  }
}
</script>

<style>
.el-row-bottom-20 {
  margin-bottom: 20px;
}

.el-row-third {
  margin-bottom: 5px;
}

.order-form-item {
  width: 220px;
}

.date-picker-item {
  text-align: left;
}

.add-product-button {
  text-align: left;
}

.price-select {
  text-align: right;
}

.el-table .warning-row {
  background: oldlace;
}

.card-header {
  text-align: left;
}

.verify-user {
  margin-left: 10px;
}

.account-pane {
  margin-top: 10px;
}

.account-label {
  height: 40px;
  line-height: 40px;
}

.verified-message {
  float: right;
  color: red;
  font-size: 20px;
}

.money-label {
  text-align: right;
}
</style>
