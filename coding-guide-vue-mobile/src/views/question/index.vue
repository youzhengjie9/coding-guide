<template>

    <h3>面试题：{{id}}</h3>

</template>

<script>
export default {
    data(){
        return{
            id:'暂无id'
        }
    },
    methods:{
        getId(){
            console.log('面试题id===========')
            this.id=this.$route.query.id
        }
    },
    mounted(){
        this.getId();
    }
}
</script>

<style>

</style>