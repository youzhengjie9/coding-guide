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

<style scope>

.title{
    padding: 0.22667rem 0.45333rem 0.05333rem;
    font-size: 0.6rem;
}

</style>