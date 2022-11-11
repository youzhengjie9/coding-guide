import Vue from 'vue'
//导入路由插件
import VueRouter from 'vue-router'
//导入进度条nprogress
import NProgress from 'nprogress'
//导入进度条nprogress样式
import 'nprogress/nprogress.css'
//导入layout公共布局页面
import Layout from '../layout/index.vue'

//解决Vue路由重复跳转报错,要放到Vue.use(VueRouter)之前
const routerPush = VueRouter.prototype.push;
VueRouter.prototype.push = function (location) {
    return routerPush.call(this, location).catch(err => {})
};

//安装路由插件
Vue.use(VueRouter);

const router = new VueRouter({
    mode:'history',
    routes: [
      {
        path:'/',
        redirect:'/index', //访问“/”路径时自动重定向到“/dashboard路径”
        // name:'dashboard',
        //Layout我们后台系统的整体布局组件
        component: Layout,
        //子路由，将在layout/index.vue下面的<router-view/>标签展示
        children:[
          {
            path:'/index',
            component: () => import('../views/index/index.vue'),
            meta: {
              index: 0
            }
          },
          {
            path:'/message',
            component: () => import('../views/message/index.vue'),
            meta: {
              index: 1
            }
          },
          {
            path:'/about',
            component: () => import('../views/about/index.vue'),
            meta: {
              index: 2
            }
          },
          {
            path:'/my',
            component: () => import('../views/my/index.vue'),
            meta: {
              index: 3
            }
          }
        ]
      },
      {
        path:'/login',
        component: () => import('../views/login/index.vue')
      },
      {
        path:'/question/detail',
        component: () => import('../views/question/index.vue')
      },
      {
        path:'/search',
        component: () => import('../views/search/index.vue')
      },
      {
        path:'/search/result',
        component: () => import('../views/search/SearchResult.vue')
      },
      {
        //配置404未找到页面路由
        path:'*',
        component: () => import('../views/error-page/404.vue')
      }    
    ]
  });



  

//配置全局路由守卫（进入路由前自动执行）
router.beforeEach((to, from, next) => {
  
  //当进入路由前进度条开启
  NProgress.start()
  
  next();

})
//配置全局路由守卫（退出路由后自动执行）
router.afterEach(() => {

  //当退出路由后进度条关闭
  NProgress.done()
  
})

//导出路由
export default router


