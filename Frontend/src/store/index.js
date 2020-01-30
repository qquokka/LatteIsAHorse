import Vue from 'vue'
import Vuex from 'vuex'
import auth from './modules/auth'
import constants from './modules/constants'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    auth,
    constants
  }
  
})