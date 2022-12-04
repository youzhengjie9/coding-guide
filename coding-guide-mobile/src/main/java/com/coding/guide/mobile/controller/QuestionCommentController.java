package com.coding.guide.mobile.controller;

import com.coding.guide.mobile.service.QuestionCommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private QuestionCommentService commentService;

    @Autowired
    public void setCommentService(QuestionCommentService commentService) {
        this.commentService = commentService;
    }



}
