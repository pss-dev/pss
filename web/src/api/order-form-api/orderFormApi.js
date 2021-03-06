import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getOrderForms (data) {
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

function verifyOrderForm (data) {
  return fetch({
    url: baseApi.baseApi('orderForm/verify'),
    method: 'put',
    data
  })
}

function initOrderForm (data) {
  return fetch({
    url: baseApi.baseApi('orderForm/init'),
    method: 'post',
    data
  })
}

export default {
  addOrderForm,
  saveOrderForm,
  deleteOrderFormInfo,
  verifyOrderForm,
  getOrderForms,
  initOrderForm
}