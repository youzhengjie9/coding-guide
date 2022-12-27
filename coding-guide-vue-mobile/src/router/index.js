import Vue from 'vue'
//导入路由插件
import VueRouter from 'vue-router'
//导入进度条nprogress
import NProgress from 'nprogress'
//导入进度条nprogress样式
import 'nprogress/nprogress.css'
//导入vuex
import store from '../store'
//导入layout公共布局页面
import Layout from '../layout/index.vue'
import { Toast } from 'vant';

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
        component: Layout,
        //子路由，将在layout/index.vue下面的<router-view/>标签展示
        children:[
          {
            path:'/index',
            name:'index',
            component: () => import('../views/index/index.vue'),
            meta: {
              index: 0,
            }
          },
          {
            path:'/message',
            name:'Message',
            component: () => import('../views/message/index.vue'),
            meta: {
              index: 1
            }
          },
          {
            path:'/about',
            name:'About',
            component: () => import('../views/about/index.vue'),
            meta: {
              index: 2
            }
          },
          {
            path:'/my',
            name:'my',
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
        path:'/register',
        component: () => import('../views/register/index.vue')
      },
      {
        path:'/question/detail',
        name:'QuestionDetail',
        component: () => import('../views/question/index.vue')
      },
      {
        path:'/search',
        name:'Search',
        component: () => import('../views/search/index.vue')
      },
      {
        path:'/search/result',
        name:'SearchResult',
        component: () => import('../views/search/SearchResult.vue')
      },
      {
        path:'/user/card',
        name:'UserCard',
        component: () => import('../views/user-card/index.vue')
      },
      {
        path:'/browse/record',
        name:'BrowseRecord',
        component: () => import('../views/browse-record/index.vue')
      },
      {
        path:'/write/question',
        name:'WriteQuestion',
        component: () => import('../views/write-question/index.vue')
      },
      {
        path:'/my/edit/data',
        name:'EditData',
        component: () => import('../views/my/edit-data/index.vue')
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

  //如果有accessToken
  if(store.state.User.accessToken){
    //并且请求路由路径是登录页则跳转到首页（防止重复登录）
    if (to.path === '/login') {
      next({path: '/'})
    }
    //如果不是登录页
    else {
      //因为vuex的state会随着页面刷新导致丢失（也就是说vuex数据不会持久化），所以存储在vuex的用户信息会丢失
      //所以当每次路由跳转（刷新页面）同时localstorage又存在着accessToken，就要去后端查询当前用户信息并保存到vuex中
      if (store.state.User.userName.length === 0) {
        
        store.dispatch('getCurrentUserInfo').then(res => {
          next({ ...to, replace: true })
        }).catch(() => {
          next({path: '/login'})
        })
      }
      else {
        next();
      }

    }
  }
  //如果没有accessToken
  else{
    //同时如果去的页面不是登录或者注册的话，则打回到登录页面，不允许进入后台系统
    if(to.path !== '/login' && to.path !== '/register'){
      Toast({
        message: '请先登录！',
        position: 'top',
        duration: 1100
      });
      next({path: '/login'})
    }else{ //如果去的页面是登录或者注册页面的话，则放行
      next();
    }
  }

})
//配置全局路由守卫（退出路由后自动执行）
router.afterEach(() => {
  //当退出路由后进度条关闭
  NProgress.done()
})

//导出路由
export default router


