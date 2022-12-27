<template>
  <div class="editDataContent">
    <van-form @submit="onSubmit">
      <div class="avatar">
        <div style="width: 2.2rem; height: 2.2rem; margin: 0 auto">
          <van-image round width="2.2rem" height="2.2rem" :src="userDataVO.avatar" />
          <input
            type="file"
            accept="image/*"
            name="avatarFile"
            @change="avatarUpload"
            class="fileInput"
            value=""
          />
        </div>
      </div>

      <van-cell-group>
        <!-- 昵称 -->
        <van-cell
          title="昵称"
          is-link
          :value="userDataVO.nickName"
          value-class="cellContent"
        />

        <!-- 性别 -->
        <van-cell title="性别">
          <van-radio-group
            v-model="userDataVO.sex"
            direction="horizontal"
            :icon-size="'13px'"
          >
            <van-radio name="0">男</van-radio>
            <van-radio name="1">女</van-radio>
            <van-radio name="2">未知</van-radio>
          </van-radio-group>
        </van-cell>

        <!-- 简介 -->
        <van-cell
          title="简介"
          is-link
          :value="userDataVO.intro"
          value-class="cellContent"
        />
        <!-- 生日 -->
        <van-cell
          title="生日"
          is-link
          :value="userDataVO.birthday"
          value-class="cellContent"
        />
        <!-- 地区 -->
        <van-cell
          title="地区"
          is-link
          :value="userDataVO.address"
          value-class="cellContent"
        />
        <!-- 学校 -->
        <van-cell
          title="学校"
          is-link
          :value="userDataVO.school"
          value-class="cellContent"
        />
        <!-- 手机号 -->
        <van-cell
          title="手机号"
          is-link
          :value="userDataVO.phone"
          value-class="cellContent"
        />
        <!-- 邮箱 -->
        <van-cell
          title="邮箱"
          is-link
          :value="userDataVO.email"
          value-class="cellContent"
        />
      </van-cell-group>
    </van-form>
  </div>
</template>

<script>
import { getCurUserData } from "@/api/user";
export default {
  name: "EditDataContent",
  data() {
    return {
      userDataVO: {},
    };
  },
  created() {
    this.loadUserDataVO();
  },
  methods: {
    //加载userDataVO
    loadUserDataVO() {
      getCurUserData().then((res) => {
        this.userDataVO = res.data.data;
      });
    },
    onSubmit(values) {
      console.log("submit", values);
    },
    //头像上传
    avatarUpload() {
      let avatarFile = document.getElementsByName("avatarFile")[0],
        file = avatarFile.files[0],
        reader = new FileReader();
      reader.readAsDataURL(file);
      let ts = this;
      //加载reader，下面的reader.result(avatarBase64)一定要放到onload方法里面，不然会获取不到
      reader.onload = function (e) {
        console.log(file);
        console.log(reader);
        //上传的图片的base64编码
        let avatarBase64 = reader.result;
        console.log(reader.result);
        ts.$store.dispatch(
          "setAvatar",
          "https://pic3.zhimg.com/80/v2-d19367c9372cedcfbe010ccf493862ae_720w.webp"
        );
      };
    },
  },
};
</script>

<style scoped>
.editDataContent {
  padding: 1.6667rem 0.4333rem 0.05333rem;
  font-size: 0.6rem;
}

.avatar {
  width: 100%;
  height: 2.2rem;
  position: relative;
}

.fileInput {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  opacity: 0;
  display: block;
  width: 2.3rem;
  clear: both;
  display: block;
  margin: auto;
  background-color: red;
}

/* 单元格右侧内容样式 */
.cellContent {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
}
</style>