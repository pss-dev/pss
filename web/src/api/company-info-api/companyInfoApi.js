import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getCompanyInfo(params) {
  return fetch({
    url: baseApi.baseApi('/company'),
    method: 'get',
    params
  })
}

function addCompanyInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/company'),
    method: 'post',
    params,
    data
  })
}

function modifyCompanyInfo(params, data) {
  return fetch({
    url: baseApi.baseApi('/company'),
    method: 'put',
    params,
    data
  })
}

function deleteCompanyInfo(params) {
  return fetch({
    url: baseApi.baseApi('/company'),
    method: 'delete',
    params
  })
}

export default {
  getCompanyInfo,
  addCompanyInfo,
	modifyCompanyInfo,
  deleteCompanyInfo
}