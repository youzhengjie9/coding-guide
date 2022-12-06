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
      @loadCurrentQuestionCommentList="loadCurrentQuestionCommentList"
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
import WriteComment from "@/components/question/comment/WriteComment.vue";

import { selectQuestionDetail } from "../../api/question";
import {
  selectCurUserAllLikeQuestionId,
  selectCurUserAllCollectQuestionId,
} from "../../api/question";
import { getSimpleUserInfoByPublisherId } from "@/api/user";
import { selectListByQuestionIdAndLimit } from "@/api/question-comment";

export default {
  data() {
    return {
      question: {}, //面试题对象
      questionTitle: "", //面试题标题
      userInfo: {}, //用户（发布者）信息
      showWriteCommentPopup: false, //控制打开和关闭“写评论”的popup弹出层
      writeCommentQuestionId: null, //点击“写评论”按钮时,记录我们发送的评论所属的面试题id
      //该面试的评论列表数组
      commentList: [],
      //该面试题的评论总数
      commentTotalCount: 0,
      commentPage: 1, //评论分页的当前页数。默认是第一页
      commentSize: 5, //评论分页的每页大小。一次5条
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
    clickWriteComment(writeCommentQuestionId) {
      this.writeCommentQuestionId = writeCommentQuestionId;
      this.showWriteCommentPopup = true;
    },
    //加载当前面试题的评论列表
    loadCurrentQuestionCommentList(callback) {
      let questionId = this.$route.query.id;
      //封装响应结果
      let responseResult = {
        lodingStatus: true,
        finishStatus: false,
      };
      selectListByQuestionIdAndLimit(
        questionId,
        this.commentPage,
        this.commentSize
      ).then((res) => {
        if (res.data.code === 200) {
          //页数+1
          this.commentPage++;
          //更新列表
          this.commentList = this.commentList.concat(
            res.data.data.questionCommentVOList
          );
          //更新总记录数
          this.commentTotalCount = res.data.data.questCommentCount;
          // 加载状态结束
          responseResult.lodingStatus = false;
          // 数据全部加载完成，说明已经没有记录可以刷新了，就显示到底了
          if (this.commentList.length >= this.commentTotalCount) {
            responseResult.finishStatus = true;
          }
        }
        callback(responseResult); //将回调结果传给下一个子组件
      });
    },
    //发送评论成功的回调方法
    sendCommentSuccess(newCommentObject) {
      //将新评论插入到评论列表数组（commentList）中，分为几种情况：
      //1：如果commentList为空（数组长度为0），则直接push到数组中即可（最简单的一种情况）
      if (this.commentList.length == 0) {
        this.commentList.push(newCommentObject);
      }
      //2：如果commentList不为空：
      else {
        //2.1：如果commentList中的所有评论的点赞都大于0，则将评论插入到倒数第一个位置
        //2.2：如果commentList中的所有评论的点赞都等于0，则将评论插入到第一个位置
        //2.3：如果commentList中的评论有一部分大于0、有一部分等于0：（并且这个评论列表一定是按照点赞数倒序排序和评论时间倒序排序）
        //---找到点赞数=0的评论（可以通过遍历等方式），在这条评论的前一个位置插入新评论---

        //判断是否插入成功标志位
        let insertSuccess = false;

        //处理2.2和2.3的情况
        for (let i = 0; i < this.commentList.length; i++) {
          if (this.commentList[i].likeCount === 0) {
            //将newCommentObject插入到指定位置中
            this.commentList.splice(i, 0, newCommentObject);
            //将insertSuccess设置为true，说明插入成功
            insertSuccess = true;
            break;
          }
        }

        //处理2.1的情况，防止所有评论的点赞都大于0导致没有插入评论，这里要多做一步判断
        if (!insertSuccess) {
          this.commentList.push(newCommentObject);
        }
      }

      // 面试题的评论总数+1
      this.question.commentCount++;

      // 关闭 “写评论” popup弹出层
      this.showWriteCommentPopup = false;
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
    //加载面试题内容
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