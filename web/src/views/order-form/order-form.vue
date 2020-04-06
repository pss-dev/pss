<template>
  <div>
    <el-button @click="showDepartmentDialog">部门</el-button>

    <department-search-dialog
      v-if="departmentDialog">
    </department-search-dialog>
  </div>
</template>

<script>
  import departmentSearchDialog from "../components/department-search-dialog.vue"

  export default {
    name: "orderForm",

    components: {
        "department-search-dialog": departmentSearchDialog
    },

    data() {
      return {
        departmentDialog: false,
      }
    },
    
    methods: {
      showDepartmentDialog() {
        this.departmentDialog = true;
      }
    },
  }
</script>

<style>
</style>
