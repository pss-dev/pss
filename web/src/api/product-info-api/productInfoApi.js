import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getProductInfo (params) {
  return fetch({
    url: baseApi.baseApi('product'),
    method: 'get',
    params
  })
}

function checkProductDuplicate (data) {
  return fetch({
    url: baseApi.baseApi('product/duplicate'),
    method: 'post',
    data
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

function generatePrice (data) {
  return fetch({
    url: baseApi.baseApi('product/generate-price'),
    method: 'post',
    data
  })
}

const templateUrl = baseApi.baseApi('product/template');

export default {
  getProductInfo,
  addProductInfo,
  modifyProductInfo,
  deleteProductInfo,
  checkProductDuplicate,
  templateUrl,
  generatePrice
}