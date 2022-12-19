package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.common.utils.SnowId;
import com.coding.guide.mobile.entity.QuestionBrowseRecord;
import com.coding.guide.mobile.mapper.QuestionBrowseRecordMapper;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.service.QuestionBrowseRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 面试题浏览记录服务impl
 *
 * @author youzhengjie
 * @date 2022/12/18 17:56:18
 */
@Service
public class QuestionBrowseRecordServiceImpl extends ServiceImpl<QuestionBrowseRecordMapper, QuestionBrowseRecord> implements QuestionBrowseRecordService {

    @Override
    public boolean addQuestionBrowseRecord(Long questionId) {
        try {
            Long userId = SecurityContext.getCurrentUserId();
            //判断用户是否访问过这篇面试题
            QuestionBrowseRecord questionBrowseRecord = this.lambdaQuery()
                    .eq(QuestionBrowseRecord::getUserId, userId)
                    .eq(QuestionBrowseRecord::getQuestionId, questionId)
                    .one();
            //1：如果该用户没有访问过这篇面试题，则新增
            if(Objects.isNull(questionBrowseRecord)){
                questionBrowseRecord = QuestionBrowseRecord.builder()
                        .id(SnowId.nextId())
                        .userId(userId)
                        .questionId(questionId)
                        .browseTime(LocalDateTime.now())
                        .build();
                this.save(questionBrowseRecord);
            }
            //2：如果该用户访问过这篇面试题，则修改browseTime为当前时间
            else {
                LambdaUpdateWrapper<QuestionBrowseRecord> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper.set(QuestionBrowseRecord::getBrowseTime,LocalDateTime.now())
                        .eq(QuestionBrowseRecord::getId,questionBrowseRecord.getId());
                this.update(lambdaUpdateWrapper);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
