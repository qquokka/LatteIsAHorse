<template>
  <div id="app">
    <modal :loginFailed="loginFailed" @login="login"/>
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
// import LoginForm from '@/components/LoginForm.vue'
import Modal from '@/components/Modal.vue'

export default {
  name: 'App',
  components: {
    // LoginForm,
    Modal
  },
  data() {
    return {
      loginFailed: false
    }
  },
  methods: {
    logout() {
      this.$session.destroy()
      this.$store.dispatch('logout')
      // console.log('로그아웃 성공')
    },
    login(credentials) {
      console.log(credentials)
      axios.post(`${this.$store.state.constants.SERVER}/signin`, credentials)
        .then(response => {
            this.loginFailed = false
            // console.log('로그인성공')
            const token = response.data.accessToken
            this.$session.start()
            this.$session.set('jwt', token)
            this.$store.dispatch('login', token)
            this.$store.commit('setToken', token)
            document.querySelector('#modalCloseButton').click()
          }).catch(error =>{
            if (error.response.data.status === 401) {
              this.loginFailed = !this.loginFailed
            }
          }
          )
    }
  },
  mounted () {
    window.addEventListener('scroll', this.onScroll)
    if  (localStorage.getItem('vue-session-key')) {
      let stored = JSON.parse(localStorage.getItem('vue-session-key'))
      this.$store.dispatch('login', stored.jwt)
      this.$store.commit('setToken', stored.jwt)
    }
    },
}
</script>
<style>
html {
  font-family: “Roboto”, sans-serif;
}
#app {
  font-family: 'Roboto', sans-serif;
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



/* width */
::-webkit-scrollbar {
  width: 5px;
}

/* Track */
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px grey;
  border-radius: 10px;
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: #42b983;
  border-radius: 10px;
}
</style>