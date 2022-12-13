import request from '../utils/request'

export function selectListByQuestionIdAndLimit(questionId,page,size){
    return request({
        method:'get',
        url:'/question/comment/selectListByQuestionIdAndLimit/'+questionId+'/'+page+'/'+size
    })
}

export function writeQuestionComment(questionCommentDTO){
    return request({
        method:'post',
        url:'/question/comment/writeQuestionComment',
        data: questionCommentDTO
    })
}

export function selectCurUserAllLikeQuestionCommentId(){
    return request({
        method:"get",
        url:'/question/comment/selectCurUserAllLikeQuestionCommentId'
    })
}

export function likeQuestionComment(commentId){
    return request({
        url:'/question/comment/likeQuestionComment/'+commentId,
        method:'post'
    })
}

