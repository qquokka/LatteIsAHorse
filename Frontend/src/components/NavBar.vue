<template>
  <nav class="navbar navbar-expand-lg sticky-top" @click="this.$router.push('/hangman/is/the/perfect/game/of/the/whole/human/history')" id="topnav">
  <div id="toggler" data-toggle="modal" data-target="#staticBackdrop"> </div>
  <router-link to="/" class="ml-2 row text-decoration-none d-none d-lg-flex" ><img src="../assets/logo_icon.png" style="margin: -30px" width="120px"><h4 class="my-auto ml-3" style="color:#3f3f3f">라떼는말이야</h4> </router-link>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <fa icon="bars" size="2x" />
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item" >
        <p class="nav-link" v-if="!this.$store.getters.isLoggedIn" @click="showmodal">
          <fa icon="key" /> 로그인
        </p>
        <p class="nav-link" @click="logout()" v-else >
          <fa icon="key" /> {{ $session.get('username') }} 님 반갑습니다.
        </p>
      </li>
      <li class="nav-item" >
        <router-link class="nav-link" to="/about"><fa icon="user" /> About Us</router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="/cafe/1"><fa icon="coffee" /> 카페 보기</router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="/map"><fa icon="map" /> 내 주변 보기</router-link>
      </li>
    </ul>
  </div>
  
</nav>

</template>

<script>
import { library } from '@fortawesome/fontawesome-svg-core'
import { faBars, faKey, faUser, faCoffee, faMap, faNewspaper } from '@fortawesome/free-solid-svg-icons'

library.add(faBars, faKey, faUser, faCoffee, faMap, faNewspaper )

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
  showmodal() {
    const modal = document.getElementById('toggler');
    modal.click()
  },
  logout() {
    this.$session.destroy()
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

.fa-bars {
  color: gray
}
@media only screen and (max-width: 991px) {
  #topnav {
    background: rgba(255,255,255,0.7) !important;
    width: fit-content;
    border-radius: 15px;
    justify-content: start;
    text-align: left;
    position: fixed;
  }
}
</style>