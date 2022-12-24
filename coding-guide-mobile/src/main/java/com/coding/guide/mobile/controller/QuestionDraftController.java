package com.coding.guide.mobile.controller;

import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.QuestionDraftDTO;
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
     * @param questionDraftDTO 面试题草稿DTO
     * @param accessToken      访问令牌。保证接口幂等性（防止用户连续点击保存草稿导致数据库插入多条记录）
     * @return {@link ResponseResult}<{@link String}>
     */
    @PostMapping(path = "/saveDraft")
    public ResponseResult<String> saveDraft(@RequestBody @Valid QuestionDraftDTO questionDraftDTO,
                                            @RequestHeader(name = "accessToken") String accessToken){

        try {
            boolean saveSuccess = questionDraftService.saveDraft(questionDraftDTO,accessToken);
            if(saveSuccess) {
                return ResponseResult.ok(null);
            }
            return ResponseResult.fail(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.fail(null);
        }

    }






}
