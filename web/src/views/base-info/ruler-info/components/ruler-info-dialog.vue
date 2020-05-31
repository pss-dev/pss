<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-table :data="rules" height="400" style="width: 100%" border>
      <el-table-column prop="name">
        <template slot-scope="scope">
          <span>{{scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="read" label="读">
        <template slot-scope="scope">
          <el-checkbox v-model="scope.row.read"></el-checkbox>
        </template>
      </el-table-column>
      <el-table-column prop="write" label="写">
        <template slot-scope="scope">
          <el-checkbox v-model="scope.row.write"></el-checkbox>
        </template>
      </el-table-column>
      <el-table-column prop="delete" label="删">
        <template slot-scope="scope">
          <el-checkbox v-model="scope.row.delete"></el-checkbox>
        </template>
      </el-table-column>
    </el-table>
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
export default {
  name: "employeeInfoDialog",
  components: {
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
      formRules: {
      },

      rules: [
        {
          name: '基本信息',
          read: false,
          write: false,
          delete: false,
        },
        {
          name: '员工信息',
          read: false,
          write: false,
          delete: false,
        },
        {
          name: '单据',
          read: false,
          write: false,
          delete: false,
        },
        {
          name: '营收',
          read: false,
          write: false,
          delete: false,
        },
        {
          name: '日志',
          read: false,
          write: false,
          delete: false,
        }
      ]
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
  },

  created: function () {
  }
}
</script>

<style>
</style>
