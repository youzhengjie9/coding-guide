import request from '../utils/request'

export function selectAllExistTag(){
    return request({
        url:'/tag/selectAllExistTag',
        method:'get'
    })
}