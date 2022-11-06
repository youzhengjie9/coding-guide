<template>
  
  <div class="container">
    <van-notice-bar
    left-icon="volume-o"
    text="coding-guide是一个集面试刷题、博客、在线考试为一体的网站,采用当今十分流行的前后端分离架构开发的项目,拥有一整套完整的前台(移动端)和后台管理系统(PC端)"/>
    <van-row class="about-box">
      <div class="about-main">
        <h2>关于coding-guide</h2>
        <div>
          <ul>
            <li>coding-guide是一个基于前后端分离架构开发的项目</li>
            <li>前端采用的技术栈:Vue2、Vue-Router、Vuex、Vue-Cli5、Axios、Vant(移动端ui组件)、Echarts</li>
            <li>后端采用的技术栈:SpringBoot2、SpringSecurity、Mybatis-Plus、JWT、Canal、minio、Druid、fastjson2、hutool、Swagger3、knife4j、lombok</li>
            <li>数据库:MySQL8.0、Redis6、ElasticSearch7.8</li>
            <li>测试工具:Jmeter、Postman</li>
            <li>运维技术:Docker、Kubernetes(后期才上)、Jenkins(看情况)</li>
            <li>开发环境:JDK8、IDEA2021.3、vscode</li>
        </ul>
        </div>
      </div>
    </van-row>
  </div>

</template>

<script>
export default {
    name:'About'
}
</script>

<style lang="scss" scoped>
.container {
  padding-top: 58px;
  .about-box {
    padding: 14px 14px;
    background: #fff;
    &:first-child {
      border-bottom: 1px dashed #e5e5e5;
    }
    .about-main {
      h2 {
        margin: 0;
        padding: 12px 0;
        text-align: center;
        &:first-child {
          padding-top: 0;
        }
      }
      ul {
        padding: 0 14px;
        li {
          list-style-type: disc;
          padding: 4px 0;
          font-size: 14px;
        }
      }
    }
  }
}
</style>