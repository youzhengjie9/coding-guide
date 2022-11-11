<template>
  <div>
    
    <van-tabs v-model="active" swipeable @click="tabClick">
        
        <van-tab v-for="item in scollerTabList" :name="item.id" :title="item.title" :key="item.id">
            
            <!-- 搜索内容列表 -->
            <search-result-list
            :currentTabId="active"
            :keyword="keyword"
            > 
            </search-result-list>


        </van-tab>
    </van-tabs>
     
  </div>
</template>

<script>
import SearchResultList from "@/components/search/SearchResultList.vue";
export default {
  data() {
    return {
      list: [],
      keyword: this.$route.query.keyword || '',
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
  },
  
  methods: {
    
    tabClick(name,title){
        
    }
  },
};
</script>

<style>
</style>