<template>
  <div>
    <el-container>
      <el-header height="30">
        <el-card shadow="never">
          <el-row class="el-row-bottom-20" :gutter="20">
            <el-col :span="6">
              <el-date-picker
                v-model="dateRange"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                @change="dateRanegChange"
              ></el-date-picker>
            </el-col>
            <el-col :span="6">
              <el-select v-model="searchModel.orderFormType" placeholder="单据类型">
                <el-option
                  v-for="item in types"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-col>
            <el-col :span="6">
              <el-select v-model="searchModel.orderFormStatus" placeholder="单据状态">
                <el-option
                  v-for="item in status"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-col>
            <el-col :span="2">
              <el-button @click="getOrderForms">查询</el-button>
            </el-col>
          </el-row>
          <el-row class="el-row-bottom-20" :gutter="20">
            <el-col :span="6">
              <div class="inputBlock">
                <el-input readonly placeholder="分支机构" v-model="searchModel.branch.name">
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
              <div class="inputBlock">
                <el-input readonly placeholder="来往单位" v-model="searchModel.company.name">
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

          <el-row :gutter="20">
            <el-col :span="6">
              <div class="inputBlock">
                <el-input readonly placeholder="经手人" v-model="searchModel.employee.name">
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
                <el-input readonly placeholder="部门" v-model="searchModel.department.name">
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
                <el-input readonly placeholder="仓库" v-model="searchModel.depot.name">
                  <el-button
                    size="small"
                    @click="showDepotDialog"
                    slot="append"
                    icon="el-icon-search"
                  ></el-button>
                </el-input>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-header>
      <el-main>
        <div>
          <base-info-table
            :titles="titData"
            :tableData="tableData"
            @handleCurrentChange="handleCurrentChange"
            @getChildData="openOrderForm"
          ></base-info-table>
        </div>
      </el-main>
      <el-footer>
        <div>
          <el-row>
            <el-col :span="3">
              <el-button :disabled="isSelectedInfoInvalid()" @click="openOrderForm">打开单据</el-button>
            </el-col>
            <el-col :span="3">
              <el-button :disabled="isSelectedInfoInvalid()" @click="deleteOrderForm">删除单据</el-button>
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
    <unit-search-dialog
      v-if="productUnitDialogVisiable"
      @submitData="submitProductUnitData"
      @closeDialog="closeProductUnitDialog"
    ></unit-search-dialog>
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
import UnitSearchDialog from "../components/unit-search-dialog.vue"
import AccountSearchDialog from "../components/account-search-dialog.vue"
import BaseInfoTable from "@/views/base-info/components/base-info-table.vue"

import TableBaseInfo from '@/views/mixIns/table-base-info.js'

import orderFormApi from "../../api/order-form-api/orderFormApi.js"
import Tool from '@/views/constant/tool.js'

export default {
  name: "orderForm",
  mixins: [TableBaseInfo],

  components: {
    "department-search-dialog": DepartmentSearchDialog,
    "branch-search-dialog": BranchSearchDialog,
    "company-search-dialog": CompanySearchDialog,
    "depot-search-dialog": DepotSearchDialog,
    "product-search-dialog": ProductSearchDialog,
    "unit-search-dialog": UnitSearchDialog,
    "account-search-dialog": AccountSearchDialog,
    "base-info-table": BaseInfoTable
  },

  props: {
  },

  data () {
    return {
      titData: [
        { prop: "createDate", label: "创建时间" },
        { prop: "company.name", label: "来往公司" },
        { prop: "employee.name", label: "经手人" }
      ],

      selectedInfo: null,
      dateRange: [],

      searchModel: {
        orderFormType: Tool.orderFormType.purchaseForm,
        orderFormStatus: Tool.orderFormStatus.draft,
        startDate: null,
        endDate: null,
        branch: {},
        company: {},
        employee: {},
        department: {},
        depot: {},
        account: {},
      },

      types: [{
        name: "进货单",
        value: Tool.orderFormType.purchaseForm
      },
      {
        name: "销售单",
        value: Tool.orderFormType.salesForm
      },
      {
        name: "入库退货单",
        value: Tool.orderFormType.purchaseReturn
      },
      {
        name: "销售退货单",
        value: Tool.orderFormType.salesReturn
      }],

      status: [{
        name: "草稿",
        value: Tool.orderFormStatus.draft
      }, {
        name: "已审核",
        value: Tool.orderFormStatus.verify
      }],

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
    dateRanegChange () {
      if (this.dateRange.length == 2) {
        this.searchModel.startDate = this.dateRange[0];
        this.searchModel.endDate = this.dateRange[1];
      }
    },

    showBranchDialog () {
      this.branchDialogVisiable = true;
    },

    closeBranchDialog () {
      this.branchDialogVisiable = false;
    },

    submitBranchData (branchValue) {
      this.searchModel.branch = branchValue;

      this.branchDialogVisiable = false;
    },

    showCompanyDialog () {
      this.companyDialogVisiable = true;
    },

    closeCompanyDialog () {
      this.companyDialogVisiable = false;
    },

    submitCompanyData (companyValue) {
      this.searchModel.company = companyValue;

      this.companyDialogVisiable = false;
    },

    showEmployeeDialog () {
      this.employeeDialogVisiable = true;
    },

    closeEmployeeDialog () {
      this.employeeDialogVisiable = false;
    },

    submitEmployeeData (employeeValue) {
      this.searchModel.employee = employeeValue;

      this.employeeDialogVisiable = false;
    },

    showDepartmentDialog () {
      this.departmentDialogVisiable = true;
    },

    closeDepartmentDialog () {
      this.departmentDialogVisiable = false;
    },

    submitDepartmentData (departmentValue) {
      this.searchModel.department = departmentValue;

      this.departmentDialogVisiable = false;
    },

    showDepotDialog () {
      this.depotDialogVisiable = true;
    },

    closeDepotDialog () {
      this.depotDialogVisiable = false;
    },

    submitDepotData (depotValue) {
      this.searchModel.depot = depotValue;

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
      this.scopeValue.product.Unit = productUnitValue;

      this.productUnitDialogVisiable = false;
    },

    showAccountDialog () {
      this.accountDialogVisiable = true;
    },

    closeAccountDialog () {
      this.accountDialogVisiable = false;
    },

    submitAccountData (accountValue) {
      this.searchModel.account = accountValue;

      this.accountDialogVisiable = false;
    },

    getOrderForms () {
      let searchModelData = {
        orderFormType: this.searchModel.orderFormType,
        orderFormStatus: this.searchModel.orderFormStatus,
        startDate: this.searchModel.startDate,
        endDate: this.searchModel.endDate,
        branchID: this.searchModel.branch.id ? this.searchModel.branch.id : null,
        companyID: this.searchModel.company.id ? this.searchModel.company.id : null,
        employeeID: this.searchModel.employee.id ? this.searchModel.employee.id : null,
        departmentID: this.searchModel.department.id ? this.searchModel.department.id : null,
        depotID: this.searchModel.depot.id ? this.searchModel.depot.id : null,
        accountID: this.searchModel.account.id ? this.searchModel.account.id : null,
      };

      console.log("======  search ", searchModelData);
      return orderFormApi.getOrderForms(searchModelData).then((res) => {
        console.log("============= getOrderForms ", res);
        this.setResponseResult(res.data);
      });
    },

    handleCurrentChange (value) {
      this.selectedInfo = value;
    },

    openOrderForm () {
      this.$emit("openOrderForm", this.selectedInfo);
    },

    deleteOrderForm () {
      orderFormApi
        .deleteOrderFormInfo(this.selectedInfo)
        .then(() => {
          this.getOrderForms();
        });
    },

    isSelectedInfoInvalid () {
      return this.selectedInfo == null;
    },
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