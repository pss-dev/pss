<template>
  <div>
    <el-container>
      <el-header height="30">
        <el-card shadow="never">
          <el-row class="el-row-bottom-20" :gutter="20">
            <el-col :span="8">
              <el-date-picker
                v-model="dateRange"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                @change="dateRanegChange"
              ></el-date-picker>
            </el-col>
            <el-col :span="4">
              <el-select v-model="searchModel.orderFormType" placeholder="单据类型">
                <el-option
                  v-for="item in types"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-col>
            <el-col :span="4">
              <el-select v-model="searchModel.orderFormStatus" placeholder="单据状态">
                <el-option
                  v-for="item in status"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-col>
          </el-row>
          <el-row class="el-row-bottom-20" :gutter="20">
            <el-col :span="4">
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
            <el-col :span="4">
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
            <el-col :span="4">
              <div class="inputBlock">
                <el-input readonly placeholder="经手人" v-model="searchModel.employee.name">
                  <el-button
                    size="small"
                    @click="showEmployeeDialog"
                    slot="append"
                    icon="el-icon-search"
                  ></el-button>
                </el-input>
              </div>
            </el-col>
            <el-col :span="4">
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
            <el-col :span="4">
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

          <el-row :gutter="20"></el-row>
          <el-row>
            <el-col :span="2">
              <el-button @click="getOrderForms">查询</el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-header>
      <el-main>
        <div>
          <el-table
            :data="tableData.slice((currentPage - 1) * pageSize,currentPage * pageSize)"
            border
            highlight-current-row
            @current-change="handleCurrentChange"
            @row-dblclick="openOrderForm"
            height="400"
            style="width: 100%"
          >
            <el-table-column prop="createDate" label="创建时间">
              <template slot-scope="scope">
                <span>{{ scope.row.createDate.substring(0, 10) }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="company.name" label="来往公司">
              <template slot-scope="scope">
                <span>{{ scope.row.company.name }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="employee.name" label="经手人">
              <template slot-scope="scope">
                <span>{{ scope.row.employee.name }}</span>
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
        </div>
      </el-main>
      <el-footer>
        <div>
          <el-button size="small" :disabled="isSelectedInfoInvalid()" @click="openOrderForm">打开单据</el-button>
          <el-button
            size="small"
            v-if="deletePermission"
            :disabled="isSelectedDraft()"
            @click="deleteOrderForm"
          >删除单据</el-button>
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
      @submitData="submitDepotData"
      @closeDialog="closeDepotDialog"
    ></depot-search-dialog>
  </div>
</template>

<script>
import DepartmentSearchDialog from "../components/department-search-dialog.vue"
import BranchSearchDialog from "../components/branch-search-dialog.vue"
import CompanySearchDialog from "../components/company-search-dialog.vue"
import DepotSearchDialog from "../components/depot-search-dialog.vue"
import EmployeeSearchDialog from "../components/employee-search-dialog"

import TableBaseInfo from "@/views/mixIns/table-base-info.js"
import orderFormApi from "../../api/order-form-api/orderFormApi.js"
import Tool from "@/views/constant/tool.js"
import PermissionBase from "@/views/mixIns/permission-base.js"
import RuleTool from "@/views/constant/rule-tool.js"

export default {
  name: "orderForm",
  mixins: [TableBaseInfo, PermissionBase],

  components: {
    "department-search-dialog": DepartmentSearchDialog,
    "branch-search-dialog": BranchSearchDialog,
    "company-search-dialog": CompanySearchDialog,
    "depot-search-dialog": DepotSearchDialog,
    "employee-search-dialog": EmployeeSearchDialog
  },

  props: {},

  data () {
    return {
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

      types: [
        {
          name: "进货单",
          value: Tool.orderFormType.purchaseForm,
        },
        {
          name: "销售单",
          value: Tool.orderFormType.salesForm,
        },
        {
          name: "入库退货单",
          value: Tool.orderFormType.purchaseReturn,
        },
        {
          name: "销售退货单",
          value: Tool.orderFormType.salesReturn,
        },
      ],

      status: [
        {
          name: "草稿",
          value: Tool.orderFormStatus.draft,
        },
        {
          name: "已审核",
          value: Tool.orderFormStatus.verify,
        },
      ],

      departmentDialogVisiable: false,
      branchDialogVisiable: false,
      companyDialogVisiable: false,
      employeeDialogVisiable: false,
      depotDialogVisiable: false,
      productDialogVisiable: false,
      productUnitDialogVisiable: false,
      accountDialogVisiable: false,
      printDialogVisiable: false,
    }
  },

  methods: {
    isSelectedDraft () {
      return (
        this.isSelectedInfoInvalid() ||
        this.selectedInfo.status == Tool.orderFormStatus.verify
      )
    },

    dateRanegChange () {
      if (this.dateRange.length == 2) {
        this.searchModel.startDate = this.dateRange[0]
        this.searchModel.endDate = this.dateRange[1]
      }
    },

    showBranchDialog () {
      this.branchDialogVisiable = true
    },

    closeBranchDialog () {
      this.branchDialogVisiable = false
    },

    submitBranchData (branchValue) {
      this.searchModel.branch = branchValue

      this.branchDialogVisiable = false
    },

    showCompanyDialog () {
      this.companyDialogVisiable = true
    },

    closeCompanyDialog () {
      this.companyDialogVisiable = false
    },

    submitCompanyData (companyValue) {
      this.searchModel.company = companyValue

      this.companyDialogVisiable = false
    },

    showEmployeeDialog () {
      this.employeeDialogVisiable = true
    },

    closeEmployeeDialog () {
      this.employeeDialogVisiable = false
    },

    submitEmployeeData (employeeValue) {
      this.searchModel.employee = employeeValue

      this.employeeDialogVisiable = false
    },

    showDepartmentDialog () {
      this.departmentDialogVisiable = true
    },

    closeDepartmentDialog () {
      this.departmentDialogVisiable = false
    },

    submitDepartmentData (departmentValue) {
      this.searchModel.department = departmentValue

      this.departmentDialogVisiable = false
    },

    showDepotDialog () {
      this.depotDialogVisiable = true
    },

    closeDepotDialog () {
      this.depotDialogVisiable = false
    },

    submitDepotData (depotValue) {
      this.searchModel.depot = depotValue

      this.depotDialogVisiable = false
    },

    getOrderForms () {
      let searchModelData = {
        orderFormType: this.searchModel.orderFormType,
        orderFormStatus: this.searchModel.orderFormStatus,
        startDate: this.searchModel.startDate,
        endDate: this.searchModel.endDate,
        branchID: this.searchModel.branch.id
          ? this.searchModel.branch.id
          : null,
        companyID: this.searchModel.company.id
          ? this.searchModel.company.id
          : null,
        employeeID: this.searchModel.employee.id
          ? this.searchModel.employee.id
          : null,
        departmentID: this.searchModel.department.id
          ? this.searchModel.department.id
          : null,
        depotID: this.searchModel.depot.id ? this.searchModel.depot.id : null,
        accountID: this.searchModel.account.id
          ? this.searchModel.account.id
          : null,
      }

      console.log("======  search ", searchModelData)
      return orderFormApi.getOrderForms(searchModelData).then((res) => {
        console.log("============= getOrderForms ", res)
        this.setResponseResult(res.data)
      })
    },

    handleCurrentChange (value) {
      this.selectedInfo = value
    },

    openOrderForm () {
      this.$emit("openOrderForm", this.selectedInfo)
    },

    deleteOrderForm () {
      this.$confirm('将执行删除操作, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        orderFormApi.deleteOrderFormInfo(this.selectedInfo).then(() => {
          this.getOrderForms();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    isSelectedInfoInvalid () {
      return this.selectedInfo == null
    },
  },

  created: function () {
    this.initPermission(RuleTool.resource.orderForm)
  },
}
</script>

<style>
.el-row-bottom-20 {
  margin-bottom: 20px;
}
</style>
