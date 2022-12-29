<template>
  <div class="ch-box">
    <div class="ch-header">
      <van-nav-bar
        title="编辑qq邮箱"
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
            type="textarea"
            maxlength="80"
            placeholder="请输入qq邮箱"
            show-word-limit
          />
        </van-cell>
      </van-cell-group>
    </div>
  </div>
</template>

<script>
import { updateCurUserData } from "@/api/user";
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
    //保存
    save() {
      if (this.curEmail.length == 0) {
        Toast.fail("qq邮箱不能为空");
      } else {
        //校验邮箱
        let flag = this.checkEmail(this.curEmail);
        //如果为false直接抛出错误提示
        if (!flag) {
          Toast.fail("qq邮箱格式错误");
        } else {
          //组装请求后端的数据
          let userDataDTO = {
            email: this.curEmail,
          };
          let ts = this;
          updateCurUserData(userDataDTO)
            .then((res) => {
              Toast.success("修改成功");
              //修改父组件数据
              ts.$parent.userDataVO.email = this.curEmail;
              //修改curShowDiv，返回编辑资料页面
              ts.$parent.curShowDiv = "index";
            })
            .catch((err) => {
              Toast.fail("修改失败");
            });
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
</style>