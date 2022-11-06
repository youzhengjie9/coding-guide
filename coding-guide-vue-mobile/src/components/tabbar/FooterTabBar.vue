<template>
  
  <div>
     
      <van-tabbar
        v-model="active"
        active-color="#f00"
        inactive-color="#2F4056"
      >
        <van-tabbar-item
          :icon="tabItem.icon"
          v-for="tabItem in tabList"
          :key="tabItem.path"
          :to="tabItem.path"
          :name="tabItem.path"
          >{{ tabItem.title }}</van-tabbar-item
        >
      </van-tabbar>

  </div>


</template>

<script>
export default {
    name:'FooterTabBar',
    data(){
      return{
        active: "/"
      }
    },
    computed:{
      tabList(){
        return this.$store.state.TabBar.tabList;
      },
      isShow() {
        const { path } = this.$route;
        return this.tabList.some(item => item.path === path);
     }
    },
    watch: {
    // 监控路由属性的变化
    $route: {
      handler(to, from) {
        if (to && from) {
          // 点右边的按钮,页面从右边进, 点左边的按钮从左边进
          if (to.meta.index > from.meta.index) {
            this.$store.dispatch('setMove','slide-left')
          } else {
            this.$store.dispatch('setMove','slide-right')
          }
        }
        // 处理页面刷新后, 默认选中的图表不再当前路由
        this.active = to.path;
      },
      //   -初始化,就先执行一次
      immediate: true
    }
  }
}
</script>

<style lang="scss" scoped>

</style>