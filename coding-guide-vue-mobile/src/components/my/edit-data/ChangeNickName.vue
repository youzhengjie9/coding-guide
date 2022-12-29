<template>
  <div class="editDataChangeNickName">
    <div class="editDataChangeNickNameHeader">
      <van-nav-bar
        title="编辑昵称"
        left-arrow
        @click-left="back"
        right-text="保存"
        @click-right="save"
      >
      </van-nav-bar>
    </div>

    <div class="editDataChangeNickNameContent">
      <van-cell-group>
        <van-cell>
          <van-field
            v-model="curNickName"
            rows="1"
            autosize
            type="textarea"
            maxlength="10"
            placeholder="请输入新的昵称(1-10个字)"
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
  name: "EditDataChangeNickName",
  props: {
    nickName: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      curNickName: "",
    };
  },
  mounted() {
    this.curNickName = this.nickName;
  },
  methods: {
    //返回
    back() {
      this.$parent.curShowDiv = "index";
    },
    //保存
    save() {
      if (this.curNickName.length == 0) {
        Toast.fail("昵称不能为空");
      } else {
        //组装请求后端的数据
        let userDataDTO = {
          nickName: this.curNickName,
        };
        let ts = this;
        updateCurUserData(userDataDTO)
          .then((res) => {
            Toast.success("修改成功");
            //修改父组件数据
            ts.$parent.userDataVO.nickName = this.curNickName;
            //修改vuex
            ts.$store.dispatch("setNickName", this.curNickName);
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
.editDataChangeNickNameContent {
  padding: 1.6667rem 0.4333rem 0.05333rem;
  font-size: 0.6rem;
}
.editDataChangeNickNameHeader {
  position: fixed;
  top: 0;
  width: 100%;
}
.van-nav-bar {
  background-color: #2f97ec;
}

.editDataChangeNickNameHeader /deep/ .van-nav-bar__title {
  color: white;
  font-size: 0.45rem;
}

.editDataChangeNickNameHeader /deep/ .van-icon {
  color: white;
}

.editDataChangeNickNameHeader /deep/ .van-nav-bar__text {
  color: white;
}

.editDataChangeNickNameContent /deep/ .van-field__control {
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