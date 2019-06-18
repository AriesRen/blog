import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueWechatTitle from 'vue-wechat-title'
import Vuetify from 'vuetify'
import ElementUI from 'element-ui';
import Gt from '@/plugins/gt'
import VueLazyload from 'vue-lazyload'
import animate from 'animate.css'

import *  as filters from '@/filters'

import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import './registerServiceWorker'
import 'element-ui/lib/theme-chalk/index.css';
import '@fortawesome/fontawesome-free/css/all.css'
import './router/permission'

Vue.config.productionTip = false
Vue.use(VueWechatTitle)
Vue.use(VueLazyload)
Vue.use(animate)

Vue.use(Vuetify, {
  icons: {
    iconfont: 'fa'
  }
})
Vue.use(ElementUI)
Vue.use(Gt)

Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
