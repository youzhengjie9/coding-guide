import request from '../utils/request'

export function saveDraft(questionDraftDTO){
    return request({
        method:'post',
        url:'/question/draft/saveDraft',
        data:questionDraftDTO
    })
}
