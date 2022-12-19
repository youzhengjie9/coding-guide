<template>

  <div class="box">

        <div class="header">
              <van-row>
                  <van-col span="24">
                      <form action="/">
                          <van-search
                              v-model="keyword"
                              show-action
                              placeholder="请输入搜索内容"
                              @search="onSearch"
                              @cancel="onCancel"
                              shape="round"
                              background="cornflowerblue"/>
                      </form>
                  </van-col>
                  
              </van-row>
        </div>
    
    <van-tabs v-model="active" swipeable @click="tabClick">
        
        <van-tab v-for="item in scollerTabList" :name="item.id" :title="item.title" :key="item.id">
            
            <!-- 展示搜索结果 -->
            <search-result-list
            :currentTabId="active"
            :keyword="keyword"
            > 
            </search-result-list>


        </van-tab>
    </van-tabs>

    <!-- 回到顶部组件 -->
    <back-to-top></back-to-top>
     
  </div>
</template>

<script>
import SearchResultList from "@/components/search/SearchResultList.vue";
import BackToTop from "@/components/common/BackToTop.vue";
export default {
  name:'SearchResult',
  data() {
    return {
      list: [],
      keyword: this.$route.query.keyword || '',
      searchHistories: this.getSearchHistoriesArray() || [] ,// 搜索历史记录
      active:0, //当前激活的标签栏index
            scollerTabList:[
                {
                    title:'最热',
                    id: 30001
                },
                {
                    title:'最新',
                    id: 30002
                },
                {
                    title:'推荐',
                    id: 30003
                }
            ]

    };
  },
  components: {
    SearchResultList,
    BackToTop
  },
  
  methods: {
    
    tabClick(name,title){
        
    },
    //点击搜索
    onSearch(keyword) {
       
       const searchHistories = this.searchHistories
       const index = searchHistories.indexOf(this.keyword)
       if (index !== -1) {
         searchHistories.splice(index, 1)
       }
       searchHistories.unshift(this.keyword)
 
       localStorage.setItem('search-histories',searchHistories)
 
       //跳转搜索页面
       this.$router.push({
             path:'/search/result',
             query:{
               keyword:this.keyword
             }
       })
       //刷新页面
       this.$router.go(0) 
 
     },
     //点击取消
     onCancel() {
        this.$router.push({
           path:'/index'
        })
     },
     //从localstorage中获取历史搜索
     getSearchHistoriesArray(){
       let sh=localStorage.getItem('search-histories');
       if(sh != null){
         let arr=sh.split(',');
         return arr;
       }
       return [];
     }
  },
  // 导航离开该组件自动调用
  beforeRouteLeave (to, from, next) {
    const { name: currentComponentName } = this.$options
    if (to.name === 'QuestionDetail') {
      //如果跳转到名为QuestionDetail的路由,则缓存当前组件（跳转前的组件）
      this.$store.dispatch('addCacheComponentName', currentComponentName)
    } else {
      // 如果该路由跳转是去其他组件，则移除当前组件缓存
      this.$store.dispatch('removeCacheComponentName', currentComponentName)
    }
    next()
  }
};
</script>

<style scoped>
.header{
    background-color:cornflowerblue;
}



</style>