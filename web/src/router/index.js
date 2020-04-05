import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '@/views/inventory-index/inventory-index'
import Department from '@/views/base-info/department-info/department-info'
// import Login from '@/views/login'

Vue.use(VueRouter)

export default new VueRouter({
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
    }
  ]
})
