import Vue from 'vue'
import Vuex from 'vuex'
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.css'
import 'vue-material/dist/theme/default.css'

import todo from './modules/todo'

Vue.use(Vuex)
Vue.use(VueMaterial)

export default () =>
  new Vuex.Store({
    modules: {
      todo
    }
  })
