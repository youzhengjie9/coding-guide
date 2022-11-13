<template>
  
   <div class="box">

    <!-- 首页的头部 -->
    <nav-head></nav-head>

    <!-- 首页轮播图 -->
    <swipe></swipe>

    <!-- 首页滚动内容部分 -->
    <nav-scoller></nav-scoller>

   </div>
  

</template>

<script>

import NavHead from '../../components/nav-head/index.vue'
import Swipe from '@/components/common/Swipe.vue';
import NavScoller from '../../components/nav-scoller/index.vue'

export default {
  components:{
    NavScoller,
    NavHead,
    Swipe
  }
}
</script>

<style lang="less" scoped>


</style>
