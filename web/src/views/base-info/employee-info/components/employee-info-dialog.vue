<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-form
      :model="employeeData"
      ref="employeeData"
      :rules="rules"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="员工姓名" prop="name">
        <el-input v-model="employeeData.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="账号" prop="account">
        <el-input v-model="employeeData.account" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="employeeData.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="分支机构" prop="branch">
        <el-input readonly v-model="employeeData.branch.name" autocomplete="off">
          <el-button size="small" @click="showBranchDialog()" slot="append" icon="el-icon-search"></el-button>
        </el-input>
      </el-form-item>
      <el-form-item label="部门" prop="department">
        <el-input readonly v-model="employeeData.department.name" autocomplete="off">
          <el-button
            size="small"
            @click="showDepartmentDialog()"
            slot="append"
            icon="el-icon-search"
          ></el-button>
        </el-input>
      </el-form-item>
    </el-form>
    <el-button @click="closeDialog">取 消</el-button>
    <el-button type="primary" @click="submitData">确 定</el-button>
    <branch-search-dialog
      v-if="branchDialogVisible"
      @closeDialog="closeBranchDialog"
      @submitData="submitBranchData"
    ></branch-search-dialog>
    <department-search-dialog
      v-if="departmentDialogVisible"
      @closeDialog="closeDepartmentDialog"
      @submitData="submitDepartmentData"
    ></department-search-dialog>
  </el-dialog>
</template>

<script>
import BranchSearchDialog from "@/views/components/branch-search-dialog";
import DepartmentSearchDialog from "@/views/components/department-search-dialog";

export default {
  name: "employeeInfoDialog",
  components: {
    "branch-search-dialog": BranchSearchDialog,
    "department-search-dialog": DepartmentSearchDialog
  },
  props: {
    "title": {
      type: String
    },

    "employeeData": {
      type: Object
    },
  },

  data () {
    return {
      dialogVisible: true,
      branchDialogVisible: false,
      departmentDialogVisible: false,
      activeName: 'first',
      rules: {
        name: [
          { required: true, message: '请输员工姓名', trigger: 'blur' }
        ],
        branch: [
          { required: true, message: '请选择分支机构', trigger: 'blur' }
        ],
        department: [
          { required: true, message: '请选择所属部门', trigger: 'blur' }
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
      this.$refs['employeeData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.employeeData);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    showBranchDialog () {
      this.branchDialogVisible = true;
    },

    closeBranchDialog () {
      this.branchDialogVisible = false;
    },

    submitBranchData (value) {
      this.employeeData.branch = value;

      this.branchDialogVisible = false;
    },

    showDepartmentDialog () {
      this.departmentDialogVisible = true;
    },

    closeDepartmentDialog () {
      this.departmentDialogVisible = false;
    },

    submitDepartmentData (value) {
      this.employeeData.department = value;

      this.departmentDialogVisible = false;
    }
  },

  created: function () {
  }
}
</script>

<style>
</style>
