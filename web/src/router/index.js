import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '@/views/inventory-index/inventory-index'
import Department from '@/views/base-info/department-info/department-info'
import OrderForm from "@/views/order-form/order-form"
import Company from "@/views/base-info/company-info/company-info"
import Branch from "@/views/base-info/branch-info/branch-info"
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
    }
  ]
})
