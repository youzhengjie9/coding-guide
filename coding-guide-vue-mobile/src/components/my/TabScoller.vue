<template>
  <div>
    <van-tabs v-model="active" swipeable @click="tabClick">
      <van-tab
        v-for="item in scollerTabList"
        :name="item.id"
        :key="item.id"
      >
      <!-- 私密的tab标签前面会加上🔒的标记 -->
        <template #title v-if="item.id == 2">
            {{item.tagName}}
            <span class="iconfont" style="font-size:19px;">&#xe9d4;</span>
        </template>

        <template #title v-else>
            {{item.tagName}}
        </template>


        <!-- 面试题目列表内容 -->
        <my-question-list :currentTabId="active"></my-question-list>

      </van-tab>
    </van-tabs>

    <!-- 回到顶部组件 -->
    <back-to-top></back-to-top>

  </div>
</template>

<script>
import BackToTop from "../common/BackToTop.vue";
import MyQuestionList from '../my/QuestionList.vue'
export default {
  name: "MyTabScoller",
  components:{
    BackToTop,
    MyQuestionList
  },
  data() {
    return {
      active: 0, //当前激活的标签栏id
      scollerTabList: [ //
        {
            id:1,
            tagName:'作品'
        },
        {
            id:2,
            tagName:'私密'
        },
        {
            id:3,
            tagName:'收藏'
        },
        {
            id:4,
            tagName:'点赞'
        },
      ],
    };
  },
  methods:{
    tabClick(tabId, tabName) {

    },
  }
};
</script>

<style scoped>

</style>