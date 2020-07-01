import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function logout () {
   return fetch({
      // url: baseApi.baseApi('logout'),
      url: 'logout',
      method: 'get'
   })
}

function login () {
   return fetch({
      url: 'login',
      method: 'get'
   })
}

export default {
   login,
   logout
}
