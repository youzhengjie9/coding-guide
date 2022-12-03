<template>
  <div class="box">
    <!-- 面试题顶部组件 -->
    <question-detail-header />

    <!-- 标题组件 -->
    <question-detail-title :questionTitle="questionTitle" />

    <!-- 用户（发布者）信息组件 -->
    <question-detail-user-info :userInfo="userInfo" />

    <!-- 面试题内容组件 -->
    <question-detail-content
      ref="questionDetailContentParent"
      :question="question"
      :commentList="commentList"
    />

    <!-- 写评论popup -->
    <van-popup
      v-model="showWriteCommentPopup"
      position="bottom"
      get-container="body"
    >
      
    <!-- “写评论”组件 -->
      <write-comment
      :writeCommentQuestionId="Number(writeCommentQuestionId)"
      @sendCommentSuccess="sendCommentSuccess"
      />

    </van-popup>

    <!-- 底部标签栏（点赞、收藏、评论） -->
    <question-detail-footer-tab-bar
      :question="question"
      @changeLikeCount="changeLikeCount"
      @changeCollectCount="changeCollectCount"
      @moveCommentList="moveCommentList"
      @clickWriteComment="clickWriteComment"
    />
  </div>
</template>

<script>
import QuestionDetailHeader from "../../components/question/detail/Header.vue";
import QuestionDetailTitle from "../../components/question/detail/Title.vue";
import QuestionDetailContent from "../../components/question/detail/Content.vue";
import QuestionDetailUserInfo from "../../components/question/detail/UserInfo.vue";
import QuestionDetailFooterTabBar from "../../components/question/detail/FooterTabBar.vue";
import WriteComment from '@/components/question/comment/WriteComment.vue'



import { selectQuestionDetail } from "../../api/question";
import {
  selectCurUserAllLikeQuestionId,
  selectCurUserAllCollectQuestionId,
} from "../../api/question";
import { getSimpleUserInfoByPublisherId } from "@/api/user";

export default {
  data() {
    return {
      question: {}, //面试题对象
      questionTitle: "", //面试题标题
      userInfo: {}, //用户（发布者）信息
      showWriteCommentPopup: false, //控制打开和关闭“写评论”的popup弹出层
      writeCommentQuestionId: null, //点击“写评论”按钮时,记录我们发送的评论所属的面试题id
      //该面试的评论列表数组
      commentList: [
        {
          id: 2300000000000001,
          userId: 5700000000000002,
          nickName: "昵称5700000000000002",
          content: "评论内容2300000000000001",
          commentTime: "2022-10-20 12:30:52",
          replyCount: 260,
          likeCount:177,
          avatar:
            "https://pic4.zhimg.com/80/v2-d43c201ae3f059caac7371785bc2b23f_720w.webp",
        },
      ],
    };
  },
  props: {},
  components: {
    QuestionDetailHeader,
    QuestionDetailTitle,
    QuestionDetailContent,
    QuestionDetailUserInfo,
    QuestionDetailFooterTabBar,
    WriteComment,
  },
  created() {
    this.loadLikeQuestionIdList();
    this.loadCollectQuestionIdList();
  },
  methods: {
    //点击“写评论”按钮。writeCommentQuestionId保存的是“我们发送的评论所属的面试题id”
    clickWriteComment(writeCommentQuestionId){
      this.writeCommentQuestionId=writeCommentQuestionId;
      this.showWriteCommentPopup=true
    },
    //发送评论成功的回调方法
    sendCommentSuccess(newCommentObject){
      
      // 将新发送的评论放到评论列表的第一个
      this.commentList.unshift(newCommentObject)

      // 面试题的评论总数+1
      this.question.commentCount++

      // 关闭 “写评论” popup弹出层
      this.showWriteCommentPopup = false
    },
    // 滚动到评论列表
    moveCommentList() {
      const commentList =
        this.$refs["questionDetailContentParent"].$refs["commentList"];
      window.scrollTo(0, commentList.offsetTop - 400);
    },
    //修改面试题的点赞数。修改为当前点赞数+val(val可以为1和-1)
    changeLikeCount(val) {
      this.question.likeCount = this.question.likeCount + val;
    },
    //修改面试题的收藏数。修改为当前收藏数+val(val可以为1和-1)
    changeCollectCount(val) {
      this.question.collectCount = this.question.collectCount + val;
    },
    //加载当前用户所有点赞的面试题id集合
    loadLikeQuestionIdList() {
      selectCurUserAllLikeQuestionId().then((res) => {
        //将数据放到VueX中
        this.$store.dispatch("initLikeList", res.data.data);
      });
    },
    //加载当前用户所有收藏的面试题id集合
    loadCollectQuestionIdList() {
      selectCurUserAllCollectQuestionId().then((res) => {
        //将数据放到VueX中
        this.$store.dispatch("initCollectList", res.data.data);
      });
    },
    //加载发布者用户信息
    loadPublisherInfo(publisherId) {
      getSimpleUserInfoByPublisherId(publisherId).then((res) => {
        this.userInfo = res.data.data;
      });
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