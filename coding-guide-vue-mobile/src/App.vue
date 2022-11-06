<template>
  <div id="app">

      <router-view/>
 
  </div>
</template>

<script>
  export default {
    name: "App"
  }
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
