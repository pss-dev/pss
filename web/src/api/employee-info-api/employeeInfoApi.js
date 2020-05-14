import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getEmployeeInfo (params) {
  return fetch({
    url: baseApi.baseApi('/employee'),
    method: 'get',
    params
  })
}

function addEmployeeInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('/employee'),
    method: 'post',
    params,
    data
  })
}

function modifyEmployeeInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('/employee'),
    method: 'put',
    params,
    data
  })
}

function deleteEmployeeInfo (params) {
  return fetch({
    url: baseApi.baseApi('/employee'),
    method: 'delete',
    params
  })
}

export default {
  getEmployeeInfo,
  addEmployeeInfo,
  modifyEmployeeInfo,
  deleteEmployeeInfo
}