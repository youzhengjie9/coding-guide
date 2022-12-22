import request from '../utils/request'

export function selectAllExistTag(){
    return request({
        url:'/tag/selectAllExistTag',
        method:'get'
    })
}

export function searchTagNameByKeywordAndLimit(page,size,keyword){
    return request({
        url:'/tag/searchTagNameByKeywordAndLimit',
        method:'get',
        params:{
            page:page,
            size:size,
            keyword:keyword
        }
    })
}