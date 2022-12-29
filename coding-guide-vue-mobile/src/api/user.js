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
        params:{
            userid:userid
        }
    })
}

export function getSimpleUserInfoByPublisherId(publisherId){
    return request({
        method:'get',
        url:'/user/getSimpleUserInfoByPublisherId',
        params:{
            publisherId:publisherId
        }
    })
}

export function getCurUserSimpleUserInfo(){
    return request({
        method:'get',
        url:'/user/getCurUserSimpleUserInfo',
    })
}
export function getCurUserData(){
    return request({
        method:'get',
        url:'/user/getCurUserData',
    })
}

export function updateCurUserData(userDataDTO){
    return request({
        method:'put',
        url:'/user/updateCurUserData',
        data:userDataDTO
    })
}