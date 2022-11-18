<template>
  <div class="box">
    <!-- header -->
    <van-nav-bar title="注册" left-text="返回" left-arrow @click-left="back" />
    <!-- 表单 -->
    <van-form @submit="handleRegister">
      <van-cell-group>
        <!-- 帐号 -->
        <van-field
          v-model="username"
          label="账号"
          clearable
          required
          placeholder="请输入用户名"
          :rules="[
            { validator: validateUserName, message: '帐号长度要在5-15位之间' },
          ]"
        />

        <!-- 手机号 -->
        <van-field
          v-model="phone"
          required
          label="手机号"
          placeholder="请输入手机号"
          :rules="[
            { required: true, message: '手机号码不能为空' },
            { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式错误' },
          ]"
        >
          <template #button>
            <van-button
              size="small"
              type="primary"
              @click="clickSendCode"
              :disabled="disable"
            >
              {{ buttonName }}
            </van-button>
          </template>
        </van-field>
        <!-- 短信验证码 -->
        <van-field
          v-model="code"
          center
          clearable
          required
          label="短信验证码"
          placeholder="请输入短信验证码"
          v-show="codeShow"
          :rules="[{ validator: validateCode, message: '验证码长度必须为6位' }]"
        >
        </van-field>
        <!-- 密码 -->
        <van-field
          v-model="password"
          required
          type="password"
          label="密码"
          placeholder="请输入密码"
          :rules="[
            { validator: validatePassword, message: '密码长度要在5-20位之间' },
          ]"
        />
        <van-field
          v-model="confirmPassword"
          required
          type="password"
          label="确认密码"
          placeholder="请再次输入密码"
          :rules="[
            {
              validator: validateConfirmPassword,
              message: '两次输入的密码需要一致',
            },
          ]"
        />
      </van-cell-group>
      <div style="margin: 16px">
        <van-button round block type="info">注册</van-button>
      </div>
    </van-form>
  </div>
</template>
 
 <script>
import { Toast } from "vant";
import { register, sendCode } from "@/api/register";
export default {
  data() {
    return {
      username: "", //用户名
      phone: "", //手机号
      code: "", //短信验证码
      password: "", //密码
      confirmPassword: "", //确认密码
      buttonName: "发送验证码",
      count: 60, //验证码倒计时
      disable: false, //控制发送验证码是否被禁用
      codeShow: false, //控制验证码输入框是否展示
    };
  },
  methods: {
    //自定义校验用户名
    validateUserName(username) {
      if (username.length >= 5 && username.length <= 15) {
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
    //自定义校验确认密码
    validateConfirmPassword(confirmPassword) {
      if (
        this.password.length >= 5 &&
        this.password.length <= 20 &&
        confirmPassword === this.password
      ) {
        return true;
      }
      return false;
    },
    //自定义校验验证码
    validateCode(code) {
      if (code.length == 6) {
        return true;
      }
      return false;
    },
    //返回上一个页面
    back() {
      this.$router.go(-1);
    },
    //点击注册（只有前端校验通过才能调用handleRegister方法，所以下面只需要给后端发注册请求即可）
    handleRegister() {
      let username = this.username;
      let phone = this.phone;
      let code = this.code;
      let password = this.password;
      let confirmPassword = this.confirmPassword;

      let registerObject = {
        username: username, //用户名
        phone: phone, //手机号
        code: code, //短信验证码
        password: password, //密码
        confirmPassword: confirmPassword, //确认密码
      };

      //注册逻辑
      //调用注册api
      register(registerObject)
        .then((res) => {
          let data = res.data;
          //说明注册成功
          if (data.code === 1100) {
            Toast.success({
              message: data.msg,
              duration: 1500,
            });
            //注册成功后跳转到登录页
            this.$router.replace({
              path: "/login",
            });
          } else {
            Toast.fail({
              message: data.msg,
              duration: 1500,
            });
          }
        })
        .catch((err) => {
          Toast.fail({
              message: "注册失败,请检查是否输入正确",
              duration: 1500,
            });
        });
    },
    //发送验证码
    sendCode(phone, timeout) {
      //调用发送验证码api
      sendCode(phone)
        .then((res) => {
          let data = res.data;
          //发送验证码成功
          if (data.code === 1102) {
            Toast.success({
              message: data.msg,
              duration: 1500,
            });
          } else {
            //发送验证码失败后摇重置按钮
            this.disable = false;
            this.buttonName = "获取验证码";
            //重置倒计时
            this.count = 60;
            clearInterval(timeout);
            //发送验证码失败
            Toast.fail({
              message: data.msg,
              duration: 1500,
            });
          }
        })
        .catch((err) => {
          //发送验证码失败后摇重置按钮
          this.disable = false;
          this.buttonName = "获取验证码";
          //重置倒计时
          this.count = 60;
          clearInterval(timeout);
          Toast.fail({
            message: "后端接口出错了",
            duration: 1500,
          });
        });
    },
    //点击发送短信验证码按钮回调
    clickSendCode() {
      //校验手机号长度是否为11位，如果为11位则发送验证码
      if (this.phone.length === 11) {
        //点击发送验证码显示验证码框
        if (this.codeShow == false) {
          this.codeShow = true;
        }
        var timeout = setInterval(() => {
          //恢复可以点击验证码
          if (this.count < 1) {
            this.disable = false;
            this.buttonName = "获取验证码";
            //重置倒计时
            this.count = 60;
            clearInterval(timeout);
          } else {
            this.disable = true;
            this.buttonName = this.count-- + "s后重发";
          }
        }, 1000);
        //点击发送验证码逻辑。（只有获取验证码按钮的disable为false才会执行到这一步），下面的setInterval会自动的修改按钮状态
        //调用发送验证码方法,并且把计时器对象（timeout传给sendCode方法），方便停止计时
        this.sendCode(this.phone, timeout);
      }
    },
  },
};
</script>
 
<style lass="scss" scoped>
.van-nav-bar {
  background-color: #2f97ec;
}

.box /deep/ .van-nav-bar__title {
  color: white;
  font-size: 0.45rem;
}

.box /deep/ .van-icon {
  color: white;
}
</style>
  