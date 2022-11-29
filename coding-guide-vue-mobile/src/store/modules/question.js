const state = {
    //当前用户所有点赞的面试题id(questionid)集合
    QuestionLikeIds:[],
    //当前用户所有收藏的面试题id(questionid)集合
    QuestionCollectIds:[]
};

const mutations = {
    //初始化点赞列表
    INIT_LIKE_LIST(state,likeList){
        state.QuestionLikeIds=likeList
    },
    //初始化收藏列表
    INIT_COLLECT_LIST(state,collectList){
        state.QuestionCollectIds=collectList
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
    }
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
    }
};

export default {
    state,
    mutations,
    actions
};