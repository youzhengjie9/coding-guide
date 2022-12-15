import request from '../utils/request'

export function selectListByCommentIdAndLimit(commentId,page,size){
    return request({
        method:'get',
        url:'/question/reply/selectListByCommentIdAndLimit/'+commentId+'/'+page+'/'+size
    })
}

export function selectCurUserAllLikeQuestionReplyId(){
    return request({
        method:"get",
        url:'/question/reply/selectCurUserAllLikeQuestionReplyId'
    })
}

export function likeQuestionReply(replyId){
    return request({
        url:'/question/reply/likeQuestionReply/'+replyId,
        method:'post'
    })
}