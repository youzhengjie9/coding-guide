<template>
  <div class="contentFooter">
    <!-- 标签栏 -->
    <div class="questionTags">
        <!-- 是否推荐 -->
        <van-tag
          v-if="question.recommend == 1"
          type="success"
          class="tagItem"
          size="medium"
        >
          推荐
        </van-tag>

        <!-- 标签列表 -->
        <van-tag
          plain
          type="primary"
          v-for="tagName in tagList"
          :key="tagName"
          class="tagItem"
          size="medium"
        >
          {{ tagName }}
        </van-tag>
    </div>

    <div class="instruct">
        <span style="font-size: 15px; margin-left: 15px">
          发布于:
          {{ question.publishTime | dateformat("YYYY-MM-DD HH:mm:ss") }}
          ·著作权归作者所有          
        </span>
    </div>


  </div>
</template>

<script>
export default {
  name: "QuestionDetailContentFooter",
  props:{
    question: Object
  },
  computed:{
    tagList(){
      //判断是否undefined，如果不判断会出现Cannot read properties of undefined (reading 'split')"
      if(typeof(this.question.tags) != "undefined"){
        return this.question.tags.split(',');
      }
    }
  }
};
</script>

<style scoped>
.contentFooter {
  width: 100%;
  height: 1.6rem;
  /* background-color: aqua; */
  margin-bottom: 100px;
}
.questionTags {

}

.tagItem {
  margin-left: 15px;
  margin-bottom: 10px;
}


</style>