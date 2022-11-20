<template>

    <div class="title">
       {{questionTitle}}
    </div>

</template>

<script>
export default {
    name:'QuestionDetailTitle',
    props:{
        questionTitle: String
    }
}
</script>

<style scoped>

.title{
    padding: 1.6667rem 0.4333rem 0.05333rem;
    font-size: 0.6rem;
}

</style>