<template>
    <div class="login_container">
      <img src="https://img.yzcdn.cn/vant/logo.png" class="img" />
      <van-cell-group class="login_form">
        <van-field
          v-model="username"
          :label-width="50"
          left-icon="manager"
          label="账号"
          clearable
          placeholder="请输入用户名"
        />
        <van-field
          v-model="password"
          left-icon="lock"
          :label-width="50"
          clearable
          type="password"
          label="密码"
          placeholder="请输入密码"
        />
      </van-cell-group>
      <p class="login_register">
        <router-link to="/forget_pwd/忘记密码" class="go_register"
          >忘记密码</router-link
        >
      </p>
      <p class="login-no">
        还没有账号,去
        <router-link to="/register/注册" class="go_register">注册</router-link>
      </p>
      <van-button
        class="login_btn"
        type="primary"
        :loading="showLoading"
        loading-type="spinner"
        block
        :loading-text="message"
        @click="handleLogin"
        >登录</van-button
      >
    </div>
  </template>
<script>
import { Toast } from 'vant';
  export default {
    name:'Login',
    data() {
      return {
        showLoading: false, // 是否加载loading
        message: "", // 按钮上的加载
        username: "", // 账号
        password: "" // 密码
      };
    },
    beforeDestroy() {
      this.showLoading = false;
      this.message = "";
    },
    methods: {
      handleLogin() {

        let username=this.username;
        let password=this.password;

        //校验帐号密码格式
        if(!username || username.length<3 || username.length>15){
          
          Toast.fail('帐号格式不正确,请重试');

        }else if(!password || password.length<5 || password.length>20){
      
          Toast.fail('密码格式不正确,请重试');
          
        }else{
          //帐号密码验证通过
          //调用后端登录接口
          
          let userObj={
            username:username,
            password:password
          }
          
          this.showLoading = true;
          this.message = "正在登录...";
          setTimeout(() => {
            this.$router.push("/");
            this.showLoading = false;
          }, 1500);
        }

      }
    }
  };
  </script>
  
  <style lang="scss">
  .login_container {
    position: relative;
    padding: 5% 20px;
    display: flex;
    flex-direction: column;
    .img {
      width: 130px;
      height: 130px;
      margin: 30px auto;
    }
    .login-no {
      .go_register {
        color: #1989fa;
        font-size: 14px;
        &:first-child {
          margin-right: 10px;
        }
      }
    }
    .login_register {
      text-align: right;
      color: #555;
      font-size: 12px;
      .go_register {
        color: #1989fa;
        font-size: 14px;
      }
    }
    .login_btn {
      margin-top: 50px;
    }
    .login_zhe {
      position: fixed;
      bottom: 0;
      left: 0;
      text-align: center;
      width: 100%;
      p:first-child {
        font-size: 14px;
      }
      p:nth-child(2) {
        color: #888;
      }
    }
  }
  </style>
  