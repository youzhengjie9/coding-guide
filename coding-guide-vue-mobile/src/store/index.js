import Vue from 'vue'
//导入vuex，实现数据（状态）共享
import Vuex from 'vuex'
//引入模块化的vuex
import TabBar from './modules/tabbar'
import User from './modules/user'
import Question from './modules/question'
import CacheComponents from './modules/cache-components'

//使用vuex插件
Vue.use(Vuex);

export default new Vuex.Store({

    //vuex模块化
    modules:{
        //注册模块
        TabBar,
        User,
        Question,
        CacheComponents,
    }

})
