import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueWechatTitle from 'vue-wechat-title'
import Vuetify from 'vuetify'
import ElementUI from 'element-ui';
// import Highlight from '@/plugins/highlight'
import mavonEditor from 'mavon-editor'

import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import 'element-ui/lib/theme-chalk/index.css';
import '@fortawesome/fontawesome-free/css/all.css'

Vue.config.productionTip = false
Vue.use(VueWechatTitle)
Vue.use(mavonEditor)

Vue.use(Vuetify, {
  icons: {
    iconfont: 'fa'
  }
})
Vue.use(ElementUI)
// Vue.use(Highlight)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
