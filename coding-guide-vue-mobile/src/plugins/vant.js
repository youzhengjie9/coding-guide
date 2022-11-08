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
  Notify,
  Divider,
  NoticeBar,
  Row,
  Col,
  Search,
  Tab,
  Tabs,
  List,
  PullRefresh,
  Cell,
  Icon,
  Tag
} from 'vant'
//按需使用vant组件
Vue.use(Button);
Vue.use(Tabbar);
Vue.use(TabbarItem);
Vue.use(Field);
Vue.use(CellGroup);
Vue.use(Notify);
Vue.use(Divider);
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