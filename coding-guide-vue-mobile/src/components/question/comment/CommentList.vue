<template>
  <div class="questionCommentBox">
    
    <!-- 评论展示列表 -->
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多的评论了..."
      @load="onLoad"
    >
      <!-- 一条评论（通过for循环遍历组成评论列表） -->
      <question-comment-item
        v-for="comment in commentList"
        :key="comment.id"
        :comment="comment"
        @clickReplyBtn="$emit('clickReplyBtn', comment)"
      />
    </van-list>
  </div>
</template>

<script>
import QuestionCommentItem from "@/components/question/comment/CommentItem.vue";
export default {
  name: "QuestionCommentList",
  components: {
    QuestionCommentItem,
  },
  props: {
    //评论或者回复的列表数据
    commentList: {
      type: Array,
      default: () => []
    },
    //当前面试题id
    currentQuestionId: Number,

    totalCount: {
      type: Number
    },

    type: {
      type: String,
      default: 'comment',//comment是评论，reply是回复
    }
  },
  data() {
    return {
      loading: false,
      finished: false,
      page: 1, //默认是第一页
      size: 7, //一次7条
      total: 0, //总记录数
    };
  },
  methods: {
    //滚动分页
    onLoad() {},
  },
};
</script>

<style scoped>
.questionCommentBox {
  width: 92%;
  margin-bottom: 100px;
  margin-left: 15px;
}
</style>