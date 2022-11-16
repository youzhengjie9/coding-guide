<template>
  
  <div class="my">

    <!-- 头部组件 -->
    <my-header></my-header>

    <!-- 标签滚动列表展示(标签页)组件 -->
    <my-tab-scoller></my-tab-scoller>


  </div>


</template>

<script>
import MyHeader from '@/components/my/Header.vue';
import MyTabScoller from '@/components/my/TabScoller.vue'

export default {
    name:'my',
    components:{
      MyHeader,
      MyTabScoller
    }
}
</script>

<style lang="less" scoped>



</style>