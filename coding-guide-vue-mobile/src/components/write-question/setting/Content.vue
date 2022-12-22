<template>
  <div class="writeQuestionSettingContent">
    <van-cell-group inset style="margin-top: 20px">
      <!-- 单元格 -->
      <van-cell required>
        <div class="cellTitle">发布形式</div>
        <div class="cellContent">
          <van-radio-group v-model="isPublic" direction="horizontal">
            <van-radio name="1">公开</van-radio>
            <van-radio name="0">私密</van-radio>
          </van-radio-group>
        </div>
      </van-cell>

      <!-- 单元格 -->
      <van-cell required>
        <div class="cellTitle">是否允许评论</div>
        <div class="cellContent">
          <van-switch v-model="allowComment" size="22px" />
        </div>
      </van-cell>

      <!-- 单元格 -->
      <van-cell required>
        <div class="cellTitle" style="width: 80px">题目难度</div>
        <div class="cellContent">
          <input
            type="input"
            :value="difficulty"
            placeholder="点击选择题目难度"
            @click="showDifficultyPopup = true"
            readonly="readonly"
            style="width: 220px; border: 0; line-height: 22px"
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
        <div class="cellTitle" style="width: 80px">题目标签</div>
        <div class="cellContent">
          <input
            type="input"
            :value="selectedTagNameList"
            placeholder="点击选择题目标签"
            @click="showTagsPopup = true"
            readonly="readonly"
            style="width: 220px; border: 0; line-height: 22px"
          />

          <van-popup
            closeable
            close-icon-position="top-left"
            v-model="showTagsPopup"
            position="bottom"
            :style="{ height: '92%' }"
          >
            <!-- 搜索框 -->
            <van-search
              v-model="tagSearchKeyword"
              @input="tagSearchDebounce"
              style="margin-top: 40px; padding: 0rem 0.32rem"
              :maxlength="20"
              :left-icon="''"
              :right-icon="'search'"
              :clear-trigger="'always'"
              placeholder="输入关键词模糊匹配"
            />

            <!-- 模糊搜索出来的内容 -->
            <van-cell style="padding: 0rem 0.42667rem">
              <ul
                class="van-search-list"
                v-if="showTagSearchList"
                style="border: 1px darkgray solid; border-radius: 0.10333rem"
              >
                <li
                  class="van-search-item"
                  v-for="tagName in tagSearchNameList"
                  :key="tagName"
                  @click="selectSearchTag(tagName)"
                  v-html="tagName"
                ></li>
              </ul>
            </van-cell>

            <!-- 已选标签 -->
            <van-cell
              title="已选标签"
              title-style="font-weight:bold"
            ></van-cell>

            <!-- 已选标签数据展示 -->

            <van-cell>
              <van-tag
                closeable
                size="medium"
                type="primary"
                style="margin-right: 10px"
                v-for="tagName in selectedTagNameList"
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
              <van-checkbox-group
                v-model="selectedTagNameList"
                :max="3"
                :icon-size="22"
              >
                <van-checkbox name="Java" style="margin-bottom: 5px">
                  <span style="font-size: 17px">Java</span>
                </van-checkbox>
                <van-checkbox name="SpringBoot" style="margin-bottom: 5px">
                  <span style="font-size: 17px">SpringBoot</span>
                </van-checkbox>
                <van-checkbox name="SpringCloud" style="margin-bottom: 5px">
                  <span style="font-size: 17px">SpringCloud</span>
                </van-checkbox>
                <van-checkbox name="前端" style="margin-bottom: 5px">
                  <span style="font-size: 17px">前端</span>
                </van-checkbox>
                <van-checkbox name="运维" style="margin-bottom: 5px">
                  <span style="font-size: 17px">运维</span>
                </van-checkbox>
                <van-checkbox name="ElasticSearch" style="margin-bottom: 5px">
                  <span style="font-size: 17px">ElasticSearch</span>
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
import { Toast } from "vant";
import { searchTagNameByKeywordAndLimit } from "@/api/tag";
export default {
  name: "writeQuestionSettingContent",
  data() {
    return {
      //文章是否公开（0代表私密、1代表公开）
      isPublic: "1",
      //是否允许评论
      allowComment: true,
      //题目难度名称
      difficultyColumns: ["简单", "中等", "较难", "困难"],
      //难度选择器popup展示状态
      showDifficultyPopup: false,
      //被选择出来的题目难度（分为简单、中等、较难、困难）
      difficulty: "",
      //被选择出来的标签名称集合
      selectedTagNameList: [],
      //标签选择popup展示状态
      showTagsPopup: false,
      //标签搜索框中输入的关键字(内容)
      tagSearchKeyword: "",
      //根据标签搜索框中输入的内容“模糊搜索”出来的tag名称集合
      tagSearchNameList: [],
      //控制是否显示标签搜索框中输入的内容“模糊搜索”出来的列表集合(tagSearchList)
      showTagSearchList: false,
    };
  },
  methods: {
    //当选择难度成功后的回调
    difficultyPickSuccess(difficulty) {
      this.difficulty = difficulty;
      this.showDifficultyPopup = false;
    },
    //点击删除/关闭已选标签
    closeTag(tagName) {
      for (let i = 0; i < this.selectedTagNameList.length; i++) {
        if (this.selectedTagNameList[i] == tagName) {
          this.selectedTagNameList.splice(i, 1);
          break;
        }
      }
    },
    //防抖方法
    debounce(fn, delay) {
      let t = null;
      return function () {
        if (t !== null) {
          clearTimeout(t);
        }
        t = setTimeout(() => {
          fn();
        }, delay);
      };
    },
    //防抖的标签搜索方法，当标签搜索框输入内容时，会立即触发此方法
    //本质上这个方法里面还是调用了tagSearch方法进行标签搜索。
    tagSearchDebounce() {
      this.debounce(this.tagSearch(), 1000);
    },
    //真正的标签搜索方法，由tagSearchDebounce方法所调用
    //因为本身这个tagSearch方法是没有防抖的，需要tagSearchDebounce方法进行增强。
    tagSearch() {
      searchTagNameByKeywordAndLimit(1, 5, this.tagSearchKeyword).then(
        (res) => {
          if (res.data.code === 200) {
            this.tagSearchNameList = res.data.data;
            if (this.tagSearchNameList && this.tagSearchNameList.length > 0) {
              this.showTagSearchList = true;
            } else {
              this.showTagSearchList = false;
            }
          }
        }
      );
    },
    //当点击模糊搜索出来的标签项的回调方法
    selectSearchTag(tagName) {
      //清空搜索框内容
      this.tagSearchKeyword = "";
      //将所点击的tag名称放入selectedTagNameList数组中
      //首先要判断selectedTagNameList中有没有相同的元素，没有才能添加进去
      if (this.selectedTagNameList.length == 0) {
        this.selectedTagNameList.push(tagName);
      } else {
        let canPush = 1; //标志位，判断是否可以添加元素，默认是1代表可以添加元素
        for (let i = 0; i < this.selectedTagNameList.length; i++) {
          //如果selectedTagNameList存在相同的元素则不允许添加
          if (this.selectedTagNameList[i] == tagName) {
            canPush = 0; //说明不能添加
            break;
          }
        }
        //如果canPush==1则可以添加
        if (canPush == 1) {
          this.selectedTagNameList.push(tagName);
        } else {
          Toast.fail("您已选择了该标签,不能再次进行选择");
        }
      }
      //关闭模糊搜索结果列表
      this.showTagSearchList = false;
    },
  },
};
</script>

<style scoped>
.cellTitle {
  width: 110px;
  height: 21px;
  float: left;
  font-size: 15px;
  line-height: 20px;
}
</style>