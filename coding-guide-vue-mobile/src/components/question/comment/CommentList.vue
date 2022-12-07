<template>
  <div class="questionCommentBox">
    
    <!-- 评论展示列表 -->
    <van-list
      v-model="commentListloading"
      :finished="commentListFinished"
      finished-text="没有更多的评论了..."
      @load="onLoad"
    >
      <!-- 一条评论（通过for循环遍历组成评论列表） -->
      <question-comment-item
        v-for="comment in commentList"
        :key="comment.id"
        :comment="comment"
        @clickReplyBtn="$emit('clickReplyBtn', comment)"
      />
    </van-list>
  </div>
</template>

<script>
import QuestionCommentItem from "@/components/question/comment/CommentItem.vue";
export default {
  name: "QuestionCommentList",
  components: {
    QuestionCommentItem,
  },
  props: {
    //该面试题的分页评论列表数据
    commentList: {
      type: Array,
      default: () => []
    }, 
  },
  data() {
    return {
      commentListloading: false, //控制显示评论加载状态
      commentListFinished: false, //评论列表分页数据是否展示到底了 
    };
  },
  methods: {
    //滚动分页
    onLoad() {

      this.$emit('loadCurrentQuestionCommentList',responseResult =>{
          if(responseResult.lodingStatus == false){
              this.commentListloading = false;
          }
          if(responseResult.finishStatus == true){
              this.commentListFinished = true;
          }
      })
      
    },
  },
};
</script>

<style scoped>
.questionCommentBox {
  width: 92%;
  margin-bottom: 100px;
  margin-left: 15px;
}
</style>