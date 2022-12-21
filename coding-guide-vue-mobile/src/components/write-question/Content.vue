<template>
  <div class="writeQuestionContentBox">
    <!-- 输入面试题标题 -->
    <van-cell-group>
      <van-field
        v-model="questionTitle"
        label="面试题标题"
        required
        autosize
        maxlength="100"
        show-word-limit
        colon
        label-width="5.6em"
        type="textarea"
        placeholder="请输入面试题标题(5-100个字)"
      />
    </van-cell-group>
    <!-- 输入面试题内容编辑器 -->
    <v-md-editor v-model="questionContent" height="550px" />
  </div>
</template>

<script>
export default {
  name: "WriteQuestionContent",
  data(){
    return{
      //面试题标题
      questionTitle: "",
      //Markdown编辑器输入的内容
      questionContent: '',
    }
  }
};
</script>

<style scoped>
</style>