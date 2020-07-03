import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getPriceInfo () {
  return fetch({
    url: baseApi.baseApi('price'),
    method: 'get',
  })
}

function modifyPriceInfo (data) {
  return fetch({
    url: baseApi.baseApi('price'),
    method: 'put',
    data
  })
}

export default {
  getPriceInfo,
  modifyPriceInfo,
}