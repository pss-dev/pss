import baseApi from '../baseApi.js'
import fetch from '@/http/fetch'

function getLogData (data) {
  return fetch({
    url: baseApi.baseApi('log'),
    method: 'post',
    data
  })
}

const exportUrl = baseApi.baseApi('log/export');

export default {
  getLogData,
  exportUrl
}
