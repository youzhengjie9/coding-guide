package com.coding.guide.mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.guide.mobile.entity.Tag;

import java.util.List;

/**
 * 标签service
 *
 * @author youzhengjie
 * @date 2022/11/10 14:53:23
 */
public interface TagService extends IService<Tag> {

    /**
     * 根据关键字模糊搜索标签名称列表
     *
     * @param page    页面
     * @param size    大小
     * @param keyword 关键字
     * @return {@link List}<{@link String}>
     */
    List<String> searchTagNameByKeywordAndLimit(int page,int size,String keyword);


}
