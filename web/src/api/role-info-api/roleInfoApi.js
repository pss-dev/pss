import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getRoleInfo () {
  return fetch({
    url: baseApi.baseApi('role'),
    method: 'get',
  })
}

function checkRoleDuplicate (data) {
  return fetch({
    url: baseApi.baseApi('role/duplicate'),
    method: 'post',
    data
  })
}

function addRoleInfo (data) {
  return fetch({
    url: baseApi.baseApi('role'),
    method: 'post',
    data
  })
}

function setRoleInfo (data) {
  return fetch({
    url: baseApi.baseApi('role'),
    method: 'put',
    data
  })
}

function deleteRoleInfo (data) {
  return fetch({
    url: baseApi.baseApi('role'),
    method: 'delete',
    data
  })
}

export default {
  getRoleInfo,
  addRoleInfo,
  setRoleInfo,
  deleteRoleInfo,
  checkRoleDuplicate
}