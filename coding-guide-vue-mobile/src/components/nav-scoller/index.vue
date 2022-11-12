<template>
  <div>

    <van-tabs v-model="active" swipeable @click="tabClick">
      <van-tab
        v-for="item in scollerTabList"
        :name="item.id"
        :title="item.tagName"
        :key="item.id"
      >
        <!-- 面试题目列表内容 -->
        <question-list
        :currentTabId="active"
        >

        </question-list>
      </van-tab>
    </van-tabs>


    <!-- 回到顶部组件 -->
    <back-to-top></back-to-top>


  </div>
</template>

<script>
import questionList from "../question/List.vue";
import BackToTop from "../common/BackToTop.vue";
import{
  selectAllExistTag
}from '../../api/tag'
import { Toast } from "vant";

export default {
  components: {
    questionList,
    BackToTop
  },
  methods: {
    tabClick(tabId, tabName) {

    },
    //初始化scollerTabList
    initScollerTabList(){
      selectAllExistTag().then(res=>{
        if(res.data.code == 200){
          let list=res.data.data;
          for(let i=0;i<list.length;i++){
            list[i].id=Number(list[i].id)
          }
          this.scollerTabList=res.data.data;
        }else{
          Toast.fail("加载滚动标签列表失败");
        }
      }).catch(err=>{
        Toast.fail("服务器异常,接口请求失败");
      })
    }
  },
  data() {
    return {
      active: 0, //当前激活的标签栏id
      scollerTabList: [],
    };
  },
  created(){
    this.initScollerTabList();
  }
};
</script>

<style>
</style>