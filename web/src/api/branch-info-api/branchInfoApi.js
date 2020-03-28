import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getBranchInfo(params) {
  return fetch({
    url: baseApi.baseApi('/branch'),
    method: 'get',
    params
  })
}

function addBranchInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/branch'),
    method: 'post',
    params,
    data
  })
}

function modifyBranchInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/branch'),
    method: 'put',
    params,
    data
  })
}

function deleteBranchInfo(params) {
  return fetch({
    url: baseApi.baseApi('/branch'),
    method: 'delete',
    params
  })
}

export default {
  getBranchInfo,
  addBranchInfo,
	modifyBranchInfo,
  deleteBranchInfo
}