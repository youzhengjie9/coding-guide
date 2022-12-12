package com.coding.guide.mobile.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.common.utils.SnowId;
import com.coding.guide.mobile.dto.QuestionReplyDTO;
import com.coding.guide.mobile.entity.QuestionReply;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.mapper.QuestionReplyMapper;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.service.QuestionReplyService;
import com.coding.guide.mobile.service.UserService;
import com.coding.guide.mobile.vo.QuestionReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 面试题回复服务impl
 *
 * @author youzhengjie
 * @date 2022/12/04 17:54:33
 */
@Service
public class QuestionReplyServiceImpl extends ServiceImpl<QuestionReplyMapper, QuestionReply> implements QuestionReplyService {

    private QuestionReplyMapper questionReplyMapper;

    private UserService userService;

    @Autowired
    public void setQuestionReplyMapper(QuestionReplyMapper questionReplyMapper) {
        this.questionReplyMapper = questionReplyMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //递归生成多级回复列表
    private void generateReplyList(QuestionReplyVO rp0, List<QuestionReplyVO> secondLevelQuestionReplyList, List<QuestionReplyVO> finallyQuestionReplyList){
        //只有del_flag=0才能被加进集合中
        if(rp0.getDelFlag() == 0){
            finallyQuestionReplyList.add(rp0);
        }
        //获取一级回复的id
        long rp0Id = rp0.getId();
        for (QuestionReplyVO rp1 : secondLevelQuestionReplyList) {
//          如果rp1是该一级回复的二级回复
            if(rp1.getRepliedId() == rp0Id){

                generateReplyList(rp1,secondLevelQuestionReplyList,finallyQuestionReplyList);

            }
        }
    }

    @Override
    public Map<String,Object> selectMapByCommentIdAndLimit(long commentId, int page, int size) {

        page=(page-1)*size;

        // TODO: 2022/12/12 这里需要后期进行优化，暂时只是为了实现回复展示的功能
        //一级面试题回复列表（replied_id=0）,并分页。
        var firstLevelQuestionReplyList = questionReplyMapper.selectListVoByReplyIdEq0AndLimit(commentId, page, size);
        //二级面试题回复列表（replied_id!=0）,不进行分页
        var secondLevelQuestionReplyList = questionReplyMapper.selectAllListVoByReplyIdNe0(commentId);

        //存放最终回复结果集合，返回给前端
        List<QuestionReplyVO> questionReplyVOList = new CopyOnWriteArrayList<>();
        for (QuestionReplyVO rp0 : firstLevelQuestionReplyList) {
            generateReplyList(rp0,secondLevelQuestionReplyList,questionReplyVOList);
        }

        return Map.of("questionReplyVOList",questionReplyVOList,
                "firstLevelQuestionReplyListCount", firstLevelQuestionReplyList.size());
    }

    @Override
    public long selectCountByReplyIdEq0AndCommentId(long commentId) {

        return questionReplyMapper.selectCountByReplyIdEq0AndCommentId(commentId);
    }

    @Override
    public QuestionReplyVO writeQuestionReply(QuestionReplyDTO questionReplyDTO) {

        Long userId = SecurityContext.getCurrentUserId();

        //查询发送回复请求的用户
        User user = userService.lambdaQuery()
                .select(
                        User::getNickName,
                        User::getAvatar
                )
                .eq(User::getId, userId)
                .eq(User::getStatus, 0)
                .one();

        //通过判断user是否为null来判断用户是否存在和用户的status是否是正常，如果不满足则回复失败
        if (Objects.isNull(user)) {
            //返回null，回复失败
            return null;
        }
        //走到这里，证明可以进行回复
        //构建面试题回复对象
        QuestionReply questionReply = QuestionReply.builder()
                .id(SnowId.nextId())
                .userId(userId)
                .repliedId(questionReplyDTO.getRepliedId())
                .commentId(questionReplyDTO.getCommentId())
                .content(questionReplyDTO.getContent())
                .replyTime(LocalDateTime.now())
                .likeCount(0)
                .delFlag(0)
                .build();
        //将评论的回复数据写入数据库
        boolean writeSuccess = this.save(questionReply);
        if(writeSuccess){
            //将questionComment属性拷贝到questionCommentVO
            QuestionReplyVO questionReplyVO = BeanUtil.copyProperties(questionReply, QuestionReplyVO.class);
            //通过被回复id，查询被回复用户的一些属性
            User repliedUser = userService.getUserIdAndNickNameByReplyId(questionReplyDTO.getRepliedId());
            //补充一些属性
            questionReplyVO.setNickName(user.getNickName())
                    .setAvatar(user.getAvatar())
                    .setRepliedUserId(repliedUser.getId())
                    .setRepliedNickName(repliedUser.getNickName());
            return questionReplyVO;
        }
        //如果写入数据库失败，则返回null，回复失败
        return null;
    }
}
