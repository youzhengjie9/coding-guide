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

export function selectQuestionByTagIdAndLimit(tagid,page,size){
    return request({
        url:'/question/selectQuestionByTagIdAndLimit/'+tagid+'/'+page+'/'+size,
        method:'get'
    })
}

export function likeQuestion(questionId){
    return request({
        url:'/question/likeQuestion/'+questionId,
        method:'get'
    })
}

export function collectQuestion(questionId){
    return request({
        url:'/question/collectQuestion/'+questionId,
        method:'get'
    })
}

export function selectCurUserAllLikeQuestionId(){
    return request({
        url:'/question/selectCurUserAllLikeQuestionId',
        method:'get'
    })
}

export function selectCurUserAllCollectQuestionId(){
    return request({
        url:'/question/selectCurUserAllCollectQuestionId',
        method:'get'
    })
}

