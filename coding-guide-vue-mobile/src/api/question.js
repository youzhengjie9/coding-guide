import request from '../utils/request'

export function selectHottestQuestionByLimit(page,size){
    return request({
        url:'/question/selectHottestQuestionByLimit/'+page+'/'+size,
        method:'get'
    })
}

export function selectQuestionDetail(id){
    return request({
        url:'/question/selectQuestionDetail/'+id,
        method:'get'
    })
}