
import Vue from 'vue'
import App from './App'

import router from './router'
import store from './store'

import lodash from 'lodash'

import ElementUI from 'element-ui'
import '@/assets/theme/index.css'

import '@/assets/icon/iconfont.css'

import {formatTime} from "./utils/time";
import BaiduMap from 'vue-baidu-map';
import $ from 'jquery';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min'


Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(BaiduMap, {
  ak: 'k8FwPDpAMU82EQjFdXy1uGeegXGL00dh'
})

Object.defineProperty(Vue.prototype, '$_', { value: lodash })


Vue.directive('title',  function (el, binding) {
  document.title = el.dataset.title
})
// 格式话时间
Vue.filter('format', formatTime)

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
