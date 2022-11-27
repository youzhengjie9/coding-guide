<template>
  <div class="box">
    <!-- 面试题顶部组件 -->
    <question-detail-header />

    <!-- 标题组件 -->
    <question-detail-title :questionTitle="questionTitle" />

    <!-- 用户（发布者）信息组件 -->
    <question-detail-user-info :userInfo="userInfo" />

    <!-- 面试题内容组件 -->
    <question-detail-content :question="question" />

    <!-- 底部标签栏（点赞、收藏、评论） -->
    <question-detail-footer-tab-bar 
    :question="question"
    @changeLikeCount="changeLikeCount"
    @changeCollectCount="changeCollectCount"
    />
  </div>
</template>

<script>
import QuestionDetailHeader from "../../components/question/detail/Header.vue";
import QuestionDetailTitle from "../../components/question/detail/Title.vue";
import QuestionDetailContent from "../../components/question/detail/Content.vue";
import QuestionDetailUserInfo from "../../components/question/detail/UserInfo.vue";
import QuestionDetailFooterTabBar from "../../components/question/detail/FooterTabBar.vue";
import { selectQuestionDetail } from "../../api/question";
import {
  selectCurUserAllLikeQuestionId,
  selectCurUserAllCollectQuestionId
} from "../../api/question";
import{
  getSimpleUserInfoByPublisherId
}from '@/api/user'
export default {
  data() {
    return {
      question: {},
      questionTitle: "",
      userInfo:{} //用户（发布者）信息
    };
  },
  components: {
    QuestionDetailHeader,
    QuestionDetailTitle,
    QuestionDetailContent,
    QuestionDetailUserInfo,
    QuestionDetailFooterTabBar,
  },
  created(){
    this.loadLikeQuestionIdList();
    this.loadCollectQuestionIdList();
  },
  methods: {
    //修改面试题的点赞数。修改为当前点赞数+val(val可以为1和-1)
    changeLikeCount(val) {
        this.question.likeCount = this.question.likeCount + val;
    },
    //修改面试题的收藏数。修改为当前收藏数+val(val可以为1和-1)
    changeCollectCount(val) {
      this.question.collectCount = this.question.collectCount + val;
    },
    //加载当前用户所有点赞的面试题id集合
    loadLikeQuestionIdList(){
        selectCurUserAllLikeQuestionId().then(res=>{
          //将数据放到VueX中
          this.$store.dispatch('initLikeList',res.data.data);
      })
    },
    //加载当前用户所有收藏的面试题id集合
    loadCollectQuestionIdList(){
        selectCurUserAllCollectQuestionId().then(res=>{
          //将数据放到VueX中
          this.$store.dispatch('initCollectList',res.data.data);
      })
    },
    //加载发布者用户信息
    loadPublisherInfo(publisherId){
      console.log(publisherId)
      getSimpleUserInfoByPublisherId(publisherId).then(res=>{
        this.userInfo=res.data.data;
      })
    },
    loadQuestionContent() {
      let questionId = this.$route.query.id;

      selectQuestionDetail(questionId)
        .then((res) => {
          if (res.data.code == 200) {
            this.question = res.data.data;
            this.questionTitle = res.data.data.title;
            //调用加载发布者用户信息方法
            this.loadPublisherInfo(this.question.userId);

          } else {
            Toast.fail("加载失败,请重试");
          }
        })
        .catch((err) => {
          Toast.fail("加载失败,请重试");
        });
    },
  },
  mounted() {

    this.loadQuestionContent();
  },
};
</script>

<style lang="scss" scoped>
</style>