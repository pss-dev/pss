<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
    <el-form
      :model="branchData"
      ref="branchData"
      :rules="rules"
      label-width="100px"
      class="demo-dynamic"
    >
      <el-form-item label="分支名称">
        <el-input v-model="branchData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="客户">
        <el-col :span="21">
          <el-input disabled v-model="branchData.customer.name" autocomplete="off"></el-input>
        </el-col>
        <el-col :span="1">
          <el-button @click="showCompanyDialog(true)">...</el-button>
        </el-col>
      </el-form-item>
      <el-form-item label="供货商">
        <el-col :span="21">
          <el-input disabled v-model="branchData.supplier.name" autocomplete="off"></el-input>
        </el-col>
        <el-col :span="1">
          <el-button @click="showCompanyDialog(false)">...</el-button>
        </el-col>
      </el-form-item>
      <el-form-item label="拼音码">
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
import companySearchDialog from "../../../components/company-search-dialog"

export default {
  name: "branchInfoDialog",
  components: {
    "company-search-dialog": companySearchDialog
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
      formLabelWidth: '120px',
      rules: {
        id: [
          { required: true, message: '请输分支编号', trigger: 'blur' }
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
