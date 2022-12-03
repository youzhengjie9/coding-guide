<template>
  <van-row class="writeComment" type="flex" align="center">
    <van-col span="20">
      <van-field
        rows="2"
        v-model.trim="content"
        autosize
        type="textarea"
        maxlength="50"
        show-word-limit
        placeholder="善语结善缘,恶语伤人心"
      />
    </van-col>
    <van-col
      class="sendCommentBtn"
      span="4"
      :style="{ color: content.length ? '#4a8ecf' : '#666' }"
      @click="sendComment"
      >发送</van-col
    >
  </van-row>
</template>

<script>
export default {
  name: "WirteComment",
  data() {
    return {
      //评论框中输入的内容
      content: "",
    };
  },
  props:{
    //在评论列表中点击“写评论”按钮时,记录我们发送的评论所属的面试题id
    writeCommentQuestionId:{
      type: Number,
      required: true,
    }
  },
  methods:{
    //点击“发送”按钮（发送评论）
    sendComment(){

      this.$toast.loading({
        duration: 0,
        forbidClick: true,
        message: '发送中...'
      })

      try {
        //获取评论框中输入的内容
        let content = this.content
        //获取我们发送的评论所属的面试题id（也就是说获取这条评论属于哪个面试题）
        let writeCommentQuestionId=this.writeCommentQuestionId
        //校验逻辑：如果评论内容为空，则评论失败
        if(!content){
          this.$toast.fail('评论内容不允许为空。发送失败')
          return;
        }

        //发送评论
        let json={
          questionId: writeCommentQuestionId,
          commentContent: content
        }

        //调用发送评论api，将请求发给后端
        
        //后端返回一个新生成的评论对象
        let newCommentObject=
        {
          id: 2300000000000002,
          userId: 5700000000000006,
          nickName: "昵称5700000000000006-测试评论",
          content: content,
          commentTime: "2022-12-3 16:30",
          replyCount: 0,
          likeCount:520,
          avatar:
            "https://pic4.zhimg.com/80/v2-d43c201ae3f059caac7371785bc2b23f_720w.webp",
        }

        //发送评论成功之后,使用emit将新评论对象传给父组件，由父组件将新评论追加到原来的评论列表中
        this.$emit('sendCommentSuccess', newCommentObject)

        this.$toast.success('发送成功')

        //将评论框的内容清空
        this.content=''

      } catch (error) {
        this.$toast.fail('系统异常，发送失败')
      }

    }
  }
};
</script>

<style scoped>
.writeComment {
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