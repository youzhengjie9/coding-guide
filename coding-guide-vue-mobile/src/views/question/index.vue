<template>

    <div class="box">

        <!-- <van-cell-group inset> -->

            <div class="md">
            <v-md-preview-html
            :html="question.content" 
            preview-class="vuepress-markdown-body"
            >
            </v-md-preview-html>
        </div>

        <!-- </van-cell-group> -->
        
        

    </div>

</template>

<script>
import {
    selectQuestionDetail
} from '../../api/question'
export default {
    data(){
        return{
            //面试题对象
            question:{}
        }
    },
    methods:{
        getQuestion(){
            let questionId=this.$route.query.id

            selectQuestionDetail(questionId).then(res=>{
                if(res.data.code==200){
                    this.question=res.data.data;
                }else{
                    Toast.fail('加载失败,请重试');
                }
            }).catch(err=>{
                Toast.fail('加载失败,请重试');
            })

        }
    },
    mounted(){
        this.getQuestion();
    }
}
</script>

<style lang="scss" scope>

.md{
    //禁止左右滚动条，这个不能放到其他位置，否则会导致vant列表不断触发onload
    // overflow-x:hidden;
}

</style>