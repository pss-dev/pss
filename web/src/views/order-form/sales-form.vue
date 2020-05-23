<template>
  <div>
    <order-form></order-form>
  </div>
</template>

<script>

import OrderForm from "@/views/order-form/order-form"

export default {
  name: "sales-form",

  components: {
    "order-form": OrderForm,
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
