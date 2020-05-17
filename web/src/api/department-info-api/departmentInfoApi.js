import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getDepartmentInfo (params) {
  return fetch({
    url: baseApi.baseApi('department'),
    method: 'get',
    params
  })
}

function addDepartmentInfo (data) {
  return fetch({
    url: baseApi.baseApi('department'),
    method: 'post',
    data
  })
}

function modifyDepartmentInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('department'),
    method: 'put',
    params,
    data
  })
}

function deleteDepartmentInfo (data) {
  return fetch({
    url: baseApi.baseApi('department'),
    method: 'delete',
    data
  })
}

export default {
  getDepartmentInfo,
  addDepartmentInfo,
  modifyDepartmentInfo,
  deleteDepartmentInfo
}