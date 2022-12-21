<template>
  
  <div class="header">

        <van-nav-bar title="浏览记录" left-text="返回" left-arrow @click-left="back">
            
        </van-nav-bar>
  </div>

</template>

<script>
import { Toast } from 'vant';
export default {
    name:'BrowseRecordHeader',
    methods: {
        //返回上一级
        back() {
            this.$router.go(-1);
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
    position: inherit;
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