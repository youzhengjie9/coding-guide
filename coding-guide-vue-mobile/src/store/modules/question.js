const state = {
    //当前用户所有点赞的面试题id(questionid)集合
    QuestionLikeIds:[],
    //当前用户所有收藏的面试题id(questionid)集合
    QuestionCollectIds:[],
    //当前用户所有点赞的面试题评论id(commentid)集合
    QuestionCommentLikeIds:[],
    //当前用户所有点赞的面试题评论的回复id(replyId)集合
    QuestionReplyLikeIds:[],
};

const mutations = {
    //初始化面试题点赞列表
    INIT_LIKE_LIST(state,likeList){
        state.QuestionLikeIds=likeList
    },
    //初始化面试题收藏列表
    INIT_COLLECT_LIST(state,collectList){
        state.QuestionCollectIds=collectList
    },
    //初始化面试题评论点赞列表
    INIT_QUESTION_COMMENT_LIKE_LIST(state,questionCommentLikeList){
        state.QuestionCommentLikeIds=questionCommentLikeList
    },
    //初始化面试题回复点赞列表
    INIT_QUESTION_REPLY_LIKE_LIST(state,questionReplyLikeList){
        state.QuestionReplyLikeIds=questionReplyLikeList
    },
    //点赞（将questionId添加到数组中）
    LIKE(state,questionId) {
        state.QuestionLikeIds.push(questionId)
    },
    //取消点赞（从数组中移除指定questionId元素）
    CANCEL_LIKE(state,questionId) {
        for (var i in state.QuestionLikeIds) {
            if (state.QuestionLikeIds[i] == questionId) {
                state.QuestionLikeIds.splice(i, 1);
            }
        }
    },
    //收藏（将questionId添加到数组中）
    COLLECT(state,questionId) {
        state.QuestionCollectIds.push(questionId)
    },
    //取消收藏（从数组中移除指定questionId元素）
    CANCEL_COLLECT(state,questionId) {
        for (var i in state.QuestionCollectIds) {
            if (state.QuestionCollectIds[i] == questionId) {
                state.QuestionCollectIds.splice(i, 1);
            }
        }
    },
    //点赞面试题评论（将commentId添加到数组中）
    LIKE_QUESTION_COMMENT(state,commentId) {
        state.QuestionCommentLikeIds.push(commentId)
    },
    //取消点赞面试题评论（从数组中移除指定questionId元素）
    CANCEL_LIKE_QUESTION_COMMENT(state,commentId) {
        for (var i in state.QuestionCommentLikeIds) {
            if (state.QuestionCommentLikeIds[i] == commentId) {
                state.QuestionCommentLikeIds.splice(i, 1);
            }
        }
    },
    //点赞面试题回复（将replyId添加到数组中）
    LIKE_QUESTION_REPLY(state,replyId) {
        state.QuestionReplyLikeIds.push(replyId)
    },
    //取消点赞面试题回复（从数组中移除指定replyId元素）
    CANCEL_LIKE_QUESTION_REPLY(state,replyId) {
        for (var i in state.QuestionReplyLikeIds) {
            if (state.QuestionReplyLikeIds[i] == replyId) {
                state.QuestionReplyLikeIds.splice(i, 1);
            }
        }
    },
};

const actions = {
    //初始化点赞数据
    initLikeList(context,likeList){
        context.commit('INIT_LIKE_LIST',likeList)
    },
    //初始化收藏数据
    initCollectList(context,collectList){
        context.commit('INIT_COLLECT_LIST',collectList)
    },
    //初始化面试题评论点赞数据
    initQuestionCommentLikeList(context,questionCommentLikeList){
        context.commit('INIT_QUESTION_COMMENT_LIKE_LIST',questionCommentLikeList)
    },
    //初始化面试题回复点赞数据
    initQuestionReplyLikeList(context,questionReplyLikeList){
        context.commit('INIT_QUESTION_REPLY_LIKE_LIST',questionReplyLikeList)
    },
    //点赞
    like(context, questionId) {
        context.commit('LIKE', questionId)
    },
    //取消点赞
    cancelLike(context, questionId) {
        context.commit('CANCEL_LIKE', questionId)
    },
    //收藏
    collect(context, questionId) {
        context.commit('COLLECT', questionId)
    },
    //取消收藏
    cancelCollect(context, questionId) {
        context.commit('CANCEL_COLLECT', questionId)
    },
    //点赞面试题评论
    likeQuestionComment(context, commentId) {
        context.commit('LIKE_QUESTION_COMMENT', commentId)
    },
    //取消点赞面试题评论
    cancelLikeQuestionComment(context, commentId) {
        context.commit('CANCEL_LIKE_QUESTION_COMMENT', commentId)
    },
    //点赞面试题回复
    likeQuestionReply(context, replyId) {
        context.commit('LIKE_QUESTION_REPLY', replyId)
    },
    //取消点赞面试题回复
    cancelLikeQuestionReply(context, replyId) {
        context.commit('CANCEL_LIKE_QUESTION_REPLY', replyId)
    },
};

export default {
    state,
    mutations,
    actions
};