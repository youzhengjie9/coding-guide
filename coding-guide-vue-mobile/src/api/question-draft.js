import request from '../utils/request'

export function saveDraft(questionDTO){
    return request({
        method:'post',
        url:'/question/draft/saveDraft',
        data:questionDTO
    })
}
