import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getDepotInfo(params) {
  return fetch({
    url: baseApi.baseApi('/depot'),
    method: 'get',
    params
  })
}

function addDepotInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/depot'),
    method: 'post',
    params,
    data
  })
}

function modifyDepotInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/depot'),
    method: 'put',
    params,
    data
  })
}

function deleteDepotInfo(params) {
  return fetch({
    url: baseApi.baseApi('/depot'),
    method: 'delete',
    params
  })
}

export default {
  getDepotInfo,
  addDepotInfo,
	modifyDepotInfo,
  deleteDepotInfo
}