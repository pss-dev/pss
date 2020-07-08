import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getRevenue (data) {
  return fetch({
    url: baseApi.baseApi('revenue'),
    method: 'post',
    data
  })
}

export default {
  getRevenue,
}