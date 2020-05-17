import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getOrderFormInfo (params) {
  return fetch({
    url: baseApi.baseApi('orderForm'),
    method: 'get',
    params
  })
}

function saveOrderFormInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('orderForm'),
    method: 'put',
    params,
    data
  })
}

function deleteOrderFormInfo (data) {
  return fetch({
    url: baseApi.baseApi('orderForm'),
    method: 'delete',
    data
  })
}

function verifyOrderFormInfo (data) {
  return fetch({
    url: baseApi.baseApi('orderForm/verify'),
    method: 'get',
    data
  })
}

export default {
  getOrderFormInfo,
  saveOrderFormInfo,
  deleteOrderFormInfo,
  verifyOrderFormInfo
}