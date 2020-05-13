import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getUnitInfo (params) {
  return fetch({
    url: baseApi.baseApi('/unit'),
    method: 'get',
    params
  })
}

function addUnitInfo (data) {
  return fetch({
    url: baseApi.baseApi('/unit'),
    method: 'post',
    data
  })
}

function modifyUnitInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('/unit'),
    method: 'put',
    params,
    data
  })
}

function deleteUnitInfo (data) {
  return fetch({
    url: baseApi.baseApi('/unit'),
    method: 'delete',
    data
  })
}

export default {
  getUnitInfo,
  addUnitInfo,
  modifyUnitInfo,
  deleteUnitInfo
}