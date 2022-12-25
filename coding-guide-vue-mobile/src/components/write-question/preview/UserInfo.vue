<template>
  <div class="writeQuestionPreviewUserInfo">
    <van-row>
      <van-col span="19">
        <div class="avatar">
          <img 
          style="width: 1rem; height: 1rem; border-radius: 50%;"
          @click="toUserCardInfo(userInfo.publisherId)"
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
    </van-row>
  </div>
</template>

<script>

export default {
  name: "WriteQuestionPreviewUserInfo",
  props: {
    userInfo: Object,
  },
  methods: {
    //进入用户资料卡页面
    toUserCardInfo(userId) {
      this.$router.push({
        path: "/user/card",
        query: {
          id: userId,
        },
      });
    },
  },
};
</script>

<style scoped>
.writeQuestionPreviewUserInfo {
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

.vant-level {
  position: inherit;
}

</style>