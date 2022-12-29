<template>
  <div class="editData">

    <!-- 编辑资料中的首页 -->
    <div v-if="curShowDiv == 'index'">
      <!-- 头部 -->
      <edit-data-header/>
      <!-- 编辑内容 -->
      <edit-data-content :userDataVO="userDataVO"/>
    </div>
    

    <!-- 修改昵称组件 -->
    <div class="changeNickName" v-if="curShowDiv == 'changeNickName'">
      <!-- 组件 -->
      <edit-data-change-nick-name :nickName="userDataVO.nickName"/>
    </div>

    <!-- 修改简介组件 -->
    <div class="changeIntro" v-if="curShowDiv == 'changeIntro'">
      <!-- 组件 -->
      <edit-data-change-intro :intro="userDataVO.intro" />
    </div>

    <!-- 修改学校组件 -->
    <div class="changeSchool" v-if="curShowDiv == 'changeSchool'">
        <!-- 组件 -->
        <edit-data-change-school :school="userDataVO.school" />
    </div>

    <!-- 修改手机号组件 -->
    <div class="changePhone" v-if="curShowDiv == 'changePhone'">
      <!-- 组件 -->
      <edit-data-change-phone :phone="userDataVO.phone" />
    </div>

    <!-- 修改邮箱组件 -->
    <div class="changeEmail" v-if="curShowDiv == 'changeEmail'">
      <!-- 组件 -->
      <edit-data-change-email :email="userDataVO.email"/>
    </div>
  </div>
</template>

<script>
import EditDataHeader from "@/components/my/edit-data/Header.vue";
import EditDataContent from "@/components/my/edit-data/Content.vue";
import { getCurUserData } from "@/api/user";
import EditDataChangeNickName from "@/components/my/edit-data/ChangeNickName.vue";
import EditDataChangeIntro from "@/components/my/edit-data/ChangeIntro.vue";
import EditDataChangeSchool from "@/components/my/edit-data/ChangeSchool.vue";
import EditDataChangePhone from "@/components/my/edit-data/ChangePhone.vue";
import EditDataChangeEmail from "@/components/my/edit-data/ChangeEmail.vue";
export default {
  name: "EditData",
  components: {
    EditDataHeader,
    EditDataContent,
    EditDataChangeNickName,
    EditDataChangeIntro,
    EditDataChangeSchool,
    EditDataChangePhone,
    EditDataChangeEmail,
  },
  data() {
    return {
      //当前展示的div
      curShowDiv: "index",
      //userDataVO对象
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
  },
};
</script>

<style scoped>
</style>