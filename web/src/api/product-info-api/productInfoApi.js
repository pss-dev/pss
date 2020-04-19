import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getProductInfo (params) {
  return fetch({
    url: baseApi.baseApi('/product'),
    method: 'get',
    params
  })
}

function addProductInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('/product'),
    method: 'post',
    params,
    data
  })
}

function modifyProductInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('/product'),
    method: 'put',
    params,
    data
  })
}

function deleteProductInfo (params) {
  return fetch({
    url: baseApi.baseApi('/product'),
    method: 'delete',
    params
  })
}

export default {
  getProductInfo,
  addProductInfo,
  modifyProductInfo,
  deleteProductInfo
}