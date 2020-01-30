import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'expose-loader?$!expose-loader?jQuery!jquery'
import 'popper.js/dist/popper.js'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import VueSession from 'vue-session'
import store from './store' // vuex

Vue.config.productionTip = false
Vue.use(VueSession,{persist: true})

new Vue({
  router,
  store, // vuex
  render: h => h(App)
}).$mount('#app')