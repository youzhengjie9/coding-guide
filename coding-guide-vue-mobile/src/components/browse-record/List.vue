<template>
  <div class="brList">
    <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
      <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多了..."
        @load="onLoad"
        :error.sync="error"
        error-text="请求失败，点击重新加载"
      >
        <!-- 题目 -->
        <quesion-item
          :list="list"
          @changeLikeCount="changeLikeCount"
          @changeCollectCount="changeCollectCount"
        />
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<script>
import quesionItem from "../question/Item.vue";
import {
  selectCurUserQuestionBrowseRecordByLimit,
  selectCurUserAllLikeQuestionId,
  selectCurUserAllCollectQuestionId,
} from "@/api/question";
export default {
  name: "BrowseRecordList",
  components: {
    quesionItem,
  },
  data() {
    return {
      list: [],
      loading: false,
      finished: false,
      refreshing: false,
      error: false,
      page: 1, //默认是第一页
      size: 7, //一次7条
      total: 0, //总记录数
    };
  },
  methods: {
    //修改List集合对应的面试题的点赞数。修改为当前点赞数+val(val可以为1和-1)
    changeLikeCount(questionId, val) {
      for (let i = 0; i < this.list.length; i++) {
        if (this.list[i].id === questionId) {
          this.list[i].likeCount = this.list[i].likeCount + val;
          break;
        }
      }
    },
    //修改List集合对应的面试题的收藏数。修改为当前收藏数+val(val可以为1和-1)
    changeCollectCount(questionId, val) {
      for (let i = 0; i < this.list.length; i++) {
        if (this.list[i].id === questionId) {
          this.list[i].collectCount = this.list[i].collectCount + val;
          break;
        }
      }
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
    //滚动分页
    onLoad() {
      setTimeout(() => {
        if (this.refreshing) {
          this.list = [];
          this.refreshing = false;
        }
        this.loadBrowseRecordList();
      }, 1000);
    },
    //下拉刷新
    onRefresh() {
      // 清空/初始化数据
      this.finished = false;
      (this.error = false),
        (this.page = 1), //默认是第一页
        (this.size = 7), //一次7条
        (this.total = 0); //总记录数

      // 重新加载数据
      // 将 loading 设置为 true，表示处于加载状态
      this.loading = true;
      this.onLoad();
    },
    //加载浏览记录列表
    loadBrowseRecordList() {
      selectCurUserQuestionBrowseRecordByLimit(this.page, this.size)
        .then((res) => {
          if (res.data.code === 200) {
            //页数+1
            this.page++;
            //更新列表
            this.list = this.list.concat(res.data.data.questionVOList);
            //更新总记录数
            this.total = res.data.data.totalCount;
            // 加载状态结束
            this.loading = false;
            // 数据全部加载完成，说明已经没有记录可以刷新了，就显示到底了
            if (this.list.length >= this.total) {
              this.finished = true;
            }
          }
        })
        .catch(() => {
          this.error = true;
          Toast.fail("服务器异常,接口请求失败");
        });
    },
  },
};
</script>

<style scoped>


</style>