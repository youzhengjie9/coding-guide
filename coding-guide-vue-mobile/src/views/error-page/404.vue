<template>
    <div class="nopage">
      <div class="nopage-404" />
      <div class="nppage-btn">
        <van-button type="primary" @click="goHome">返回上一个页面</van-button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Page404',
    methods: {
      goHome() {
        if (this.$route.query.noGoBack) {
        this.$router.push({ path: '/' })
        } else {
        this.$router.go(-1)
        }
      }
    }
  };
  </script>
  
  <style lang="scss" scoped>
  .nopage {
    display: flex;
    margin-top: 20%;
    flex-direction: column;
    align-items: center;
    .nopage-404 {
      width: 100%;
      height: 240px;
      background-image: url("./../../assets/404_images/404.gif");
      background-repeat: no-repeat;
      background-size: 100% 100%;
    }
    .nppage-btn {
      margin-top: 20px;
    }
  }
  </style>
  