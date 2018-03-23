import Vue from 'vue'
import Vuex from 'vuex'

import todo from './modules/todo'
import auth from './modules/auth'


Vue.use(Vuex)

export default () =>
  new Vuex.Store({
    modules: {
      todo,
      auth
    }
  })
