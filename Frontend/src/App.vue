<template>
  <div id="app">
    <modal :loginFailed="loginFailed" @login="login" />
    <router-view :key="$route.fullPath" />
  </div>
</template>
<script>
import axios from "axios";
import Modal from "@/components/Modal.vue";

export default {
  name: "App",
  components: {
    Modal
  },
  data() {
    return {
      loginFailed: false
    };
  },
  methods: {
    logout() {
      this.$session.destroy();
      this.$store.dispatch("logout");
    },
    login(credentials) {
      console.log(credentials);
      axios
        .post(`${this.$store.state.constants.SERVER}/signin`, credentials)
        .then(response => {
          this.loginFailed = false;
          const token = response.data.accessToken;
          this.$session.start();
          this.$session.set("jwt", token);
          this.$session.set("username", response.data.username);
          this.$store.dispatch("login", token);
          this.$store.commit("setToken", token);
          document.querySelector("#modalCloseButton").click();
        })
        .catch(error => {
          if (error.response.data.status === 401) {
            this.loginFailed = !this.loginFailed
            alert(error.response.data)
          }
        })
    }
  },
  mounted() {
    window.addEventListener("scroll", this.onScroll);
    if (localStorage.getItem("vue-session-key")) {
      let stored = JSON.parse(localStorage.getItem("vue-session-key"));
      this.$store.dispatch("login", stored.jwt);
      this.$store.commit("setToken", stored.jwt);
    }
  }
}
</script>
<style>
<<<<<<< HEAD
html {
  overflow-x: hidden;
  min-height: 100vh;
}
=======
>>>>>>> f313ebb77130435cddb6aba1c69247f3518a23ed
#app {
  font-family: 'Roboto', sans-serif;
  text-align: center;
  color: #2c3e50;
  background-color: #ffffff;
}
#nav {
  padding: 30px;
}

.router-link-active {
  color: #42b983 !important;
}

::-webkit-scrollbar {
  width: 7px;
}
::-webkit-scrollbar-track {
  border-radius: 10px;
}

::-webkit-scrollbar-thumb {
  background: violet;
  border-radius: 10px;
}

</style>
