<template>
  <div class="userCardHeaderBox">
    <div class="menu">
      <!-- 左上角的返回上一级 -->
      <van-icon name="arrow-left" size="30" @click="back" />

      <!-- 右上角的分享 -->
      <div class="share">
        <van-icon name="share-o" size="30" @click="toShare" />
      </div>
    </div>

    <!-- 头部的中间部分 -->
    <div class="center">
      <van-col span="4">
        <van-image
          round
          class="avator"
          width="1.5rem"
          height="1.5rem"
          :src="userCard.avatar"
        />
      </van-col>

      <van-col span="20">
        <div class="userinfo">
          <!-- 昵称 -->
          <span class="nickname">{{ userCard.nickName }}</span>
          <br />
          <!-- 帐号/用户名 -->
          <span class="username">帐号: {{ userCard.userName }}</span>
        </div>
      </van-col>
    </div>


    <div class="introduce">
      该用户暂时没有个人简介
    </div>

    <!-- 性别 -->
    <div class="sex">
      <!-- 男 -->

      <van-tag size="medium" color="deepskyblue" v-if="userCard.sex == 0">
        <i class="iconfont icon-xingbienan" style="font-size: 17px"></i>
        &nbsp;19岁
      </van-tag>

      <!-- 女 -->
      <van-tag size="medium" color="deeppink" v-else-if="userCard.sex == 1">
        <i class="iconfont icon-xingbienv" style="font-size: 17px"></i>
        &nbsp;19岁
      </van-tag>

      <!-- 未知 -->
      <van-tag size="medium" color="#7232dd" plain v-else>
        <!-- 未知 -->
        &nbsp;19岁
      </van-tag>
    </div>

    <!-- 地址（省市区） -->
    <div class="address" v-if="userCard.address != ''">
      <van-tag size="medium" plain type="primary">
        {{ userCard.address }}
      </van-tag>
    </div>

    <!-- 学校 -->
    <div class="school" v-if="userCard.school != ''">
      <van-tag plain size="medium" type="primary">
        {{ userCard.school }}
      </van-tag>
    </div>

    <!-- 底部的左侧 -->
    <div class="footer-left">
      <!-- 用户数据名称 -->
      <div class="data_name">
        <van-col span="6">
          <span style="font-size: 15px">获赞</span>
        </van-col>

        <van-col span="7">
          <span style="font-size: 15px">被收藏</span>
        </van-col>

        <van-col span="6">
          <span style="font-size: 15px">关注</span>
        </van-col>

        <van-col span="5">
          <span style="font-size: 15px">粉丝</span>
        </van-col>
      </div>

      <!-- 用户数据 -->
      <div class="data">
        <!-- 获赞数 -->
        <van-col span="6">
          <span style="font-size: 15px; color: orangered">
            {{ userCard.likedCount }}
          </span>
        </van-col>
        <!-- 收藏数 -->
        <van-col span="7">
          <span style="font-size: 15px; color: orangered">
            {{ userCard.collectedCount }}
          </span>
        </van-col>
        <!-- 关注数 -->
        <van-col span="6">
          <span style="font-size: 15px; color: orangered">
            {{ userCard.followCount }}
          </span>
        </van-col>
        <!-- 粉丝数 -->
        <van-col span="5">
          <span style="font-size: 15px; color: orangered">
            {{ userCard.fansCount }}
          </span>
        </van-col>
      </div>
    </div>

    <!-- 底部的右侧 -->
    <div class="footer-right" v-if="showFooterRight">
      <!-- 关注 -->
      <!-- <div class="follow">
        <button class="follow-btn">关注</button>
      </div> -->

      <!-- 已关注 -->
      <div class="follow">
        <button class="already-follow-btn">已关注</button>
      </div>

      <!-- 聊天 -->
      <div class="chat">
        <i class="iconfont icon-chat-o" style="font-size: 30px"></i>
      </div>
    </div>
  </div>
</template>

<script>
import { Toast } from "vant";
import { getUserCardInfoByUserId } from "@/api/user";

export default {
  name: "UserCardHeader",
  data() {
    return {
      //用户资料卡
      userCard: {},
       //是否展示底部右侧（footer-right）div（关注/已关注按钮、私聊），默认是展示
      showFooterRight: true,
    };
  },
  created() {
    this.loadUserCardInfo();
  },
  methods: {
    //点击分享
    toShare() {
      Toast("分享用户资料卡功能暂未实现！");
    },
    //点击返回上一级
    back() {
      this.$router.go(-1);
    },
    //加载用户资料卡信息
    loadUserCardInfo() {
      let userid = this.$route.query.id;
      getUserCardInfoByUserId(userid).then((res) => {
        //保存userCard
        this.userCard = res.data.data;
        //从Vuex中获取当前登录用户的userName。
        let currentLoginUserName = this.$store.state.User.userName;
        //获取当前查看的用户资料卡的用户userName
        let currentLookUserName = this.userCard.userName;
        //通过对比userName，来判断这个资料卡是不是自己的。
        //如果当前查看的用户资料卡是当前我们登录的用户的
        if (currentLookUserName === currentLoginUserName) {
          //将.footer类隐藏（我们的动态样式会根据showFooter的值来选择class样式）
          //如果showFooter=true说明需要展示头部的底部,则使用otherUserCardHeaderBox样式
          //如果showFooter=false说明不需要展示头部的底部,则使用myUserCardHeaderBox样式
          this.showFooter = false;
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.userCardHeaderBox {
  width: 100%;
  height: 260px;
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

.share {
  height: 0.66667rem;
  padding: 4px;
  margin-right: 2px;
  float: right;
}

.center {
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

.introduce{

  width: 75%;
  height: auto; //自动高度
  margin-left: 15px;
  float: left;
  font-size: 13px;
  margin-bottom: 8px;
  // 文本在div只会占用两行，如果超出会在最后面显示"..."
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.sex {
  width: 19%;
  height: 17px;
  margin-left: 15px;
  float: left;
}

.address {
  width: 37%;
  height: 25px;
  float: left;
}

.school {
  width: 40%;
  height: 25px;
  float: left;
}

.footer-left {
  width: 62%;
  height: 2rem;
  float: left;
}

.data_name {
  width: 97%;
  height: 15px;
  padding: 4px;
  margin-top: 10px;
  margin-left: 15px;
  float: left;
}

.data {
  width: 97%;
  height: 15px;
  padding: 4px;
  // margin-top: 30px;
  margin-left: 15px;
  float: left;
}

.footer-right {
  width: 32%;
  height: 60px;
  // padding: 4px;
  margin-left: 15px;
  float: left;
}

.follow {
  height: 0.7rem;
  width: 1.79rem;
  margin-top: 20px;
  float: left;
}

/* 关注按钮 */

.follow-btn {
  height: 0.7rem;
  width: 1.79rem;
  border-radius: 0.5rem;
  font-size: 0.35rem;
  background-color: rgb(240, 9, 9); /* 背景颜色 */
  color: white; /* 字体 */
  border: 1px solid rgb(240, 9, 9); /* 边框 */
}

/* 已关注按钮 */
.already-follow-btn {
  height: 0.7rem;
  width: 1.79rem;
  border-radius: 0.5rem;
  font-size: 0.35rem;
  background-color: gray; /* 背景颜色 */
  color: white; /* 字体 */
  border: 1px solid gray; /* 边框 */
}

/* 私聊按钮 */
.chat {
  width: 32px;
  height: 32px;
  margin-left: 15px;
  margin-top: 17px;
  float: left;
}
</style>