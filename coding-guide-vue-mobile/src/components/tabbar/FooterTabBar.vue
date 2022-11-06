<template>
  
  <van-tabbar
       v-model="active"
       active-color="#ee0a24" 
       inactive-color="#000">
          <van-tabbar-item to="/" icon="home-o">首页</van-tabbar-item>
          <van-tabbar-item to="/message" icon="chat-o">消息</van-tabbar-item>
          <van-tabbar-item to="/about" icon="chat-o">关于</van-tabbar-item>
          <van-tabbar-item to="/my" icon="manager-o">我</van-tabbar-item>
   </van-tabbar>

</template>

<script>
export default {
    name:'FooterTabBar',
    data(){
      return{
        active: 0
      }
    }
}
</script>

<style scope>

</style>