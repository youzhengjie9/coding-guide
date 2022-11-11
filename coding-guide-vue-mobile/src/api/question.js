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

export function searchHottestQuestionByKeyWordAndLimit(page,size,keyword){
    return request({
        url:'/question/searchHottestQuestionByKeyWordAndLimit',
        method:'get',
        params:{
            page:page,
            size:size,
            keyword:keyword
        }
    })
}

export function searchLatestQuestionByKeyWordAndLimit(page,size,keyword){
    return request({
        url:'/question/searchLatestQuestionByKeyWordAndLimit',
        method:'get',
        params:{
            page:page,
            size:size,
            keyword:keyword
        }
    })
}

export function searchRecommendQuestionByKeyWordAndLimit(page,size,keyword){
    return request({
        url:'/question/searchRecommendQuestionByKeyWordAndLimit',
        method:'get',
        params:{
            page:page,
            size:size,
            keyword:keyword
        }
    })
}