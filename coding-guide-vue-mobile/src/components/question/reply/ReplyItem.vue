<template>
  <van-cell class="replyItem">
    <!-- 用户头像。点击头像进入用户资料卡页面 -->

    <img
      slot="icon"
      style="width: 1rem; height: 1rem; border-radius: 50%;margin-right: 10px;"
      :src="reply.avatar"
      @click="toUserCardInfo(reply.userId)"
    />

    <!-- 用户昵称（点击昵称也可以进入用户资料卡页面）。有两种不同的展示方式： -->
    <!-- 第1种：如果该“回复”回复的是评论。（repliedUserId==null则是这种情况） -->
    <span slot="title" v-if="(reply.repliedUserId==null)">

      <!-- 回复的发送者 -->
      <span style="color: #466b9d" @click="toUserCardInfo(reply.userId)">
        {{ reply.nickName }}
      </span>

    </span>
    <!-- 第2种：如果该“回复”回复的是其他人的回复。（repliedUserId!=null则是这种情况） -->
    <span slot="title" v-else>

      <!-- 回复的发送者 -->
      <span style="color: #466b9d" @click="toUserCardInfo(reply.userId)">
        {{ reply.nickName }}
      </span>
      
      <!-- 用户昵称之前的分隔符 -->
      <span style="color:darkgrey;">
        &nbsp;回复&nbsp;
      </span> 

      <!-- 被回复者 -->
      <span style="color: #466b9d" @click="toUserCardInfo(reply.repliedUserId)">
        {{ reply.repliedNickName }}
      </span>

    </span>

    <div slot="label">
      <!-- 回复内容 -->
      <p style="color: #363636">
        {{ reply.content }}
      </p>

      <p>
        <!-- 回复时间 -->
        <span class="replyTime"> {{ reply.replyTime | dateformat("YYYY-MM-DD HH:mm") }} </span>

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
      <i
        class="iconfont icon-yidianzan"
        v-if="isLike(reply.id)"
        @click="likeQuestionReply(reply.id)"
        style="font-size: 20px; color: red"
      >
      </i>

      <span 
      style="color: red"
      v-if="isLike(reply.id)"
      >
        {{reply.likeCount}} 
      </span>

      <!-- 未被点赞 -->
      <i
        class="iconfont icon-yidianzan"
        v-if="!isLike(reply.id)"
        @click="likeQuestionReply(reply.id)"
        style="font-size: 20px"
      >
      </i>

      <span v-if="!isLike(reply.id)"> 
        {{reply.likeCount}} 
      </span>

    </div>

  </van-cell>
</template>

<script>
import{
  likeQuestionReply
} from '@/api/question-reply'
import { Toast } from 'vant';
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
    likeQuestionReply(replyId) {
      //如果Vuex中不包含该replyId，则说明要调用点赞接口
      if (
        !this.$store.state.Question.QuestionReplyLikeIds.includes(Number(replyId))
      ) {
        likeQuestionReply(replyId).then((res) => {
          this.$store.dispatch("likeQuestionReply", Number(replyId));
          //修改父组件前端对应的回复点赞数+1
          this.$parent.$parent.changeQuestionReplyLikeCount(replyId,1);
          Toast.success("点赞成功");
        });
      }
      //反之，说明要调用取消点赞接口(和点赞接口是同一个)
      else {
        likeQuestionReply(replyId).then((res) => {
          this.$store.dispatch("cancelLikeQuestionReply", Number(replyId));
          //修改父组件前端对应的回复点赞数-1
          this.$parent.$parent.changeQuestionReplyLikeCount(replyId,-1);
          Toast.success("取消点赞成功");
        });
      }
    },
    //判断面试题回复是否被点赞，只需要判断Vuex中是否包含该replyId，包含则说明点赞过
    isLike(replyId) {
      return this.$store.state.Question.QuestionReplyLikeIds.includes(
        Number(replyId)
      );
    },
    //进入用户资料卡页面
    toUserCardInfo(userId){
      
      this.$router.push({
        path:'/user/card',
        query: {
          id: userId,
        },
      })

    }
  },
};
</script>

<style scoped>
.replyItem {
  position: inherit; /* 解决vant组件导致回复区样式阻挡头部 */
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