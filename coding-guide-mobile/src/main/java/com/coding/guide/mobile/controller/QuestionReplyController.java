package com.coding.guide.mobile.controller;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.service.QuestionReplyService;
import com.coding.guide.mobile.vo.QuestionCommentVO;
import com.coding.guide.mobile.vo.QuestionReplyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 面试题回复控制器
 *
 * @author youzhengjie
 * @date 2022/12/04 17:53:32
 */
@RestController
@Api("面试题回复控制器")
@RequestMapping(path = "/mobile/question/reply")
public class QuestionReplyController {

    private QuestionReplyService questionReplyService;

    @Autowired
    public void setReplyService(QuestionReplyService questionReplyService) {
        this.questionReplyService = questionReplyService;
    }


    /**
     * 根据评论id分页查询面试题回复列表
     *
     * @param commentId 面试题id
     * @return {@link ResponseResult}<{@link Map}<{@link Object},{@link Object}>>
     */
    @GetMapping(path = "/selectListByCommentIdAndLimit/{commentId}/{page}/{size}")
    @ApiOperation("根据评论id分页查询面试题回复列表")
    public ResponseResult<Map<Object,Object>> selectListByCommentIdAndLimit(@PathVariable("commentId") long commentId,
                                                                             @PathVariable("page") int page,
                                                                             @PathVariable("size") int size){
        try {
            List<QuestionReplyVO> questionReplyVOList=
                    questionReplyService.selectListVoByCommentIdAndLimit(commentId,page,size);

            long questionReplyCount=questionReplyService.selectCountByCommentId(commentId);

            Map<Object, Object> map = Map.of("questionReplyVOList", questionReplyVOList,
                    "questionReplyCount", questionReplyCount);
            return ResponseResult.ok(map);
        }catch (Exception e){
            return ResponseResult.fail(null);
        }
    }

}
