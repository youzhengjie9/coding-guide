<template>
  
  <div>
    <!-- 遍历面试题 -->
    <div class="iq" 
    v-for="(item,index) in list" 
    :key="index">
      <div class="title">
       
        <router-link class="titleHref" :to = "{ path: '/question/detail', query: { id: item.id } }">
            {{item.title}}
        </router-link>

      </div>

      <!-- 标签栏 -->
      <div>
        <!-- 是否推荐 -->
        <van-tag v-if="item.recommend == 1" type="success" class="tagItem" size="medium">
          推荐
        </van-tag>

        <!-- 标签列表 -->
        <van-tag 
            plain type="primary"
            v-for="(tagName) in item.tagList" 
            :key="tagName"
            class="tagItem"
            size="medium">
            {{tagName}}
        </van-tag>
         
      </div>

      <div>
        
          <span style="font-size: 15px;margin-left: 15px;">发布日期: {{item.publishTime | dateformat('YYYY-MM-DD HH:mm:ss')}}</span>
          <!-- 简单难度 -->
          <van-tag v-if="item.difficulty == 1" type="primary" class="tagItem" size="medium" style="margin-right: 15px;" >
            简单
          </van-tag>

          <!-- 中等难度 -->
          <van-tag v-if="item.difficulty == 2" type="success" class="tagItem" size="medium" style="margin-right: 15px;" >
            中等
          </van-tag>

          <!-- 较难难度 -->
          <van-tag v-if="item.difficulty == 3" type="warning" class="tagItem" size="medium" style="margin-right: 15px;" >
            较难
          </van-tag>

          <!-- 困难难度 -->
          <van-tag v-if="item.difficulty == 4" type="danger" class="tagItem" size="medium" style="margin-right: 15px;" >
            困难
          </van-tag>
      </div>

      <div>
        <van-row gutter="5">
          <van-col span="6" class="foot">
            <van-icon name="eye-o" size="20" />
            <span style="font-size: 15px;">{{item.readCount}}</span>
          </van-col>
          <van-col span="5">
            <van-icon name="like-o" size="20" />
            <span style="font-size: 15px">{{item.likeCount}}</span>
          </van-col>
          <van-col span="5">
            <van-icon name="star-o" size="20" />
            <span style="font-size: 15px">{{item.collectCount}}</span>
          </van-col>

          <van-col span="5">
            <van-icon name="bulb-o" size="20" />
            <span style="font-size: 15px">{{item.meetCount}}</span>
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
  name: "quesionItem",
  props:{
    list:Array
  }
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