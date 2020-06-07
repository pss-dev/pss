import axios from 'axios'
import { ElementUI, Loading } from 'element-ui';

const service = axios.create({
  headers: {
    'Content-type': 'application/json'
  },
  timeout: 30000
})

function errorNotice ({ title = '服务器通讯异常', error } = {}) {
  const duration = 3000
  ElementUI.$notify.error({
    title,
    message: error,
    duration
  });
}

let loading        //定义loading变量

function startLoading () {    //使用Element loading-start 方法
  loading = Loading.service({
    lock: true,
    text: '加载中……',
    background: 'rgba(0, 0, 0, 0.7)'
  })
}
function endLoading () {    //使用Element loading-close 方法
  loading.close()
}

let needLoadingRequestCount = 0
export function showFullScreenLoading () {
  if (needLoadingRequestCount === 0) {
    startLoading()
  }
  needLoadingRequestCount++
}

export function tryHideFullScreenLoading () {
  if (needLoadingRequestCount <= 0) return
  needLoadingRequestCount--
  if (needLoadingRequestCount === 0) {
    endLoading()
  }
}

service.interceptors.request.use(config => {
  showFullScreenLoading();
  return config;
}, error => {
  errorNotice({ error })
  return Promise.reject(error)
})

service.interceptors.response.use(response => {
  // const code = response.data.code;
  // if (code !== '000000') {
  //   errorNotice({error: response.data.msg})
  // }
  tryHideFullScreenLoading();
  return response
}, error => {
  errorNotice(error)
})

export default service