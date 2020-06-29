import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getEmployeeInfo () {
  return fetch({
    url: baseApi.baseApi('employee'),
    method: 'get',
  })
}

function addEmployeeInfo (data) {
  return fetch({
    url: baseApi.baseApi('employee'),
    method: 'post',
    data
  })
}

function modifyEmployeeInfo (data) {
  return fetch({
    url: baseApi.baseApi('employee'),
    method: 'put',
    data
  })
}

function deleteEmployeeInfo (params) {
  return fetch({
    url: baseApi.baseApi('employee'),
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