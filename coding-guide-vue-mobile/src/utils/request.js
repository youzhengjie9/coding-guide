//二次封装axios
import axios from 'axios'
import store from '@/store'

const service = axios.create({
  //我们springboot后端服务器的ip+端口号
  
  //后端的内网ip地址（当项目在本机内网上运行则配置这个）
  baseURL: "http://localhost:16666/mobile",

  //后端的外网反向代理ip地址（当后端项目部署到云服务器或者反向代理就要这样配置成自己的地址）
  // baseURL: "http://36efff3.r6.cpolar.top/mobile",

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

//添加axios请求拦截器（在发送axios请求前自动执行）
service.interceptors.request.use(function (config) {
    //如果有accessToken，则每一次发送请求之前都要在localStorage中拿到accessToken并放到请求头中
    if (store.state.User.accessToken && config.url !== '/refreshToken') {
      
    	config.headers['accessToken'] = store.state.User.accessToken
  	}
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });

//添加axios响应拦截器（axios请求发送后，后台返回数据给前端，当接收响应数据后自动执行）
service.interceptors.response.use(function (response) {
    // 响应数据
     const res = response.data;

     if(res){ //前提是res不为空，否则如果res为空后面res.code会空调用异常。
        //如果返回来的code是301，说明accessToken过期了，这时候就可以请求refreshToken接口
        if(res.code === 301){
          // console.log('============')
          // console.log(localStorage.getItem('refreshToken'))
          //调用vuex中的toRefreshToken方法，刷新token
          store.dispatch('toRefreshToken');
        }
     }
     

     return response; //记得要返回response。
     
  }, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
  });

//对外暴露
export default service
