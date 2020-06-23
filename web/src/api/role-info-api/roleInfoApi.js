import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getRoleInfo () {
  return fetch({
    url: baseApi.baseApi('role'),
    method: 'get',
  })
}

function addRoleInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('role'),
    method: 'post',
    params,
    data
  })
}

function modifyRoleInfo (data) {
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
  modifyRoleInfo,
  deleteRoleInfo
}