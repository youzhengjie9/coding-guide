<template>
  
  <div>

    <van-tabs v-model="active" swipeable @click="tabClick">
        
        <van-tab v-for="item in scollerTabList" :name="item.id" :title="item.title" :key="item.id">
            
            <!-- 面试题目列表内容 -->
            <question-list>
                
            </question-list>


        </van-tab>
    </van-tabs>

  </div>

</template>

<script>

import questionList from '../question/List.vue'

export default {
    components:{
        questionList
    },
    methods:{
        tabClick(name,title){
            console.log(name)
            console.log(title)
        }
    },
    data(){
        return {
            active:0, //当前激活的标签栏index
            scollerTabList:[
                {
                    title:'推荐',
                    id: 1
                },
                {
                    title:'Java',
                    id: 1002
                },
                {
                    title:'Python',
                    id: 1003
                },
                {
                    title:'c++',
                    id: 1004
                },
                {
                    title:'人工智能',
                    id: 1005
                },
                {
                    title:'大数据',
                    id: 1006
                },
                {
                    title:'分布式',
                    id: 1007
                },
                {
                    title:'微服务',
                    id: 1008
                },
                {
                    title:'云原生',
                    id: 1009
                },
                {
                    title:'SpringBoot',
                    id: 1010
                },
                {
                    title:'SpringCloud',
                    id: 1011
                },
                {
                    title:'vue',
                    id: 1012
                },
                {
                    title:'后端',
                    id: 1013
                },
                {
                    title:'前端',
                    id: 1014
                },
            ]
        }
    }
}
</script>

<style>

</style>