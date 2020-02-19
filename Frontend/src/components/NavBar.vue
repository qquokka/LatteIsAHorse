<template>
  <nav class="navbar navbar-expand-lg sticky-top" id="topnav">
  <div id="toggler" data-toggle="modal" data-target="#staticBackdrop"> </div>
  <router-link to="/" class="ml-2 row text-decoration-none d-none d-lg-flex" ><img :src="require('../assets/navlogo.png')" style="margin: -30px" width="120px"><h4 class="mt-auto ml-3" style="font-size: 1.5vw;font-family:cursive !important">라떼는말이야</h4> </router-link>
  <div class="navbar-collapse" id="navbarNavDropdown">
    <div class="row ml-auto mr-1">
      <div class="col nav-col d-lg-none" >
        <router-link class="p-0 m-0" to="/">
        <div class="nav-link p-0" >
          <div><fa icon="home"  :size="navfasize" /></div>
          
          <p class="nav-text">홈</p>
          <p class="nav-mobile-text">HOME</p>
        </div>
        </router-link>
      </div>
      <div class="col nav-col" >
        <div class="nav-link" v-if="!this.$store.getters.isLoggedIn" @click="showmodal">
          <div><fa icon="key"  :size="navfasize" /></div>
          <div class="nav-text">로그인</div>
          <div class="nav-mobile-text">LOGIN</div>
        </div>
        <div class="nav-link" v-else @click="goToUserPage">
          <div><fa icon="user-lock"  :size="navfasize" /></div>
          <div class="nav-text">{{ uname() }}님</div>
          <div class="nav-mobile-text">MYPAGE</div>
        </div>
      </div>
      <div class="col nav-col" v-if="this.$store.getters.isLoggedIn">
        <div class="nav-link" @click="logout()">
          <div><fa icon="key"  :size="navfasize" /></div>
          <div class="nav-text">로그아웃</div>
          <div class="nav-mobile-text">LOGOUT</div>
        </div>
      </div>
      <div class="col nav-col" >
        <router-link class="nav-link" to="/about">
          <div><fa icon="horse"  :size="navfasize" /></div>
          <div class="nav-text">About</div>
          <div class="nav-mobile-text">HORSE</div>
        </router-link>
      </div>
      <div class="col nav-col d-none d-lg-block">
        <router-link class="nav-link" to="/cafe/1">
          <div><fa icon="coffee"  :size="navfasize" /></div>
          <div class="nav-text">카페보기</div>
        </router-link>
      </div>
      <div class="col nav-col">
        <router-link class="nav-link" to="/map">
          <div><fa icon="map"  :size="navfasize" /></div>
          <div class="nav-text">주변보기</div>
          <div class="nav-mobile-text">NEAR</div>
        </router-link>
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
    navfasize: "sm",
  } 
},
props: {
        bgcolor: {
          type: String,
          default: '#FAF9F9'
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
    this.scrolled = window.scrollY > 0
    const topnav = document.getElementById('topnav')
    if (this.scrolled) {
      topnav.style.background = "white"
    }
    else {
      topnav.style.background = this.bgcolor
      topnav.style.backgroundImage = "unset"
    }
  },
  handleWidth() {
    if (window.innerWidth < 991) {
      this.navfasize = "2x"
    } else {
      this.navfasize = "sm"
    }
    
  }
},
created() {
  window.addEventListener('scroll', this.handleScroll);
  window.addEventListener('resize', this.handleWidth);

},
mounted() {
  this.handleWidth()
  this.handleScroll()
},
destroyed() {
  window.removeEventListener('scroll', this.handleScroll);
  window.addEventListener('resize', this.handleWidth);
},
}
</script>
<style>
nav {
  transition: 250ms;
}
#navbarNavDropdown > .row {
  min-width: fit-content;
}
.nav-col {
  padding: 0 !important;
  min-width: 8vw;
}
.nav-col > .nav-link {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 1rem 0;
}

.nav-link {
  margin:auto;
  color: inherit !important;
  cursor:pointer;
  transition: 0.25s ease-in-out;
  position:relative;
  padding: 0;
}
.nav-link:hover:after {
  color: goldenrod !important;
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
  background: #BEE3DB;
  transition: width 0.15s ease-in 0s, right 0.3s ease 0s;
  width: 0;
}
.nav-mobile-text {
    display: none;
    font-size:2vw;
    font-weight: 900;
    margin: 0;
}
.nav-text {
  margin: 0 0 0 1rem;
  font-size: calc(0.2rem + 0.8vw);
  overflow: nowrap;
  text-overflow: clip
}
@media only screen and (max-width: 991px) {
  body {
    padding-bottom: 75px;
  }
  .nav-link:after {
    background: transparent
  }
  #topnav {
    background: white !important;
    border-top: 1px solid lightgray;
    justify-content: start;
    text-align: center;
    position: fixed;
    height: fit-content;
    margin-top: auto;
    bottom: 0;
    width: 100%;
    max-width: 100vw;
    padding: 1rem 0;
  }
  .nav-text {
    display: none;
  }
  .nav-col {
    max-width: 18vw;
    padding: 0 !important;
  }
  .col > .nav-link {
    padding: 0;
    display: block;
  }
  .nav-mobile-text {
    display: block;
  }

}
</style>
