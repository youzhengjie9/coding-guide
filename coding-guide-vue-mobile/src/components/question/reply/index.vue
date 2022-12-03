<template>
  <div class="page-container">
    <!-- 评论回复 -->
    <van-nav-bar class="nav-bar" :title="`${currentReplyComment.replyCount}条回复`">
      <van-icon slot="left" name="cross" @click="$emit('click-close')" />
    </van-nav-bar>

    <!-- 当前评论 -->
    <question-comment-item :comment="currentReplyComment" :canReply="false" />

    <van-cell title="全部回复" :border="false" />

    <!-- 当前评论的回复列表 -->
    <question-reply-list
      :replyList="replyList"
      :total-count.sync="currentReplyComment.replyCount"
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
      <write-reply 
      :repliedObject="repliedObject"
      :writeReplyQuestionId="questionId"
      @sendReplySuccess="sendReplySuccess"
      />

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
    //当前评论或者回复所属面试题id
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
      showWriteReplyPopup: false, //控制“写回复”弹出层
      repliedObject: null, //被回复的reply对象
      //回复列表
      replyList:[
        {
          id: 3300000000000001,
          userId: 5700000000000001,
          nickName: "昵称5700000000000001",
          content: "回复内容3300000000000001",
          replyTime: "2022-10-22 12:30:52",
          likeCount:10,
          avatar:
            "https://pic4.zhimg.com/80/v2-d43c201ae3f059caac7371785bc2b23f_720w.webp",
        },
        {
          id: 3300000000000002,
          userId: 5700000000000002,
          nickName: "昵称5700000000000002",
          content: "回复内容3300000000000002",
          replyTime: "2022-10-25 15:30:52",
          likeCount:20,
          avatar:
            "https://pic3.zhimg.com/80/v2-a47c3e88413625022fa19f8661d4bece_720w.webp",
        },
        
        {
          id: 3300000000000003,
          userId: 5700000000000003,
          nickName: "昵称5700000000000003",
          content: "回复内容3300000000000003",
          replyTime: "2022-11-22 13:30:52",
          likeCount:30,
          avatar:
            "https://pic4.zhimg.com/80/v2-d43c201ae3f059caac7371785bc2b23f_720w.webp",
        },

      ],

    };
  },
  methods: {
    //发送回复成功的回调方法
    sendReplySuccess(newReplyObject){
      // 将新发送的回复放到回复列表的第一个
      this.replyList.unshift(newReplyObject)
      // 该条评论的回复总数 +1 .使用emit通过父组件修改我们点击“回复”按钮的那条评论的回复数
      this.$emit('changeCurrentReplyCommentReplyCount',1);
      // 关闭 “回复/写回复” popup弹出层
      this.showWriteReplyPopup = false
    },
    
    //点击“写回复”按钮会打开发布评论和回复的popup弹出层
    clickOpenReplyComment() {
      //保存在评论列表中被点击“回复”的reply对象（注意：是评论列表中的“回复”按钮）
      this.repliedObject=this.currentReplyComment;
      //打开“写回复”popup弹出层
      this.showWriteReplyPopup = true;
    },
    //点击“回复”按钮
    clickReplyBtn(reply){
      //保存在回复列表中被点击“回复”的reply对象（注意：是回复列表中的“回复”按钮）
      this.repliedObject=reply
      //打开“写回复”popup弹出层
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
