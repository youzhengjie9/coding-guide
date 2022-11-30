<template>
  <div class="questionCommentBox">
    <p
      style="color: #323233; font-size: 0.37333rem; line-height: 0.64rem"
      ref="commentList"
    >
      全部评论
    </p>

    <!-- <p v-for="count in 100" :key="count" class="questionComment">
      评论：{{ count }}
    </p> -->

    <!-- 一条评论（通过for循环组成评论列表） -->
    <question-comment-item 
    v-for="i in 20"
    :key="i"
    />


  </div>
</template>

<script>
import QuestionCommentItem from '@/components/question/comment/Item.vue'
export default {
  name: "QuestionCommentList",
  components:{
    QuestionCommentItem
  },
  props:{
  }
};
</script>

<style scoped>
.questionCommentBox {
  width: 92%;
  margin-bottom: 100px;
  margin-left: 15px;
}

/* .questionComment {
  width: 95%;
  margin-left: 15px;
  line-height: 0.64rem;
} */
</style>