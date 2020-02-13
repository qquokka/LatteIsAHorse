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
    Modal,
  },
  data() {
    return {
      loginFailed: false,
      fullPage: true
    };
  },
  methods: {
    logout() {
      this.$session.destroy();
      this.$store.dispatch("logout");
    },
    login(credentials) {
      axios
        .post(`${this.$store.state.constants.SERVER}/signin`, credentials)
        .then(response => {
          this.loginFailed = false;
          const token = response.data.accessToken;
          this.$session.start();
          this.$session.set("jwt", token);
          this.$session.set("username", response.data.username);
          this.$session.set("expire", Date.now());
          //sh 추가
          this.$session.set("roles", response.data.roles)
          this.$store.dispatch("login", token);
          this.$store.commit("setToken", token);
          console.log(response.data)
          document.querySelector("#modalCloseButton").click();
        })
        .catch(error => {
          console.log(error)
          // if (error.response.data.status === 401) {
          //   this.loginFailed = !this.loginFailed;
          //   alert(error.response.data);
          // }
        });
    }
  },
  mounted() {
    window.addEventListener("scroll", this.onScroll);
    if (localStorage.getItem("vue-session-key")) {
      let stored = JSON.parse(localStorage.getItem("vue-session-key"));
      this.$store.dispatch("login", stored.jwt);
      this.$store.commit("setToken", stored.jwt);
    }
    setInterval(() => {
      if(this.$session.exists("jwt")){
        if (Date.now() - this.$session.getItem("expire") > 3600000) {
          this.logout()
        }
      }
    }, 60000);
  }
};
</script>
<style>
@import url("https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&display=swap&subset=korean");
html {
  overflow-x: hidden;
  min-height: 100vh;
}
#app {
  font-family: "Nanum Gothic", sans-serif;
  text-align: center;
  color: #5f4f5f;
  font-weight: 600;
  background-color: #ffffff;
}
#nav {
  padding: 30px;
}
a,
.router-link {
  text-decoration: none !important;
  color: #5f4f5f !important;
}
a:hover,
.router-link:hover {
  color: #6f4f5f !important;
}

.router-link-active {
  color: #42b983 !important;
}

::-webkit-scrollbar {
  width: 5px;
}
::-webkit-scrollbar-track {
  border-radius: 10px;
}

::-webkit-scrollbar-thumb {
  background: violet;
  border-radius: 10px;
}
</style>
