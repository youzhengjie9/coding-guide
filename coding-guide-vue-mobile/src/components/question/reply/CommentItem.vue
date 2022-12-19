<template>
  <van-cell class="comment-item">
    <!-- 用户头像 -->

    <img
      slot="icon"
      style="width: 1rem; height: 1rem; border-radius: 50%; margin-right: 10px"
      :src="comment.avatar"
      @click="toUserCardInfo(comment.userId)"
    />

    <!-- 用户昵称 -->
    <span
      style="color: #466b9d"
      slot="title"
      @click="toUserCardInfo(comment.userId)"
    >
      {{ comment.nickName }}
    </span>

    <div slot="label">
      <!-- 评论内容 -->
      <p style="color: #363636">
        {{ comment.content }}
      </p>

      <p>
        <!-- 评论时间 -->
        <span class="commentTime">
          {{ comment.commentTime | dateformat("YYYY-MM-DD HH:mm") }}
        </span>

        <!-- “回复”按钮 -->
        <button
          v-if="canReply"
          class="showReplyBtn"
          @click="$emit('clickReplyBtn')"
        >
          回复
          {{ comment.replyCount }}
        </button>
      </p>
    </div>
  </van-cell>
</template>

<script>
import { likeQuestionComment } from "@/api/question-comment";
import { Toast } from "vant";
export default {
  name: "QuestionReplyCommentItem",
  props: {
    //评论数据
    comment: {
      type: Object,
      required: true,
    },
    //这条评论是否显示“回复”按钮，默认是显示
    canReply: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {};
  },
  methods: {
    //进入用户资料卡页面
    toUserCardInfo(userId) {
      this.$router.push({
        path: "/user/card",
        query: {
          id: userId,
        },
      });
    },
  },
};
</script>

<style scoped>
.comment-item {
  position: inherit; /* 解决vant组件导致评论区样式阻挡头部 */
}
.showReplyBtn {
  border-radius: 0.5rem;
  background-color: white; /* 背景颜色 */
  color: rgb(114, 50, 221); /* 字体 */
  border: 1px solid rgb(114, 50, 221); /* 边框 */
  display: inline-block;
  min-width: 1.33333rem;
  height: 0.58667rem;
  font-size: 0.26667rem;
  line-height: 0.53333rem;
  vertical-align: middle;
}

.commentTime {
  margin-right: 3px;
}

.commentLikeContainer {
  /* width: 55px; */
  align-items: center;
  justify-content: space-between;
  font-size: 13px;
}
</style>