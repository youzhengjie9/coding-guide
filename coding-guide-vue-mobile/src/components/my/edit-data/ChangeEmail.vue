<template>
  <div class="ch-box">
    <div class="ch-header">
      <van-nav-bar
        title="绑定qq邮箱"
        left-arrow
        @click-left="back"
        right-text="保存"
        @click-right="save"
      >
      </van-nav-bar>
    </div>

    <div class="ch-content">
      <van-cell-group>
        <van-cell>
          <van-field
            v-model="curEmail"
            rows="1"
            autosize
            required
            label="qq邮箱"
            type="textarea"
            maxlength="50"
            placeholder="请输入qq邮箱"
            show-word-limit
          />
          <van-field
            v-model="code"
            center
            required
            label="验证码"
            placeholder="请输入验证码"
          >
            <template #button>
              <van-button
                size="small"
                type="primary"
                @click.prevent="sendCode"
                :disabled="sended"
              >
                {{ sendBtnText }}
              </van-button>
            </template>
          </van-field>
        </van-cell>
      </van-cell-group>
    </div>
  </div>
</template>

<script>
import { updateCurUserData, sendBindEmailCode,bindEmail } from "@/api/user";
import { Toast } from "vant";
export default {
  name: "EditDataChangeEmail",
  props: {
    email: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      curEmail: "",
      //输入的验证码
      code: "",
      //是否已经发送了验证码
      sended: false,
      //文本
      sendBtnText: "点击发送验证码",
      //计时器对象
      timer: null,
      //倒数60秒
      counter: 60,
    };
  },
  mounted() {
    this.curEmail = this.email;
  },
  methods: {
    //返回
    back() {
      this.$parent.curShowDiv = "index";
    },
    //校验邮箱(true则说明格式正确,false则说明不正确)
    checkEmail(email) {
      //qq邮箱正则表达式
      var reg_email = /^[1-9][0-9]{4,10}@qq.com$/;
      return reg_email.test(email);
    },
    //倒计时
    countDown() {
      // 将setInterval()方法赋值给前面定义的timer计时器对象，等用clearInterval()方法时方便清空这个计时器对象
      this.timer = setInterval(() => {
        // 替换文本，用es6里面的``这个来创建字符串模板，让秒实时改变
        this.sendBtnText = `${this.counter}秒后重新发送`;
        this.counter--;
        if (this.counter < 0) {
          // 当计时小于零时，重置倒计时
          this.reset();
        }
      }, 1000);
    },
    //重置倒计时
    reset() {
      // 重置按钮可用
      this.sended = false;
      // 重置文本内容
      this.sendBtnText = "点击发送验证码";
      if (this.timer) {
        // 存在计时器对象，则清除
        clearInterval(this.timer);
        // 重置秒数，防止下次混乱
        this.counter = 60;
        // 计时器对象重置为空
        this.timer = null;
      }
    },
    //发送验证码
    sendCode() {
      if (this.curEmail.length == 0) {
        Toast.fail("qq邮箱不能为空");
        return false;
      } else {
        //校验邮箱
        let flag = this.checkEmail(this.curEmail);
        //如果为false直接抛出错误提示
        if (!flag) {
          Toast.fail("qq邮箱格式错误");
          return false;
        } else {
          //发送验证码
          sendBindEmailCode(this.curEmail).then(res=>{
            if(res.data.code == 200){
              Toast.success("验证码发送成功,将在5分钟后过期");
              // 将按钮禁用，防止再次点击
              this.sended = true;
              // 开始倒计时，60s之后才能再次点击
              this.countDown();
            }else{
              Toast.fail('发送验证码失败')
            }
          })
          
        }
      }
    },
    //保存
    save() {
      if (this.curEmail.length == 0 || this.code.length == 0) {
        Toast.fail("qq邮箱和验证码不能为空");
      } else {
        if (this.code.length != 6) {
          Toast.fail("验证码必须为6位数字");
        } else {
          //校验邮箱
          let flag = this.checkEmail(this.curEmail);
          //如果为false直接抛出错误提示
          if (!flag) {
            Toast.fail("qq邮箱格式错误");
          } else {
            //组装请求后端的数据
            let bindEmailDTO = {
              email: this.curEmail,
              code: this.code,
            };
            let ts = this;
            bindEmail(bindEmailDTO)
              .then((res) => {
                //验证码正确，绑定成功
                if(res.data.code == 1201){
                  Toast.success("绑定邮箱成功");
                  //修改父组件数据
                  ts.$parent.userDataVO.email = this.curEmail;
                  //修改curShowDiv，返回编辑资料页面
                  ts.$parent.curShowDiv = "index";
                }else{
                   Toast.fail('验证码错误')
                }
                
              })
              .catch((err) => {
                Toast.fail("绑定邮箱失败");
              });
          }
        }
      }
    },
  },
};
</script>

<style lass="scss" scoped>
.ch-header {
  position: fixed;
  top: 0;
  width: 100%;
}
.ch-content {
  padding: 1.6667rem 0.4333rem 0.05333rem;
  font-size: 0.6rem;
}

.van-nav-bar {
  background-color: #2f97ec;
}

.ch-header /deep/ .van-nav-bar__title {
  color: white;
  font-size: 0.45rem;
}

.ch-header /deep/ .van-icon {
  color: white;
}

.ch-header /deep/ .van-nav-bar__text {
  color: white;
}

.ch-content /deep/ .van-field__control {
  display: block;
  box-sizing: border-box;
  width: 100%;
  min-width: 0;
  padding-left: 0.32rem;
  color: #323233;
  line-height: inherit;
  text-align: left;
  background-color: #f7f8f9;
  border-radius: 0.05333rem;
  border: 0;
  resize: none;
  -webkit-box-align: center;
}

.ch-content /deep/ .van-field__label {
  width: 3.2em;
}
</style>