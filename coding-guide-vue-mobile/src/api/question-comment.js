import request from '../utils/request'

export function selectListByQuestionIdAndLimit(questionId,page,size){
    return request({
        method:'get',
        url:'/question/comment/selectListByQuestionIdAndLimit/'+questionId+'/'+page+'/'+size
    })
}

export function writeQuestionComment(writeQuestionCommentDTO){
    return request({
        method:'post',
        url:'/question/comment/writeQuestionComment',
        data: writeQuestionCommentDTO
    })
}
