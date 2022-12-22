<template>
  <div class="WriteQuestionSettingFooter">
    <van-cell-group inset>
      <van-cell>
        <van-button
          round
          type="default"
          style="margin-right: 20px; width: 150px"
          @click="saveDraft"
        >
          保存草稿
        </van-button>
        <van-button
          round
          type="danger"
          style="width: 150px"
          @click="publishQuestion"
        >
          发布内容
        </van-button>
      </van-cell>
    </van-cell-group>
  </div>
</template>

<script>
export default {
  name: "WriteQuestionSettingFooter",
  methods: {
    //点击保存草稿按钮
    saveDraft() {
      this.$emit("saveDraft");
    },
    //点击发布内容按钮(发布面试题)
    publishQuestion() {
      this.$emit("publishQuestion");
    },
  },
};
</script>

<style scoped>
.WriteQuestionSettingFooter {
  position: fixed;
  bottom: 0px;
}
</style>