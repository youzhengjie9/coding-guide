<template>
  
  <div class="header">

        <van-nav-bar title="题目详情" left-text="返回" left-arrow @click-left="back">
            <template #right>
                <van-icon name="share-o" size="18" @click="toShare" />
            </template>
        </van-nav-bar>
  </div>

</template>

<script>
import { Toast } from 'vant';
export default {
    name:'QuestionDetailHeader',
    methods: {
        //返回上一级
        back() {
            this.$router.go(-1);
            // //为了解决跳转后页面样式混乱bug，所以跳转后要进行刷新页面
            // setTimeout(function () {
            //     window.location.reload();
            // }, 100);
        },
        //去分享
        toShare(){
            Toast('分享功能暂未实现')
        }
    },
}
</script>

<style lass="scss" scoped>

.header{
    position: fixed;
    top: 0;
    width: 100%;
}
.van-nav-bar {
background-color: #2f97ec;
}


.header /deep/ .van-nav-bar__title {
color: white;
font-size: 0.45rem;
}

.header /deep/ .van-icon {
color: white;
}

</style>