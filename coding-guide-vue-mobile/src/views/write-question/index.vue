<template>
  <div class="writeQuestionBox">
    <!-- 写面试题div（当step==1则展示写面试题div） -->
    <div v-show="step == 1">
      <!-- 头部 -->
      <write-question-header />
      <!-- 写面试题的Markdown组件(内容) -->
      <write-question-content />
    </div>

    <!-- 当step==2则展示面试题设置div -->
    <!-- 面试题设置div（也就是当点击写面试题div中的“下一步”按钮所展示的div组件） -->
    <div v-show="step == 2">
      <!-- 头部 -->
      <write-question-setting-header />
      <!-- 设置内容 -->
      <write-question-setting-content />
      <!-- 底部（发布或保存草稿） -->
      <!-- 当子组件使用$emit调用父组件方法时，父组件一定要用@去绑定对应的方法,否则$emit会失效 -->
      <write-question-setting-footer
        @saveDraft="saveDraft"
        @publishQuestion="publishQuestion"
      />
    </div>

    <!-- 当step==3时则展示预览页面 -->
    <div v-show="step == 3">

      <!-- 面试题顶部组件 -->
      <write-question-preview-header />

      <!-- 标题组件 -->
      <write-question-preview-title :questionTitle="questionTitle"/>

      <!-- 用户（发布者）信息组件 --> 
      <write-question-preview-user-info :userInfo="userInfo" />

      <!-- 面试题内容组件 -->
      <write-question-preview-content :questionContent="questionContent"/>

      <!-- 底部标签栏 -->
      <write-question-preview-footer-tab-bar />

    </div>


  </div>
</template>

<script>
import WriteQuestionHeader from "@/components/write-question/Header.vue";
import WriteQuestionContent from "@/components/write-question/Content.vue";
import WriteQuestionSettingHeader from "@/components/write-question/setting/Header.vue";
import WriteQuestionSettingContent from "@/components/write-question/setting/Content.vue";
import WriteQuestionSettingFooter from "@/components/write-question/setting/Footer.vue";
import WriteQuestionPreviewHeader from "@/components/write-question/preview/Header.vue";
import WriteQuestionPreviewTitle from "@/components/write-question/preview/Title.vue";
import WriteQuestionPreviewUserInfo from "@/components/write-question/preview/UserInfo.vue";
import WriteQuestionPreviewContent from "@/components/write-question/preview/Content.vue";
import WriteQuestionPreviewFooterTabBar from "@/components/write-question/preview/FooterTabBar.vue";
import { Toast } from "vant";
import { saveDraft } from "@/api/question-draft";
import { publishQuestion } from "@/api/question";
import {
  getCurUserSimpleUserInfo
} from '@/api/user'
export default {
  name: "WriteQuestion",
  components: {
    WriteQuestionHeader,
    WriteQuestionContent,
    WriteQuestionSettingHeader,
    WriteQuestionSettingContent,
    WriteQuestionSettingFooter,
    WriteQuestionPreviewHeader,
    WriteQuestionPreviewTitle,
    WriteQuestionPreviewUserInfo,
    WriteQuestionPreviewContent,
    WriteQuestionPreviewFooterTabBar
  },
  data() {
    return {
      //默认是1(写面试题页面为1,面试题设置页面为2,预览页面为3)
      step: 1,
      //面试题标题
      questionTitle: "",
      //Markdown编辑器输入的内容
      questionContent: "",
      userInfo: {}, //用户（发布者）信息
    };
  },
  methods: {
    //修改step值
    changeStep(val) {
      //从write-question-content组件中获取值保存到本组件中
      this.questionTitle = this.$children[1].questionTitle;
      this.questionContent = this.$children[1].questionContent;
      //判断面试题标题是否为5-100个字
      if (this.questionTitle.length >= 5 && this.questionTitle.length <= 100) {
        //判断面试题内容是否大于5个字，如果是则可以下一步
        if (this.questionContent.length >= 5) {
          this.step = val;
        } else {
          Toast.fail("面试题内容必须大于5个字");
        }
      } else {
        Toast.fail("面试题标题必须为5-100个字");
      }
    },
    //点击保存草稿按钮
    saveDraft() {
      //后端需要的其他数据
      let questionTitle = this.questionTitle;
      let questionContent = this.questionContent;
      //从write-question-setting-content组件中获取我们设置的内容保存起来
      let isPublic = this.$children[3].isPublic;
      let allowComment = this.$children[3].allowComment;
      let difficulty = this.$children[3].difficulty;
      let tags = this.$children[3].selectedTagNameList.toString();
      //简单检验参数
      if (difficulty == "") {
        Toast.fail("未选择题目难度,保存草稿失败");
      } else {
        //封装请求后端的数据
        let questionDTO = {
          questionTitle: questionTitle,
          questionContent: questionContent,
          isPublic: isPublic,
          allowComment: allowComment,
          difficulty: difficulty,
          tags: tags,
        };

        saveDraft(questionDTO)
          .then((res) => {
            //保存成功
            if (res.data.code == 200) {
              //清空组件中的数据
              this.questionTitle = "";
              this.questionContent = "";
              //跳转到首页
              this.$router.replace({
                path: "/index",
              });
            } else {
              Toast.fail(res.data.msg);
            }
          })
          .catch((err) => {
            Toast.fail("系统异常,保存草稿失败");
          });
      }
    },
    //点击发布内容按钮(发布面试题)
    publishQuestion() {
      //后端需要的其他数据
      let questionTitle = this.questionTitle;
      let questionContent = this.questionContent;
      //从write-question-setting-content组件中获取我们设置的内容保存起来
      let isPublic = this.$children[3].isPublic;
      let allowComment = this.$children[3].allowComment;
      let difficulty = this.$children[3].difficulty;
      let tags = this.$children[3].selectedTagNameList.toString();
      //简单检验参数
      if (difficulty == "") {
        Toast.fail("未选择题目难度,保存草稿失败");
      } else {
        //封装请求后端的数据
        let questionDTO = {
          questionTitle: questionTitle,
          questionContent: questionContent,
          isPublic: isPublic,
          allowComment: allowComment,
          difficulty: difficulty,
          tags: tags,
        };

        publishQuestion(questionDTO)
          .then((res) => {
            //保存成功
            if (res.data.code == 200) {
              //清空组件中的数据
              this.questionTitle = "";
              this.questionContent = "";
              //跳转到首页
              this.$router.replace({
                path: "/index",
              });
            } else {
              Toast.fail(res.data.msg);
            }
          })
          .catch((err) => {
            Toast.fail("系统异常,发布内容失败");
          });
      }
    },
    //加载当前用户的信息
    loadCurUserInfo() {
      getCurUserSimpleUserInfo().then((res) => {
        this.userInfo = res.data.data;
      });
    },
  },
  mounted(){
    this.loadCurUserInfo();
  }
};
</script>

<style scoped>
</style>