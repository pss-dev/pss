import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getDepotInfo (params) {
  return fetch({
    url: baseApi.baseApi('/depot'),
    method: 'get',
    params
  })
}

function addDepotInfo (data) {
  return fetch({
    url: baseApi.baseApi('/depot'),
    method: 'post',
    data
  })
}

function modifyDepotInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('/depot'),
    method: 'put',
    params,
    data
  })
}

function deleteDepotInfo (data) {
  return fetch({
    url: baseApi.baseApi('/depot'),
    method: 'delete',
    data
  })
}

export default {
  getDepotInfo,
  addDepotInfo,
  modifyDepotInfo,
  deleteDepotInfo
}