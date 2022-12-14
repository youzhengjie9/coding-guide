import request from '../utils/request'

export function selectCurUserFollowUserIdList(){
    return request({
        method:'get',
        url:'/user/follow/selectCurUserFollowUserIdList',
    })
}

export function followUser(followUserId){
    return request({
        method:'get',
        url:'/user/follow/'+followUserId,
    })
}