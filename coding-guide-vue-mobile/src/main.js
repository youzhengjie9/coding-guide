import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引入时间格式化插件
import moment from 'moment'
//引入lib-flexiblejs
import 'lib-flexible/flexible.js'
//引入vant插件
import "./plugins/vant.js";
//引入v-md-editor插件
import './plugins/v-md-editor'


//使用vue-router插件
Vue.use(router)



//定义一个全局过滤器实现日期格式化
Vue.filter('dateformat',function (input,fmtstring) {//当input为时间戳时，需转为Number类型
  // 使用momentjs这个日期格式化类库实现日期的格式化功能
  return moment(input).format(fmtstring);
})

Vue.config.productionTip = false

new Vue({
  el:'#app',
  router,
  store,
  render: h => h(App)
})