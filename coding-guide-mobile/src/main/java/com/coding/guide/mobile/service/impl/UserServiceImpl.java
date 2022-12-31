package com.coding.guide.mobile.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import com.coding.guide.common.utils.ConverUtil;
import com.coding.guide.common.utils.GenerateCodeUtil;
import com.coding.guide.common.utils.ImageBase64ToMultipartFileUtil;
import com.coding.guide.mobile.constant.RedisConstant;
import com.coding.guide.mobile.dto.BindEmailDTO;
import com.coding.guide.mobile.dto.UserDataDTO;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.entity.UserDetail;
import com.coding.guide.mobile.mapper.UserDetailMapper;
import com.coding.guide.mobile.mapper.UserMapper;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.security.SecurityUser;
import com.coding.guide.mobile.service.*;
import com.coding.guide.mobile.vo.SimpleUserInfoVO;
import com.coding.guide.mobile.vo.UserCardInfoVO;
import com.coding.guide.mobile.vo.UserDataVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务impl
 *
 * @author youzhengjie
 * @date 2022/11/18 00:00:09
 */
@Service
@Slf4j
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private QuestionService questionService;

    private UserDetailService userDetailService;

    private IntegralLevelService integralLevelService;

    private UserFollowService userFollowService;

    private UserMapper userMapper;

    private UserDetailMapper userDetailMapper;

    private OssUploadService ossUploadService;

    private RedisTemplate redisTemplate;

    /**
     * java邮件api
     */
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private static final String DEFAULT_INTRO = "该用户暂时没有个人简介...";

    @Autowired
    public void setUserFollowService(UserFollowService userFollowService) {
        this.userFollowService = userFollowService;
    }
    @Autowired
    public void setIntegralLevelService(IntegralLevelService integralLevelService) {
        this.integralLevelService = integralLevelService;
    }
    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }
    @Autowired
    public void setUserDetailService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserDetailMapper(UserDetailMapper userDetailMapper) {
        this.userDetailMapper = userDetailMapper;
    }

    @Autowired
    @Qualifier("qiniuOssUploadServiceImpl")
    public void setOssUploadService(OssUploadService ossUploadService) {
        this.ossUploadService = ossUploadService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public UserCardInfoVO getCurUserCardInfo() {
        //因为每次进入这个方法之前都要经过jwt认证过滤器并每次都会从Redis拿到最新的SecurityUser对象，所以可以直接通过下面的方法拿到最新的User对象
        User user = SecurityContext.getCurrentUser();
        Long userId = user.getId();
        //执行一次bean拷贝
        UserCardInfoVO userCardInfoVO = BeanUtil.copyProperties(user, UserCardInfoVO.class);
        //查询UserDetail对象
        UserDetail userDetail = userDetailService.lambdaQuery()
                .select(
                        UserDetail::getProvince,
                        UserDetail::getCity,
                        UserDetail::getArea,
                        UserDetail::getSchool,
                        UserDetail::getBirthday,
                        UserDetail::getIntro
                )
                .eq(UserDetail::getUserId, userId)
                .one();
        //通过birthday计算出用户年龄
        int age = userDetail.getBirthday().until(LocalDate.now()).getDays();
        userCardInfoVO.setAge(age);
        //address
        String province=userDetail.getProvince();
        String city=userDetail.getCity();
        String area=userDetail.getArea();
        String address = ConverUtil.converAddress(province, city,area);
        userCardInfoVO.setAddress(address);
        //school
        userCardInfoVO.setSchool(Objects.isNull(userDetail.getSchool())? "" : userDetail.getSchool());
        //intro
        userCardInfoVO.setIntro(StringUtils.isBlank(userDetail.getIntro()) ? DEFAULT_INTRO : userCardInfoVO.getIntro());
        //查询点赞总数
        String likedCount = questionService.selectLikedCountByUserId(userId);
        //查询收藏总数
        String collectedCount = questionService.selectCollectedCountByUserId(userId);
        //查询关注数
        String followCount = userFollowService.getFollowCountByUserId(userId);
        //查询粉丝数
        String fansCount = userFollowService.getFansCountByUserId(userId);
        //放入userCardInfoVO对象中
        userCardInfoVO.setLikedCount(likedCount)
                .setCollectedCount(collectedCount)
                .setFollowCount(followCount)
                .setFansCount(fansCount);
        return userCardInfoVO;
    }

    @Override
    public UserCardInfoVO getUserCardInfoByUserId(long userid) {

        //查询User对象
        User user = this.lambdaQuery()
                .select(
                        User::getUserName,
                        User::getNickName,
                        User::getSex,
                        User::getAvatar
                )
                .eq(User::getId, userid)
                .eq(User::getStatus, 0)
                .one();
        //只有当User对象查询不为空才执行下面操作
        //user对象不为空说明这个用户是存在的、并且status为0和del_flag=0。
        if(Objects.nonNull(user)){
            //执行一次bean拷贝
            UserCardInfoVO userCardInfoVO = BeanUtil.copyProperties(user, UserCardInfoVO.class);
            //查询UserDetail对象
            UserDetail userDetail = userDetailService.lambdaQuery()
                    .select(
                            UserDetail::getProvince,
                            UserDetail::getCity,
                            UserDetail::getArea,
                            UserDetail::getSchool,
                            UserDetail::getBirthday,
                            UserDetail::getIntro
                    )
                    .eq(UserDetail::getUserId, userid)
                    .one();
            //通过birthday计算出用户年龄
            int age = userDetail.getBirthday().until(LocalDate.now()).getDays();
            userCardInfoVO.setAge(age);
            //address
            String province=userDetail.getProvince();
            String city=userDetail.getCity();
            String area=userDetail.getArea();
            String address = ConverUtil.converAddress(province, city,area);
            userCardInfoVO.setAddress(address);
            //school
            userCardInfoVO.setSchool(Objects.isNull(userDetail.getSchool())? "" : userDetail.getSchool());
            //intro
            userCardInfoVO.setIntro(StringUtils.isBlank(userDetail.getIntro()) ? DEFAULT_INTRO : userCardInfoVO.getIntro());
            //查询点赞总数
            String likedCount = questionService.selectLikedCountByUserId(userid);
            //查询收藏总数
            String collectedCount = questionService.selectCollectedCountByUserId(userid);
            //查询关注数
            String followCount = userFollowService.getFollowCountByUserId(userid);
            //查询粉丝数
            String fansCount = userFollowService.getFansCountByUserId(userid);
            //放入userCardInfoVO对象中
            userCardInfoVO.setLikedCount(likedCount)
                    .setCollectedCount(collectedCount)
                    .setFollowCount(followCount)
                    .setFansCount(fansCount);
            return userCardInfoVO;
        }
        //反之如果user对象为空则返回null
        return null;
    }

    @Override
    public SimpleUserInfoVO getSimpleUserInfoByPublisherId(long publisherId) {

        //获取当前登录的用户id。
        var currentUserId = SecurityContext.getCurrentUserId();
        //根据发布者用户id（publisherId）查询用户的nickName和avatar和integral
        var user = this.lambdaQuery()
                .select(
                        User::getNickName,
                        User::getAvatar,
                        User::getIntegral
                )
                .eq(User::getId, publisherId)
                .eq(User::getStatus, 0)
                .one();
        //第一次bean拷贝
        var simpleUserInfoVO = BeanUtil.copyProperties(user, SimpleUserInfoVO.class);
        //根据publisherId查询其学校
        UserDetail userDetail = userDetailService.lambdaQuery()
                .select(UserDetail::getSchool)
                .eq(UserDetail::getUserId, publisherId)
                .one();
        //获取用户当前的积分
        var integral = user.getIntegral();
        var integralLevel = integralLevelService.getIntegralLevelByIntegral(integral);
        String levelFormat = "Lv" + integralLevel.getLevel() + "-" + integralLevel.getDescribe();
        simpleUserInfoVO.setBackgroundColor(integralLevel.getBackgroundColor())
                        .setLevelFormat(levelFormat)
                        .setPublisherId(publisherId)
                        .setSchool((Objects.nonNull(userDetail)) ? userDetail.getSchool() : "" );

        // TODO: 2022/11/28 查询当前用户（currentUserId）是否关注了该用户（publisherId）

        return simpleUserInfoVO;
    }

    @Override
    public User getUserIdAndNickNameByReplyId(long replyId) {


        return userMapper.getUserIdAndNickNameByReplyId(replyId);
    }

    @Override
    public SimpleUserInfoVO getCurUserSimpleUserInfo() {

        //获取当前登录的用户id。
        var currentUserId = SecurityContext.getCurrentUserId();
        //根据当前用户的id查询用户的nickName和avatar和integral
        var user = this.lambdaQuery()
                .select(
                        User::getNickName,
                        User::getAvatar,
                        User::getIntegral
                )
                .eq(User::getId, currentUserId)
                .eq(User::getStatus, 0)
                .one();
        //第一次bean拷贝
        var simpleUserInfoVO = BeanUtil.copyProperties(user, SimpleUserInfoVO.class);
        //根据currentUserId查询其学校
        UserDetail userDetail = userDetailService.lambdaQuery()
                .select(UserDetail::getSchool)
                .eq(UserDetail::getUserId, currentUserId)
                .one();
        //获取用户当前的积分
        var integral = user.getIntegral();
        var integralLevel = integralLevelService.getIntegralLevelByIntegral(integral);
        String levelFormat = "Lv" + integralLevel.getLevel() + "-" + integralLevel.getDescribe();
        simpleUserInfoVO.setBackgroundColor(integralLevel.getBackgroundColor())
                .setLevelFormat(levelFormat)
                .setPublisherId(currentUserId)
                .setSchool((Objects.nonNull(userDetail)) ? userDetail.getSchool() : "" );

        return simpleUserInfoVO;

    }

    @Override
    public UserDataVO getCurUserData() {

        Long currentUserId = SecurityContext.getCurrentUserId();

        User user = this.lambdaQuery().select(
                User::getNickName,
                User::getAvatar,
                User::getSex
        ).eq(User::getId, currentUserId).one();

        UserDetail userDetail = userDetailService.lambdaQuery().select(
                UserDetail::getIntro,
                UserDetail::getBirthday,
                UserDetail::getProvince,
                UserDetail::getCity,
                UserDetail::getArea,
                UserDetail::getSchool,
                UserDetail::getEmail,
                UserDetail::getPhone
        ).eq(UserDetail::getUserId, currentUserId).one();
        UserDataVO userDataVO = BeanUtil.copyProperties(user, UserDataVO.class);
        BeanUtil.copyProperties(userDetail,userDataVO);
        String province = userDetail.getProvince();
        String city = userDetail.getCity();
        String area = userDetail.getArea();
        String address = ConverUtil.converAddress(province, city,area);
        userDataVO.setAddress(address);
        return userDataVO;
    }

    /**
     * 判断对象中有没有非空（非null）的元素
     *
     * @param obj obj
     * @return boolean false代表这个对象中所有属性都为null，true代表这个对象中不是所有属性都为null
     * @throws IllegalAccessException 非法访问异常
     */
    private static boolean hasNonNull(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        //默认是没有非空元素（也就是说这个对象全部属性都为null）
        boolean hasNonNull = false;
        //如果没有对象没有字段
        if(fields.length == 0){
            return hasNonNull;
        }
        //遍历对象的所有字段
        for (Field field : fields) {
            //获取当前遍历到的field的属性名
            String fieldName = field.getName();
            //排除serialVersionUID属性
            if(!"serialVersionUID".equals(fieldName)){
                field.setAccessible(true);
                //获取当前遍历到的field的属性值
                Object fieldValue = field.get(obj);
                //如果这个属性值不为空（非null）
                if(!Objects.isNull(fieldValue)){
                    //把hasNonNull设置为true
                    hasNonNull = true;
                    break;
                }
            }
        }
        return hasNonNull;
    }

    @Override
    public ResponseResult<String> updateUserData(Long userId, UserDataDTO userDataDTO) {

        try {
            ResponseResult<String> responseResult = new ResponseResult<>();
            responseResult.setCode(ResponseType.SUCCESS.getCode());
            responseResult.setMsg(ResponseType.SUCCESS.getMessage());
            //bean拷贝
            User user = BeanUtil.copyProperties(userDataDTO, User.class);
            UserDetail userDetail = BeanUtil.copyProperties(userDataDTO, UserDetail.class);
            //特殊处理avatar、address
            String avatarBase64 = userDataDTO.getAvatarBase64();
            //如果avatar不为空则进行处理
            if(!StringUtils.isBlank(avatarBase64)){
                MultipartFile avatarFile = ImageBase64ToMultipartFileUtil.convert(avatarBase64);
                //上传头像图片并获取该头像图片的url
                String avatarUrl = ossUploadService.imageUpload(avatarFile).getData().toString();
                //放到user对象中后面将更新到数据库
                user.setAvatar(avatarUrl);
                //将头像图片的url返回给前端
                responseResult.setData(avatarUrl);
            }
            //如果address不为空则进行处理
            String address = userDataDTO.getAddress();
            if(!StringUtils.isBlank(address)){
                //解析address
                Map<String, String> parseAddressMap = ConverUtil.parseAddress(address);
                String province = parseAddressMap.get(ConverUtil.PROVINCE_KEY);
                String city = parseAddressMap.get(ConverUtil.CITY_KEY);
                String area = parseAddressMap.get(ConverUtil.AREA_KEY);
                //将省市区属性放到userDetail对象中后面将更新到数据库
                userDetail.setProvince(province)
                        .setCity(city)
                        .setArea(area);
            }
            System.out.println(user);
            System.out.println(userDetail);
            //只要user对象中有一个非空元素就执行修改，反之如果user对象中的属性全是null则不执行修改
            if(hasNonNull(user)){
                user.setId(userId);
                user.setUpdateTime(LocalDateTime.now());
                userMapper.updateUserData(user);
                //根据用户名查询user
                User us = this.lambdaQuery().eq(User::getId, userId).one();
                //构造出SecurityUser对象
                SecurityUser securityUser = new SecurityUser(us);
                //更新redis中的SecurityUser对象缓存
                redisTemplate.opsForValue().set(RedisConstant.SECURITY_USER_KEY_PREFIX+userId,securityUser,3, TimeUnit.DAYS);
            }
            //只要userDetail对象中有一个非空元素就执行修改，反之如果userDetail对象中的属性全是null则不执行修改
            if(hasNonNull(userDetail)){
                userDetail.setUserId(userId);
                userDetail.setUpdateTime(LocalDateTime.now());
                userDetailMapper.updateUserDetailData(userDetail);
            }
            return responseResult;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public ResponseResult<String> sendBindEmailCode(String email) {
        Long userId = SecurityContext.getCurrentUserId();
        final String key =RedisConstant.BIND_EMAIL_CODE_KEY_PREFIX+userId+":"+email;
        //创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 6,
                1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy());
        //异步发送邮件验证码
        threadPoolExecutor.execute(()->{
            final String code = GenerateCodeUtil.generateNumberCode(6);
            redisTemplate.opsForValue().set(key,code,5,TimeUnit.MINUTES);
            //发送邮箱验证码
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromEmail);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("coding-guide绑定邮箱验证码");
            simpleMailMessage.setText("您的验证码为: "+code+" , 验证码将在5分钟后过期。");
            javaMailSender.send(simpleMailMessage);
        });
        return ResponseResult.ok(null);
    }

    @Override
    public ResponseResult<String> bindEmail(Long userId, BindEmailDTO bindEmailDTO) {
        ResponseResult<String> responseResult = new ResponseResult<>();
        String email = bindEmailDTO.getEmail();
        String code = bindEmailDTO.getCode();
        final String key =RedisConstant.BIND_EMAIL_CODE_KEY_PREFIX+userId+":"+email;
        //从redis中获取真正的验证码
        String trueCode = (String) redisTemplate.opsForValue().get(key);
        //如果和前端输入的验证码一直则通过
        if(code.equals(trueCode)){
            UserDetail userDetail = UserDetail.builder()
                    .email(email)
                    .userId(userId)
                    .updateTime(LocalDateTime.now())
                    .build();
            userDetailMapper.updateUserDetailData(userDetail);
            //删除验证码
            redisTemplate.delete(key);
            responseResult.setCode(ResponseType.BIND_EMAIL_CODE_SUCCESS.getCode())
                    .setMsg(ResponseType.BIND_EMAIL_CODE_SUCCESS.getMessage());
            return responseResult;
        }
        responseResult.setCode(ResponseType.BIND_EMAIL_CODE_ERROR.getCode())
                .setMsg(ResponseType.BIND_EMAIL_CODE_ERROR.getMessage());
        return responseResult;

    }

}

