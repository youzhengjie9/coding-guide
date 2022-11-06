module.exports = {
  presets: [
    '@vue/cli-plugin-babel/preset'
  ],
  //配置vant自动按需引入组件插件
  plugins: [
    [
      'dynamic-import-webpack'
    ],
    ['import', {
      libraryName: 'vant',
      libraryDirectory: 'es',
      style: true
    }, 'vant']
  ]
}
