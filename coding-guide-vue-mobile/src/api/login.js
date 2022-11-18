//引入二次封装axios
import request from '../utils/request'

//请求后端进行登录,formData是表单对象
export function login(loginObject){
    return request({
        method:'post',
        url:'/login/',
        data:loginObject
    })
}