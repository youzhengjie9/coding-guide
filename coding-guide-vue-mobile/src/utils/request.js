//二次封装axios
import axios from 'axios'
import store from '@/store'

const service = axios.create({
  //我们springboot后端服务器的ip+端口号
  baseURL: "http://localhost:16666/mobile",
  //6s没有响应就算超时
  timeout: 6000
})

//二次封装axios的使用（在api包下使用方式）
/*
1：get请求传参：（get请求传参是params属性）

export function 自定义的方法名(){
    return request({
        method: 'get',
        url: '/xxx',
        params: {
            参数1: '111',
            参数2: '222'
         }
    })
}

2：post请求传参：（post请求传参是data属性，后端要用@RequestBody接收）

export function 自定义的方法名(){
    return request({
        method: 'post',
        url: '/xxx',
        data: {
            参数1: '111',
            参数2: '222'
        }
    })
}


*/



//对外暴露
export default service
