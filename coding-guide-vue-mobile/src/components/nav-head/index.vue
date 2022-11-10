<template>
    <div class="box">
        <van-row>
            <van-col span="12">
            <span class="top-title">
                coding-guide
            </span>
            </van-col>
            <van-col span="12">
            <van-search shape="round" @click="pushSearchPage" disabled placeholder="搜索面试题" background="cornflowerblue" />
            </van-col>
        </van-row>
    </div>
  
</template>

<script>
export default {
  methods:{
    //跳转搜索页
    pushSearchPage(){
      this.$router.push({
          path:'/search'
      })
    }
  }
}
</script>

<style lang="less" scoped>

.box{
    background-color:cornflowerblue;
}

.top-title{
  font-size: 26px;
  line-height: 50px;
  margin-left: 10px;
  color:white
}
</style>