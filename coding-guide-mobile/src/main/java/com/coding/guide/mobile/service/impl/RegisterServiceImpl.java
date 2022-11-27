package com.coding.guide.mobile.service.impl;

import cn.hutool.core.util.PhoneUtil;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.utils.SnowId;
import com.coding.guide.mobile.constant.RedisConstant;
import com.coding.guide.mobile.dto.UserRegisterDTO;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.entity.UserDetail;
import com.coding.guide.mobile.service.RegisterService;
import com.coding.guide.mobile.service.UserDetailService;
import com.coding.guide.mobile.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
@Transactional //开启事务
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //手机验证码过期时间，单位是:分钟
    private static final long PHONE_CODE_EXPIRED= 5L;

    //默认昵称前缀
    private static final String DEFAULT_NICK_NAME_PREFIX="昵称-";

    //默认头像
    private static final String DEFAULT_AVATAR="https://pic4.zhimg.com/80/v2-c5a0d0d57c1a85c6db56e918707f54a3_720w.webp";

    /**
     * 注册用户
     *
     * @param userRegisterDTO 用户注册dto
     * @return {@link ResponseResult}
     */
    @Override
    public ResponseResult register(UserRegisterDTO userRegisterDTO) {

        ResponseResult<String> responseResult = new ResponseResult<>();

        try {
            //校验密码和确认密码是否一致
            boolean checkPwd=checkConfirmPassword(userRegisterDTO.getPassword(),userRegisterDTO.getConfirmPassword());
            if(!checkPwd){
                responseResult.setCode(ResponseType.CONFIRM_PASSWORD_ERROR.getCode());
                responseResult.setMsg(ResponseType.CONFIRM_PASSWORD_ERROR.getMessage());
                return responseResult;
            }
            //校验手机号格式是否正确
            boolean isPhone = PhoneUtil.isMobile(userRegisterDTO.getPhone());
            if(!isPhone){
                responseResult.setCode(ResponseType.PHONE_ERROR.getCode());
                responseResult.setMsg(ResponseType.PHONE_ERROR.getMessage());
                return responseResult;
            }
            //校验验证码是否正确
            boolean checkCode = checkPhoneCode(userRegisterDTO.getPhone(), userRegisterDTO.getCode());
            //如果验证码不正确
            if(!checkCode){
                responseResult.setCode(ResponseType.PHONE_CODE_ERROR.getCode());
                responseResult.setMsg(ResponseType.PHONE_CODE_ERROR.getMessage());
                return responseResult;
            }
            //校验注册的用户名是否已存在数据库（不含已删除（del_flag=1）的用户）
            //mybatis-plus会默认排除del_flag=1的用户，所以不用进行这个判断
            Long count = userService.lambdaQuery().eq(User::getUserName, userRegisterDTO.getUserName())
                    .count();
            //说明用户已经存在，不能创建
            if(count !=0){
                responseResult.setCode(ResponseType.USERNAME_EXISTED.getCode());
                responseResult.setMsg(ResponseType.USERNAME_EXISTED.getMessage());
                return responseResult;
            }

            //走到这步，说明已经把数据校验完成了，就可以注册用户了
            User user = new User();
            BeanUtils.copyProperties(userRegisterDTO,user);

            long userid = SnowId.nextId();
            user.setId(userid)
                    .setNickName(DEFAULT_NICK_NAME_PREFIX+user.getUserName())
                    .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()))
                    .setStatus(0)
                    .setSex(2)
                    .setAvatar(DEFAULT_AVATAR)
                    .setIntegral(0)
                    .setMoney(new BigDecimal("0.00"))
                    .setCreateTime(LocalDate.now())
                    .setUpdateTime(LocalDateTime.now()).setDelFlag(0);
            //将user对象数据添加到t_user表中
            userService.save(user);

            UserDetail userDetail = UserDetail.builder()
                    .id(SnowId.nextId())
                    .userId(userid)
                    .phone(userRegisterDTO.getPhone())
                    .birthday(LocalDate.now())
                    .updateTime(LocalDateTime.now())
                    .delFlag(0)
                    .build();
            //将userDetail对象添加到t_user_detail表中
            userDetailService.save(userDetail);

            //最后要把验证码删除掉
            redisTemplate.delete(RedisConstant.PHONE_CODE_KEY_PREFIX +userRegisterDTO.getPhone());

            responseResult.setCode(ResponseType.REGISTER_SUCCESS.getCode());
            responseResult.setMsg(ResponseType.REGISTER_SUCCESS.getMessage());
            return responseResult;

        }catch (Exception e){
           throw new RuntimeException("注册失败");
        }
    }

    private boolean checkConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    /**
     * 检查手机验证码
     *
     * @param phone 手机
     * @param code  代码
     * @return boolean true则说明验证通过
     */
    private boolean checkPhoneCode(String phone, String code) {

        //如果手机号和验证码其中一个为空，则直接返回false
        if(!StringUtils.hasText(phone) || !StringUtils.hasText(code)){
            return false;
        }
        //判断redis中是否有验证码key，如果没有则直接返回false
        String key=RedisConstant.PHONE_CODE_KEY_PREFIX+phone;
        if(!redisTemplate.hasKey(key)){
            return false;
        }
        //走到这步说明redis中有验证码key，可以从redis中取出正确的验证码
        String realCode = (String) redisTemplate.opsForValue().get(key);
        //将真正的验证码和前端传过来的验证码进行对比
        return realCode.equals(code);
    }

    /**
     * 生成手机6位数字验证码
     */
    private String generateCode(){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            //在高并发下ThreadLocalRandom的性能远远大于Random的性能
            int number = ThreadLocalRandom.current().nextInt(10);
            stringBuffer.append(number);
        }
        return stringBuffer.toString();
    }

    @Override
    public ResponseResult<String> sendCode(String phone) {

        ResponseResult<String> responseResult = new ResponseResult<>();

        try {
            boolean isPhone = PhoneUtil.isMobile(phone);
            //校验手机号格式是否正确
            if(isPhone){
                //生成验证码
                String code = generateCode();
                //将验证码存入redis中
                redisTemplate.opsForValue().set(RedisConstant.PHONE_CODE_KEY_PREFIX+phone,code,PHONE_CODE_EXPIRED, TimeUnit.MINUTES);
                responseResult.setCode(ResponseType.SEND_PHONE_CODE_SUCCESS.getCode());
                responseResult.setMsg(ResponseType.SEND_PHONE_CODE_SUCCESS.getMessage());
                return responseResult;
            }else {
                responseResult.setCode(ResponseType.SEND_PHONE_CODE_ERROR.getCode());
                responseResult.setMsg(ResponseType.SEND_PHONE_CODE_ERROR.getMessage());
                return responseResult;
            }
        }catch (Exception e){
            e.printStackTrace();
            responseResult.setCode(ResponseType.SEND_PHONE_CODE_ERROR.getCode());
            responseResult.setMsg(ResponseType.SEND_PHONE_CODE_ERROR.getMessage());
            return responseResult;
        }

    }
}
