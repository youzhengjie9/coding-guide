const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

/* 覆盖webpack的配置 */
module.exports = {
  devServer: { // 自定义服务配置
    port: 5678 //自定义端口号
  },
  lintOnSave: false //关闭es lint检测，不然会出现很多bug

}
