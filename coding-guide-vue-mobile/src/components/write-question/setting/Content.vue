<template>
  <div class="writeQuestionSettingContent">
     
    <van-cell-group inset style="margin-top:20px">
      <!-- 单元格 -->
      <van-cell required>
          <div class="cellTitle">
            发布形式
          </div>
          <div class="cellContent">
            <van-radio-group v-model="isPublic" direction="horizontal">
              <van-radio name="1">公开</van-radio>
              <van-radio name="0">私密</van-radio>
            </van-radio-group>
          </div>
      </van-cell>

      <!-- 单元格 -->
      <van-cell required>
          <div class="cellTitle">
            是否允许评论
          </div>
          <div class="cellContent">
            <van-switch v-model="allowComment" size="22px"/>
          </div>
      </van-cell>

      <!-- 单元格 -->
      <van-cell required>
          <div class="cellTitle" style="width:80px">
            题目难度
          </div>
          <div class="cellContent">
             
            <input
            type="input"
            :value="difficulty"
            placeholder="点击选择题目难度"
            @click="showDifficultyPopup = true"
            readonly="readonly"
            style="width:220px;border: 0;line-height: 22px;"
            />
            
            <van-popup v-model="showDifficultyPopup" position="bottom">
                <van-picker
                  title="题目难度选择"
                  show-toolbar
                  :columns="difficultyColumns"
                  @confirm="difficultyPickSuccess"
                  @cancel="showDifficultyPopup = false"
                />
            </van-popup>
    
          </div>
      </van-cell>


      <!-- 单元格 -->
      <van-cell required>
          <div class="cellTitle" style="width:80px">
            题目标签
          </div>
          <div class="cellContent">
             
            <input
            type="input"
            :value="tags"
            placeholder="点击选择题目标签"
            @click="showTagsPopup = true"
            readonly="readonly"
            style="width:220px;border: 0;line-height: 22px;"
            />
            
            <van-popup v-model="showTagsPopup" position="bottom" :style="{ height: '92%' }">
                
              <van-search placeholder="输入关键词模糊匹配" />


              <!-- 已选标签 -->
              <van-cell title="已选标签" title-style="font-weight:bold"></van-cell>

              <!-- 已选标签数据展示 -->

              <van-cell>
                  <van-tag
                    closeable
                    size="medium" 
                    type="primary"
                    style="margin-right:10px"
                    v-for="tagName in tags" 
                    :key="tagName"
                    @close="closeTag(tagName)"
                    >
                    {{ tagName }}
                  </van-tag>
              </van-cell>
              


              <!-- 为你推荐 -->
              <van-cell title="为你推荐" title-style="font-weight:bold">
                  <template #right-icon>
                    换一换<i class="iconfont icon-weibiaoti--"></i>
                  </template>
              </van-cell>

              <!-- 为你推荐数据展示 -->
              <van-cell>
                <!-- :max=“3”代表最多只能选择3个 -->
                  <van-checkbox-group v-model="tags" :max="3" :icon-size="22">
                      <van-checkbox name="Java" style="margin-bottom:5px">
                        <span style="font-size:17px">Java</span>
                      </van-checkbox>
                      <van-checkbox name="SpringBoot" style="margin-bottom:5px">
                        <span style="font-size:17px">SpringBoot</span>
                      </van-checkbox>
                      <van-checkbox name="SpringCloud" style="margin-bottom:5px">
                        <span style="font-size:17px">SpringCloud</span>
                      </van-checkbox>
                      <van-checkbox name="前端" style="margin-bottom:5px">
                        <span style="font-size:17px">前端</span>
                      </van-checkbox>
                      <van-checkbox name="运维" style="margin-bottom:5px">
                        <span style="font-size:17px">运维</span>
                      </van-checkbox>
                      <van-checkbox name="ElasticSearch" style="margin-bottom:5px">
                        <span style="font-size:17px">ElasticSearch</span>
                      </van-checkbox>
                  </van-checkbox-group>
              </van-cell>
              
            </van-popup>
    
          </div>
      </van-cell>

    </van-cell-group>
     
  </div>
</template>

<script>
export default {
  name: "writeQuestionSettingContent",
  data() {
    return {
      //文章是否公开（0代表私密、1代表公开）
      isPublic: '1',
      //是否允许评论
      allowComment:true,
      //题目难度名称
      difficultyColumns: ['简单', '中等', '较难', '困难'],
      //难度选择器popup展示状态
      showDifficultyPopup: false,
      //选择出来的题目难度（分为简单、中等、较难、困难）
      difficulty: '',
      //选择出来的标签
      tags: [],
      //标签选择popup展示状态
      showTagsPopup: false,

    };
  },
  methods:{
    //当选择难度成功后的回调
    difficultyPickSuccess(difficulty) {
      this.difficulty = difficulty;
      this.showDifficultyPopup = false;
    },
    //点击删除/关闭已选标签
    closeTag(tagName){
      for(let i = 0 ; i< this.tags.length ;i++){
        if(this.tags[i] == tagName){
          this.tags.splice(i, 1);
          break;
        }
      }
    }
    
  }
};
</script>

<style scoped>

.cellTitle{

  width: 110px;
  height: 21px;
  float: left;
  font-size: 15px;
  line-height: 20px;

}

</style>