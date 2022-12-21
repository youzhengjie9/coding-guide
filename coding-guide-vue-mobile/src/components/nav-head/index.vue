<template>
  <div class="box">
    <van-row>
      <van-col span="11">
        <span class="top-title"> coding-guide </span>
      </van-col>
      <van-col span="10">
        <van-search
          shape="round"
          @click="pushSearchPage"
          disabled
          placeholder="搜索面试题"
          background="cornflowerblue"
        />
      </van-col>
      <van-col span="3">
        <p></p>
        <i class="iconfont icon-xinzeng"
        style="font-size: 36px;color:rgb(244 56 15);"
        @click="toWriteQuestionPage"
        />
      </van-col>
    </van-row>
  </div>
</template>

<script>
export default {
  methods: {
    //跳转搜索页
    pushSearchPage() {
      this.$router.push({
        path: "/search",
      });
    },
    //跳转到写面试题页面
    toWriteQuestionPage(){
      this.$router.push({
        path: "/write/question",
      });
    }
  },
};
</script>

<style lang="less" scoped>
.box {
  background-color: cornflowerblue;
}

.top-title {
  font-size: 26px;
  line-height: 50px;
  margin-left: 10px;
  color: white;
}
</style>