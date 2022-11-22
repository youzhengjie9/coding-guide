const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

/* 覆盖webpack的配置 */
module.exports = {
  devServer: { // 自定义服务配置
    port: 5678, //自定义端口号
    //webpack5环境下解决Vue项目内网穿透访问显示Invalid Host header异常（allowedHosts和historyApiFallback）
    allowedHosts: 'all',
		historyApiFallback: true
  },
  lintOnSave: false //关闭es lint检测，不然会出现很多bug

}
