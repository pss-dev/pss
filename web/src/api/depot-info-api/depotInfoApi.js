import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getDepotInfo (params) {
  return fetch({
    url: baseApi.baseApi('depot'),
    method: 'get',
    params
  })
}

function checkDepotDuplicate (data) {
  return fetch({
    url: baseApi.baseApi('depot/duplicate'),
    method: 'post',
    data
  })
}

function addDepotInfo (data) {
  return fetch({
    url: baseApi.baseApi('depot'),
    method: 'post',
    data
  })
}

function modifyDepotInfo (data) {
  return fetch({
    url: baseApi.baseApi('depot'),
    method: 'put',
    data
  })
}

function deleteDepotInfo (data) {
  return fetch({
    url: baseApi.baseApi('depot'),
    method: 'delete',
    data
  })
}

function getDepotProducts (params) {
  return fetch({
    url: baseApi.baseApi('depot/products'),
    method: 'get',
    params
  })
}

export default {
  getDepotInfo,
  addDepotInfo,
  modifyDepotInfo,
  deleteDepotInfo,
  getDepotProducts,
  checkDepotDuplicate
}