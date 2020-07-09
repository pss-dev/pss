import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from './router'
import Print from 'vue-print-nb'
import store from '@/vuex/store.js'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(Print)
new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
