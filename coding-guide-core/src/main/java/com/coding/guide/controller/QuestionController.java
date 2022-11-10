package com.coding.guide.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.coding.guide.data.ResponseResult;
import com.coding.guide.entity.Question;
import com.coding.guide.enums.ResponseType;
import com.coding.guide.service.QuestionService;
import com.coding.guide.vo.QuestionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 面试题控制器
 *
 * @author youzhengjie
 * @date 2022/11/09 00:01:45
 */
@RestController
@Api("面试题控制器")
@RequestMapping(path = "/question")
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
    @ApiOperation("查询最热门的公开的文章并分页（按访问量排序，如果访问量相同则分别按点赞数、收藏数、评论数排序）")
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
     * 根据id查询公开的面试题数据
     *
     * @param id id
     * @return {@link ResponseResult}<{@link Question}>
     */
    @GetMapping(path = "/selectQuestionDetail/{id}")
    @ApiOperation("根据id查询公开的面试题数据")
    public ResponseResult<Question> selectQuestionDetail(@PathVariable("id") long id){

        ResponseResult<Question> responseResult = new ResponseResult<>();

        Question question = questionService.lambdaQuery()
                .select(Question::getId,
                        Question::getUserid,
                        Question::getTitle,
                        Question::getContent,
                        Question::getAllowComment,
                        Question::getRecommend,
                        Question::getReadCount,
                        Question::getLikeCount,
                        Question::getCollectCount,
                        Question::getCommentCount,
                        Question::getMeetCount,
                        Question::getDifficulty,
                        Question::getTags,
                        Question::getPublishTime)
                .eq(Question::getIsPublic, 1)
                .eq(Question::getId,id)
                .one();

        responseResult.setCode(ResponseType.SUCCESS.getCode())
                .setMsg(ResponseType.SUCCESS.getMessage())
                .setData(question);
        return responseResult;

    }




}
