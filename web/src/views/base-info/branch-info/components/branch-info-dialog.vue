<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-form
      :model="branchData"
      ref="branchData"
      :rules="rules"
      label-width="100px"
      class="demo-dynamic"
    >
      <el-form-item label="分支名称" prop="name">
        <el-input v-model="branchData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="客户" prop="customer">
        <el-input readonly :value="getCustomerName()" autocomplete="off">
          <el-button
            size="small"
            @click="showCompanyDialog(true)"
            slot="append"
            icon="el-icon-search"
          ></el-button>
        </el-input>
      </el-form-item>
      <el-form-item label="供货商" prop="supplier">
        <el-input readonly :value="getSupplierName()" autocomplete="off">
          <el-button
            size="small"
            @click="showCompanyDialog(false)"
            slot="append"
            icon="el-icon-search"
          ></el-button>
        </el-input>
      </el-form-item>
      <el-form-item label="拼音码" prop="initials">
        <el-input v-model="branchData.initials" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <dialog-footer @closeDialog="closeDialog" @submitData="submitData"></dialog-footer>
    <company-search-dialog
      @closeDialog="closeCompanyDialog"
      @submitData="submitCompanyData"
      :companyType="companyType"
      v-if="companydialogVisible"
    ></company-search-dialog>
  </el-dialog>
</template>

<script>
import CompanySearchDialog from "../../../components/company-search-dialog.vue"
import BaseInfoDialogFooter from "@/views/base-info/components/base-info-dialog-footer.vue"

export default {
  name: "branchInfoDialog",
  components: {
    "company-search-dialog": CompanySearchDialog,
    "dialog-footer": BaseInfoDialogFooter
  },

  props: {
    "title": {
      type: String
    },
    "branchData": {
      type: Object
    },
  },

  data () {
    return {
      dialogVisible: true,
      companydialogVisible: false,
      isCustomer: true,
      companyType: 0,
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ]
      },
    }
  },

  methods: {
    closeDialog () {
      console.log(this.form);
      this.$emit('closeDialog');
    },

    handleClose () {
      this.$emit('closeDialog')
    },

    getCustomerName () {
      if (this.branchData && this.branchData.customer) {
        return this.branchData.customer.name;
      }

      return "";
    },

    getSupplierName () {
      if (this.branchData && this.branchData.supplier) {
        return this.branchData.supplier.name;
      }

      return "";
    },

    submitData () {
      this.$refs['branchData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.branchData);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    showCompanyDialog (type) {
      this.isCustomer = type;

      if (this.isCustomer) {
        this.companyType = 0;
      }
      else {
        this.companyType = 1;
      }


      this.companydialogVisible = true;
    },

    closeCompanyDialog () {
      this.companydialogVisible = false;
    },

    submitCompanyData (value) {
      if (this.isCustomer) {
        this.branchData.customer = value;
      }
      else {
        this.branchData.supplier = value;
      }

      this.companydialogVisible = false;
    }
  },

  created: function () {
  }
}
</script>

<style>
</style>
