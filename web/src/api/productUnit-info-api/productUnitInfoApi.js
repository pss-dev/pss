import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getProductUnitInfo (params) {
  return fetch({
    url: baseApi.baseApi('/productUnit'),
    method: 'get',
    params
  })
}

function addProductUnitInfo (data) {
  return fetch({
    url: baseApi.baseApi('/productUnit'),
    method: 'post',
    data
  })
}

function modifyProductUnitInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('/productUnit'),
    method: 'put',
    params,
    data
  })
}

function deleteProductUnitInfo (data) {
  return fetch({
    url: baseApi.baseApi('/productUnit'),
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