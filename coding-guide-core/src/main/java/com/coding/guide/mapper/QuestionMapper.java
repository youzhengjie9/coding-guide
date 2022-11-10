package com.coding.guide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 面试题Mapper
 *
 * @author youzhengjie
 * @date 2022/11/08 23:58:03
 */
@Mapper
@Repository
public interface QuestionMapper extends BaseMapper<Question> {


    /**
     * 查询最热门的公开的文章并分页（按访问量排序，如果访问量相同则分别按点赞数、收藏数、评论数排序）
     *
     * @param page
     * @param size
     * @return {@link List}<{@link Question}>
     */
    List<Question> selectHottestQuestionByLimit(@Param("page") int page, @Param("size") int size);

}
