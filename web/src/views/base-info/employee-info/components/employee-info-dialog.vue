<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
    <el-tabs v-model="activeName">
      <el-tab-pane label="基本资料" name="first">
        <el-form :model="employeeData" ref="employeeData" :rules="rules" class="demo-ruleForm">
          <el-form-item label="员工姓名" :label-width="formLabelWidth">
            <el-input v-model="employeeData.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="账号" :label-width="formLabelWidth">
            <el-input v-model="employeeData.account" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" :label-width="formLabelWidth">
            <el-input v-model="employeeData.password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="分支机构" :label-width="formLabelWidth">
            <el-input readonly v-model="employeeData.branch.name" autocomplete="off">
              <el-button
                size="small"
                @click="showBranchDialog()"
                slot="append"
                icon="el-icon-search"
              ></el-button>
            </el-input>
          </el-form-item>
          <el-form-item label="部门" :label-width="formLabelWidth">
            <el-input readonly v-model="employeeData.department.name" autocomplete="off">
              <el-button
                size="small"
                @click="showDepartmentDialog()"
                slot="append"
                icon="el-icon-search"
              ></el-button>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="closeDialog">取 消</el-button>
            <el-button type="primary" @click="submitData">确 定</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="权限" name="second"></el-tab-pane>
    </el-tabs>
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
      formLabelWidth: '120px',
      activeName: 'first',
      rules: {
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
