import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getProductInfo (params) {
  return fetch({
    url: baseApi.baseApi('product'),
    method: 'get',
    params
  })
}

function addProductInfo (data) {
  return fetch({
    url: baseApi.baseApi('product'),
    method: 'post',
    data
  })
}

function modifyProductInfo (data) {
  return fetch({
    url: baseApi.baseApi('product'),
    method: 'put',
    data
  })
}

function deleteProductInfo (data) {
  return fetch({
    url: baseApi.baseApi('product'),
    method: 'delete',
    data
  })
}

export default {
  getProductInfo,
  addProductInfo,
  modifyProductInfo,
  deleteProductInfo
}