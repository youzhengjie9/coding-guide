<template>
  <div class="page-container">
    <!-- 评论回复 -->
    <van-nav-bar
      class="nav-bar"
      :title="`${currentReplyComment.replyCount}条回复`"
    >
      <van-icon slot="left" name="cross" @click="$emit('click-close')" />
    </van-nav-bar>

    <!-- 当前评论 -->
    <question-reply-comment-item
      :comment="currentReplyComment"
      :canReply="false"
      @click="toUserCardInfo(reply.repliedUserId)"
    />

    <van-cell title="全部回复" :border="false" />

    <!-- 当前评论的回复列表 -->
    <question-reply-list
      :replyList="replyList"
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
        @click="clickWriteReplyBtn"
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
        :repliedId="Number(repliedId)"
        :commentId="Number(commentId)"
        @sendReplySuccess="sendReplySuccess"
      />
    </van-popup>
  </div>
</template>

<script>
import QuestionReplyCommentItem from "@/components/question/reply/CommentItem.vue";
import QuestionReplyList from "@/components/question/reply/ReplyList.vue";
import WriteReply from "@/components/question/reply/WriteReply.vue";

import { selectListByCommentIdAndLimit } from "@/api/question-reply";

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
    QuestionReplyCommentItem,
    WriteReply,
  },
  data() {
    return {
      showWriteReplyPopup: false, //控制“写回复”弹出层
      repliedObject: null, //被回复的reply对象
      replyList: [], //回复列表
      replyTotalCount: 0, //该评论的一级回复总数
      firstLevelQuestionReplyListCount: 0, //该评论的一级回复已经查询出来的数量
      replyPage: 1, //回复分页的当前页数。默认是第一页
      replySize: 3, //回复分页的每页大小。一次3条
      repliedId: 0, //被回复的那条回复的id主键，用于记录这条回复到底回复了哪条回复（如果为0则说明回复评论,那么这个属性就没有任何作用,反之说明回复别人的回复,这个属性才有作用）
      commentId: 0, //这条回复所属的评论id
    };
  },
  methods: {
    //发送回复成功回调方法
    sendReplySuccess(newReplyObject) {
      //将新回复插入到回复列表数组（replyList）中，分为几种情况：
      //1：如果replyList为空（数组长度为0），则直接push到数组中即可（最简单的一种情况）
      if (this.replyList.length == 0) {
        this.replyList.push(newReplyObject);
      }
      //2：如果replyList不为空：
      else {
        //标记是否插入成功
        let insertSuccess = false;

        //2.1: 如果repliedId为0,则直接将回复插入replyList最后面
        if(this.repliedId == 0){
          this.replyList.push(newReplyObject);
          insertSuccess = true;
        }
        //2.2：如果repliedId不等于0,则在replyList中找到被回复的那条回复的id主键（repliedId）
        //,在其后面添加我们的新回复（newReplyObject），然后把insertSuccess设置为true
        else{
          for(let i = 0 ; i< this.replyList.length ; i++){
            if(this.replyList[i].id === this.repliedId){
              this.replyList.splice(i+1, 0, newReplyObject);
              insertSuccess = true;
              break;
            }
          }

        }
        //2.3：不管repliedId是否为0,如果insertSuccess为false,则直接将回复插入replyList最后面
        if(!insertSuccess){
          this.replyList.push(newReplyObject);
        }
        // 该条评论的回复总数 +1 .使用emit通过父组件修改我们点击“回复”按钮的那条评论的回复数
        this.$emit("changeCurrentReplyCommentReplyCount", 1);

        // 关闭 “回复/写回复” popup弹出层
        this.showWriteReplyPopup = false;
      }
    },
    //点击回复列表的“写回复”按钮会打开发布回复的popup弹出层
    clickWriteReplyBtn() {
      //保存在评论列表中被点击“回复”的reply对象（注意：是评论列表中的“回复”按钮）
      this.repliedObject = this.currentReplyComment;
      //由于是回复评论，所以这里设置为0
      this.repliedId = 0;
      //当前回复的评论id
      this.commentId = this.currentReplyComment.id;
      //打开“写回复”popup弹出层
      this.showWriteReplyPopup = true;
    },
    //点击回复列表中的“回复”按钮
    clickReplyBtn(reply) {
      //保存在回复列表中被点击“回复”的reply对象（注意：是回复列表中的“回复”按钮）
      this.repliedObject = reply;
      //由于是回复别人的回复
      this.repliedId = this.repliedObject.id;
      //当前回复的评论id
      this.commentId = this.currentReplyComment.id;
      //打开“写回复”popup弹出层
      this.showWriteReplyPopup = true;
    },
    //加载当前评论的回复列表
    async loadCurrentCommentReplyList() {
      //封装响应结果
      let responseResult = {
        lodingStatus: true,
        finishStatus: false,
      };
      let commentid = this.currentReplyComment.id;

      //使用async/await将axios变为同步操作
      let axiosResult = await selectListByCommentIdAndLimit(
        commentid,
        this.replyPage,
        this.replySize
      ).then((res) => {
        if (res.data.code === 200) {
          //页数+1
          this.replyPage++;
          //更新列表
          this.replyList = this.replyList.concat(
            res.data.data.questionReplyVOList
          );
          //更新一级回复总记录数
          this.replyTotalCount =
            res.data.data.firstLevelQuestionReplyTotalCount;
          //更新已经查询出来的一级回复记录数之和
          this.firstLevelQuestionReplyListCount =
            this.firstLevelQuestionReplyListCount +
            res.data.data.firstLevelQuestionReplyListCount;
          // 加载状态结束
          responseResult.lodingStatus = false;
          // 数据全部加载完成，说明已经没有记录可以刷新了，就显示到底了
          if (this.firstLevelQuestionReplyListCount >= this.replyTotalCount) {
            responseResult.finishStatus = true;
          }
        }
      });
      //-----由于selectListByCommentIdAndLimit方法使用到了async/await
      //-----所以只有这个方法的then操作全部执行完成才会执行到当前这步（相当于同步操作）
      //此时走到这步，说明selectListByCommentIdAndLimit方法全部执行完了，我们直接返回结果即可。
      //-----注意：返回值一定要用Promise.resolve(xxx)包装
      //只有使用Promise.resolve包装,后面才能获取yyy.then(res=>{ console.log(res) })获取
      return Promise.resolve(responseResult);
    },
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
