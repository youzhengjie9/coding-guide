package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.mobile.entity.QuestionReply;
import com.coding.guide.mobile.mapper.QuestionReplyMapper;
import com.coding.guide.mobile.service.QuestionReplyService;
import org.springframework.stereotype.Service;

/**
 * 面试题回复服务impl
 *
 * @author youzhengjie
 * @date 2022/12/04 17:54:33
 */
@Service
public class QuestionReplyServiceImpl extends ServiceImpl<QuestionReplyMapper, QuestionReply> implements QuestionReplyService {

}
