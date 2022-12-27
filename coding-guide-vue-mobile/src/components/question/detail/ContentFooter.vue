<template>
  <div class="contentFooter">
    <!-- 标签栏 -->
    <div class="questionTags">
      <!-- 是否推荐 -->
      <div
        v-if="question.recommend == 1"
        :key="'recommend' + question.recommend"
        class="recommendItem"
      >
        推荐
      </div>

      <!-- 标签列表 -->
      <div
      v-show="tagShow(tagList)"
      v-for="tagName in tagList" 
      :key="tagName" 
      class="ordinaryTagItem">
        {{ tagName }}
      </div>
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
  props: {
    question: Object,
  },
  computed: {
    tagList() {
      //判断是否undefined，如果不判断会出现Cannot read properties of undefined (reading 'split')"
      if (typeof this.question.tags != "undefined") {
        return this.question.tags.split(",");
      }
    },
  },
  methods:{
    tagShow(tagList){
      //如果tagList第一个元素是空字符串，说明没有任何标签，所以返回false不展示
      if(tagList[0] == ''){
        return false;
      }
      return true;
    },
  }
};
</script>

<style scoped>
.contentFooter {
  width: 100%;
  height: 1.6rem;
}
.questionTags {
  width: 100%;
  height: 30px;
}

/* 普通蓝色标签样式 */
.ordinaryTagItem {
  width: auto;
  height: 20px;
  float: left;
  border: 1px solid;
  margin-left: 15px;
  margin-bottom: 10px;
  color: #1989fa;
  background-color: #fff;
  padding: 0.05333rem 0.16rem;
  display: inline-flex;
  -webkit-box-align: center;
  align-items: center;
  font-size: 0.32rem;
  line-height: 0.42667rem;
  border-radius: 0.05333rem;
  pointer-events: none;
}

.recommendItem {
  width: auto;
  height: 20px;
  float: left;
  border: 1px solid;
  margin-left: 15px;
  margin-bottom: 10px;
  color: #fff;
  background-color: #07c160;
  padding: 0.05333rem 0.16rem;
  display: inline-flex;
  -webkit-box-align: center;
  align-items: center;
  font-size: 0.32rem;
  line-height: 0.42667rem;
  border-radius: 0.05333rem;
  pointer-events: none;
}

.instruct {
  width: 100%;
  height: 30px;
  float: left;
}
</style>