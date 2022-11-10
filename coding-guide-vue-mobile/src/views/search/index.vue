<template>

  <div class="box">

        <div class="header">
              <van-row>
                  <van-col span="24">
                      <form action="/">
                          <van-search
                              v-model="value"
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
            <search-history></search-history>
        </div>
        

  </div>
   


</template>

<script>
import SearchHistory from '@/components/search/SearchHistory.vue';
export default {
  data() {
    return {
      value: "",
    };
  },
  components:{
    SearchHistory
  },
  methods: {
    onSearch(val) {
       
    },
    onCancel() {
       this.$router.push({
          path:'/index'
       })
    },
  },
};
</script>

<style lang="less" scoped>

.header{
    background-color:cornflowerblue;
}

</style>