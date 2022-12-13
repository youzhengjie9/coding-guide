package com.coding.guide.mobile.controller;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.QuestionCommentDTO;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.service.QuestionCommentService;
import com.coding.guide.mobile.vo.QuestionCommentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 面试题评论控制器
 *
 * @author youzhengjie
 * @date 2022/12/04 17:53:32
 */
@RestController
@Api("面试题评论控制器")
@RequestMapping(path = "/mobile/question/comment")
public class QuestionCommentController {

    private QuestionCommentService questionCommentService;

    @Autowired
    public void setCommentService(QuestionCommentService questionCommentService) {
        this.questionCommentService = questionCommentService;
    }

    /**
     * 根据面试题id分页查询面试题评论列表
     *
     * @param questionId 面试题id
     * @return {@link ResponseResult}<{@link Map}<{@link Object},{@link Object}>>
     */
    @GetMapping(path = "/selectListByQuestionIdAndLimit/{questionId}/{page}/{size}")
    @ApiOperation("根据面试题id分页查询面试题评论列表")
    public ResponseResult<Map<Object, Object>> selectListByQuestionIdAndLimit(@PathVariable("questionId") long questionId,
                                                                              @PathVariable("page") int page,
                                                                              @PathVariable("size") int size) {
        try {
            List<QuestionCommentVO> questionCommentVOList =
                    questionCommentService.selectListVoByQuestionIdAndLimit(questionId, page, size);

            long questCommentCount = questionCommentService.selectCountByQuestionId(questionId);

            Map<Object, Object> map = Map.of("questionCommentVOList", questionCommentVOList,
                    "questionCommentCount", questCommentCount);
            return ResponseResult.ok(map);
        } catch (Exception e) {
            return ResponseResult.fail(null);
        }
    }

    /**
     * 获取当前用户点赞的所有面试题评论id
     *
     * @return {@link ResponseResult}<{@link List}<{@link Long}>>
     */
    @GetMapping(path = "/selectCurUserAllLikeQuestionCommentId")
    @ApiOperation("获取当前用户点赞的所有面试题评论id")
    public ResponseResult<List<Long>> selectCurUserAllLikeQuestionCommentId(){
        try {
            //当前的用户id
            Long userid = SecurityContext.getCurrentUserId();
            //获取用户点赞的所有面试题评论id集合
            List<Long> likeQuestionCommentIds=questionCommentService.selectAllLikeQuestionCommentIdByUserId(userid);
            return ResponseResult.ok(likeQuestionCommentIds);
        }catch (Exception e){
            return ResponseResult.fail(null);
        }
    }

    /**
     * 点赞面试题的评论
     * ---------------
     * 调用此接口，如果该条面试题评论没有被该用户点赞过，则点赞，反之则取消点赞
     * @param commentId 点赞的面试题的评论id
     * @return {@link ResponseResult}<{@link Object}>
     */
    @PostMapping(path = "/likeQuestionComment/{commentId}")
    @ApiOperation("点赞面试题的评论")
    public ResponseResult<Object> likeQuestionComment(@PathVariable("commentId") Long commentId){
        try {
            //当前的用户id
            Long userid = SecurityContext.getCurrentUserId();
            boolean res=questionCommentService.likeQuestionComment(userid,commentId);
            return ResponseResult.ok(res);
        }catch (Exception e){
            return ResponseResult.fail(null);
        }

    }



    /**
     * 评论面试题
     *
     * @param questionCommentDTO 面试题评论dto
     * @return {@link ResponseResult}<{@link QuestionCommentVO}>
     */
    @PostMapping(path = "/writeQuestionComment")
    @ApiOperation("评论面试题")
    public ResponseResult<QuestionCommentVO> writeQuestionComment(@RequestBody @Valid QuestionCommentDTO questionCommentDTO) {
        try {
            QuestionCommentVO questionCommentVO = questionCommentService.writeQuestionComment(questionCommentDTO);
            //如果questionCommentVO为null，说明评论失败
            if(Objects.isNull(questionCommentVO)){
                return ResponseResult.fail(null);
            }
            //评论成功返回questionCommentVO
            return ResponseResult.ok(questionCommentVO);
        } catch (Exception e) {
            return ResponseResult.fail(null);
        }
    }


}
