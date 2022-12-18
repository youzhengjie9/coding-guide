<template>
  <div id="app">
    <!-- 
      Vue组件缓存(keep-alive)。当组件被缓存之后，再次进入这个组件时其生命周期不再执行。
      缓存组件名存储在$store.state.CacheComponents.cacheComponentNameList
    -->

    <keep-alive :include="$store.state.CacheComponents.cacheComponentNameList">
      <router-view />
    </keep-alive>

  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style lang="scss" scoped>
/* 自定义nprogress进度条颜色样式*/
#nprogress .bar {
  background: blue !important;
}
html,
body,
#app {
  width: 100%;
  height: 100%;
}

#app {
  display: flex;
  flex-direction: column;
}
</style>
