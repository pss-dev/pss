import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getBranchInfo (params) {
  return fetch({
    url: baseApi.baseApi('/branch'),
    method: 'get',
    params
  })
}

function addBranchInfo (data) {
  return fetch({
    url: baseApi.baseApi('/branch'),
    method: 'post',
    data
  })
}

function modifyBranchInfo (params, data) {
  return fetch({
    url: baseApi.baseApi('/branch'),
    method: 'put',
    params,
    data
  })
}

function deleteBranchInfo (data) {
  return fetch({
    url: baseApi.baseApi('/branch'),
    method: 'delete',
    data
  })
}

export default {
  getBranchInfo,
  addBranchInfo,
  modifyBranchInfo,
  deleteBranchInfo
}