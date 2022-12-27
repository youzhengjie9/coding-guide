<template>
  
  <div class="editDataHeader">

        <van-nav-bar title="编辑资料" left-arrow @click-left="back">
        </van-nav-bar>
  </div>

</template>

<script>
export default {
    name:'EditDataHeader',
    methods: {
        //返回
        back() {
            this.$router.push({
                path:'/my'
            })
        },
    },
}
</script>

<style lass="scss" scoped>

.editDataHeader{
    position: fixed;
    top: 0;
    width: 100%;
}
.van-nav-bar {
background-color: #2f97ec;
}


.editDataHeader /deep/ .van-nav-bar__title {
color: white;
font-size: 0.45rem;
}

.editDataHeader /deep/ .van-icon {
color: white;
}

</style>