<template>
  <div class="login_container">
    <img src="https://img.yzcdn.cn/vant/logo.png" class="img" />
    <!-- 表单 -->
    <van-form @submit="handleLogin">
      <van-cell-group class="login_form">
        <van-field
          v-model="userName"
          :label-width="50"
          left-icon="manager"
          label="账号"
          clearable
          placeholder="请输入用户名"
          :rules="[
            { validator: validateUserName, message: '帐号长度要在5-15位之间' },
          ]"
        />
        <van-field
          v-model="password"
          left-icon="lock"
          :label-width="50"
          clearable
          type="password"
          label="密码"
          placeholder="请输入密码"
          :rules="[
            { validator: validatePassword, message: '密码长度要在5-20位之间' },
          ]"
        />
      </van-cell-group>
      <!-- <p class="forgetPasswordBox">
        <router-link to="/forgetPassword" class="forgetPassword"
          >忘记密码</router-link
        >
      </p> -->
      <p class="forgetPasswordBox">
        <span class="forgetPassword" @click="forgetPassword">
          忘记密码
        </span>
      </p>
      <p class="registerBox">
        还没有账号,去
        <router-link to="/register" class="register">注册</router-link>
      </p>
      <van-button class="login_btn" type="primary" block round>登录</van-button>
    </van-form>
  </div>
</template>
<script>
import { Toast } from "vant";
import { login } from "@/api/login";
export default {
  name: "Login",
  data() {
    return {
      message: "", // 按钮上的加载
      userName: "", // 账号(用户名)
      password: "", // 密码
    };
  },
  methods: {
    //自定义校验用户名
    validateUserName(userName) {
      if (userName.length >= 5 && userName.length <= 15) {
        return true;
      }
      return false;
    },
    //自定义校验密码
    validatePassword(password) {
      if (password.length >= 5 && password.length <= 20) {
        return true;
      }
      return false;
    },
    //点击忘记密码
    forgetPassword(){
        Toast.fail({
              message: '忘记密码功能暂未实现',
              duration: 1500,
          });
      // this.$router.push({
      //   path:'/forgetPassword'
      // })
    },
    //登录
    handleLogin() {
      let userName = this.userName;
      let password = this.password;

      let loginObject = {
        userName: userName,
        password: password,
      };

      //调用userLogin的api方法
      login(loginObject)
        .then((res) => {
          let data = res.data;
          //前端拿到响应结果，如果下面的data为null则说明是帐号正确、密码错误。
          if (data == null) {
            Toast.fail({
              message: '用户名或者密码不正确',
              duration: 1500,
            });
          } else {
            //用户登录成功
            if (data.code === 600) {
              this.$store.dispatch("loginSuccess", data);
              //登录成功后跳转到首页
              this.$router.push({
                path: "/",
              });
            } else if (data.code === 601) {
              Toast.fail({
                message: data.msg,
                duration: 1500,
              });
            }

          }
        })
        .catch((err) => {
          Toast.fail({
            message: "系统出错,请重试",
            duration: 1500,
          });
        });
    },
  },
};
</script>

<style lang="scss" scoped>
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
  .registerBox {
    margin-left: 10px;
    .register {
      color: #1989fa;
      font-size: 14px;
      &:first-child {
        margin-right: 10px;
      }
    }
  }
  .forgetPasswordBox {
    text-align: right;
    color: #555;
    font-size: 12px;
    .forgetPassword {
      color: #1989fa;
      font-size: 14px;
    }
  }
  .login_btn {
    margin-top: 50px;
  }
}
</style>
