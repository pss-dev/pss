import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getEmployeeInfo () {
  return fetch({
    url: baseApi.baseApi('employee'),
    method: 'get',
  })
}

function checkEmployeeDuplicate (data) {
  return fetch({
    url: baseApi.baseApi('employee/duplicate'),
    method: 'post',
    data
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

function deleteEmployeeInfo (data) {
  return fetch({
    url: baseApi.baseApi('employee'),
    method: 'delete',
    data
  })
}

function getPermission () {
  return fetch({
    url: baseApi.baseApi('permission'),
    method: 'get',
  })
}

function getPrincipal () {
  return fetch({
    url: baseApi.baseApi('principal'),
    method: 'get',
  })
}

export default {
  getEmployeeInfo,
  addEmployeeInfo,
  modifyEmployeeInfo,
  deleteEmployeeInfo,
  getPermission,
  getPrincipal,
  checkEmployeeDuplicate
}