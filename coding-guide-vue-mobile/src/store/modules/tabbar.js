
const state = {
    //点击下面的tabBar跳转页面滑动的方向
    move:'slide-left',
    tabList: [
      {
        title: "首页",
        path: '/index',
        icon: "home-o"
      },
      {
        title: "消息",
        path: '/message',
        icon: "chat-o"
      },
      {
        title: "关于",
        path: '/about',
        icon: "more-o"
      },
      {
        title: "我",
        path: '/my',
        icon: "manager-o"
      }
    ]
  };
  
  const mutations = {
    SET_MOVE(state,move){
      state.move=move;
    }
  };
  
  const actions = {
    setMove(context,move){
      context.commit('SET_MOVE',move)
    }
  };
  
  export default {
    state,
    mutations,
    actions
  };