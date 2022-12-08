<template>
  <div class="questionReplyBox">
    <!-- 回复展示列表 -->
    <van-list
      v-model="replyListloading"
      :finished="replyListFinished"
      finished-text="没有更多的回复了"
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
  name: "QuestionReplyList",
  components: {
    QuestionReplyItem,
  },
  props: {
    //回复列表的数据
    replyList: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      replyListloading: false, //控制显示评论加载状态
      replyListFinished: false, //评论列表分页数据是否展示到底了
    };
  },
  methods: {
    //滚动分页
    onLoad() {
      //调用父组件reply/index.vue的loadCurrentCommentReplyList方法
      let promiseResult = this.$parent.loadCurrentCommentReplyList();
      //---获取promise对象传过来的值---
      promiseResult.then((responseResult) => {
        if (responseResult.lodingStatus == false) {
          this.replyListloading = false;
        }
        if (responseResult.finishStatus == true) {
          this.replyListFinished = true;
        }
      });
    },
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