# coding-guide-vue-mobile

## 基本插件安装

### 安装vue-router
```
npm i vue-router@3
```

### 安装移动端组件vant2（vue2对应的一定要是vant2）

```
npm i vant@latest-v2 -S
```

### 安装sass

```
npm install sass@1.26.8 --save-dev
```

```
npm install sass-loader@8.0.2 --save-dev
```


### 安装和配置vant自动按需引入组件插件

```
npm i babel-plugin-import -D
```

- 使用 babel7可以在 babel.config.js 中配置

```
module.exports = {
  plugins: [
    ['import', {
      libraryName: 'vant',
      libraryDirectory: 'es',
      style: true
    }, 'vant']
  ]
};
```




### 安装vuex
```
npm i vuex@3
```

### 安装axios和vue-axios
```
npm install --save axios vue-axios
```


### 安装less
```
npm i less less-loader@7
```

### 安装vue-fragment
```
npm install vue-fragment --save
```

### 安装nprogress进度条的插件
```
npm i -S nprogress
```

### 安装echarts 
```
npm install echarts --save
```


### 安装vue2的Markdown编辑器（v-md-editor）

```
npm i @kangc/v-md-editor -S
```

### 安装vant插件,包含我国所有省市区数据

```
npm i @vant/area-data
```




## 遇到的部分问题

### this.$router.push跳转当前页面不刷新问题

可以加入下面这段代码：（缺点就是会有一瞬间白屏）
```js
this.$router.go(0) 
```



## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
