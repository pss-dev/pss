<template>
  <div>
    <div>
      <el-row>
        <el-col :span="6">
          <div>
            <el-date-picker type="date" v-model="orderFormData.createDate" placeholder="选择日期"></el-date-picker>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="inputBlock">
            <el-input placeholder="分支机构" v-model="orderFormData.branch.name">
              <el-button size="small" @click="showBranchDialog" slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="inputBlock">
            <el-input placeholder="来往单位">
              <el-button
                size="small"
                @click="showCompanyDialog"
                slot="append"
                icon="el-icon-search"
              ></el-button>
            </el-input>
          </div>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="6">
          <div class="inputBlock">
            <el-input placeholder="经手人">
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
          <div class="inputBlock">
            <el-input placeholder="部门">
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
          <div class="inputBlock">
            <el-input placeholder="仓库">
              <el-button size="small" @click="showDepotDialog" slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </div>
        </el-col>
      </el-row>
    </div>

    <div>
      <el-row>
        <el-col :span="6">
          <el-button size="small" @click="showProductSelectDialog(null)">添加商品</el-button>
        </el-col>
        <el-col :span="6">
          <el-select v-model="defaultPrice" placeholder="默认价格">
            <el-option v-for="item in prices" :key="item.id" :label="item.label" :value="item.id"></el-option>
          </el-select>
        </el-col>
      </el-row>
    </div>

    <div>
      <el-table :data="orderFormData.product" height="400" style="width: 100%" border show-summary>
        <el-table-column prop="productID" label="商品" width="130">
          <template slot-scope="scope">
            <el-input v-model="scope.row.productID" placeholder="商品选择">
              <el-button
                size="small"
                @click="showProductSelectDialog(scope)"
                slot="append"
                icon="el-icon-search"
              ></el-button>
            </el-input>
          </template>
        </el-table-column>
        <el-table-column prop="productName" label="商品名称">
          <template slot-scope="scope">
            <span>{{scope.row.productName}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="productUnit" label="单位">
          <template slot-scope="scope">
            <el-input v-model="scope.row.productUnit" placeholder="商品选择">
              <el-button
                size="small"
                @click="showProductUnitSelectDialog(scope)"
                slot="append"
                icon="el-icon-search"
              ></el-button>
            </el-input>
          </template>
        </el-table-column>
        <el-table-column prop="stock" label="账面库存">
          <template slot-scope="scope">
            <span>{{scope.row.stock}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="count" label="数量">
          <template slot-scope="scope">
            <el-input v-model="scope.row.count" placeholder="数量"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="price" label="单价">
          <template slot-scope="scope">
            <el-input v-model="scope.row.price" placeholder="单价"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="amount" label="金额">
          <template slot-scope="scope">
            <el-input v-model="scope.row.amount" placeholder="金额"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="note" label="备注">
          <template slot-scope="scope">
            <el-input v-model="scope.row.note" placeholder="备注"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="specification" label="规格">
          <template slot-scope="scope">
            <span>{{scope.row.specification}}</span>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div>
      <el-row>
        <el-col :span="3">
          <el-input placeholder="账户选择">
            <el-button
              size="small"
              slot="append"
              icon="el-icon-search"
              @click="showAccountDialog()"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="3">
          <el-input placeholder="收款金额"></el-input>
        </el-col>
        <el-col :span="3">
          <el-input placeholder="抹零金额"></el-input>
        </el-col>
        <el-col :span="3">
          <el-input disabled="true" placeholder="抹零后金额"></el-input>
        </el-col>
        <el-col :span="3">
          <el-button @click="save()">保存草稿</el-button>
        </el-col>
        <el-col :span="3">
          <el-button @click="verifyOrderForm()">审核过账</el-button>
        </el-col>
      </el-row>
    </div>

    <branch-search-dialog
      v-if="branchDialogVisiable"
      @submitData="submitBranchData"
      @closeDialog="closeBranchDialog"
    ></branch-search-dialog>
    <company-search-dialog
      v-if="companyDialogVisiable"
      @submitData="submitCompanyData"
      @closeDialog="closeCompanyDialog"
    ></company-search-dialog>
    <department-search-dialog
      v-if="departmentDialogVisiable"
      @submitData="submitDepartmentData"
      @closeDialog="closeDepartmentDialog"
    ></department-search-dialog>
    <depot-search-dialog
      v-if="depotDialogVisiable"
      @submitData="submitDepotData"
      @closeDialog="closeDepotDialog"
    ></depot-search-dialog>
    <product-search-dialog
      v-if="productDialogVisiable"
      @submitData="submitProductData"
      @closeDialog="closeProductDialog"
    ></product-search-dialog>
    <product-unit-search-dialog
      v-if="productUnitDialogVisiable"
      @submitData="submitProductUnitData"
      @closeDialog="closeProductUnitDialog"
    ></product-unit-search-dialog>
    <account-search-dialog
      v-if="accountDialogVisiable"
      @submitData="submitAccountData"
      @closeDialog="closeAccountDialog"
    ></account-search-dialog>
  </div>
</template>

<script>
import DepartmentSearchDialog from "../components/department-search-dialog.vue"
import BranchSearchDialog from "../components/branch-search-dialog.vue"
import CompanySearchDialog from "../components/company-search-dialog.vue"
import DepotSearchDialog from "../components/depot-search-dialog.vue"
import ProductSearchDialog from "../components/product-search-dialog.vue"
import ProductUnitSearchDialog from "../components/product-unit-search-dialog.vue"
import AccountSearchDialog from "../components/account-search-dialog.vue"

import orderFormApi from "../../api/order-form-api/orderFormApi.js"

export default {
  name: "orderForm",

  components: {
    "department-search-dialog": DepartmentSearchDialog,
    "branch-search-dialog": BranchSearchDialog,
    "company-search-dialog": CompanySearchDialog,
    "depot-search-dialog": DepotSearchDialog,
    "product-search-dialog": ProductSearchDialog,
    "product-unit-search-dialog": ProductUnitSearchDialog,
    "account-search-dialog": AccountSearchDialog
  },

  data () {
    return {
      orderFormData: {
        id: "",
        createDate: new Date(),
        branch: { id: "", name: "" },
        company: { id: "", name: "" },
        employee: { id: "", name: "" },
        department: { id: "", name: "" },
        depot: { id: "", name: "" },
        account: { id: "", name: "" },
        money: 0, // 收款
        wipe: 0, // 抹零
        actionType: 0,

        product: [
          {
            productID: '',
            productName: '',
            productUnit: { id: "", name: "" },
            stock: 0, //库存
            count: 0,
            price: 0,
            amount: 0,
            note: '',
            specification: '', //规格
            actionType: 0
          }
        ],

      },

      prices: [],
      defaultPrice: "",
      scopeValue: {},

      departmentDialogVisiable: false,
      branchDialogVisiable: false,
      companyDialogVisiable: false,
      employeeDialogVisiable: false,
      depotDialogVisiable: false,
      productDialogVisiable: false,
      productUnitDialogVisiable: false,
      accountDialogVisiable: false
    }
  },

  methods: {
    showBranchDialog () {
      this.branchDialogVisiable = true;
    },

    closeBranchDialog () {
      this.branchDialogVisiable = false;
    },

    submitBranchData (branchValue) {
      this.orderFormData.branch.id = branchValue.id;
      this.orderFormData.branch.name = branchValue.name;

      this.branchDialogVisiable = false;
    },

    showCompanyDialog () {
      this.companyDialogVisiable = true;
    },

    closeCompanyDialog () {
      this.companyDialogVisiable = false;
    },

    submitCompanyData (companyValue) {
      this.orderFormData.company.id = companyValue.id;
      this.orderFormData.company.name = companyValue.name;

      this.companyDialogVisiable = false;
    },

    showEmployeeDialog () {
      this.employeeDialogVisiable = true;
    },

    closeEmployeeDialog () {
      this.employeeDialogVisiable = false;
    },

    submitEmployeeData (employeeValue) {
      this.orderFormData.employee.id = employeeValue.id;
      this.orderFormData.employee.name = employeeValue.name;

      this.employeeDialogVisiable = false;
    },

    showDepartmentDialog () {
      this.departmentDialogVisiable = true;
    },

    closeDepartmentDialog () {
      this.departmentDialogVisiable = false;
    },

    submitDepartmentData (departmentValue) {
      console.log("======  ", departmentValue);
      this.orderFormData.department.id = departmentValue.id;
      this.orderFormData.department.name = departmentValue.name;

      this.departmentDialogVisiable = false;
    },

    showDepotDialog () {
      this.depotDialogVisiable = true;
    },

    closeDepotDialog () {
      this.depotDialogVisiable = false;
    },

    submitDepotData (depotValue) {
      this.orderFormData.depot.id = depotValue.id;
      this.orderFormData.depot.name = depotValue.name;

      this.depotDialogVisiable = false;
    },

    showProductSelectDialog (scope) {
      this.productDialogVisiable = true;
      this.scopeValue = scope.row;
    },

    closeProductDialog () {
      this.productDialogVisiable = false;
    },

    submitProductData (productValue) {
      this.scopeValue.productID = productValue.id;

      this.productDialogVisiable = false;
    },

    showProductUnitSelectDialog (scope) {
      this.productUnitDialogVisiable = true;
      this.scopeValue = scope.row;
    },

    closeProductUnitDialog () {
      this.productUnitDialogVisiable = false;
    },

    submitProductUnitData (productUnitValue) {
      this.scopeValue.productUnit.id = productUnitValue.id;
      this.scopeValue.productUnit.name = productUnitValue.name;
      this.scopeValue.productUnit.price = productUnitValue.price;

      this.productUnitDialogVisiable = false;
    },

    showAccountDialog () {
      this.accountDialogVisiable = true;
    },

    closeAccountDialog () {
      this.accountDialogVisiable = false;
    },

    submitAccountData (accountValue) {
      this.orderFormData.account.id = accountValue.id;
      this.orderFormData.account.name = accountValue.name;

      this.accountDialogVisiable = false;
    },

    addProduct () {
      let productData = {
        productID: '',
        productName: '',
        productUnit: '',
        stock: 0, //库存
        count: 0,
        price: 0,
        amount: 0,
        note: '',
        specification: '', //规格
        actionType: 0
      };

      this.orderFormData.product.push(productData);
    },

    save () {
      orderFormApi.saveOrderForm(this.orderFormData).then((res) => {
        console.log(res);
      });
    },

    verifyOrderForm () {
      var params = {
        id: this.orderFormData.id,
      };

      orderFormApi.verifyOrderForm(params).then((res) => {
        console.log(res);
      });
    }
  },
}
</script>

<style>
.inputBlock {
  padding-left: 10px;
}
</style>
