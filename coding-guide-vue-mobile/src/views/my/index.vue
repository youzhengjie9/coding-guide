<template>
  <div class="my">
    <!-- 头部组件 -->
    <my-header></my-header>

    <!-- 标签滚动列表展示(标签页)组件 -->
    <my-tab-scoller></my-tab-scoller>
  </div>
</template>

<script>
import MyHeader from "@/components/my/Header.vue";
import MyTabScoller from "@/components/my/TabScoller.vue";

export default {
  name: "my",
  components: {
    MyHeader,
    MyTabScoller,
  },
  // 导航离开该组件自动调用
  beforeRouteLeave(to, from, next) {
    const { name: currentComponentName } = this.$options;
    if (to.name === "index" || to.name === "Message" || to.name === "About") {
      //如果跳转到名为UserCard的路由,则缓存当前组件（跳转前的组件）
      this.$store.dispatch("addCacheComponentName", currentComponentName);
    } else {
      // 如果该路由跳转是去其他组件，则移除当前组件缓存
      this.$store.dispatch("removeCacheComponentName", currentComponentName);
    }
    next();
  },
};
</script>

<style lang="less" scoped>
</style>