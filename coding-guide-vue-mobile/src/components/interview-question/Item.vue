<template>

    <div>
        <van-row gutter="20">
            <van-col span="6">
                <van-icon name="eye-o" />
                20000
            </van-col>
            <van-col span="6">span: 6</van-col>
            <van-col span="6">span: 6</van-col>
            <van-col span="6">span: 6</van-col>
        </van-row>
        <!-- 分割线 -->
        <van-divider
            :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }">
        </van-divider>
    </div>

</template>

<script>
export default {
    name:'InterviewQuesionItem'
}
</script>

<style>

</style>