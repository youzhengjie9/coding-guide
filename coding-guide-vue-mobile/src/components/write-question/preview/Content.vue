<template>
  <div class="writeQuestionPreviewContent">
    <!-- Markdown内容 -->
    <v-md-preview :text="questionContent" />

    <!-- Markdown内容结束分隔线 -->
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      内容到底了
    </van-divider>

    <!-- 回到顶部组件 -->
    <back-to-top></back-to-top>
  </div>
</template>

<script>
import BackToTop from "@/components/common/BackToTop.vue";
export default {
  name: "WriteQuestionPreviewContent",
  props: {
    //面试题内容
    questionContent: {
      type: String,
      default: ''
    },
  },
  components: {
    BackToTop,
  }
};
</script>

<!-- 这里不加scoped，.github-markdown-body样式就不会生效 -->
<!-- 由于没有加scoped，那么下面的classname一定要全局唯一 -->
<style lang="scss">
.writeQuestionPreviewContent {
  //禁止左右滚动条，这个不能放到其他位置，否则会导致vant列表不断触发onload
  // overflow-x:hidden;
}
.github-markdown-body {
  padding: 0.22667rem 0.45333rem 0.05333rem;
}

/**
 解决Markdown编译器的内容覆盖了顶部和底部的固定样式（top:0和bottom:0）
*/

.github-markdown-body div[class*="v-md-pre-wrapper-"] {
  position: inherit;
}

.github-markdown-body div[class*="v-md-pre-wrapper-"] pre,
.github-markdown-body
  div[class*="v-md-pre-wrapper-"]
  pre[class*="v-md-prism-"] {
  position: inherit;
}
</style>