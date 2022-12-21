<template>
  
  <div class="writeQuestionHeader">

        <van-nav-bar title="写面试题" left-arrow @click-left="back">
            <template #right>
                <span style="color:white;margin-right: 15px;" @click="preview">
                    预览
                </span>
                <span style="color:white" @click="next">
                    下一步
                </span>
            </template>
        </van-nav-bar>
  </div>

</template>

<script>
import { Toast } from 'vant';
export default {
    name:'WriteQuestionHeader',
    methods: {
        //返回上一级
        back() {
            this.$router.go(-1);
        },
        //预览
        preview(){
            Toast('预览')
        },
        //下一步，直接把step设置为2即可
        next(){
            this.$parent.changeStep(2);
        }
    },
}
</script>

<style lass="scss" scoped>

.writeQuestionHeader{
    position: inherit;
    top: 0;
    width: 100%;
}
.van-nav-bar {
background-color: #2f97ec;
}


.writeQuestionHeader /deep/ .van-nav-bar__title {
color: white;
font-size: 0.45rem;
}

.writeQuestionHeader /deep/ .van-icon {
color: white;
}

</style>