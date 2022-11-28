package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.entity.UserFollow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户关注映射器
 *
 * @author youzhengjie
 * @date 2022/11/28 09:14:34
 */
@Mapper
@Repository
public interface UserFollowMapper extends BaseMapper<UserFollow> {

    /**
     * 查询指定用户关注的所有用户的id集合
     *
     * @param userid 用户id
     * @return {@link List}<{@link Long}>
     */
    List<Long> getFollowUserIdListByUserId(@Param("userid") long userid);

    /**
     * 查询指定用户关注的所有用户对象集合
     *
     * @param userid 用户id
     * @return {@link List}<{@link User}>
     */
    List<User> getFollowUserListByUserId(@Param("userid") long userid);

    /**
     * 查询指定用户关注数（返回结果要进行数量格式化）
     *
     * @param userid 用户id
     * @return {@link Long}
     */
    Long getFollowCountByUserId(@Param("userid") long userid);


    /**
     * 查询指定用户的所有粉丝用户对象集合
     *
     * @param userid 用户id
     * @return {@link List}<{@link User}>
     */
    List<User> getFansUserListByUserId(@Param("userid") long userid);

    /**
     * 查询指定用户粉丝数（返回结果要进行数量格式化）
     *
     * @param userid 用户id
     * @return {@link Long}
     */
    Long getFansCountByUserId(@Param("userid") long userid);

}
