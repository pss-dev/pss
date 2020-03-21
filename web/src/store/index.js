
import Vue from 'vue'
import Vuex from 'vuex'
import userInfo from './moudles/userInfo'

Vue.use(Vuex)

export default new Vuex.store({
  modules: {
    userInfo
  }
})