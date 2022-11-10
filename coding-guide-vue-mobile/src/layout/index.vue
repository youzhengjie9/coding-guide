<template>
  
  <div class="container">

    <!-- <transition :name="move"> -->
      <!-- <keep-alive> -->
          <router-view/>
      <!-- </keep-alive> -->
    <!-- </transition> -->
    
    
    <!-- 底部TabBar -->
    <div class="footer">
        <footer-tab-bar></footer-tab-bar>
    </div>

  </div>

</template>

<script>
import FooterTabBar from '../components/tabbar/FooterTabBar.vue'

export default {
    name:'Layout',
    components:{
      FooterTabBar
    },
    computed:{
      move(){
        return this.$store.state.TabBar.move
      }
    }
}


</script>

<style lang="scss" scoped>

// 加了这个就导致vant的滚动分页一直触发onload方法------------
// .container {
//   flex: 1;
//   overflow: scroll;
// }

.footer {
  background: #f2f2f2;
}


// 路由切换动画
.slide-left-enter-active,
.slide-left-leave-active,
.slide-right-enter-active,
.slide-right-leave-active {
  transition: all 0.4s linear;
}

.slide-left-enter-active,
.slide-right-enter-active {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
}

.slide-right-enter {
  transform: translate(-100%);
}

.slide-right-leave-to {
  transform: translate(100%);
}

.slide-left-enter {
  transform: translate(100%);
}

.slide-left-leave-to {
  transform: translate(-100%);
}
</style>