<template>
  <div class="box">
    <!-- 历史记录 -->
    <div id="searchHistory">
      <van-cell-group>
        <van-cell title="历史记录">
          <template v-if="isDeleteShow">
            <span @click="clearAllHistory">全部删除</span>
            &nbsp;&nbsp;
            <span @click="isDeleteShow = false">完成</span>
          </template>
          <van-icon
            v-else
            name="delete"
            @click="isDeleteShow = true"
          ></van-icon>
        </van-cell>
        <van-cell
          :title="item"
          v-for="(item, index) in searchHistories"
          :key="index"
          @click="clickHistory(item, index)"
        >
          <van-icon v-show="isDeleteShow" name="close"></van-icon>
        </van-cell>
      </van-cell-group>
    </div>

    <!-- 分割线 -->
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
    </van-divider>

    <!-- 热门搜索 -->
    <div id="hotSearch">
      <van-cell-group>
        <van-cell title="热门搜索"> </van-cell>
        <!-- <van-cell
              :title="item"
              v-for="(item, index) in searchHistories"
              :key="index"
              @click="onHistoryClick(item, index)"
            >
              <van-icon
                v-show="isDeleteShow"
                name="close"
              ></van-icon>
            </van-cell> -->
      </van-cell-group>
    </div>

    <!-- 分割线 -->
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
    </van-divider>

    <!-- 猜你想搜 -->
    <!-- <div id="recommend">
          <van-cell-group>
            <van-cell title="猜你想搜">
              <van-icon name="replay" @click="replay">换一换</van-icon>
            </van-cell>
            <van-cell
              :title="item"
              v-for="(item, index) in searchHistories"
              :key="index"
              @click="onHistoryClick(item, index)"
            >
              <van-icon
                v-show="isDeleteShow"
                name="close"
              ></van-icon>
            </van-cell>
          </van-cell-group>
    </div> -->
  </div>
</template>

<script>
import { Toast, Dialog } from "vant";
export default {
  data() {
    return {
      isDeleteShow: false, // 删除框是否弹出
    };
  },
  props: {
    searchHistories: {
      type: Array,
      default: () => [],
    },
  },
  methods: {
    //删除全部历史记录
    clearAllHistory() {
      Dialog.confirm({
        title: "是否删除全部历史记录？",
      })
        .then(() => {
          //点击确认删除全部历史记录的回调
          this.searchHistories = [];
          localStorage.removeItem("search-histories");
        })
        .catch(() => {
          //点击取消回调
        });
    },
    replay() {
      Toast("换一个成功");
    },
    clickHistory(item, index) {
      if (this.isDeleteShow) {
        // 删除所点击的那条历史记录
        this.searchHistories.splice(index, 1);
        localStorage.setItem("search-histories", this.searchHistories);
      } else {
        // 开始搜索所点击的那条历史记录
        //跳转搜索页面
        this.$router.push({
          path: "/search/result",
          query: {
            keyword: item,
          },
        });
      }
    },
  },
};
</script>

<style scope>
</style>