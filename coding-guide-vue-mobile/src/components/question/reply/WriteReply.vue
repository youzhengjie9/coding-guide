<template>
  <van-row class="writeReply" type="flex" align="center">
    <van-col span="20">
      <van-field
        rows="2"
        v-model.trim="content"
        autosize
        type="textarea"
        maxlength="100"
        show-word-limit
        :placeholder="inputPlaceholder"
      />
    </van-col>
    <van-col
      class="sendCommentBtn"
      span="4"
      :style="{ color: content.length ? '#4a8ecf' : '#666' }"
      @click="sendReply"
      >发送</van-col
    >
  </van-row>
</template>

<script>
import { writeQuestionReply } from "@/api/question-reply";
import { Toast } from "vant";
export default {
  name: "WirteReply",
  data() {
    return {
      //回复框中输入的内容
      content: "",
    };
  },
  props: {
    //回复列表中被点击 “回复” 按钮的reply（回复）对象
    repliedObject: {
      type: Object,
      default: null,
    },
    //被回复的那条回复的id主键，用于记录这条回复到底回复了哪条回复（如果为0则说明回复评论,那么这个属性就没有任何作用,反之说明回复别人的回复,这个属性才有作用）
    repliedId: {
      type: Number,
      required: true,
    },
    //这条回复所属的评论id
    commentId: {
      type: Number,
      required: true,
    },
  },
  computed: {
    //输入框的placeholder
    inputPlaceholder() {
      let repliedObject = this.repliedObject;
      return repliedObject ? `回复 @${repliedObject.nickName} : ` : "";
    },
  },
  methods: {
    //点击“发送”按钮（发送回复）
    sendReply() {
      this.$toast.loading({
        duration: 0,
        forbidClick: true,
        message: "发送中...",
      });
        //获取回复框中输入的内容
        let content = this.content;
        //获取被回复的那条回复的id主键，用于记录这条回复到底回复了哪条回复（如果为0则说明回复评论,那么这个属性就没有任何作用,反之说明回复别人的回复,这个属性才有作用）
        let repliedId = this.repliedId;
        //获取这条回复所属的评论id
        let commentId = this.commentId;
        //校验逻辑：如果回复内容为空，则回复失败
        if (!content) {
          this.$toast.fail("回复内容不允许为空。发送失败");
          return;
        }

        //发送回复
        let questionReplyDTO = {
          repliedId: repliedId,
          commentId: commentId,
          content: content,
        };

        //调用发送回复api，将请求发给后端
        writeQuestionReply(questionReplyDTO).then((res) => {
          //判断回复成功还是失败
          //如果回复成功了
          if (res.data.code == 200) {
            //接收后端返回一个新生成的回复对象
            let newReplyObject = res.data.data;

            //发送回复成功之后,将新回复对象传给父组件，由父组件将新回复追加到原来的回复列表中
            this.$parent.$parent.sendReplySuccess(newReplyObject);

            Toast.success("回复成功");

            //将回复框的内容清空
            this.content = "";
          } else if (res.data.code == 500) {
            Toast.fail("回复失败");
          }
        }).catch((err) => {
          Toast.fail("系统异常,回复失败");
        });

    },
  },
};
</script>

<style scoped>
.writeReply {
  padding: 12px;
}

.van-cell {
  background-color: #f5f7f9;
}

.sendCommentBtn {
  font-size: 15px;
  text-align: center;
}
</style>