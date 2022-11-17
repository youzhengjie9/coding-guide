package com.coding.guide.mobile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.mobile.service.TagService;
import com.coding.guide.mobile.entity.Tag;
import com.coding.guide.mobile.mapper.TagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 标签service impl
 *
 * @author youzhengjie
 * @date 2022/11/10 14:53:34
 */
@Service
@Slf4j
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {



}
