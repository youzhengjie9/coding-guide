<template>
  <div class="ch-box">
    <div class="ch-header">
      <van-nav-bar
        title="编辑学校"
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
            v-model="curSchool"
            rows="1"
            autosize
            type="textarea"
            maxlength="15"
            placeholder="请输入学校(1-15个字)"
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
  name: "EditDataChangeSchool",
  props: {
    school: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      curSchool: "",
    };
  },
  mounted() {
    this.curSchool = this.school;
  },
  methods: {
    //返回
    back() {
      this.$parent.curShowDiv = "index";
    },
    //保存
    save() {
      if (this.curSchool.length == 0) {
        Toast.fail("学校不能为空");
      } else {
        //组装请求后端的数据
        let userDataDTO = {
          school: this.curSchool,
        };
        let ts = this;
        updateCurUserData(userDataDTO)
          .then((res) => {
            Toast.success("修改成功");
            //修改父组件数据
            ts.$parent.userDataVO.school = this.curSchool;
            //修改curShowDiv，返回编辑资料页面
            ts.$parent.curShowDiv = "index";
          })
          .catch((err) => {
            Toast.fail("修改失败");
          });
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