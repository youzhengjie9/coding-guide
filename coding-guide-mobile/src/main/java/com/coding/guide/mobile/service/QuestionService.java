package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.dto.QuestionDTO;
import com.coding.guide.mobile.entity.Question;

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
     * @param page 页面
     * @param size 大小
     * @return {@link List}<{@link Question}>
     */
    List<Question> selectHottestQuestionByLimit(int page, int size);


    /**
     * 根据id查询公开的面试题详情
     *
     * @param id id
     * @return {@link Question}
     */
    Question selectQuestionDetail(long id);


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

    /**
     * 根据tagid查询公开的文章并分页（按t_question的sort字段排序）
     *
     * @param page  页面
     * @param size  大小
     * @param tagid tagid
     * @return {@link List}<{@link Question}>
     */
    List<Question> selectQuestionByTagIdAndLimit(int page, int size, long tagid);


    /**
     * 根据tagid查询面试题数量
     *
     * @param tagid tagid
     * @return long
     */
    long selectQuestionCountByTagId(long tagid);

    /**
     * 点赞面试题
     *
     * @param userid     用户id
     * @param questionId 问题id
     * @return boolean
     */
    boolean likeQuestion(Long userid, Long questionId);

    /**
     * 收藏面试题
     *
     * @param userid     用户id
     * @param questionId 问题id
     * @return boolean
     */
    boolean collectQuestion(Long userid, Long questionId);


    /**
     * 获取用户点赞的所有文章id
     *
     * @param userid 用户标识
     * @return {@link List}<{@link Long}>
     */
    List<Long> selectAllLikeQuestionIdByUserId(Long userid);


    /**
     * 获取用户收藏的所有文章id集合
     *
     * @param userid 用户标识
     * @return {@link List}<{@link Long}>
     */
    List<Long> selectAllCollectQuestionIdByUserId(Long userid);

    /**
     * 查询用户的作品（公开的面试题），并按照发布日期排序
     *
     * @param userid 用户id
     * @param page          页面
     * @param size          大小
     * @return {@link List}<{@link Question}>
     */
    List<Question> selectUserPublicQuestionByLimit(Long userid, int page, int size);


    /**
     * 查询用户的私密（私密的面试题），并按照发布日期排序
     *
     * @param userid 用户id
     * @param page          页面
     * @param size          大小
     * @return {@link List}<{@link Question}>
     */
    List<Question> selectUserPrivateQuestionByLimit(Long userid, int page, int size);


    /**
     * 查询用户的收藏（收藏并且公开的面试题）
     *
     * @param userid 用户id
     * @param page          页面
     * @param size          大小
     * @return {@link List}<{@link Question}>
     */
    List<Question> selectUserCollectQuestionByLimit(Long userid, int page, int size);


    /**
     * 查询用户的收藏数（收藏并且公开的面试题数）
     *
     * @return {@link Long}
     */
    Long selectUserCollectQuestionCount(Long userid);


    /**
     * 查询用户的点赞（点赞并且公开的面试题）
     *
     * @param userid 用户id
     * @param page          页面
     * @param size          大小
     * @return {@link List}<{@link Question}>
     */
    List<Question> selectUserLikeQuestionByLimit(Long userid, int page, int size);


    /**
     * 查询用户的点赞数（点赞并且公开的面试题数）
     *
     * @param userid 用户id
     * @return {@link Long}
     */
    Long selectUserLikeQuestionCount(Long userid);

    /**
     * 根据用户id查询用户发布的所有面试题被点赞数之和
     *
     * @param userid 用户id
     */
    String selectLikedCountByUserId(Long userid);

    /**
     * 根据用户id查询用户发布的所有面试题被收藏数之和
     *
     * @param userid 用户id
     */
    String selectCollectedCountByUserId(Long userid);

    /**
     * 分页查询用户的面试题浏览记录
     *
     * @param currentUserId 当前用户id
     * @param page          页面
     * @param size          大小
     * @return {@link List}<{@link Question}>
     */
    List<Question> selectUserQuestionBrowseRecordByLimit(Long currentUserId, int page, int size);

    /**
     * 查询用户的面试题浏览记录数量
     *
     * @param currentUserId 当前用户id
     * @return {@link Long}
     */
    Long selectUserQuestionBrowseRecordCount(Long currentUserId);


    /**
     * 发布面试题
     *
     * @param questionDTO 面试题DTO
     * @param accessToken 访问令牌。保证接口幂等性（防止用户连续点击发布内容导致数据库插入多条记录）
     * @return boolean
     */
    ResponseResult<String> publishQuestion(QuestionDTO questionDTO, String accessToken);

}
