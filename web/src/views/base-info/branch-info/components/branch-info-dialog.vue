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
        <el-input readonly v-model="branchData.customer.name" autocomplete="off">
          <el-button
            size="small"
            @click="showCompanyDialog(true)"
            slot="append"
            icon="el-icon-search"
          ></el-button>
        </el-input>
      </el-form-item>
      <el-form-item label="供货商" prop="supplier">
        <el-input readonly v-model="branchData.supplier.name" autocomplete="off">
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
      <el-form-item>
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </el-form-item>
    </el-form>
    <company-search-dialog
      @closeDialog="closeCompanyDialog"
      @submitData="submitCompanyData"
      v-if="companydialogVisible"
    ></company-search-dialog>
  </el-dialog>
</template>

<script>
import CompanySearchDialog from "../../../components/company-search-dialog"

export default {
  name: "branchInfoDialog",
  components: {
    "company-search-dialog": CompanySearchDialog
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
