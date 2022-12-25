<template>
  <div class="writeQuestionPreviewFooterTabBar">
    <van-row>
      <van-col span="10">
        <van-button
          class="write-btn"
          type="primary"
          round
          plain 
          @click="clickWriteComment"
          style="margin-left: 5px;width: 160px;"
        >写评论</van-button>
      </van-col>

      <!-- 点赞 -->
      <van-col span="4" style="padding: 0.12667rem">
        <van-cell center>
          <i class="van-icon van-icon-like"
          style="font-size: 27px;"
          @click="like"
          >
          </i>
          <br />
          <span>0</span>
        </van-cell>
      </van-col>
      <!-- 收藏 -->
      <van-col span="4" style="padding: 0.12667rem">
        <van-cell center>
          <i class="van-icon van-icon-star"
          style="font-size: 27px;"
          @click="collect"
          >
          </i>
          <br />
          <span>0</span>
        </van-cell>
      </van-col>
      <!-- 评论 -->
      <van-col span="4" style="padding: 0.12667rem">
        <van-cell center>
          <van-icon name="comment-o" size="27" @click="comment"/>
          <br />
          <span>0</span>
        </van-cell>
      </van-col>

    </van-row>
  </div>
</template>

<script>
import { Toast } from 'vant';
export default {
  name: "WriteQuestionPreviewFooterTabBar",
  methods: {
    clickWriteComment(){
       Toast.fail('预览模式不允许写评论')
    },
    like(){
       Toast.fail('预览模式不允许点赞')
    },
    collect(){
       Toast.fail('预览模式不允许收藏')
    },
    comment(){
       Toast.fail('预览模式没有评论')
    },
  },
};
</script>

<style scoped>
.writeQuestionPreviewFooterTabBar {
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