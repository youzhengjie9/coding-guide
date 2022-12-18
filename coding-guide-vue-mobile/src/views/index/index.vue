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
  name:'index',
  components:{
    NavScoller,
    NavHead,
    Swipe
  },
  // 导航离开该组件自动调用
  beforeRouteLeave (to, from, next) {
    const { name: currentComponentName } = this.$options
    if (to.name === 'QuestionDetail' || to.name === 'Message' || to.name === 'About' || to.name === 'my') {
      //如果跳转到名为QuestionDetail的路由,则缓存当前组件（跳转前的组件）
      this.$store.dispatch('addCacheComponentName', currentComponentName)
    } else {
      // 如果该路由跳转是去其他组件，则移除当前组件缓存
      this.$store.dispatch('removeCacheComponentName', currentComponentName)
    }
    next()
  }
}
</script>

<style lang="less" scoped>


</style>
