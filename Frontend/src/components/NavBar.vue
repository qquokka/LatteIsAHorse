<template>
  <nav class="navbar navbar-expand-lg sticky-top" id="topnav">
  <div id="toggler" data-toggle="modal" data-target="#staticBackdrop"> </div>
  <router-link to="/" class="ml-2 row text-decoration-none d-none d-lg-flex" ><img src="../assets/logo_icon.png" style="margin: -30px" width="120px"><h4 class="my-auto ml-3" style="color:#3f3f3f">라떼는말이야</h4> </router-link>
  <div class="navbar-collapse" id="navbarNavDropdown">
    <div class="row">
      <div class="col d-lg-none" >
        <div class="nav-link" >
          <fa icon="home" size="2x" />
          <p class="nav-text">홈</p>
          <p class="nav-mobile-text">HOME</p>
        </div>
      </div>
      <div class="col" >
        <div class="nav-link" v-if="!this.$store.getters.isLoggedIn" @click="showmodal">
          <fa icon="key" size="2x" />
          <p class="nav-text">로그인</p>
        </div>
        <div class="nav-link" v-else @click="goToUserPage">
          <fa icon="user-lock" size="2x" />
          <p class="nav-text">{{ uname() }}님</p>
        </div>
      </div>
      <div class="col">
        <div class="nav-link" v-if="this.$store.getters.isLoggedIn" @click="logout()">
          <fa icon="key" size="2x" />
          <p class="nav-text">로그아웃</p>
        </div>
      </div>
      <div class="col" >
        <router-link class="nav-link" to="/about"><fa icon="horse" size="2x" /><p class="nav-text">About</p></router-link>
      </div>
      <div class="col d-none d-lg-block">
        <router-link class="nav-link" to="/cafe/1"><fa icon="coffee" size="2x" /><p class="nav-text">카페보기</p></router-link>
      </div>
      <div class="col">
        <router-link class="nav-link" to="/map"><fa icon="map" size="2x" /><p class="nav-text">내 주변 보기</p></router-link>
      </div>

    </div>
  </div>
  
</nav>

</template>

<script>
import { library } from '@fortawesome/fontawesome-svg-core'
import { faBars, faKey, faUser, faCoffee, faMap, faNewspaper, faUserLock, faHorse } from '@fortawesome/free-solid-svg-icons'

library.add(faBars, faKey, faUser, faCoffee, faMap, faNewspaper, faUserLock, faHorse )

export default {
  
  data () {
  return {
    scrolled: false,
  } 
},
props: {
        iswhite: {
          type: Boolean,
          default: false
        }
},
computed: {
  isLogged() {
    return this.$store.getters.isLoggedIn
  }
},
methods: {
  goToUserPage() {
    this.$router.push('/profile')
  },
  showmodal() {
    const modal = document.getElementById('toggler');
    modal.click()
  },
  uname() {
    return this.$session.get('username')
  },
  logout() {
    if(this.$session.exists()){
      this.$session.destroy()
    }
    this.$store.dispatch('logout')
  },
  handleScroll() {
    if (this.iswhite) {
      this.scrolled = true
    } else {
      this.scrolled = window.scrollY > 0
    }
    const topnav = document.getElementById('topnav')
    if (this.scrolled) {
      topnav.style.background = "whitesmoke"
      topnav.style.boxShadow = "2px 2px 12px gray"
    }
    else {
      topnav.style.background = "none"
      topnav.style.backgroundImage = "unset"
      topnav.style.boxShadow = "0px 0px 0px"
    }
  },
},
created() {
  window.addEventListener('scroll', this.handleScroll);
},
destroyed() {
  window.removeEventListener('scroll', this.handleScroll);
},
}
</script>

<style>
nav {
  transition: 0.25s ease-in-out;
  top: 0;
}
i {
  color: inherit !important;
}
.nav-link {
  margin:auto;
  cursor:pointer;
  transition: 0.25s ease-in-out;
  position:relative
}
.nav-link:hover:after {
  width: 100%;
  left: 0;
}
.nav-link:after {
  background: none repeat scroll 0 0 transparent;
  bottom: 0;
  content: "";
  display: block;
  height: 3px;
  right: 0;
  position: absolute;
  background: violet;
  transition: width 0.15s ease-in 0s, right 0.3s ease 0s;
  width: 0;
}
@media only screen and (max-width: 991px) {
  #topnav {
    border-top: 1px solid lightgray;
    background: white !important;
    width: 100%;
    justify-content: start;
    text-align: left;
    position: fixed;
    bottom: 0;
    height: 61px;
    margin-top: auto;
    /* box-shadow:  !important; */
  }
  .nav-text {
    font-size: 0;
  }
  .nav-mobile-text {
    color: black;
    font-size: 2em;
  }
}
</style>