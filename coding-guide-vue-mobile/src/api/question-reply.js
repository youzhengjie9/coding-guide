import request from '../utils/request'

export function selectListByCommentIdAndLimit(commentId,page,size){
    return request({
        method:'get',
        url:'/question/reply/selectListByCommentIdAndLimit/'+commentId+'/'+page+'/'+size
    })
}
