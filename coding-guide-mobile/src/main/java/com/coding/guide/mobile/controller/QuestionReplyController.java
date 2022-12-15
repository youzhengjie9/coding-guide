package com.coding.guide.mobile.controller;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.QuestionReplyDTO;
import com.coding.guide.mobile.security.SecurityContext;
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
    public void setQuestionReplyService(QuestionReplyService questionReplyService) {
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
     * 获取当前用户点赞的所有面试题回复id
     *
     * @return {@link ResponseResult}<{@link List}<{@link Long}>>
     */
    @GetMapping(path = "/selectCurUserAllLikeQuestionReplyId")
    @ApiOperation("获取当前用户点赞的所有面试题回复id")
    public ResponseResult<List<Long>> selectCurUserAllLikeQuestionReplyId(){
        try {
            //当前的用户id
            Long userId = SecurityContext.getCurrentUserId();
            //获取用户点赞的所有面试题回复id集合
            List<Long> likeQuestionReplyIds=questionReplyService.selectAllLikeQuestionReplyIdByUserId(userId);
            return ResponseResult.ok(likeQuestionReplyIds);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.fail(null);
        }
    }

    /**
     * 点赞面试题的回复
     * ---------------
     * 调用此接口，如果该条面试题回复没有被该用户点赞过，则点赞，反之则取消点赞
     * @param replyId 点赞的面试题的回复id
     * @return {@link ResponseResult}<{@link Object}>
     */
    @PostMapping(path = "/likeQuestionReply/{replyId}")
    @ApiOperation("点赞面试题的回复")
    public ResponseResult<Object> likeQuestionReply(@PathVariable("replyId") Long replyId){
        try {
            //当前的用户id
            Long userId = SecurityContext.getCurrentUserId();
            boolean res=questionReplyService.likeQuestionReply(userId,replyId);
            return ResponseResult.ok(res);
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
            e.printStackTrace();
            return ResponseResult.fail(null);
        }
    }

}
