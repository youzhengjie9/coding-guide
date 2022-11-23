package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.mobile.entity.QuestionCollect;
import com.coding.guide.mobile.entity.QuestionLike;
import com.coding.guide.mobile.mapper.QuestionCollectMapper;
import com.coding.guide.mobile.mapper.QuestionLikeMapper;
import com.coding.guide.mobile.service.QuestionCollectService;
import com.coding.guide.mobile.service.QuestionLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 收藏面试题impl
 *
 * @author youzhengjie
 * @date 2022/11/22 23:28:41
 */
@Service
@Slf4j
public class QuestionCollectServiceImpl extends ServiceImpl<QuestionCollectMapper, QuestionCollect> implements QuestionCollectService {


}
