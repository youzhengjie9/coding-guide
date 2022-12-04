package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.QuestionReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 面试题回复映射器
 *
 * @author youzhengjie
 * @date 2022/12/04 17:54:06
 */
@Mapper
@Repository
public interface QuestionReplyMapper extends BaseMapper<QuestionReply> {


}
