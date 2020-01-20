import Vue from 'vue'
import App from './App.vue'
import VueYouTubeEmbed from 'vue-youtube-embed'
Vue.use(VueYouTubeEmbed)
// if you don't want install the component globally
Vue.use(VueYouTubeEmbed, { global: false })
// if you want to install the component globally with a different name
Vue.use(VueYouTubeEmbed, { global: true, componentId: "youtube-media" })
// for bootstrap
import 'expose-loader?$!expose-loader?jQuery!jquery'
import 'popper.js/dist/popper.js'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
