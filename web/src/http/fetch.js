import axios from 'axios'
import ElementUI from 'element-ui';

const service = axios.create({
  headers: {
    'Content-type': 'application/json'
  },
  timeout: 30000 
})

function errorNotice({title = '服务器通讯异常', error} = {}) {
  const duration = 3000
  ElementUI.$notify.error({
    title,
    message: error,
    duration
  });
}

service.interceptor.request.use(config => {}, error => {
  errorNotice({error})
  return Promise.reject(error)
})

service.interceptor.response.use(response => {
  const code = response.data.code;
  if (code !== '000000') {
    errorNotice({error: response.data.msg})
  }
  return response
}, error => {
  errorNotice(error)
})

export default service