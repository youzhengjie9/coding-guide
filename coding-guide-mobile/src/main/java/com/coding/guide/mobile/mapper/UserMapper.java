package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户映射器
 *
 * @author youzhengjie
 * @date 2022/12/09 18:39:58
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据回复id获取用户的id和昵称
     *
     * @return {@link User}
     */
    User getUserIdAndNickNameByReplyId(@Param("replyId") long replyId);


}
