import Vue from 'vue'
//桌面端适配
import '@vant/touch-emulator';
//vant ui
import 'vant/lib/index.css'
//按需引入vant组件
import {
  Button,
  Tabbar,
  TabbarItem,
  Field,
  CellGroup,
  // Notify,
  Divider,
  Dialog,
  NoticeBar,
  NavBar,
  Row,
  Col,
  Search,
  Swipe,
  SwipeItem,
  Tab,
  Tabs,
  Toast,
  List,
  PullRefresh,
  Popup ,
  Cell,
  Icon,
  Image as VanImage,
  Tag,
  Lazyload 
} from 'vant'
//按需使用vant组件
Vue.use(Button);
Vue.use(Tabbar);
Vue.use(TabbarItem);
Vue.use(Field);
Vue.use(CellGroup);
// Vue.use(Notify);
Vue.use(Divider);
Vue.use(Dialog);
Vue.use(NoticeBar);
Vue.use(Row);
Vue.use(Col);
Vue.use(Search);
Vue.use(Tab);
Vue.use(Tabs);
Vue.use(List);
Vue.use(PullRefresh);
Vue.use(Cell);
Vue.use(Icon);
Vue.use(Tag);
Vue.use(Toast);
Vue.use(NavBar);
Vue.use(Swipe);
Vue.use(SwipeItem); 
Vue.use(Lazyload);
Vue.use(VanImage);
Vue.use(Popup);