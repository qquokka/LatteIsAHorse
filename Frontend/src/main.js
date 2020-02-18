import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'expose-loader?$!expose-loader?jQuery!jquery'
import 'popper.js/dist/popper.js'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import VueSession from 'vue-session'
import store from './store' // vuex
import * as VueGoogleMaps from 'vue2-google-maps'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import './registerServiceWorker'
import ImageUploader from "vue-image-upload-resize";

Vue.component('fa', FontAwesomeIcon)
Vue.config.productionTip = false
Vue.use(VueSession,{persist: true})
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyCQYe6AWVGlHjJ--FzqH70T2lypvg0Qo00',
    libraries: 'places,drawing,visualization'
  }
})
Vue.use(ImageUploader);

Vue.config.productionTip = false;

new Vue({
  router,
  store, // vuex
  render: h => h(App)
}).$mount('#app')