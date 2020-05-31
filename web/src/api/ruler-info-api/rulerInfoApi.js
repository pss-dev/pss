import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getRulerInfo (params) {
  return fetch({
    url: baseApi.baseApi('ruler'),
    method: 'get',
    params
  })
}

function addRulerInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('ruler'),
    method: 'post',
    params,
    data
  })
}

function modifyRulerInfo (data) {
  return fetch({
    url: baseApi.baseApi('ruler'),
    method: 'put',
    data
  })
}

function deleteRulerInfo (params) {
  return fetch({
    url: baseApi.baseApi('ruler'),
    method: 'delete',
    params
  })
}

export default {
  getRulerInfo,
  addRulerInfo,
  modifyRulerInfo,
  deleteRulerInfo
}