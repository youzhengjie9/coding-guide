<template>
  <div class="userinfo">
    <van-row>
      <van-col span="19">
        <div class="avatar">
          <van-image
            class="vant-avatar"
            round
            width="1rem"
            height="1rem"
            :src="userInfo.avatar"
          />
        </div>

        <div class="username">
          <span style="margin-left: 0.2rem; font-size: 0.42rem">
            {{ userInfo.nickName }}
            <van-tag :color="userInfo.backgroundColor" class="vant-level">
              {{ userInfo.levelFormat }}
            </van-tag>
          </span>
          <br />
          <!-- 学校 -->
          <span 
          v-if="userInfo.school != null && userInfo.school != ''"
          style="margin-left: 0.2rem; font-size: 0.32rem">

            <i style="color: darkviolet">{{userInfo.school}}</i>

          </span>
        </div>
      </van-col>
      <van-col span="5">
        <div class="vant-follow">

          <!-- 未关注 -->
          <button 
          class="follow"
          v-if="!isFollow(userInfo.publisherId)"
          >
            关注
          </button>

          <!-- 已关注 -->
          <button
          v-else
          class="already-follow"
          >
            已关注
          </button>

          
        </div>
      </van-col>
    </van-row>
  </div>
</template>

<script>
export default {
  name: "QuestionDetailUserInfo",
  props: {
    userInfo: Object,
  },
  methods: {
    //判断发布这篇面试题的用户是否被当前用户关注，
    isFollow(userid) {
      return this.$store.state.User.userFollowIdList.includes(Number(userid));
    },
  },
};
</script>

<style scoped>
.userinfo {
  padding: 0.52667rem 0.45333rem 0.55333rem;
}
.avatar {
  float: left;
}

.username {
  /* margin-top: 0.13rem; */
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
}

.vant-avatar {
  position: inherit;
}

.vant-level {
  position: inherit;
}
.vant-follow {
  
  position: inherit;
}

/* 关注按钮 */
.follow{
  height: 0.70rem;
  width:  1.5rem;
  border-radius: 0.5rem;
  font-size: 0.35rem;
  background-color: white; /* 背景颜色 */
  color: red;  /* 字体 */
  border: 1px solid red; /* 边框 */
}

/* 已关注按钮 */
.already-follow{
  height: 0.70rem;
  width:  1.5rem;
  border-radius: 0.5rem;
  font-size: 0.35rem;
  background-color: white; /* 背景颜色 */
  color: black;  /* 字体 */
  border: 1px solid black; /* 边框 */
}


</style>