<template>
  <van-cell class="comment-item">
    <!-- 用户头像 -->
    <van-image
      slot="icon"
      round
      width="30"
      height="30"
      style="margin-right: 10px"
      class="avatar"
      :src="comment.avatar"
    />

    <!-- 用户昵称 -->
    <span style="color: #466b9d" slot="title">{{ comment.nickName }}</span>

    <div slot="label">
      <!-- 评论内容 -->
      <p style="color: #363636">
        {{ comment.content }}
      </p>

      <p>
        <!-- 评论时间 -->
        <span class="commentTime"> {{ comment.commentTime }} </span>

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

    <!-- 评论点赞展示区域 -->
    <div slot="right-icon" class="commentLikeContainer">
      <!-- 已被点赞 -->
      <!-- <i
        class="iconfont icon-yidianzan"
        style="font-size: 20px; color: red"
      >
      </i>

      <span style="color: red">
        10000
      </span> -->

      <!-- 未被点赞 -->
      <i
        class="iconfont icon-yidianzan"
        style="font-size: 20px"
        @click="likeComment"
      >
      </i>
      
      <span> {{comment.likeCount}} </span>
    </div>
  </van-cell>
</template>

<script>
export default {
  name: "QuestionCommentItem",
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
    //点赞评论
    likeComment(commentId) {},
  },
};
</script>

<style scoped>
.comment-item {
  position: inherit; /* 解决vant组件导致评论区样式阻挡头部 */
}
.avatar {
  position: inherit; /* 解决评论区样式阻挡头部 */
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