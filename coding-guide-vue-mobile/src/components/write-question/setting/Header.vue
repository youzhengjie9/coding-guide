<template>
  
  <div class="writeQuestionSettingHeader">

        <van-nav-bar title="设置" left-arrow @click-left="back">
            
        </van-nav-bar>
  </div>

</template>

<script>
import { Toast } from 'vant';
export default {
    name:'WriteQuestionSettingHeader',
    methods: {
        //返回到写面试题页面，直接把step设置为1即可。
        back() {
            this.$parent.changeStep(1);
        }
    },
}
</script>

<style lass="scss" scoped>

.writeQuestionSettingHeader{
    position: inherit;
    top: 0;
    width: 100%;
}
.van-nav-bar {
background-color: #2f97ec;
}


.writeQuestionSettingHeader /deep/ .van-nav-bar__title {
color: white;
font-size: 0.45rem;
}

.writeQuestionSettingHeader /deep/ .van-icon {
color: white;
}

</style>