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
      />
    </van-col>
    <van-col
      class="publishCommentBtn"
      span="4"
      :style="{ color: content.length ? '#4a8ecf' : '#666' }"
      @click="publishComment"
      >发布</van-col
    >
  </van-row>
</template>

<script>
export default {
  name: "WirteComment",
  data() {
    return {
      content: "",
    };
  },
  methods:{
    //点击“发布”按钮
    publishComment(){

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

.publishCommentBtn {
  font-size: 15px;
  text-align: center;
}

</style>