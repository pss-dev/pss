import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getPriceInfo(params) {
  return fetch({
    url: baseApi.baseApi('/price'),
    method: 'get',
    params
  })
}

function addPriceInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/price'),
    method: 'post',
    params,
    data
  })
}

function modifyPriceInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/price'),
    method: 'put',
    params,
    data
  })
}

function deletePriceInfo(params) {
  return fetch({
    url: baseApi.baseApi('/price'),
    method: 'delete',
    params
  })
}

export default {
  getPriceInfo,
  addPriceInfo,
	modifyPriceInfo,
  deletePriceInfo
}