<template>
  
  <div>
    <!-- 面试题 -->
    <div class="iq">
      <div class="title">
       
        <router-link class="titleHref" :to = "{ path: '/question/detail', query: { id: '5612333123' } }">
            【JAVA基础-集合】Java中的HashMap的底层原理实现、工作原理?
        </router-link>

      </div>

      <!-- 标签栏 -->
      <div>
        <van-tag type="success" class="tagItem" size="medium">推荐</van-tag>
        <van-tag plain type="primary" class="tagItem" size="medium"
          >Java</van-tag
        >
        <van-tag plain type="primary" class="tagItem" size="medium"
          >后端</van-tag
        >
        <van-tag plain type="primary" class="tagItem" size="medium"
          >SpringBoot</van-tag
        >
      </div>

      <div>
        <!-- 简单难度 -->
        <!-- <van-tag type="primary" class="tagItem" size="medium" style="margin-right: 15px;" >
          简单
        </van-tag> -->

        <!-- 中等难度 -->
        <!-- <van-tag type="success" class="tagItem" size="medium" style="margin-right: 15px;" >
          中等
        </van-tag> -->

        <!-- 较难难度 -->
        <!-- <van-tag type="warning" class="tagItem" size="medium" style="margin-right: 15px;" >
          较难
        </van-tag> -->

        <!-- 困难难度 -->
        <van-tag type="danger" class="tagItem" size="medium" style="margin-right: 15px;" >
          困难
        </van-tag>

        <span style="font-size: 15px;">发布日期: 2022-10-20 23:15:22</span>
      </div>

      <div>
        <van-row gutter="5">
          <van-col span="6" class="foot">
            <van-icon name="eye-o" size="20" />
            <span style="font-size: 15px;">20000000</span>
          </van-col>
          <van-col span="5">
            <van-icon name="like-o" size="20" />
            <span style="font-size: 15px">302312</span>
          </van-col>
          <van-col span="5">
            <van-icon name="star-o" size="20" />
            <span style="font-size: 15px">351000</span>
          </van-col>

          <van-col span="5">
            <van-icon name="bulb-o" size="20" />
            <span style="font-size: 15px">999990</span>
          </van-col>
          
        </van-row>
      </div>

      <!-- 分割线 -->
      <van-divider
        :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
      >
      </van-divider>
    </div>

    
  </div>
</template>

<script>
export default {
  name: "quesionItem"
};
</script>

<style scope>

.titleHref{
    font-size: 15px;
    font-weight:bold;
    color: black;
}

.tagItem {
  margin-left: 15px;
  margin-bottom: 10px;
}

.title {
  height: auto;  
  margin-left: 15px;
  margin-bottom: 10px;
  margin-top: 15px;

  overflow:hidden;
  text-overflow:ellipsis;
  display:-webkit-box;
  -webkit-box-orient:vertical;
  -webkit-line-clamp:4; 

}

.foot {
  margin-left: 15px;
}
</style>