import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getProductUnitInfo(params) {
  return fetch({
    url: baseApi.baseApi('/productUnit'),
    method: 'get',
    params
  })
}

function addProductUnitInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/productUnit'),
    method: 'post',
    params,
    data
  })
}

function modifyProductUnitInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/productUnit'),
    method: 'put',
    params,
    data
  })
}

function deleteProductUnitInfo(params) {
  return fetch({
    url: baseApi.baseApi('/productUnit'),
    method: 'delete',
    params
  })
}

export default {
  getProductUnitInfo,
  addProductUnitInfo,
	modifyProductUnitInfo,
  deleteProductUnitInfo
}