<template>
  <div class="page-container">
    <!-- 评论回复 -->
    <van-nav-bar class="nav-bar" :title="`${replyCount}条回复`">
      <van-icon slot="left" name="cross" @click="$emit('click-close')" />
    </van-nav-bar>

    <!-- 当前评论 -->
    <question-comment-item :comment="currentReplyComment" :canReply="false" />

    <van-cell title="全部回复" :border="false" />

    <!-- 当前评论的回复列表 -->
    <question-reply-list
      :replyList="replyList"
      :total-count.sync="replyCount"
      @clickReplyBtn="clickReplyBtn"
    />

    <!-- 点击“写回复”按钮会打开发布回复的popup弹出层 -->
    <div class="footer">
      <van-button
        class="write-btn"
        type="primary"
        round
        plain
        size="small"
        @click="clickOpenReplyComment"
        >写回复</van-button
      >
    </div>

    <!-- 写回复popup弹出层 -->
    <van-popup
      v-model="showWriteReplyPopup"
      position="bottom"
      get-container="body"
    >
      <!-- “写回复”组件 -->
      <write-reply />

    </van-popup>
    
  </div>
</template>

<script>
import QuestionCommentItem from "@/components/question/comment/CommentItem.vue";
import QuestionReplyList from "@/components/question/reply/ReplyList.vue";
import WriteReply from "@/components/question/reply/WriteReply.vue";


export default {
  name: "QuestionReply",
  props: {
    //我们点击“回复”按钮的那条评论。
    currentReplyComment: {
      type: Object,
      required: true,
    },
    //当前面试题id
    questionId: {
      type: Number,
      required: false,
    },
  },
  components: {
    QuestionReplyList,
    QuestionCommentItem,
    WriteReply
  },
  data() {
    return {
      replyCount: 100,
      showWriteReplyPopup: false, //控制“写回复”弹出层
      //回复列表
      replyList:[
        {
          id: "3310001",
          userId: "5700000000000001",
          nickName: "昵称3310001",
          content: "回复内容3310001",
          commentTime: "2022-10-22 12:30:52",
          replyCount: "20",
          avatar:
            "https://pic4.zhimg.com/80/v2-d43c201ae3f059caac7371785bc2b23f_720w.webp",
        },
        {
          id: "3310002",
          userId: "5700000000000002",
          nickName: "昵称3310002",
          content: "回复内容3310002",
          commentTime: "2022-10-23 11:30:52",
          replyCount: "60",
          avatar:
            "https://pic3.zhimg.com/80/v2-a47c3e88413625022fa19f8661d4bece_720w.webp",
        },
        {
          id: "3310003",
          userId: "5700000000000003",
          nickName: "昵称3310003",
          content: "回复内容3310003",
          commentTime: "2022-10-22 13:30:52",
          replyCount: "15",
          avatar:
            "https://pic4.zhimg.com/80/v2-d43c201ae3f059caac7371785bc2b23f_720w.webp",
        },

      ],

    };
  },
  methods: {
    //点击“写回复”按钮会打开发布评论和回复的popup弹出层
    clickOpenReplyComment() {
      
      this.showWriteReplyPopup = true;
    },
    //点击“回复按钮”。
    clickReplyBtn(){
      
      this.showWriteReplyPopup = true
    }
  },
};
</script>

<style scoped lang="less">
.page-container {
  padding: 46px 0 50px;
  font-size: 14px;
}

.nav-bar {
  position: fixed;
  left: 0;
  right: 0;
  top: 10%;
}

.footer {
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  box-sizing: border-box;
  display: flex;
  justify-content: space-around;
  align-items: center;
  height: 44px;
  border-top: 1px solid #d8d8d8;
  background-color: #fff;
  .write-btn {
    width: 60%;
  }
  .van-icon {
    font-size: 20px;
  }
  .comment-icon {
    bottom: -2px;
  }
  .share-icon {
    bottom: -2px;
  }
}
</style>
