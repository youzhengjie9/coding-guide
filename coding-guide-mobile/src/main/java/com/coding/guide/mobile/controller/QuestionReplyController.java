package com.coding.guide.mobile.controller;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.QuestionReplyDTO;
import com.coding.guide.mobile.service.QuestionReplyService;
import com.coding.guide.mobile.vo.QuestionCommentVO;
import com.coding.guide.mobile.vo.QuestionReplyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
            Map<String,Object> resultMap=
                    questionReplyService.selectMapByCommentIdAndLimit(commentId,page,size);

            List<QuestionReplyVO> questionReplyVOList = (List<QuestionReplyVO>) resultMap.get("questionReplyVOList");
            int firstLevelQuestionReplyListCount = (int) resultMap.get("firstLevelQuestionReplyListCount");

            long firstLevelQuestionReplyTotalCount=questionReplyService.selectCountByReplyIdEq0AndCommentId(commentId);

            Map<Object, Object> map = Map.of("questionReplyVOList", questionReplyVOList,
                    "firstLevelQuestionReplyTotalCount", firstLevelQuestionReplyTotalCount,
                    "firstLevelQuestionReplyListCount",firstLevelQuestionReplyListCount);
            return ResponseResult.ok(map);
        }catch (Exception e){
            return ResponseResult.fail(null);
        }
    }

    /**
     * 回复面试题的评论
     *
     * @param questionReplyDTO 面试题回复DTO
     * @return {@link ResponseResult}<{@link QuestionCommentVO}>
     */
    @PostMapping(path = "/writeQuestionReply")
    @ApiOperation("回复面试题的评论")
    public ResponseResult<QuestionReplyVO> writeQuestionReply(@RequestBody @Valid QuestionReplyDTO questionReplyDTO) {
        try {
            QuestionReplyVO questionReplyVO = questionReplyService.writeQuestionReply(questionReplyDTO);
            //如果questionReplyVO为null，说明回复失败
            if(Objects.isNull(questionReplyVO)){
                return ResponseResult.fail(null);
            }
            //回复成功返回questionReplyVO
            return ResponseResult.ok(questionReplyVO);
        } catch (Exception e) {
            return ResponseResult.fail(null);
        }
    }

}
