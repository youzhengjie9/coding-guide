<template>
  <div class="questionReplyBox">
    
    <!-- 回复展示列表 -->
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多的回复了..."
      @load="onLoad"
    >
      <!-- 一条回复（通过for循环遍历组成回复列表） -->
      <question-reply-item
        v-for="reply in replyList"
        :key="reply.id"
        :reply="reply"
        @clickReplyBtn="$emit('clickReplyBtn', reply)"
      />
    </van-list>
  </div>
</template>

<script>
import QuestionReplyItem from "@/components/question/reply/ReplyItem.vue";

export default {
  name: "QuestionCommentList",
  components: {
    QuestionReplyItem,
  },
  props: {
    //回复列表的数据
    replyList: {
      type: Array,
      default: () => []
    },
    //当前面试题id
    currentQuestionId: Number,
    //回复总记录数
    totalCount: {
      type: Number
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
.questionReplyBox {
  width: 92%;
  margin-bottom: 100px;
  margin-left: 15px;
}
</style>