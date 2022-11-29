<template>
  
  <div class="questionComment">

    <p ref="commentList">
      全部评论
    </p>

    <p v-for="count in 100" :key="count">
      评论：{{count}}
    </p>
    
  </div>

</template>

<script>

export default {
    name:'QuestionCommentList'
}
</script>

<style scoped>

.questionComment{
  width: 95%;
  margin-bottom: 100px;
  margin-left: 15px;
}


</style>