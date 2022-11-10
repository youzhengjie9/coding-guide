package com.coding.guide.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.entity.Question;

import java.util.List;

/**
 * 面试题service
 *
 * @author youzhengjie
 * @date 2022/11/09 00:00:10
 */
public interface QuestionService extends IService<Question> {


    /**
     * 查询最热门的公开的文章并分页（按访问量排序，如果访问量相同则分别按点赞数、收藏数、评论数排序）
     *
     * @param page
     * @param size
     * @return {@link List}<{@link Question}>
     */
    List<Question> selectHottestQuestionByLimit(int page, int size);

}
