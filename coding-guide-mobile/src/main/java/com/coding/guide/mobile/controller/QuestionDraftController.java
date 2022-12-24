package com.coding.guide.mobile.controller;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.QuestionDTO;
import com.coding.guide.mobile.service.QuestionDraftService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 面试题草稿控制器
 *
 * @author youzhengjie
 * @date 2022/12/23 16:09:00
 */
@RestController
@Api("面试题草稿控制器")
@RequestMapping(path = "/mobile/question/draft")
public class QuestionDraftController {

    private QuestionDraftService questionDraftService;

    @Autowired
    public void setQuestionDraftService(QuestionDraftService questionDraftService) {
        this.questionDraftService = questionDraftService;
    }

    /**
     * 保存草稿
     *
     * @param questionDTO 面试题DTO
     * @param accessToken 访问令牌。保证接口幂等性（防止用户连续点击保存草稿导致数据库插入多条记录）
     * @return {@link ResponseResult}<{@link String}>
     */
    @PostMapping(path = "/saveDraft")
    public ResponseResult<String> saveDraft(@RequestBody @Valid QuestionDTO questionDTO,
                                            @RequestHeader(name = "accessToken") String accessToken){

        try {
            return questionDraftService.saveDraft(questionDTO, accessToken);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.fail("保存草稿失败");
        }

    }






}
