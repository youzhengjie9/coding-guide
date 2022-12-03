<template>
  <van-cell class="replyItem">
    <!-- 用户头像 -->
    <van-image
      slot="icon"
      round
      width="30"
      height="30"
      style="margin-right: 10px"
      class="avatar"
      :src="reply.avatar"
    />

    <!-- 用户昵称 -->
    <span style="color: #466b9d" slot="title">{{ reply.nickName }}</span>

    <div slot="label">
      <!-- 回复内容 -->
      <p style="color: #363636">
        {{ reply.content }}
      </p>

      <p>
        <!-- 回复时间 -->
        <span class="replyTime"> {{ reply.replyTime }} </span>

        <!-- “回复”按钮 -->
        <button
          class="showReplyBtn"
          @click="$emit('clickReplyBtn')"
        >
          回复
        </button>
      </p>
    </div>

    <!-- 回复点赞展示区域 -->
    <div slot="right-icon" class="replyLikeContainer">
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
        @click="likeReply"
      >
      </i>

      <span> {{reply.likeCount}} </span>
    </div>
  </van-cell>
</template>

<script>
export default {
  name: "QuestionReplyItem",
  props: {
    //单个回复数据（外层通过for循环这个item组件形成一个列表）
    reply: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {};
  },
  methods: {
    //点赞回复
    likeReply(replyId) {},
  },
};
</script>

<style scoped>
.replyItem {
  position: inherit; /* 解决vant组件导致回复区样式阻挡头部 */
}
.avatar {
  position: inherit; /* 解决回复区样式阻挡头部 */
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

.replyTime {
  margin-right: 3px;
}

.replyLikeContainer {
  /* width: 55px; */
  align-items: center;
  justify-content: space-between;
  font-size: 13px;
}
</style>