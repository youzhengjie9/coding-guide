<template>
  <div class="myHeaderBox">

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
        <van-cell title="浏览记录" @click="toBrowseRecord" />
        <van-cell title="钱包" />
        <van-cell title="会员" />
        <van-cell title="退出登录" @click="logout" />
      </van-cell-group>
    </van-popup>
    <!-- 头部的中间部分 -->
    <div class="center">
      <van-col span="4">
        <img
          style="width: 1.5rem; height: 1.5rem; border-radius: 50%;"
          :src="userCard.avatar"
        />
      </van-col>

      <van-col span="20">
        <div class="userinfo">
          <!-- 昵称 -->
          <span class="nickname">{{userCard.nickName}}</span>
          <br />
          <!-- 帐号/用户名 -->
          <span class="username">帐号: {{userCard.userName}}</span>
        </div>
      </van-col>
    </div>

    <!-- 用户简介 -->
    <div class="introduce">
      {{userCard.intro}}
    </div>
    
    <!-- 性别 -->
    <div class="sex">

      <!-- 男 -->

      <van-tag size="medium" color="deepskyblue" v-if="userCard.sex == 0">
        
        <i class="iconfont icon-xingbienan" style="font-size: 17px;"></i>
        &nbsp;19岁
      </van-tag>

      <!-- 女 -->
      <van-tag size="medium" color="deeppink" v-else-if="userCard.sex == 1">
        <i class="iconfont icon-xingbienv" style="font-size: 17px;"></i>
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
        {{userCard.address}}
      </van-tag>
    </div>

    <!-- 学校 -->
    <div class="school" v-if="userCard.school != ''">
      <div class="schoolTag">
        {{ userCard.school }}
      </div>
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
    <div class="footer-right">

      <!-- 编辑资料 -->
      <div class="edit">
        <button class="edit-btn" @click="toEdit">编辑资料</button>
      </div>

    </div>
    
  </div>
</template>

<script>
import { Toast } from "vant";
import{
  logout
} from '@/api/logout'
import{
  getCurUserCardInfo
} from '@/api/user'

export default {
  data() {
    return {
      menuShow: false, //控制左上角菜单弹出层展示状态
      userCard:{}  //用户资料卡
    };
  },
  created(){
    this.loadCurUserCardInfo();
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
    //点击浏览记录
    toBrowseRecord(){
      this.$router.push({
        path:'/browse/record'
      });
    },
    //退出
    logout(){
        let accessToken=localStorage.getItem('accessToken');
        logout(accessToken).then(res=>{
            if(res.data.code == 800){
              this.$store.dispatch("logoutSuccess");
              Toast.success({
                message: res.data.msg,
                duration: 1500,
              });
              this.$router.push({
                path: "/login",
              });
            }else{
              Toast.fail({
                message: '退出登录失败',
                duration: 1500,
              });
            }
        }).catch(err=>{
              Toast.fail({
                message: '系统异常',
                duration: 1500,
              });
        })
    },
    //加载当前用户个人资料卡信息
    loadCurUserCardInfo(){
      getCurUserCardInfo().then(res=>{
        this.userCard=res.data.data;
      })
    },
    //点击编辑资料按钮
    toEdit(){
      Toast("编辑资料功能暂未实现！");
    }
  },
};
</script>

<style lang="scss" scoped>
.myHeaderBox {
  width: 100%;
  height: 257px;
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
  margin-right: 2px;
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
  width: auto;
  height: 25px;
  float: left;
  margin-right: 12px;
}

.addressTag{
  width: auto;
  height: 14px;
  float: left;
  border: 1px solid;
  color: #1989fa;
  background-color: #fff;
  padding: 0.05333rem 0.16rem;
  display: inline-flex;
  -webkit-box-align: center;
  align-items: center;
  font-size: 0.32rem;
  line-height: 0.42667rem;
  border-radius: 0.05333rem;
  pointer-events: none;
}


.school {
  width: auto;
  height: 25px;
  float: left;
}

.schoolTag{
  width: auto;
  height: 14px;
  float: left;
  border: 1px solid;
  color: #1989fa;
  background-color: #fff;
  padding: 0.05333rem 0.16rem;
  display: inline-flex;
  -webkit-box-align: center;
  align-items: center;
  font-size: 0.32rem;
  line-height: 0.42667rem;
  border-radius: 0.05333rem;
  pointer-events: none;
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


.edit {
  height: 0.85rem;
  width: 2.40rem;
  margin-top: 20px;
  float: left;
}

.edit-btn{
  height: 0.85rem;
  width: 2.40rem;
  border-radius: 0.5rem;
  font-size: 0.35rem;
  background-color: gray; /* 背景颜色 */
  color: white; /* 字体 */
  border: 1px solid white; /* 边框 */
}



</style>