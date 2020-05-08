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
            <el-input placeholder="分支机构">
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
        <el-table-column prop="productUnit" label="单位"></el-table-column>
        <el-table-column prop="stock" label="账面库存"></el-table-column>
        <el-table-column prop="count" label="数量"></el-table-column>
        <el-table-column prop="price" label="单价"></el-table-column>
        <el-table-column prop="amount" label="金额"></el-table-column>
        <el-table-column prop="note" label="备注"></el-table-column>
        <el-table-column prop="specification" label="规格"></el-table-column>
      </el-table>
    </div>

    <div>footer</div>

    <department-search-dialog
      v-if="departmentDialogVisiable"
      @submitData="submitDepartmentData"
      @closeDialog="closeDepartmentDialog"
    ></department-search-dialog>
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
  </div>
</template>

<script>
import departmentSearchDialog from "../components/department-search-dialog.vue"
import branchSearchDialog from "../components/branch-search-dialog.vue"
import companySearchDialog from "../components/company-search-dialog.vue"
import depotSearchDialog from "../components/depot-search-dialog"
import productSearchDialog from "../components/product-search-dialog"

export default {
  name: "orderForm",

  components: {
    "department-search-dialog": departmentSearchDialog,
    "branch-search-dialog": branchSearchDialog,
    "company-search-dialog": companySearchDialog,
    "depot-search-dialog": depotSearchDialog,
    "product-search-dialog": productSearchDialog
  },

  data () {
    return {
      orderFormData: {
        createDate: new Date(),
        department: '',
        branch: '',
        company: '',
        employee: '',
        depot: '',

        product: [
          {
            productID: '',
            productName: '',
            productUnit: '',
            stock: 0, //库存
            count: 0,
            price: 0,
            amount: 0,
            note: '',
            specification: '' //规格
          }
        ],

      },

      departmentDialogVisiable: false,
      branchDialogVisiable: false,
      companyDialogVisiable: false,
      employeeDialogVisiable: false,
      depotDialogVisiable: false,
      productDialogVisiable: false
    }
  },

  methods: {
    showBranchDialog () {
      this.branchDialogVisiable = true;
    },

    closeBranchDialog () {
      this.branchDialogVisiable = false;
    },

    showCompanyDialog () {
      this.companyDialogVisiable = true;
    },

    closeCompanyDialog () {
      this.companyDialogVisiable = false;
    },

    showEmployeeDialog () {
      this.employeeDialogVisiable = true;
    },

    closeEmployeeDialog () {
      this.employeeDialogVisiable = false;
    },

    showDepartmentDialog () {
      this.departmentDialogVisiable = true;
    },

    closeDepartmentDialog () {
      this.departmentDialogVisiable = false;
    },

    showDepotDialog () {
      this.depotDialogVisiable = true;
    },

    closeDepotDialog () {
      this.depotDialogVisiable = false;
    },

    showProductSelectDialog (scope) {
      this.productDialogVisiable = true;
      console.log("======== ", scope);
    },

    closeProductDialog () {
      this.productDialogVisiable = false;
    }
  },
}
</script>

<style>
.inputBlock {
  padding-left: 10px;
}
</style>
