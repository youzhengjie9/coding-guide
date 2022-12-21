<template>

    <div class="writeQuestionBox">

        <!-- 写面试题div（当step==1则展示写面试题div） -->
        <div v-show="step == 1">
            <!-- 头部 -->
            <write-question-header/>
            <!-- 写面试题的Markdown组件(内容) -->
            <write-question-content/>
        </div>

        <!-- 反之,当step!=1则展示面试题设置div -->
        <!-- 面试题设置div（也就是当点击写面试题div中的“下一步”按钮所展示的div组件） -->
        <div v-show="step != 1">
            <!-- 头部 -->
            <write-question-setting-header />
            <!-- 设置内容 -->
            <write-question-setting-content />
            <!-- 底部（发布或保存草稿） -->
        </div>
        

    </div>
  
</template>

<script>
import WriteQuestionHeader from '@/components/write-question/Header.vue'
import WriteQuestionContent from '@/components/write-question/Content.vue'
import WriteQuestionSettingHeader from '@/components/write-question/setting/Header.vue'
import writeQuestionSettingContent from '@/components/write-question/setting/Content.vue'
import { Toast } from 'vant'
export default {
    name:'PublishQuestion',
    components:{
        WriteQuestionHeader,
        WriteQuestionContent,
        WriteQuestionSettingHeader,
        writeQuestionSettingContent
    },
    data(){
        return {
            //当前操作是第几步，默认是1(写面试题为第1步,面试题设置为第2步)
            step:2,
            //面试题标题
            questionTitle: "",
            //Markdown编辑器输入的内容
            questionContent: '',
        }
    },
    methods:{
        //修改step值
        changeStep(val){
            //从write-question-content组件中获取值
            this.questionTitle=this.$children[1].questionTitle
            this.questionContent=this.$children[1].questionContent
            //判断面试题标题是否为5-100个字
            if(this.questionTitle.length>=5 && this.questionTitle.length<=100){
                //判断面试题内容是否大于5个字，如果是则可以下一步
                if(this.questionContent.length >= 5){
                    this.step = val;
                }else{
                    Toast.fail('面试题内容必须大于5个字')
                }
            }else{
                Toast.fail('面试题标题必须为5-100个字')
            }
        }
    }
}
</script>

<style scoped>



</style>