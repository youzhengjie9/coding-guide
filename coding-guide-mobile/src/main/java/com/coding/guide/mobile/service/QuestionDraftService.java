package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.mobile.dto.QuestionDraftDTO;
import com.coding.guide.mobile.entity.QuestionDraft;

/**
 * 面试题草稿服务
 *
 * @author youzhengjie
 * @date 2022/12/23 16:03:36
 */
public interface QuestionDraftService extends IService<QuestionDraft> {


    /**
     * 保存草稿
     *
     * @param questionDraftDTO 面试题草稿DTO
     * @param accessToken      访问令牌。保证接口幂等性（防止用户连续点击保存草稿导致数据库插入多条记录）
     * @return boolean
     */
    boolean saveDraft(QuestionDraftDTO questionDraftDTO,String accessToken);


}
