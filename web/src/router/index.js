import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '@/views/inventory-index/inventory-index'
import Department from '@/views/base-info/department-info/department-info'
import OrderForm from "@/views/order-form/order-form"
import Company from "@/views/base-info/company-info/company-info"
import Branch from "@/views/base-info/branch-info/branch-info"
import Price from "@/views/base-info/price-info/price-info"
import Depot from "@/views/base-info/depot-info/depot-info"
import ProductUnit from "@/views/base-info/product-unit-info/product-unit-info"
// import Login from '@/views/login'

Vue.use(VueRouter)

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: '/',
      meata: {
        title: '首页'
      },
      component: Index
    },
    {
      path: '/dept',
      meata: {
        title: 'Department'
      },
      component: Department
    },
    {
      path: '/order',
      meata: {
        title: 'OrderForm'
      },
      component: OrderForm
    },
    {
      path: '/company',
      meata: {
        title: 'Company'
      },
      component: Company
    },
    {
      path: '/branch',
      meata: {
        title: 'Branch'
      },
      component: Branch
    },
    {
      path: '/price',
      meata: {
        title: 'Price'
      },
      component: Price
    },
    {
      path: '/depot',
      meata: {
        title: 'Depot'
      },
      component: Depot
    },
    {
      path: '/punit',
      meata: {
        title: 'ProductUnit'
      },
      component: ProductUnit
    }
  ]
})
