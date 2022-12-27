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
          v-show="tagShow(item.tagList)"
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
            <!-- 已被点赞 -->
            <i
              class="van-icon van-icon-like"
              v-if="isLike(item.id)"
              style="font-size: 20px; color: red"
              @click="likeQuestion(item.id)"
            >
            </i>
            <!-- 未被点赞 -->
            <i
              class="van-icon van-icon-like"
              v-else
              style="font-size: 20px"
              @click="likeQuestion(item.id)"
            >
            </i>

            <span class="number">
              {{ item.likeCount }}
            </span>
          </van-col>

          <!-- 收藏 -->

          <van-col span="5">
            <!-- 已被收藏 -->
            <i
              class="van-icon van-icon-star"
              v-if="isCollect(item.id)"
              style="font-size: 20px; color: red"
              @click="collectQuestion(item.id)"
            >
            </i>
            <!-- 未被收藏 -->
            <i
              class="van-icon van-icon-star"
              v-else
              style="font-size: 20px"
              @click="collectQuestion(item.id)"
            >
            </i>

            <span class="number">
              {{ item.collectCount }}
            </span>
          </van-col>

          <!-- 遇见 -->

          <van-col span="5">
            <van-icon name="bulb-o" size="20" color="red" />
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
import { likeQuestion, collectQuestion } from "@/api/question";
import { Toast } from "vant";
export default {
  name: "quesionItem",
  props: {
    list: Array,
  },
  methods: {
    tagShow(tagList){
      //如果tagList第一个元素是空字符串，说明没有任何标签，所以返回false不展示
      if(tagList[0] == ''){
        return false;
      }
      return true;
    },
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
    //修改Props传过来的List集合对应的面试题的点赞数。修改为当前点赞数+val(val可以为1和-1)
    changeLikeCount(questionId,val){
      //由于子组件不能修改props的属性，必须通过下面这种方式让父组件去修改props属性
      this.$emit('changeLikeCount',questionId,val);
    },
    //修改Props传过来的List集合对应的面试题的收藏数。修改为当前收藏数+val(val可以为1和-1)
    changeCollectCount(questionId,val){
      //由于子组件不能修改props的属性，必须通过下面这种方式让父组件去修改props属性
      this.$emit('changeCollectCount',questionId,val);
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
          this.changeLikeCount(questionId,1);
          Toast.success("点赞成功");
        });
      }
      //反之，说明要调用取消点赞接口(和点赞接口是同一个)
      else {
        likeQuestion(questionId).then((res) => {
          this.$store.dispatch("cancelLike", Number(questionId));
          //点赞数-1
          this.changeLikeCount(questionId,-1);
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
          this.changeCollectCount(questionId,1);
          Toast.success("收藏成功");
        });
      }
      //反之，说明要调用取消收藏接口
      else {
        collectQuestion(questionId).then((res) => {
          this.$store.dispatch("cancelCollect", Number(questionId));
          //收藏数-1
          this.changeCollectCount(questionId,-1);
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

.number {
  font-size: 17px;
}
</style>