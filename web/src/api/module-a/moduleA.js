import { baseApi } from '../baseApi'
import fetch from '@/http/fetch'

function getUserInfo(params) {
  return fetch({
    url: baseApi('/getUserInfo'),
    method: 'get',
    params
  })
}

function modifyUserInfo(data) {
  return fetch({
    url: baseApi('/modifyUserInfo'),
    method: 'post',
    data
  })
}

export default {
  modifyUserInfo,
  getUserInfo
}