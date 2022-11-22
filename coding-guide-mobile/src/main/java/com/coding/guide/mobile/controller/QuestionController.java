package com.coding.guide.mobile.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.mobile.entity.Question;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.mobile.security.SecurityUser;
import com.coding.guide.mobile.vo.QuestionVO;
import com.coding.guide.mobile.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 面试题控制器
 *
 * @author youzhengjie
 * @date 2022/11/09 00:01:45
 */
@RestController
@Api("面试题控制器")
@RequestMapping(path = "/mobile/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 查询最热门的公开的文章并分页（按访问量排序，如果访问量相同则分别按点赞数、收藏数、评论数排序）
     *
     * @param page     页数
     * @param size     大小
     * @return {@link ResponseResult}<{@link List}<{@link Question}>>
     */
    @GetMapping(path = "/selectHottestQuestionByLimit/{page}/{size}")
    @ApiOperation("查询最热门的公开的文章并分页")
    public ResponseResult<Map<Object,Object>> selectHottestQuestionByLimit(@PathVariable("page") int page,
                                                            @PathVariable("size") int size){
        ResponseResult<Map<Object,Object>> responseResult = new ResponseResult<>();

        page=(page-1)*size;
        List<Question> questionList=questionService.selectHottestQuestionByLimit(page,size);
        //stream+bean拷贝
        List<QuestionVO> questionVOList = questionList.stream().map(question -> {
            QuestionVO questionVO = new QuestionVO();
            BeanUtil.copyProperties(question, questionVO);
            //将标签字符串tags转成List<String>集合
            String[] tagArray = question.getTags().split(",");
            List<String> tagList = Arrays.asList(tagArray);
            questionVO.setTagList(tagList);
            return questionVO;
        }).collect(Collectors.toList());

        long totalCount = questionService.count(new LambdaQueryWrapper<Question>().eq(Question::getIsPublic, 1));

        Map<Object, Object> map = MapUtil.builder()
                .put("questionVOList", questionVOList)
                .put("totalCount", totalCount)
                .build();

        responseResult.setCode(ResponseType.SUCCESS.getCode())
                .setMsg(ResponseType.SUCCESS.getMessage())
                .setData(map);
        return responseResult;

    }

    /**
     * 根据id查询公开的面试题详情
     * jmeter(500线程、循环10次)
     * 1：直接查询数据库------->（qps：723.7）
     * 2：先查redis(没有事先将缓存加进redis，说白了就是没有缓存预热)、再查数据库-------->（qps：780）
     * 3：先查redis(先将缓存加进redis，进行缓存预热)、再查数据库-------->（qps：1826）
     * 4：先查caffeine本地缓存，然后查redis(先将缓存加进redis，进行缓存预热)、再查数据库-------->（qps：4251）
     *
     * @param id id
     * @return {@link ResponseResult}<{@link Question}>
     */
    @GetMapping(path = "/selectQuestionDetail/{id}")
    @ApiOperation("根据id查询公开的面试题详情")
    public ResponseResult<Question> selectQuestionDetail(@PathVariable("id") long id){

        ResponseResult<Question> responseResult = new ResponseResult<>();

        Question question = questionService.selectQuestionDetail(id);

        responseResult.setCode(ResponseType.SUCCESS.getCode())
                .setMsg(ResponseType.SUCCESS.getMessage())
                .setData(question);

        return responseResult;
    }


    /**
     * 根据关键字搜索与之匹配的最热门的公开的文章并分页（按访问量排序，如果访问量相同则分别按点赞数、收藏数、评论数排序）
     *
     * @param page    页数
     * @param size    大小
     * @param keyword 搜索关键字 //todo 目前keyword为面试题的title
     * @return {@link ResponseResult}<{@link Map}<{@link Object},{@link Object}>>
     */
    @GetMapping(path = "/searchHottestQuestionByKeyWordAndLimit")
    @ApiOperation("根据关键字搜索与之匹配的最热门的公开的文章并分页")
    public ResponseResult<Map<Object,Object>> searchHottestQuestionByKeyWordAndLimit(@RequestParam("page") int page,
                                                                                     @RequestParam("size") int size,
                                                                                     @RequestParam("keyword") String keyword){
        ResponseResult<Map<Object,Object>> responseResult = new ResponseResult<>();

        page=(page-1)*size;
        // TODO: 2022/11/11 根据keyword去匹配面试题的title
        List<Question> questionList=questionService.searchHottestQuestionByKeyWordAndLimit(page,size,keyword);
        //stream+bean拷贝
        List<QuestionVO> questionVOList = questionList.stream().map(question -> {
            QuestionVO questionVO = new QuestionVO();
            BeanUtil.copyProperties(question, questionVO);
            //将标签字符串tags转成List<String>集合
            String[] tagArray = question.getTags().split(",");
            List<String> tagList = Arrays.asList(tagArray);
            questionVO.setTagList(tagList);
            return questionVO;
        }).collect(Collectors.toList());

        // TODO: 2022/11/11  根据keyword去匹配面试题的title
        long totalCount = questionService.selectQuestionCountByKeyWord(keyword);

        Map<Object, Object> map = MapUtil.builder()
                .put("questionVOList", questionVOList)
                .put("totalCount", totalCount)
                .build();

        responseResult.setCode(ResponseType.SUCCESS.getCode())
                .setMsg(ResponseType.SUCCESS.getMessage())
                .setData(map);
        return responseResult;

    }

    /**
     * 根据关键字搜索与之匹配的最新的公开的文章并分页（由于后期我们采用的是分布式id，是按时间递增的，所以我们这里按id进行排序，相当于按时间排序）
     *
     * @param page    页数
     * @param size    大小
     * @param keyword 搜索关键字 //todo 目前keyword为面试题的title
     * @return {@link ResponseResult}<{@link Map}<{@link Object},{@link Object}>>
     */
    @GetMapping(path = "/searchLatestQuestionByKeyWordAndLimit")
    @ApiOperation("根据关键字搜索与之匹配的最新的公开的文章并分页")
    public ResponseResult<Map<Object,Object>> searchLatestQuestionByKeyWordAndLimit(@RequestParam("page") int page,
                                                                                     @RequestParam("size") int size,
                                                                                     @RequestParam("keyword") String keyword){
        ResponseResult<Map<Object,Object>> responseResult = new ResponseResult<>();

        page=(page-1)*size;
        // TODO: 2022/11/11 根据keyword去匹配面试题的title
        List<Question> questionList=questionService.searchLatestQuestionByKeyWordAndLimit(page,size,keyword);
        //stream+bean拷贝
        List<QuestionVO> questionVOList = questionList.stream().map(question -> {
            QuestionVO questionVO = new QuestionVO();
            BeanUtil.copyProperties(question, questionVO);
            //将标签字符串tags转成List<String>集合
            String[] tagArray = question.getTags().split(",");
            List<String> tagList = Arrays.asList(tagArray);
            questionVO.setTagList(tagList);
            return questionVO;
        }).collect(Collectors.toList());

        // TODO: 2022/11/11  根据keyword去匹配面试题的title
        long totalCount = questionService.selectQuestionCountByKeyWord(keyword);

        Map<Object, Object> map = MapUtil.builder()
                .put("questionVOList", questionVOList)
                .put("totalCount", totalCount)
                .build();

        responseResult.setCode(ResponseType.SUCCESS.getCode())
                .setMsg(ResponseType.SUCCESS.getMessage())
                .setData(map);
        return responseResult;

    }

    /**
     * 根据关键字搜索与之匹配的被推荐的公开的文章并分页（排序默认按照访问量排序，如果访问量相同则分别按点赞数、收藏数、评论数排序）
     *
     * @param page    页数
     * @param size    大小
     * @param keyword 搜索关键字 //todo 目前keyword为面试题的title
     * @return {@link ResponseResult}<{@link Map}<{@link Object},{@link Object}>>
     */
    @GetMapping(path = "/searchRecommendQuestionByKeyWordAndLimit")
    @ApiOperation("根据关键字搜索与之匹配的被推荐的公开的文章并分页")
    public ResponseResult<Map<Object,Object>> searchRecommendQuestionByKeyWordAndLimit(@RequestParam("page") int page,
                                                                                    @RequestParam("size") int size,
                                                                                    @RequestParam("keyword") String keyword){
        ResponseResult<Map<Object,Object>> responseResult = new ResponseResult<>();

        page=(page-1)*size;
        // TODO: 2022/11/11 根据keyword去匹配面试题的title
        List<Question> questionList=questionService.searchRecommendQuestionByKeyWordAndLimit(page,size,keyword);
        //stream+bean拷贝
        List<QuestionVO> questionVOList = questionList.stream().map(question -> {
            QuestionVO questionVO = new QuestionVO();
            BeanUtil.copyProperties(question, questionVO);
            //将标签字符串tags转成List<String>集合
            String[] tagArray = question.getTags().split(",");
            List<String> tagList = Arrays.asList(tagArray);
            questionVO.setTagList(tagList);
            return questionVO;
        }).collect(Collectors.toList());

        // TODO: 2022/11/11  根据keyword去匹配面试题的title
        long totalCount = questionService.selectRecommendQuestionCountByKeyWord(keyword);

        Map<Object, Object> map = MapUtil.builder()
                .put("questionVOList", questionVOList)
                .put("totalCount", totalCount)
                .build();

        responseResult.setCode(ResponseType.SUCCESS.getCode())
                .setMsg(ResponseType.SUCCESS.getMessage())
                .setData(map);
        return responseResult;

    }

    /**
     * 根据tagid查询公开的文章并分页（按t_question的sort字段排序）
     *
     * @param page  页数
     * @param size  大小
     * @param tagid tagid（标签id）
     * @return {@link ResponseResult}<{@link Map}<{@link Object},{@link Object}>>
     */
    @GetMapping(path = "/selectQuestionByTagIdAndLimit/{tagid}/{page}/{size}")
    @ApiOperation("根据tagid查询公开的文章并分页")
    public ResponseResult<Map<Object,Object>> selectQuestionByTagIdAndLimit(@PathVariable("page") int page,
                                                                           @PathVariable("size") int size,
                                                                            @PathVariable("tagid") long tagid){
        ResponseResult<Map<Object,Object>> responseResult = new ResponseResult<>();

        page=(page-1)*size;
        List<Question> questionList=questionService.selectQuestionByTagIdAndLimit(page,size,tagid);
        //stream+bean拷贝
        List<QuestionVO> questionVOList = questionList.stream().map(question -> {
            QuestionVO questionVO = new QuestionVO();
            BeanUtil.copyProperties(question, questionVO);
            //将标签字符串tags转成List<String>集合
            String[] tagArray = question.getTags().split(",");
            List<String> tagList = Arrays.asList(tagArray);
            questionVO.setTagList(tagList);
            return questionVO;
        }).collect(Collectors.toList());

        long totalCount = questionService.selectQuestionCountByTagId(tagid);

        Map<Object, Object> map = MapUtil.builder()
                .put("questionVOList", questionVOList)
                .put("totalCount", totalCount)
                .build();

        responseResult.setCode(ResponseType.SUCCESS.getCode())
                .setMsg(ResponseType.SUCCESS.getMessage())
                .setData(map);
        return responseResult;

    }

    /**
     * 点赞面试题
     * ---------------
     * 调用此接口，如果面试题没有被该用户点赞过，则点赞，如果面试题已经被该用户点赞过，则取消点赞
     * @param questionId 点赞的面试题id
     * @return {@link ResponseResult}<{@link Object}>
     */
    @GetMapping(path = "/likeQuestion/{questionId}")
    @ApiOperation("点赞面试题")
    public ResponseResult<Object> likeQuestion(@PathVariable("questionId") Long questionId){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Long currentUserId = securityUser.getUser().getId();
        System.out.println(questionId+"<====>"+currentUserId);

        return ResponseResult.ok(null);
    }

    /**
     * 收藏面试题
     * 调用此接口，如果面试题没有被该用户收藏过，则收藏，如果面试题已经被该用户收藏过，则取消收藏
     * @param questionId 收藏的面试题id
     * @return {@link ResponseResult}<{@link Object}>
     */
    @GetMapping(path = "/collectQuestion/{questionId}")
    @ApiOperation("收藏面试题")
    public ResponseResult<Object> collectQuestion(@PathVariable("questionId") Long questionId){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Long currentUserId = securityUser.getUser().getId();
        System.out.println(questionId+"<====>"+currentUserId);

        return ResponseResult.ok(null);
    }


}
