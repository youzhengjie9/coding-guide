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
 
        <div class="history">
            <search-history
            :searchHistories="searchHistories"
            >

            </search-history>
        </div>
        

  </div>
   


</template>

<script>
import SearchHistory from '@/components/search/SearchHistory.vue';
export default {
  data() {
    return {
      keyword: "", //搜索关键字
      searchHistories: this.getSearchHistoriesArray() || [] // 搜索历史记录
    };
  },
  components:{
    SearchHistory
  },
  methods: {
    //点击搜索
    onSearch(keyword) {
       
      const searchHistories = this.searchHistories
      const index = searchHistories.indexOf(this.keyword)
      if (index !== -1) {
        searchHistories.splice(index, 1)
      }
      searchHistories.unshift(this.keyword)

      console.log(searchHistories.length)
      localStorage.setItem('search-histories',searchHistories)

      //跳转搜索页面
      this.$router.push({
            path:'/search/result',
            query:{
              keyword:this.keyword
            }
      })

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
};
</script>

<style lang="less" scoped>

.header{
    background-color:cornflowerblue;
}

</style>