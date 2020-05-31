import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getLogData (params) {
  return fetch({
    url: baseApi.baseApi('log'),
    method: 'get',
    params
  })
}


export default {
  getLogData,
}