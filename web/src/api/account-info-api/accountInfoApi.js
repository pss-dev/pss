import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getAccountInfo(params) {
  return fetch({
    url: baseApi.baseApi('/account'),
    method: 'get',
    params
  })
}

function addAccountInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/account'),
    method: 'post',
    params,
    data
  })
}

function modifyAccountInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/account'),
    method: 'put',
    params,
    data
  })
}

function deleteAccountInfo(params) {
  return fetch({
    url: baseApi.baseApi('/account'),
    method: 'delete',
    params
  })
}

export default {
  getAccountInfo,
  addAccountInfo,
	modifyAccountInfo,
  deleteAccountInfo
}