<template>
  <div id="app">
      <nav-bar />
      <!-- <div v-if="!isAuthenticated">
        <router-link to="/">Home</router-link> |
        <router-link to="/login">Login</router-link>
      </div> -->
      <!-- <div v-else>
        <router-link to="/">Home</router-link> |
        <a href="#" @click.prevent="logout">Logout</a>
      </div> -->
    <!-- </div> -->
    <router-view/>
  </div>
</template>
<script>
import router from './router'
import NavBar from '@/components/NavBar.vue'
export default {
  name: 'App',
  components: {
    NavBar,
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