<template>
  
  <div class="userinfo">
    <van-row>
        <van-col span="18">
            <div class="avatar">
                <van-image
                round
                width="1rem"
                height="1rem"
                lazy-load
                src="https://img01.yzcdn.cn/vant/cat.jpeg"
                />
            </div>

            <div class="username">
                
                <span style="margin-left:0.2rem;font-size: 0.42rem;">

                    abcabc123
                <!-- 会员图标，如果有会员则显示这个图标 -->
                <van-icon name="diamond-o" color="red" size="20"/>

                </span>
                <!-- <br/> -->
                <!-- <span style="margin-left:0.2rem;font-size: 0.32rem;">
                    <van-icon name="diamond-o" color="red" size="20"/>
                </span> -->

            </div>

        </van-col>
        <van-col span="6" >
            <van-button color="red" plain round style="height:0.70rem;">关注</van-button>
            <!-- <van-button color="black" plain round style="height:0.70rem;">已关注</van-button> -->
        </van-col>
    </van-row>
  </div>


</template>

<script>
export default {
    name:'QuestionDetailUserInfo'
}
</script>

<style scoped>

.userinfo{
    padding: 0.22667rem 0.45333rem 0.05333rem;
}
.avatar{
    float: left;
}

.username{
    margin-top: 0.15rem;
    overflow:hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    -o-text-overflow:ellipsis;
}

/*
修改.van-image、van-icon、van-button的position，解决面试题详情中的用户头像、图标、关注按钮遮挡头部组件
*/
.van-image{
    position: inherit;
}
.van-icon{
    position: inherit;
}
.van-button{
    position: inherit;
}


</style>