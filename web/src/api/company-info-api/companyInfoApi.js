import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getCompanyInfo (params) {
  return fetch({
    url: baseApi.baseApi('company'),
    method: 'get',
    params
  })
}

function addCompanyInfo (data) {
  return fetch({
    url: baseApi.baseApi('company'),
    method: 'post',
    data
  })
}

function modifyCompanyInfo (data) {
  return fetch({
    url: baseApi.baseApi('company'),
    method: 'put',
    data
  })
}

function deleteCompanyInfo (data) {
  return fetch({
    url: baseApi.baseApi('company'),
    method: 'delete',
    data
  })
}

export default {
  getCompanyInfo,
  addCompanyInfo,
  modifyCompanyInfo,
  deleteCompanyInfo
}