<template>
  <div class="questionContent">
    <!-- Markdown内容 -->
    <v-md-preview :text="question.content" />

    <!-- Markdown内容结束分隔线 -->
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      内容到底了
    </van-divider>

    <!-- 内容底部组件 -->
    <question-detail-content-footer :question="question" />

    <!-- 分隔栏  -->
    <p
    v-if="question.allowComment == 1"
      style="
        color: #323233;
        font-size: 0.37333rem;
        line-height: 0.64rem;
        margin-left: 15px;
      "
      ref="commentList"
    >
      全部评论
    </p>

    <!-- 全部评论展示列表。 -->
    <question-comment-list
      v-if="question.allowComment == 1"
      ref="commentListParent"
      @clickReplyBtn="clickReplyBtn"
      :commentList="commentList"
      @loadCurrentQuestionCommentList="loadCurrentQuestionCommentList"
    />

    <!-- 当点击评论的“回复”按钮，展示所点击的那条评论对应的所有回复数据 -->
    <!-- 这个van-popup不去设置关闭弹出层按钮，而是让question-comment-reply组件中自己去实现 -->
    <van-popup
      v-if="question.allowComment == 1"
      v-model="showReplyListPopup"
      position="bottom"
      get-container="body"
      duration="0.3"
      :style="{ height: '90%' }"
    >
      <!-- 展示点击“回复”按钮的那条评论对应的所有回复数据  -->
      <question-reply
        v-if="showReplyListPopup == true"
        :currentReplyComment="currentReplyComment"
        @click-close="showReplyListPopup = false"
        @changeCurrentReplyCommentReplyCount="changeCurrentReplyCommentReplyCount"
      />
      
    </van-popup>

    <!-- 回到顶部组件 -->
    <back-to-top></back-to-top>
  </div>
</template>

<script>
import BackToTop from "@/components/common/BackToTop.vue";
import QuestionDetailContentFooter from "@/components/question/detail/ContentFooter.vue";
import QuestionCommentList from "@/components/question/comment/CommentList.vue";
import QuestionReply from "@/components/question/reply/index.vue";
import WriteComment from "@/components/question/comment/WriteComment.vue";

export default {
  name: "QuestionDetailContent",
  props: {
    //面试题对象
    question: Object,
    //该面试题的分页评论列表数据
    commentList: {
      type: Array,
      default: () => []
    },
  },
  components: {
    BackToTop,
    QuestionDetailContentFooter,
    QuestionCommentList,
    QuestionReply,
    WriteComment,
  },
  data() {
    return {
      showReplyListPopup: false, //控制打开和关闭popup弹出层（点击评论的“回复”按钮则设置为true，点击关闭则false）
      showWriteReplyPopup: false, //控制打开和关闭“写回复”的popup弹出层
      //当前回复的评论数据
      currentReplyComment: {},
    };
  },
  methods: {
    loadCurrentQuestionCommentList(callback){
      this.$emit('loadCurrentQuestionCommentList',code =>{
        callback(code) //将回调结果传给下一个子组件
      })
    },
    // 点击评论列表的“回复按钮”时打开回复列表的popup弹出层。
    //（currentReplyComment是我们点击回复的那条评论）
    clickReplyBtn(currentReplyComment) {
      //打开回复列表的popup弹出层
      this.showReplyListPopup = true;
      //更新当前回复的评论数据
      this.currentReplyComment = currentReplyComment;
    },
    //修改我们点击“回复”按钮的那条评论的回复数
    changeCurrentReplyCommentReplyCount(count){
      this.currentReplyComment.replyCount+=count;
    },
  },
};
</script>

<!-- 这里不加scoped，.github-markdown-body样式就不会生效 -->
<!-- 由于没有加scoped，那么下面的classname一定要全局唯一 -->
<style lang="scss">
.questionContent {
  //禁止左右滚动条，这个不能放到其他位置，否则会导致vant列表不断触发onload
  // overflow-x:hidden;
}
.github-markdown-body {
  padding: 0.22667rem 0.45333rem 0.05333rem;
}

/**
 解决Markdown编译器的内容覆盖了顶部和底部的固定样式（top:0和bottom:0）
*/

.github-markdown-body div[class*="v-md-pre-wrapper-"] {
  position: inherit;
}

.github-markdown-body div[class*="v-md-pre-wrapper-"] pre,
.github-markdown-body
  div[class*="v-md-pre-wrapper-"]
  pre[class*="v-md-prism-"] {
  position: inherit;
}
</style>