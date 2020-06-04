<template>
  <div>
    <order-form :orderFormData="data"></order-form>
  </div>
</template>

<script>

import OrderForm from "@/views/order-form/order-form"

export default {
  name: "purchase-form",

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
