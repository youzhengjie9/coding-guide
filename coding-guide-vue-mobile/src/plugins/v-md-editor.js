import Vue from 'vue';
//配置v-md-editor的预览组件（用于展示）
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
//配置v-md-editor的编辑器组件（用于编写内容）
import VMdEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
//github主题
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';

// highlightjs
import hljs from 'highlight.js';
//插件1：代码行号展示插件
// import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
//插件2：快捷复制代码插件
// import createCopyCodePlugin from '@kangc/v-md-editor/lib/plugins/copy-code/index';
// import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';

//插件3：Emoji 表情插件
// import createEmojiPlugin from '@kangc/v-md-editor/lib/plugins/emoji/index';
// import '@kangc/v-md-editor/lib/plugins/emoji/emoji.css';


//配置v-md-editor的预览组件的主题为github主题
VMdPreview.use(githubTheme, {
  Hljs: hljs,
});
//配置v-md-editor的编辑器组件的主题为github主题
VMdEditor.use(githubTheme, {
  Hljs: hljs,
});


/*
配置插件方式：VMdPreview.use(xxx插件)即可
具体有哪些插件可以看官网：http://ckang1229.gitee.io/vue-markdown-editor/zh/plugins/copy-code.html

*/

//配置插件1：配置代码行号展示插件
// VMdPreview.use(createLineNumbertPlugin())

//配置插件2：快捷复制代码插件
// VMdPreview.use(createCopyCodePlugin())

//配置插件3：Emoji 表情插件
// VMdPreview.use(createEmojiPlugin())


//正式启动v-md-editor的预览组件插件
Vue.use(VMdPreview);
//正式启动v-md-editor的编辑器组件插件
Vue.use(VMdEditor);