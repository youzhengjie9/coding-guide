<template>
  <div class="footerTabBar">
    <van-row>
      <van-col span="10">
        <!-- 禁止评论按钮 -->
        <van-button
          class="write-btn"
          type="primary"
          round
          plain
          v-if="question.allowComment == 0"
          @click="forbidComment"
          style="margin-left: 5px;width: 160px;"
        >写评论</van-button>

        <!-- 允许评论按钮 -->
        <van-button
          class="write-btn"
          type="primary"
          round
          plain 
          v-if="question.allowComment == 1"
          @click="$emit('clickWriteComment',question.id)"
          style="margin-left: 5px;width: 160px;"
        >写评论</van-button>
      </van-col>

      <!-- 点赞 -->
      <van-col span="4" style="padding: 0.12667rem">
        <van-cell center>

          <!-- 已点赞 -->
          <i class="van-icon van-icon-like" v-if="isLike(question.id)"
          style="font-size: 27px;color: red;"
          @click="likeQuestion(question.id)">
          </i>
          <!-- 未点赞 -->
          <i class="van-icon van-icon-like" v-else
          style="font-size: 27px;"
          @click="likeQuestion(question.id)">
          </i>

          <br />
          <span>{{question.likeCount}}</span>
        </van-cell>
      </van-col>
      <!-- 收藏 -->
      <van-col span="4" style="padding: 0.12667rem">
        <van-cell center>

          <!-- 已收藏 -->
          <i class="van-icon van-icon-star" v-if="isCollect(question.id)" 
          style="font-size: 27px;color: red;" 
          @click="collectQuestion(question.id)">
          </i>
          <!-- 未收藏 -->
          <i class="van-icon van-icon-star" v-else 
          style="font-size: 27px;" 
          @click="collectQuestion(question.id)">
          </i>

          <br />
          <span>{{question.collectCount}}</span>
        </van-cell>
      </van-col>
      <!-- 评论 -->
      <van-col span="4" style="padding: 0.12667rem">
        <van-cell center>
          <van-icon name="comment-o" size="27" @click="moveCommentList"/>
          <br />
          <span>{{question.commentCount}}</span>
        </van-cell>
      </van-col>


    </van-row>
  </div>
</template>

<script>
import { likeQuestion, collectQuestion } from "@/api/question";
import { Toast } from "vant";

export default {
  name: "QuestionDetailFooterTabBar",
  props:{
    question: Object
  },
  components:{
    
  },
  methods: {
    forbidComment(){
      Toast.fail('该面试题不允许评论！')
    },
    moveCommentList(){
      this.$emit('moveCommentList')
    },
    //修改Props传过来的question的点赞数。修改为当前点赞数+val(val可以为1和-1)
    changeLikeCount(val){
      //由于子组件不能修改props的属性，必须通过下面这种方式让父组件去修改props属性
      this.$emit('changeLikeCount',val);
    },
    //修改Props传过来的question的收藏数。修改为当前收藏数+val(val可以为1和-1)
    changeCollectCount(val){
      //由于子组件不能修改props的属性，必须通过下面这种方式让父组件去修改props属性
      this.$emit('changeCollectCount',val);
    },
    //如果没有点赞过则点赞，如果点赞过则取消点赞
    likeQuestion(questionId) {
      //如果Vuex中不包含该questionId，则说明要调用点赞接口
      if (
        !this.$store.state.Question.QuestionLikeIds.includes(Number(questionId))
      ) {
        likeQuestion(questionId).then((res) => {
          this.$store.dispatch("like", Number(questionId));
          //点赞数+1
          this.changeLikeCount(1);
          Toast.success("点赞成功");
        });
      }
      //反之，说明要调用取消点赞接口(和点赞接口是同一个)
      else {
        likeQuestion(questionId).then((res) => {
          this.$store.dispatch("cancelLike", Number(questionId));
          //点赞数-1
          this.changeLikeCount(-1);
          Toast.success("取消点赞成功");
        });
      }
    },
    //如果没有收藏过则收藏，如果收藏过则取消收藏
    collectQuestion(questionId) {
      // collectQuestion(questionId).then(res=>{

      // })
      //如果Vuex中不包含该questionId，则说明要调用收藏接口
      if (
        !this.$store.state.Question.QuestionCollectIds.includes(
          Number(questionId)
        )
      ) {
        collectQuestion(questionId).then((res) => {
          this.$store.dispatch("collect", Number(questionId));
          //收藏数+1
          this.changeCollectCount(1);
          Toast.success("收藏成功");
        });
      }
      //反之，说明要调用取消收藏接口
      else {
        collectQuestion(questionId).then((res) => {
          this.$store.dispatch("cancelCollect", Number(questionId));
          //收藏数-1
          this.changeCollectCount(-1);
          Toast.success("取消收藏成功");
        });
      }
    },
    //判断面试题是否被点赞，只需要判断Vuex中是否包含该questionId，包含则说明点赞过
    isLike(questionId) {
      return this.$store.state.Question.QuestionLikeIds.includes(
        Number(questionId)
      );
    },
    //判断面试题是否被收藏，只需要判断Vuex中是否包含该questionId，包含则说明收藏过
    isCollect(questionId) {
      return this.$store.state.Question.QuestionCollectIds.includes(
        Number(questionId)
      );
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