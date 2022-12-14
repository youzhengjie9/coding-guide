package com.coding.guide.mobile.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.guide.common.utils.SnowId;
import com.coding.guide.mobile.dto.QuestionCommentDTO;
import com.coding.guide.mobile.entity.QuestionComment;
import com.coding.guide.mobile.entity.QuestionCommentLike;
import com.coding.guide.mobile.entity.User;
import com.coding.guide.mobile.mapper.QuestionCommentMapper;
import com.coding.guide.mobile.security.SecurityContext;
import com.coding.guide.mobile.service.QuestionCommentLikeService;
import com.coding.guide.mobile.service.QuestionCommentService;
import com.coding.guide.mobile.service.UserService;
import com.coding.guide.mobile.vo.QuestionCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 面试题评论服务impl
 *
 * @author youzhengjie
 * @date 2022/12/04 17:54:57
 */
@Service
@Transactional
public class QuestionCommentServiceImpl extends ServiceImpl<QuestionCommentMapper, QuestionComment> implements QuestionCommentService {

    private QuestionCommentMapper questionCommentMapper;

    private QuestionCommentLikeService questionCommentLikeService;

    private UserService userService;

    @Autowired
    public void setQuestionCommentMapper(QuestionCommentMapper questionCommentMapper) {
        this.questionCommentMapper = questionCommentMapper;
    }

    @Autowired
    public void setQuestionCommentLikeService(QuestionCommentLikeService questionCommentLikeService) {
        this.questionCommentLikeService = questionCommentLikeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<QuestionCommentVO> selectListVoByQuestionIdAndLimit(long questionId, int page, int size) {
        page = (page - 1) * size;

        return questionCommentMapper.selectListVoByQuestionIdAndLimit(questionId, page, size);
    }

    @Override
    public long selectCountByQuestionId(long questionId) {

        return questionCommentMapper.selectCountByQuestionId(questionId);
    }

    @Override
    public QuestionCommentVO writeQuestionComment(QuestionCommentDTO questionCommentDTO) {

        Long userId = SecurityContext.getCurrentUserId();

        //查询发送评论请求的用户
        User user = userService.lambdaQuery()
                .select(
                        User::getNickName,
                        User::getAvatar
                )
                .eq(User::getId, userId)
                .eq(User::getStatus, 0)
                .one();

        //通过判断user是否为null来判断用户是否存在和用户的status是否是正常，如果不满足则评论失败
        if (Objects.isNull(user)) {
            //返回null，评论失败
            return null;
        }
        //走到这里，证明可以评论
        //构建面试题评论对象
        QuestionComment questionComment = QuestionComment.builder()
                .id(SnowId.nextId())
                .userId(userId)
                .questionId(questionCommentDTO.getQuestionId())
                .content(questionCommentDTO.getContent())
                .commentTime(LocalDateTime.now())
                .replyCount(0)
                .likeCount(0)
                .delFlag(0)
                .build();
        //将面试题评论数据写入数据库
        boolean writeSuccess = this.save(questionComment);
        //判断是否写入数据库成功
        if (writeSuccess) {
            //将questionComment属性拷贝到questionCommentVO
            QuestionCommentVO questionCommentVO = BeanUtil.copyProperties(questionComment, QuestionCommentVO.class);
            //额外补全questionCommentVO一些属性
            questionCommentVO.setNickName(user.getNickName())
                    .setAvatar(user.getAvatar());
            return questionCommentVO;
        }
        //如果写入数据库失败，则返回null，评论失败
        return null;
    }

    @Override
    public List<Long> selectAllLikeQuestionCommentIdByUserId(Long userid) {
        return questionCommentLikeService.lambdaQuery()
                .select(QuestionCommentLike::getCommentId)
                .eq(QuestionCommentLike::getUserId, userid)
                .list()
                .parallelStream()
                .map(QuestionCommentLike::getCommentId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean likeQuestionComment(Long userid, Long commentId) {
        try {
            //判断用户是否点赞过这条评论
            Long count = questionCommentLikeService.lambdaQuery()
                    .eq(QuestionCommentLike::getUserId, userid)
                    .eq(QuestionCommentLike::getCommentId, commentId)
                    .count();
            //如果这条面试题评论没有被该用户点赞过，则点赞
            if (count == 0) {
                //修改t_question_comment表对应的评论点赞数+1
                questionCommentMapper.incrLikeCount(commentId);
                //添加一条点赞记录到t_question_comment_like
                QuestionCommentLike questionCommentLike = QuestionCommentLike.builder()
                        .id(SnowId.nextId())
                        .userId(userid)
                        .commentId(commentId)
                        .likeTime(LocalDateTime.now())
                        .build();
                questionCommentLikeService.save(questionCommentLike);
            }
            //如果这条面试题评论被该用户点赞过，则取消点赞
            else {
                //修改t_question_comment表对应的评论点赞数-1
                questionCommentMapper.decrLikeCount(commentId);
                //删除t_question_comment_like表中对应的点赞记录
                LambdaQueryWrapper<QuestionCommentLike> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(QuestionCommentLike :: getUserId,userid)
                                .eq(QuestionCommentLike :: getCommentId,commentId);
                questionCommentLikeService.remove(lambdaQueryWrapper);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
