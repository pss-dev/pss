import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getBranchInfo (params) {
  return fetch({
    url: baseApi.baseApi('branch'),
    method: 'get',
    params
  })
}

function checkBranchDuplicate (data) {
  return fetch({
    url: baseApi.baseApi('branch/duplicate'),
    method: 'post',
    data
  })
}

function addBranchInfo (data) {
  return fetch({
    url: baseApi.baseApi('branch'),
    method: 'post',
    data
  })
}

function modifyBranchInfo (data) {
  return fetch({
    url: baseApi.baseApi('/branch'),
    method: 'put',
    data
  })
}

function deleteBranchInfo (data) {
  return fetch({
    url: baseApi.baseApi('branch'),
    method: 'delete',
    data
  })
}

export default {
  getBranchInfo,
  addBranchInfo,
  modifyBranchInfo,
  deleteBranchInfo,
  checkBranchDuplicate
}
