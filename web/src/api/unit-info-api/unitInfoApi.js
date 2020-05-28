import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getUnitInfo (params) {
  return fetch({
    url: baseApi.baseApi('unit'),
    method: 'get',
    params
  })
}

function addUnitInfo (data) {
  return fetch({
    url: baseApi.baseApi('unit'),
    method: 'post',
    data
  })
}

function modifyUnitInfo (data) {
  return fetch({
    url: baseApi.baseApi('unit'),
    method: 'put',
    data
  })
}

function deleteUnitInfo (data) {
  return fetch({
    url: baseApi.baseApi('unit'),
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