<template>

    <div class="swipe">

        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item>
                <img src="../../assets/swipe/01.png" style="width: 390px;height:169px;object-fit: cover;" />
            </van-swipe-item>
            <van-swipe-item>
                <img src="../../assets/swipe/02.png" style="width: 390px;height:169px;object-fit: cover;" />
            </van-swipe-item>
            <van-swipe-item>
                <img src="../../assets/swipe/03.png" style="width: 390px;height:169px;object-fit: cover;" />
            </van-swipe-item>
            <van-swipe-item>
                <img src="../../assets/swipe/04.png" style="width: 390px;height:169px;object-fit: cover;" />
            </van-swipe-item>
        </van-swipe>

    </div>


</template>

<script>
export default {
    name:'Swipe',
    data() {
    return {
      images: [
        'https://img01.yzcdn.cn/vant/apple-1.jpg',
        'https://img01.yzcdn.cn/vant/apple-2.jpg',
      ],
    };
  },
}
</script>

<style scope>
 .my-swipe .van-swipe-item {
    color: #fff;
    font-size: 20px;
    line-height: 150px;
    text-align: center;
    background-color: #39a9ed;
    width: 390px;
    height:160px;
  }
</style>