<template>
  <div class="box">
    <!-- 左上角的菜单 -->
    <div class="menu">
      <van-icon name="wap-nav" size="30" @click="showMenuPopup" />
    
      <!-- 右上角的分享 -->
      <div class="share">
        <van-icon name="share-o" size="30" @click="toShare" />
      </div>
    </div>
    
    <!-- 左上角菜单弹出层 -->
    <van-popup
      v-model="menuShow"
      position="left"
      :style="{ width: '40%', height: '100%' }"
    >
      <div style="height: 1.5rem"></div>
      <van-cell-group inset>
        <van-cell title="搜索用户" />
        <!-- 分割线 -->
        <van-divider
          :style="{
            color: '#1989fa',
            borderColor: '#1989fa',
            padding: '0 16px',
          }"
        >
        </van-divider>
        <van-cell title="我的草稿" />
        <van-cell title="浏览记录" />
        <van-cell title="钱包" />
        <van-cell title="会员" />
      </van-cell-group>
    </van-popup>
    <!-- 头部的中间部分 -->
    <div class="center">
      <van-col span="4">
        <van-image
          round
          class="avator"
          width="1.5rem"
          height="1.5rem"
          src="https://img.yzcdn.cn/vant/cat.jpeg"
        />
      </van-col>

      <van-col span="20">
        <div class="userinfo">
          <!-- 昵称 -->
          <span class="nickname">abcabc123</span>
          <br />
          <!-- 帐号/用户名 -->
          <span class="username">帐号: abcabc123</span>
        </div>
      </van-col>
    </div>

    
    <!-- 性别 -->
    <div class="sex">

      <!-- 男 -->

      <!-- <van-tag color="deepskyblue">
        <svg class="icon" aria-hidden="true" style="width:23px;height:23px;">
          <use xlink:href="#icon-xingbienan" rel="external nofollow" ></use>
        </svg>
        &nbsp;19岁
      </van-tag> -->

      <van-tag color="deeppink">
        <!-- 女 -->
        <svg class="icon" aria-hidden="true" style="width:17px;height:17px;">
          <use xlink:href="#icon-xingbienv" rel="external nofollow" ></use>
        </svg>
        &nbsp;19岁
      </van-tag>

    </div>

    <!-- 地址（省市区） -->
    <div class="address">
      <van-tag plain type="primary">
        广东省-河源市-源城区
      </van-tag>
    </div>

    <!-- 学校 -->
    <div class="school">

      <van-tag plain type="primary" style="margin-right:10px">
        东莞理工学院城市学院
      </van-tag>
    </div>

    <div class="footer_top">
          <van-col span="6">
            <span style="font-size: 15px">获赞</span>
          </van-col>

          <van-col span="6">
            <span style="font-size: 15px">被收藏</span>
          </van-col>

          <van-col span="6">
            <span style="font-size: 15px">关注</span>
          </van-col>

          <van-col span="6">
            <span style="font-size: 15px">粉丝</span>
          </van-col>
        </div>

        <!--  -->
        <div class="footer_bottom">
          <van-col span="6">
            <span style="font-size: 15px; color: orangered">9999999</span>
          </van-col>

          <van-col span="6">
            <span style="font-size: 15px; color: orangered">9999999</span>
          </van-col>

          <van-col span="6">
            <span style="font-size: 15px; color: orangered">9999999</span>
          </van-col>

          <van-col span="6">
            <span style="font-size: 15px; color: orangered">9999999</span>
          </van-col>
        </div>
    
  </div>
</template>

<script>
import { Toast } from "vant";
export default {
  data() {
    return {
      menuShow: false, //控制左上角菜单弹出层展示状态
    };
  },
  methods: {
    //打开左上角菜单弹出层
    showMenuPopup() {
      this.menuShow = true;
    },
    //点击分享
    toShare() {
      Toast("分享个人资料功能暂未实现！");
    },
  },
};
</script>

<style lang="scss" scoped>
.box {
  width: 100%;
  height: 200px;
  background: url("../../assets/my/my01.png") no-repeat;
  background-size: 100% 100%;
}

.menu {
  width: 93%;
  height: 0.66667rem;
  padding: 4px;
  margin-top: 10px;
  margin-left: 15px;
  float: left; //采用浮动解决margin-top上面白屏问题
}

.share{
  height: 0.66667rem;
  padding: 4px;
  margin-right: 25px;
  float: right;
}

.center{

  width: 93%;
  // height: 60px;
  padding: 4px;
  margin-top: 5px;
  margin-left: 15px;
  float: left;
}


.userinfo {
  padding: 0 5px;
  color: #000;
}

.nickname {
  font-size: 0.72rem;
}
.username {
  font-size: 0.35rem;
}

.sex {
  width: 16%;
  height: 17px;
  margin-left: 15px;
  float: left;
}

.address {
  width: 35%;
  height: 25px;
  float: left;
}

.school {
  width: 45%;
  height: 25px;
  float: left;
}

.footer_top {
  width: 93%;
  height: 15px;
  padding: 4px;
  margin-top: 10px;
  margin-left: 15px;
  float: left;
}

.footer_bottom {
  width: 93%;
  height: 15px;
  padding: 4px;
  // margin-top: 30px;
  margin-left: 15px;
  float: left;
}

</style>