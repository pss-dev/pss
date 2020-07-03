import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getAccountInfo (params) {
  return fetch({
    url: baseApi.baseApi('account'),
    method: 'get',
    params
  })
}

function addAccountInfo (data) {
  return fetch({
    url: baseApi.baseApi('account'),
    method: 'post',
    data
  })
}

function modifyAccountInfo (data) {
  return fetch({
    url: baseApi.baseApi('account'),
    method: 'put',
    data
  })
}

function deleteAccountInfo (data) {
  return fetch({
    url: baseApi.baseApi('account'),
    method: 'delete',
    data
  })
}

export default {
  getAccountInfo,
  addAccountInfo,
  modifyAccountInfo,
  deleteAccountInfo
}