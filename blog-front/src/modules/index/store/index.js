import Vue from 'vue'
import Vuex from 'vuex'
import user from './user'
import getters from './getters'
import permission from './permission'
import app from './app'


Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    app,
    user,
    permission
  },
  getters
})