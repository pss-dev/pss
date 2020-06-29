import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getOrderFormInfo (data) {
  return fetch({
    url: baseApi.baseApi('orderForm/search'),
    method: 'post',
    data
  })
}

function addOrderForm (data) {
  return fetch({
    url: baseApi.baseApi('orderForm'),
    method: 'post',
    data
  })
}

function saveOrderForm (data) {
  return fetch({
    url: baseApi.baseApi('orderForm'),
    method: 'put',
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
  addOrderForm,
  saveOrderForm,
  deleteOrderFormInfo,
  verifyOrderFormInfo
}