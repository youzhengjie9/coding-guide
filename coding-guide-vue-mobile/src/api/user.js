import request from '../utils/request.js'

export function getCurrentUserInfo(){
    return request({
        method:'get',
        url:'/user/getCurrentUserInfo'
    })
}
