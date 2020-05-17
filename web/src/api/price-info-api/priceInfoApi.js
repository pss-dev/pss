import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getPriceInfo (params) {
  return fetch({
    url: baseApi.baseApi('price'),
    method: 'get',
    params
  })
}

function modifyPriceInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('price'),
    method: 'put',
    params,
    data
  })
}

export default {
  getPriceInfo,
  modifyPriceInfo,
}