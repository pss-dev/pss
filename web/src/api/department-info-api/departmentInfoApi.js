import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getDepartmentInfo(params) {
  return fetch({
    url: baseApi.baseApi('/department'),
    method: 'get',
    params
  })
}

function addDepartmentInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/department'),
    method: 'post',
    params,
    data
  })
}

function modifyDepartmentInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/department'),
    method: 'put',
    params,
    data
  })
}

function deleteDepartmentInfo(params) {
  return fetch({
    url: baseApi.baseApi('/department'),
    method: 'delete',
    params
  })
}

export default {
  getDepartmentInfo,
  addDepartmentInfo,
	modifyDepartmentInfo,
  deleteDepartmentInfo
}