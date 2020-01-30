<template>
  <div id="app">
    <modal @login="login"/>
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
      isAuthenticated: this.$session.has('jwt'),
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
      axios.post(`${this.$store.state.constants.SERVER}/signin`, credentials)
        .then(response => {
            console.log('로그인성공')
            console.log(response.data.token)
            const token = response.data.token
            this.$session.start()
            this.$session.set('jwt', token)
            this.$store.dispatch('login', token)
            router.push('/')
          }).catch(error =>
            console.log(error)
          )
    }
  },
  updated() {
    this.isAuthenticated = this.$session.has('jwt')
  },
  mounted () {
  window.addEventListener('scroll', this.onScroll)
},
}
</script>
<style>
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