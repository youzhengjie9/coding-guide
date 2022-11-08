<template>
  
  <div>

    <!-- <van-pull-refresh v-model="refreshing" @refresh="onRefresh"> -->

        <van-list
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了"
            :error.sync="error"
            error-text="请求失败，点击重新加载"
            @load="onLoad">


            <!-- 题目 -->
            <quesion-item/>
            <!-- 题目 -->
            <quesion-item/>
            <!-- 题目 -->
            <quesion-item/>
            <!-- 题目 -->
            <quesion-item/>
            <!-- 题目 -->
            <quesion-item/>

        </van-list>

    <!-- </van-pull-refresh> -->

  </div>

</template>

<script>
import quesionItem from '../question/Item.vue'
export default {
  name:'questionList',
  components:{
    quesionItem
  },
  data() {
    return {
      list: [],
      error: false,
      loading: false,
      finished: false,
      pageNum: 1, //默认是第一页
      pageSize: 10, //一次10条
      total: 0 //总记录数
    };
  },
  methods: {

    //滚动分页
    onLoad() {
      // //
      // const param = {
      //   pageNum: this.pageNum,
      //   pageSize: this.pageSize
      // }
      // getList(param).then(res => {
      //   if (res.data.code === 200) {
      //     //页数+1
      //     this.pageNum++
      //     //更新列表
      //     this.list = this.list.concat(res.data.data.list)
      //     //更新总记录数
      //     this.total = res.data.data.total
      //     // 加载状态结束
      //     this.loading = false
      //     // 数据全部加载完成，说明已经没有记录可以刷新了，就显示到底了
      //     if (this.list.length >= this.total) {
      //       this.finished = true
      //     }
      //   }
      // }).catch(() => {
      //   this.error = true
      // })

    },
    //下拉刷新
    onRefresh() {
      // 清空列表数据
      this.finished = false;

      // 重新加载数据
      // 将 loading 设置为 true，表示处于加载状态
      this.loading = true;
      this.onLoad();
    },
  },
};
</script>

<style>

</style>