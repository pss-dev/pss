import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getRoleInfo () {
  return fetch({
    url: baseApi.baseApi('role'),
    method: 'get',
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

function deleteRoleInfo (params) {
  return fetch({
    url: baseApi.baseApi('role'),
    method: 'delete',
    params
  })
}

export default {
  getRoleInfo,
  addRoleInfo,
  setRoleInfo,
  deleteRoleInfo
}