package com.coding.guide.mobile.controller;

import cn.hutool.core.bean.BeanUtil;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.entity.QuestionComment;
import com.coding.guide.mobile.service.QuestionCommentService;
import com.coding.guide.mobile.vo.QuestionCommentVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

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
    public ResponseResult<Map<Object,Object>> selectListByQuestionIdAndLimit(@PathVariable("questionId") long questionId,
                                                                                    @PathVariable("page") int page,
                                                                                    @PathVariable("size") int size){
        try {
            List<QuestionCommentVO> questionCommentVOList=
                    questionCommentService.selectListVoByQuestionIdAndLimit(questionId,page,size);

            long questCommentCount=questionCommentService.selectCountByQuestionId(questionId);

            Map<Object, Object> map = Map.of("questionCommentVOList", questionCommentVOList,
                    "questionCommentCount", questCommentCount);
            return ResponseResult.ok(map);
        }catch (Exception e){
            return ResponseResult.fail(null);
        }
    }

}
