<template>
  <div class="content">

    <v-md-preview :text="question.content"> </v-md-preview>

    <!-- 回到顶部组件 -->
    <back-to-top></back-to-top>
  </div>
</template>

<script>
import BackToTop from "@/components/common/BackToTop.vue";
export default {
  name:'QuestionDetailContent',
  props:{
    question: Object
  },
  components:{
    BackToTop
  }
};
</script>

<style lang="scss" scope>
.content {
  //禁止左右滚动条，这个不能放到其他位置，否则会导致vant列表不断触发onload
  // overflow-x:hidden;
}
.github-markdown-body{
  padding: 0.22667rem 0.45333rem 0.85333rem;
}
</style>