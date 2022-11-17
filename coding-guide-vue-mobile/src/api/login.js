//引入二次封装axios
import request from '../utils/request'

//请求后端拿到验证码数据
export function getCaptcha(){
    return request({
        method: 'get',
        url: '/captcha'
    })
}

//请求后端进行登录,formData是表单对象
export function userLogin(formData){
    return request({
        method:'post',
        url:'/user/login',
        data:formData
    })
}