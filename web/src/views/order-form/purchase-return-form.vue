<template>
  <div>
    <order-form :orderFormDataValue="data" :orderFormType="getFormType()"></order-form>
  </div>
</template>

<script>

import OrderForm from "@/views/order-form/order-form"
import Tool from '@/views/constant/tool.js'

export default {
  name: "return-form",

  components: {
    "order-form": OrderForm,
  },

  props: {
    "data": {
      type: Object,
    }
  },

  data () {
    return {
      getFormType () {
        return Tool.orderFormType.purchaseReturn;
      }
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
