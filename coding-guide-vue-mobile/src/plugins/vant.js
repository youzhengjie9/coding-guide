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
  Form,
  CellGroup,
  Divider,
  Dialog,
  NoticeBar,
  NavBar,
  Row,
  Col,
  Search,
  Swipe,
  SwipeItem,
  Switch,
  Tab,
  Tabs,
  Toast,
  List,
  PullRefresh,
  Popup ,
  Picker ,
  Cell,
  Icon,
  Image as VanImage,
  Tag,
  Lazyload,
  RadioGroup,
  Radio,
  Checkbox,
  CheckboxGroup,
  Uploader 
} from 'vant'
//按需使用vant组件
Vue.use(Button);
Vue.use(Tabbar);
Vue.use(TabbarItem);
Vue.use(Field);
Vue.use(CellGroup);
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
Vue.use(Form);
Vue.use(Radio);
Vue.use(RadioGroup);
Vue.use(Switch);
Vue.use(Picker);
Vue.use(Checkbox);
Vue.use(CheckboxGroup);
Vue.use(Uploader); 