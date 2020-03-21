import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '@/views/index/inventory-index'
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
    }
  ]
})
