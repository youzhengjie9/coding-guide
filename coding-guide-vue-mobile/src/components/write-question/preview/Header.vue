<template>
  
  <div class="writeQuestionPreviewHeader">

        <van-nav-bar title="预览题目" left-arrow @click-left="back">
            <template #right>
                <van-icon name="share-o" size="18" @click="toShare" />
            </template>
        </van-nav-bar>
  </div>

</template>

<script>
import { Toast } from 'vant';
export default {
    name:'WriteQuestionPreviewHeader',
    methods: {
        //返回到写面试题页面
        back() {
            this.$parent.changeStep(1);
        },
        //去分享
        toShare(){
            Toast.fail('预览模式不能分享')
        }
    },
}
</script>

<style lass="scss" scoped>

.writeQuestionPreviewHeader{
    position: fixed;
    top: 0;
    width: 100%;
}
.van-nav-bar {
background-color: #2f97ec;
}


.writeQuestionPreviewHeader /deep/ .van-nav-bar__title {
color: white;
font-size: 0.45rem;
}

.writeQuestionPreviewHeader /deep/ .van-icon {
color: white;
}

</style>