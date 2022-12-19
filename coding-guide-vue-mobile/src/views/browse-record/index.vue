<template>
    
    <div>
        <!-- 面试题浏览记录头部 -->
        <browse-record-header></browse-record-header>
        <!-- 面试题浏览记录列表内容 -->
        <browse-record-list></browse-record-list>
        <!-- 回到顶部组件 -->
        <back-to-top></back-to-top>
    </div>
     
</template>

<script>
import BrowseRecordList from "../../components/browse-record/List.vue";
import BrowseRecordHeader from "../../components/browse-record/Header.vue";
import BackToTop from "../../components/common/BackToTop.vue";

export default {
    name:'BrowseRecord',
    components:{
        BrowseRecordList,
        BrowseRecordHeader,
        BackToTop
    }
}
</script>

<style lang="sass" scoped>

</style>