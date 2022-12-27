<template>
  
  <div class="editData">

    <!-- 头部 -->
    <edit-data-header />

    <!-- 编辑内容 -->
    <edit-data-content />

  </div>


</template>

<script>
import EditDataHeader from '@/components/my/edit-data/Header.vue'
import EditDataContent from '@/components/my/edit-data/Content.vue'
export default {
  name:"EditData",
  components:{
    EditDataHeader,
    EditDataContent
  }
}
</script>

<style scoped>



</style>