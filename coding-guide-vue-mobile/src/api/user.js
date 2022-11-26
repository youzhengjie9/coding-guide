import request from '../utils/request.js'

export function getCurrentUserInfo(){
    return request({
        method:'get',
        url:'/user/getCurrentUserInfo'
    })
}

export function getCurUserCardInfo(){
    return request({
        method:'get',
        url:'/user/getCurUserCardInfo'
    })
}

export function getUserCardInfoByUserId(userid){
    return request({
        method:'get',
        url:'/user/getUserCardInfoByUserId',
        data:{
            userid:userid
        }
    })
}
