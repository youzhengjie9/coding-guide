<template>
  <div class="editDataContent">
    <!-- 编辑页面展示首页 -->
    <div>
      <div class="avatar">
        <div style="width: 2.2rem; height: 2.2rem; margin: 0 auto">
          <van-image
            round
            width="2.2rem"
            height="2.2rem"
            :src="userDataVO.avatar"
          />
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
          @click="openChangeNikeName"
        />

        <!-- 性别 -->
        <van-cell title="性别">
          <van-radio-group
            v-model="sex"
            direction="horizontal"
            :icon-size="'13px'"
            @change="changeSex"
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
          @click="openChangeIntro"
        />
        <!-- 生日 -->
        <van-cell
          title="生日"
          is-link
          :value="birthday"
          value-class="cellContent"
          @click="showBirthdayPopup = true"
        />
        <!-- 地区 -->
        <van-cell
          title="地区"
          is-link
          :value="address"
          value-class="cellContent"
          @click="showAddressPopup = true"
        />
        <!-- 学校 -->
        <van-cell
          title="学校"
          is-link
          :value="userDataVO.school"
          value-class="cellContent"
          @click="openChangeSchool"
        />
        <!-- 手机号 -->
        <van-cell
          title="手机号"
          is-link
          :value="userDataVO.phone"
          value-class="cellContent"
          @click="openChangePhone"
        />
        <!-- qq邮箱 -->
        <van-cell
          title="qq邮箱"
          is-link
          :value="userDataVO.email"
          value-class="cellContent"
          @click="openChangeEmail"
        />
      </van-cell-group>
    </div>

    <!-- 生日日期选择器（日历） -->
    <div class="birthday">
      <van-popup v-model="showBirthdayPopup" position="bottom">
        <van-calendar
          v-model="showBirthdayPopup"
          @confirm="saveBirthday"
          confirm-text="保存"
          :minDate="minDate"
          :maxDate="maxDate"
        />
      </van-popup>
    </div>

    <!-- 地区选择器 -->
    <div class="address">
      <van-popup v-model="showAddressPopup" position="bottom">
        <van-area
          :area-list="areaList"
          confirm-button-text="保存"
          title="选择地区"
          columns-num="3"
          @confirm="saveAddress"
          @cancel="showAddressPopup = false"
        />
      </van-popup>
    </div>
  </div>
</template>

<script>
// 导入vant的省市区数据
import { areaList } from "@vant/area-data";
import { updateCurUserData } from "@/api/user";
import { Toast } from "vant";

export default {
  name: "EditDataContent",
  props: {
    userDataVO: {
      type: Object,
      default: {},
    },
  },
  data() {
    return {
      address: "", //当前选择的地区（如果没有选择则是我们原来的地区）
      showAddressPopup: false, //控制是否打开地区选择的popup弹出层
      areaList, //我国所有的省市区数据。直接返回即可，不用赋任何值，里面已经有数据了。
      birthday: "", //当前选择的生日日期
      showBirthdayPopup: false, //控制是否打开生日日期选择的popup弹出层
      minDate: new Date(1930, 0, 1), //最小日期
      maxDate: new Date(), //最大日期(今天)
      sex: "", //性别
    };
  },
  mounted() {
    //不然address的数据就渲染不上去，调小了会导致渲染不上，调大了会影响体验
    setTimeout(() => {
      this.address = this.userDataVO.address;
      this.birthday = this.userDataVO.birthday;
      this.sex = this.userDataVO.sex;
    }, 170);
  },
  methods: {
    changeSex(sex) {
      //防止第一次加载时触发这个方法导致请求后端进行修改
      if (this.$parent.userDataVO.sex != sex) {
        //组装请求后端的数据
        let userDataDTO = {
          sex: sex,
        };
        let ts = this;
        updateCurUserData(userDataDTO)
          .then((res) => {
            Toast.success("修改成功");
            //修改父组件属性
            ts.$parent.userDataVO.sex = sex;
          })
          .catch((err) => {
            Toast.fail("修改失败");
          });
      }
    },
    //确认选择地区
    saveAddress(values) {
      let address = values
        .filter((item) => !!item)
        .map((item) => item.name)
        .join("-");

      //组装请求后端的数据
      let userDataDTO = {
        address: address,
      };
      updateCurUserData(userDataDTO)
        .then((res) => {
          Toast.success("修改成功");
          //修改组件的birthday属性
          this.address = address;
          //修改父组件属性
          this.$parent.userDataVO.address = this.address;
          //关闭popup
          this.showAddressPopup = false;
        })
        .catch((err) => {
          Toast.fail("修改失败");
        });
    },
    //确认选择的生日日期
    saveBirthday(birthdayDate) {
      let birthday = this.formatDate(birthdayDate);
      //组装请求后端的数据
      let userDataDTO = {
        birthday: birthday,
      };
      updateCurUserData(userDataDTO)
        .then((res) => {
          Toast.success("修改成功");
          //修改组件的birthday属性
          this.birthday = birthday;
          //修改父组件属性
          this.$parent.userDataVO.birthday = this.birthday;
          //关闭popup
          this.showBirthdayPopup = false;
        })
        .catch((err) => {
          Toast.fail("修改失败");
        });
    },
    //格式化日期(年-月-日)
    formatDate(date) {
      return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
    },
    //打开修改昵称组件
    openChangeNikeName() {
      this.$parent.curShowDiv = "changeNickName";
    },
    //打开修改简介组件
    openChangeIntro() {
      this.$parent.curShowDiv = "changeIntro";
    },
    //打开修改学校组件
    openChangeSchool() {
      this.$parent.curShowDiv = "changeSchool";
    },
    //打开修改手机号组件
    openChangePhone() {
      this.$parent.curShowDiv = "changePhone";
    },
    //打开修改qq邮箱组件
    openChangeEmail() {
      this.$parent.curShowDiv = "changeEmail";
    },
    //头像上传
    avatarUpload() {
      let avatarFile = document.getElementsByName("avatarFile")[0],
        file = avatarFile.files[0],
        reader = new FileReader();
      //解决：当用户取消上传头像时导致报错的bug
      if (typeof file !== "undefined") {
        reader.readAsDataURL(file);
        let ts = this;
        //加载reader，下面的reader.result(avatarBase64)一定要放到onload方法里面，不然会获取不到
        reader.onload = function (e) {
          //上传的图片的base64编码
          let avatarBase64 = reader.result;
          //组装请求后端的数据
          let userDataDTO = {
            avatarBase64: avatarBase64
          };
          updateCurUserData(userDataDTO)
            .then((res) => {
              Toast.success("修改成功");
              //修改父组件属性
              ts.$parent.userDataVO.avatar= res.data.data;
              //修改vuex
              ts.$store.dispatch("setAvatar", res.data.data);
            })
            .catch((err) => {
              Toast.fail("修改失败");
            });
        };
      }
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