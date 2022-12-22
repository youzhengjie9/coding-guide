package com.coding.guide.mobile.controller;

import cn.hutool.core.bean.BeanUtil;
import com.coding.guide.mobile.entity.Tag;
import com.coding.guide.mobile.service.TagService;
import com.coding.guide.mobile.vo.TagVO;
import com.coding.guide.common.data.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 标签控制器
 *
 * @author youzhengjie
 * @date 2022/11/12 18:09:08
 */
@RestController
@Api("标签控制器")
@RequestMapping(path = "/mobile/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 查询所有没被删除的标签（也就是条件为del_flag=0）
     *
     * @return {@link ResponseResult}<{@link List}<{@link TagVO}>>
     */
    @ApiOperation("查询所有没被删除的标签")
    @GetMapping(path = "/selectAllExistTag")
    public ResponseResult<List<TagVO>> selectAllExistTag(){

        List<Tag> tagList = tagService.lambdaQuery()
                .select(Tag::getId, Tag::getTagName)
                .list();
        final CopyOnWriteArrayList<TagVO> tagVOList = new CopyOnWriteArrayList<>();
        //初始化数据
        tagVOList.add(new TagVO().setId(1L).setTagName("最热"));
        tagVOList.add(new TagVO().setId(2L).setTagName("最新"));
        tagVOList.add(new TagVO().setId(3L).setTagName("推荐"));

        tagList.forEach(tag -> {
            TagVO tagVO = new TagVO();
            BeanUtil.copyProperties(tag,tagVO);
            tagVOList.add(tagVO);
        });

        return ResponseResult.ok(tagVOList);

    }


    /**
     * 根据关键字分页模糊搜索标签名称列表
     *
     * @param keyword 关键字
     * @param page    页面
     * @param size    大小
     * @return {@link ResponseResult}<{@link List}<{@link String}>>
     */
    @ApiOperation("根据关键字模糊搜索标签名称列表")
    @GetMapping(path = "/searchTagNameByKeywordAndLimit")
    public ResponseResult<List<String>> searchTagNameByKeywordAndLimit(@RequestParam("page") int page,
                                                                       @RequestParam("size") int size,
                                                                       @RequestParam("keyword") String keyword){
        try {
            //如果keyword是空的
            if(StringUtils.isBlank(keyword)){
                return ResponseResult.ok(null);
            }
            //如果keyword不为空
            else {
                page=(page-1)*size;
                List<String> tagNameList = tagService.searchTagNameByKeywordAndLimit(page, size, keyword);
                return ResponseResult.ok(tagNameList);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.fail(null);
        }
    }

}
