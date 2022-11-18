<template>
  
  <div class="header">
        <van-nav-bar
        title="题目详情"
        left-text="返回"
        left-arrow
        @click-left="onClickLeft"
        />
  </div>

</template>

<script>
import { Toast } from 'vant';
export default {
    name:'QuestionDetailHeader',
    methods: {
        onClickLeft() {
            Toast('返回');
        }
    },
}
</script>

<style lass="scss" scoped>

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