<template>
  <div>
    <!-- 遍历面试题 -->
    <div class="iq" v-for="(item, index) in list" :key="index">
      <!-- 标题 -->
      <div class="title">
        <span class="titleHref" @click="toQuestionDetail(item.id)">
          {{ item.title }}
        </span>
      </div>

      <!-- 标签栏 -->
      <div>
        <!-- 是否推荐 -->
        <van-tag
          v-if="item.recommend == 1"
          type="success"
          class="tagItem"
          size="medium"
        >
          推荐
        </van-tag>

        <!-- 标签列表 -->
        <van-tag
          plain
          type="primary"
          v-for="tagName in item.tagList"
          :key="tagName"
          class="tagItem"
          size="medium"
        >
          {{ tagName }}
        </van-tag>
      </div>

      <div>
        <span style="font-size: 15px; margin-left: 15px"
          >发布日期:
          {{ item.publishTime | dateformat("YYYY-MM-DD HH:mm:ss") }}</span
        >
        <!-- 简单难度 -->
        <van-tag
          v-if="item.difficulty == 1"
          type="primary"
          class="tagItem"
          size="medium"
          style="margin-right: 15px"
        >
          简单
        </van-tag>

        <!-- 中等难度 -->
        <van-tag
          v-if="item.difficulty == 2"
          type="success"
          class="tagItem"
          size="medium"
          style="margin-right: 15px"
        >
          中等
        </van-tag>

        <!-- 较难难度 -->
        <van-tag
          v-if="item.difficulty == 3"
          type="warning"
          class="tagItem"
          size="medium"
          style="margin-right: 15px"
        >
          较难
        </van-tag>

        <!-- 困难难度 -->
        <van-tag
          v-if="item.difficulty == 4"
          type="danger"
          class="tagItem"
          size="medium"
          style="margin-right: 15px"
        >
          困难
        </van-tag>
      </div>

      <div>
        <van-row gutter="5">
          <!-- 浏览量 -->
          <van-col span="6" class="foot">
            <van-icon name="eye-o" size="22" />
            <span class="number">
              {{ item.readCount }}
            </span>
          </van-col>

          <!-- 点赞 -->

          <van-col span="5">
            <!-- <i class="iconfont icon-dianzan" style="font:15px;color:red;"></i> -->
            <van-icon name="like" size="20" color="red" @click="likeQuestion(item.id)"/>
            <span class="number">
              {{ item.likeCount }}
            </span>
          </van-col>

          <!-- 收藏 -->

          <van-col span="5">
            <!-- <i class="iconfont icon-shoucang" style="font:15px;color:red;"></i> -->
            <van-icon name="star" size="20" color="red" @click="collectQuestion(item.id)"/>
            <span class="number">
              {{ item.collectCount }}
            </span>
          </van-col>

          <!-- 遇见 -->

          <van-col span="5">
            <van-icon name="bulb-o" size="20" color="red"/>
            <span class="number">
              {{ item.meetCount }}
            </span>
          </van-col>

        </van-row>
      </div>

      <!-- 分割线 -->
      <van-divider
        :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
      >
      </van-divider>
    </div>
  </div>
</template>

<script>
import {
  likeQuestion,
  collectQuestion
} from '@/api/question'
export default {
  name: "quesionItem",
  props: {
    list: Array,
  },
  methods: {
    //跳转到指定面试题详情
    toQuestionDetail(questionId) {
      //跳转路由
      this.$router.push({
        path: "/question/detail",
        query: {
          id: questionId,
        },
      }); 
    },
    //点赞
    likeQuestion(questionId){
      likeQuestion(questionId).then(res=>{
        
      })
    },
    collectQuestion(questionId){
      collectQuestion(questionId).then(res=>{
        
      })
    }
  },
};
</script>

<style scoped>
.titleHref {
  font-size: 15px;
  font-weight: bold;
  color: black;
}

.tagItem {
  margin-left: 15px;
  margin-bottom: 10px;
}

.title {
  height: auto;
  margin-left: 15px;
  margin-bottom: 10px;
  margin-top: 15px;

  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 4;
}

.foot {
  margin-left: 15px;
}

.number{
  font-size: 17px;
}

</style>