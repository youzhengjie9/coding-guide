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
export default {
  name: "WirteReply",
  data() {
    return {
      //回复框中输入的内容
      content: "",
    };
  },
  props:{
    //回复列表中被点击 “回复” 按钮的reply（回复）对象
    repliedObject: {
      type: Object,
      default: null
    },
    //在回复列表中点击的“写回复”按钮或者回复列表中的“回复”按钮时,记录我们发送的回复所属的面试题id
    writeReplyQuestionId:{
      type: Number,
      required: true,
    }
  },
  computed:{
    //输入框的placeholder
    inputPlaceholder () {
      let repliedObject = this.repliedObject;
      return repliedObject ? `回复 @${repliedObject.nickName} : ` : ''
    }
  },
  methods:{
    //点击“发送”按钮（发送回复）
    sendReply(){
      this.$toast.loading({
        duration: 0,
        forbidClick: true,
        message: '发送中...'
      })
      try {
        //获取回复框中输入的内容
        let content = this.content
        //获取我们发送的回复所属的面试题id（也就是说获取这条回复属于哪个面试题）
        let writeReplyQuestionId=this.writeReplyQuestionId
        //校验逻辑：如果回复内容为空，则回复失败
        if(!content){
          this.$toast.fail('回复内容不允许为空。发送失败')
          return;
        }

        //发送回复
        let json={
          questionId: writeReplyQuestionId,
          replyContent: content
        }

        //调用发送回复api，将请求发给后端

        //后端返回一个新生成的回复对象
        let newReplyObject={
          id: 3700000000000003,
          userId: 5700000000000003,
          nickName: "昵称5700000000000003-测试回复",
          content: content,
          replyTime: "2022-10-8 12:33:21",
          likeCount:0,
          avatar:
            "https://pic1.zhimg.com/80/v2-3330141ad8f6029c499016deae2f8eac_720w.webp",
        }

        //发送回复成功之后,使用emit将新评论对象传给父组件，由父组件将新回复追加到原来的回复列表中
        this.$emit('sendReplySuccess', newReplyObject)

        this.$toast.success('发送成功')

        //将回复框的内容清空
        this.content=''

      } catch (error) {
        this.$toast.fail('系统异常，发送失败')
      }
    }
  }
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