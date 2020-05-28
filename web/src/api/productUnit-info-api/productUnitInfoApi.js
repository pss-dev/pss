import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getProductUnitInfo (params) {
  return fetch({
    url: baseApi.baseApi('productUnit'),
    method: 'get',
    params
  })
}

function addProductUnitInfo (data) {
  return fetch({
    url: baseApi.baseApi('productUnit'),
    method: 'post',
    data
  })
}

function modifyProductUnitInfo (data) {
  return fetch({
    url: baseApi.baseApi('productUnit'),
    method: 'put',
    data
  })
}

function deleteProductUnitInfo (data) {
  return fetch({
    url: baseApi.baseApi('productUnit'),
    method: 'delete',
    data
  })
}

export default {
  getProductUnitInfo,
  addProductUnitInfo,
  modifyProductUnitInfo,
  deleteProductUnitInfo
}