<template>

    <div class="box">
        
            <!-- 面试题顶部组件 -->
            <question-detail-header/>

            <!-- 标题组件 -->
            <question-detail-title :questionTitle="questionTitle"/>

            <!-- 用户信息组件 -->
            <question-detail-user-info />

            <!-- 面试题内容组件 -->
            <question-detail-content :question="question" />

          
        

    </div>

</template>

<script>
import QuestionDetailHeader from '../../components/question/detail/Header.vue'
import QuestionDetailTitle from '../../components/question/detail/Title.vue'
import QuestionDetailContent from '../../components/question/detail/Content.vue'
import QuestionDetailUserInfo from '../../components/question/detail/UserInfo.vue'
import { selectQuestionDetail } from "../../api/question";
export default {
    data(){
        return {
            question:{},
            questionTitle:''
        }
    },
    components:{
        QuestionDetailHeader,
        QuestionDetailTitle,
        QuestionDetailContent,
        QuestionDetailUserInfo
    },
    methods:{
        getQuestion() {
            let questionId = this.$route.query.id;

            selectQuestionDetail(questionId)
                .then((res) => {
                if (res.data.code == 200) {
                    this.question = res.data.data;
                    this.questionTitle=res.data.data.title
                } else {
                    Toast.fail("加载失败,请重试");
                }
                })
                .catch((err) => {
                Toast.fail("加载失败,请重试");
                });
        },
    },
    mounted() {
    this.getQuestion();
  },
}
</script>

<style lang="scss" scope>



</style>