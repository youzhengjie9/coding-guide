import request from '../utils/request'

export function refreshToken(){
    return request({
        method:'post',
        url:'/refreshToken',
        headers:{
            refreshToken:localStorage.getItem('refreshToken')
        }
    })
}