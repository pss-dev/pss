import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex)

let store = new Vuex.Store({
  state: {
    permissions: {}
  },
  getters: {
    getpermissions (state) {
      return state.permissions;
    },
  },
  actions: {
  },
  mutations: {
    setpermissions (state, value) {
      state.permissions = value;
    }
  }
});

export default store;