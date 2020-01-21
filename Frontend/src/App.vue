<template>
  <div id="app">
      <nav-bar />
      <login-form   @login-event="login"/>
      <!-- <div v-if="!isAuthentic
      ated">
        <router-link to="/">Home</router-link> |
        <router-link to="/login">Login</router-link>
      </div> -->
      <!-- <div v-else>
        <router-link to="/">Home</router-link> |
        <a href="#" @click.prevent="logout">Logout</a>
      </div> -->
    <!-- </div> -->
    <router-view />
  </div>
</template>
<script>
import axios from 'axios'
import router from './router'
import NavBar from '@/components/NavBar.vue'
import LoginForm from '@/components/LoginForm.vue'
export default {
  name: 'App',
  components: {
    NavBar,
    LoginForm,
  },
  data() {
    return {
      isAuthenticated: this.$session.has('jwt')
    }
  },
  methods: {
    logout() {
      this.$session.destroy()
      this.$store.dispatch('logout')
      router.push('/login')
    },
    login(credentials) {
      console.log(credentials)
      axios.post('http://127.0.0.1:8000/api-token-auth/', credentials)
        .then(response => {
            console.log('로그인성공')
            console.log(response.data.token)
            const token = response.data.token
            this.$session.start()
            this.$session.set('jwt', token)
            this.$store.dispatch('login', token)
            router.push('/')
          })
    }   
  },
  updated() {
    this.isAuthenticated = this.$session.has('jwt')
  }
}
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #42b983;
}
</style>