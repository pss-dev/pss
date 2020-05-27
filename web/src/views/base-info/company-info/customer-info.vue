<template>
  <div>
    <company-info :companyType="0"></company-info>
  </div>
</template>

<script>

import CompanyInfo from "@/views/base-info/company-info/company-info"

export default {
  name: "customer-info",

  components: {
    "company-info": CompanyInfo,
  },

  data () {
    return {
    }
  },

  methods: {
  },

  created: function () {
  }
}
</script>

<style>
</style>
