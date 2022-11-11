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

    /**
     * 根据关键字搜索与之匹配的最热门的公开的文章并分页（按访问量排序，如果访问量相同则分别按点赞数、收藏数、评论数排序）
     *
     * @param page    页面
     * @param size    大小
     * @param keyword 关键字 //todo 目前keyword为面试题的title
     * @return {@link List}<{@link Question}>
     */
    List<Question> searchHottestQuestionByKeyWordAndLimit(int page, int size, String keyword);


    /**
     * 查询符合关键字的记录数
     *
     * @param keyword 关键字
     * @return long
     */
    long selectQuestionCountByKeyWord(String keyword);

    /**
     * 根据关键字搜索与之匹配的最新的公开的文章并分页（由于后期我们采用的是分布式id，是按时间递增的，所以我们这里按id进行排序，相当于按时间排序）
     *
     * @param page    页面
     * @param size    大小
     * @param keyword 关键字 //todo 目前keyword为面试题的title
     * @return {@link List}<{@link Question}>
     */
    List<Question> searchLatestQuestionByKeyWordAndLimit(int page, int size, String keyword);


    /**
     * 据关键字搜索与之匹配的被推荐的公开的文章并分页（排序默认按照访问量排序，如果访问量相同则分别按点赞数、收藏数、评论数排序）
     *
     * @param page    页数
     * @param size    大小
     * @param keyword 关键字
     * @return {@link List}<{@link Question}>
     */
    List<Question> searchRecommendQuestionByKeyWordAndLimit(int page, int size, String keyword);


    /**
     * 查询符合关键字、并且是被推荐的记录数
     *
     * @param keyword 关键字
     * @return long
     */
    long selectRecommendQuestionCountByKeyWord(String keyword);

}
