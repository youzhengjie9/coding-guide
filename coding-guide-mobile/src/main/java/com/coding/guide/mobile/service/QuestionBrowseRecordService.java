package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.mobile.entity.QuestionBrowseRecord;

/**
 * 面试题浏览记录服务
 *
 * @author youzhengjie
 * @date 2022/12/18 17:54:05
 */
public interface QuestionBrowseRecordService extends IService<QuestionBrowseRecord> {

    /**
     * 添加面试题浏览记录。
     * <p>
     * 1：如果该用户没有访问过这篇面试题，则新增
     * 2：如果该用户访问过这篇面试题，则修改browseTime为当前时间
     * @param questionId 访问的面试题id
     */
    boolean addQuestionBrowseRecord(Long questionId);

}
