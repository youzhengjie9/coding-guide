import request from '../utils/request'

export function logout(accessToken){
    return request({
        method:'post',
        url:'/logout/',
        headers:{
            accessToken:accessToken
        }
    })
}