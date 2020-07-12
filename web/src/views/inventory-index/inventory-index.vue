<template>
  <div class="inventory-index">
    <index-header :userName="userName"></index-header>
    <index-main></index-main>
  </div>
</template>

<script>
import indexHeader from "./components/index-header";
import indexMain from "./components/index-main";
import EmployeeApi from '@/api/employee-info-api/employeeInfoApi.js'

export default {
  components: {
    indexHeader,
    indexMain
  },
  data () {
    return {
      userName: ""
    };
  },
  created () {
    EmployeeApi.getPrincipal().then((res) => {
      if (res.data) {
        console.log("=====  ", res);
        this.userName = res.data;
      }
    });
  }
};
</script>

<style></style>
