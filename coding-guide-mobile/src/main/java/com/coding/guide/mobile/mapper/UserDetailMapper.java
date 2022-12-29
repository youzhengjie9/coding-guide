package com.coding.guide.mobile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.guide.mobile.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * UserDetail映射器
 *
 * @author youzhengjie
 * @date 2022/11/26 23:22:36
 */
@Mapper
@Repository
public interface UserDetailMapper extends BaseMapper<UserDetail> {

    /**
     * 修改用户资料
     *
     * @param userDetail userDetail
     */
    void updateUserDetailData(@Param("userDetail") UserDetail userDetail);


}
