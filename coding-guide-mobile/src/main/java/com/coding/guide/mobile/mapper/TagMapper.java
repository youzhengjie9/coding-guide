package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 标签mapper
 *
 * @author youzhengjie
 * @date 2022/11/10 14:52:55
 */
@Mapper
@Repository
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据关键字模糊搜索标签名称列表
     *
     * @param page    页面
     * @param size    大小
     * @param keyword 关键字
     * @return {@link List}<{@link String}>
     */
    List<String> searchTagNameByKeywordAndLimit(@Param("page") int page,
                                                @Param("size") int size,
                                                @Param("keyword") String keyword);


}
