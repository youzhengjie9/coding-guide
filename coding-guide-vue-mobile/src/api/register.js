import request from '../utils/request'

export function register(userRegisterDTO){
    return request({
        method:'post',
        url:'/register/',
        data: userRegisterDTO
    })
}

export function sendCode(phone){
    return request({
        method:'get',
        url:'/register/sendCode',
        params:{
            phone:phone
        }
    })
}
