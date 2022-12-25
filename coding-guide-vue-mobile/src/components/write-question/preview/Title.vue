<template>

    <div class="writeQuestionPreviewTitle">
       {{questionTitle}}
    </div>

</template>

<script>
export default {
    name:'WriteQuestionPreviewTitle',
    props:{
        questionTitle: String
    }
}
</script>

<style scoped>

.writeQuestionPreviewTitle{
    padding: 1.6667rem 0.4333rem 0.05333rem;
    font-size: 0.6rem;
}

</style>