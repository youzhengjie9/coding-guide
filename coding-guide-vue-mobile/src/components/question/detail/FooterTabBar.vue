<template>
  <div class="footerTabBar">
    <van-row>
      <van-col span="10">
        <van-search
          left-icon=""
          shape="round"
          placeholder="快来评论鸭"
          style="padding: 0.36667rem 0.32rem"
        />
      </van-col>

      <!-- 点赞 -->
      <van-col span="4" style="padding: 0.12667rem">
        <van-cell center>
          <i class="van-icon van-icon-like" style="font-size: 27px;" @click="likeQuestion(item.id)"></i>
          <br />
          <span>{{question.likeCount}}</span>
        </van-cell>
      </van-col>
      <!-- 收藏 -->
      <van-col span="4" style="padding: 0.12667rem">
        <van-cell center>
          <i class="van-icon van-icon-star" style="font-size: 27px;" @click="likeQuestion(item.id)"></i>
          <br />
          <span>{{question.collectCount}}</span>
        </van-cell>
      </van-col>
      <!-- 评论 -->
      <van-col span="4" style="padding: 0.12667rem">
        <van-cell center>
          <van-icon name="comment-o" size="27" />
          <br />
          <span>{{question.commentCount}}</span>
        </van-cell>
      </van-col>
    </van-row>
  </div>
</template>

<script>
import { likeQuestion, collectQuestion } from "@/api/question";

export default {
  name: "QuestionDetailFooterTabBar",
  props:{
    question: Object
  },
  methods: {
    //点赞
    likeQuestion(questionId) {
      // likeQuestion(questionId).then((res) => {});

    },
    collectQuestion(questionId) {
      // collectQuestion(questionId).then((res) => {});

    },
  },
};
</script>

<style scoped>
.footerTabBar {
  width: 100%;
  height: 60px;
  background-color: white;
  position: fixed;
  bottom: 0;
}
/* 字体居中 */
.van-cell__value--alone {
  text-align: center;
}

.van-cell {
  padding: 0.06667rem 0.02667rem;
}
</style>