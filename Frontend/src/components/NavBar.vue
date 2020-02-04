<template>
  <nav class="navbar navbar-expand-lg sticky-top" @click="this.$router.push('/hangman/is/the/perfect/game/of/the/whole/human/history')" id="topnav">
  <router-link to="/" class="ml-2 row text-decoration-none" ><img src="../assets/logo_icon.png" style="margin: -30px" width="120px"><h4 class="my-auto ml-3" style="color:#3f3f3f">라떼는말이야</h4> </router-link>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="fa fa-bars fa-2x"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item" v-if="!isLoggedIn">
        <p class="nav-link" data-toggle="modal" data-target="#staticBackdrop">
          <i class="fa fa-key"></i> 로그인
        </p>
      </li>
      <li class="nav-item" v-else>
        <p class="nav-link" @click="logout()">
          <i class="fa fa-key"></i> 로그아웃
        </p>
      </li>
      <li class="nav-item" >
        <router-link class="nav-link" to="/about"><i class="fa fa-user"></i> About Us</router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="/cafe/1"><i class="fa fa-coffee"></i> 카페 보기</router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="/posts"><i class="fa fa-newspaper"></i> 포스트 보기</router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="/map"><i class="fa fa-map"></i> 내 주변 보기</router-link>
      </li>
    </ul>
  </div>
  
</nav>

</template>

<script>
export default {
  
  data () {
  return {
    scrolled: false,
    isAuthenticated: this.$session.exists('jwt'),
  };
},
props: {
        iswhite: {
          type: Boolean,
          default: false
        }
},
methods: {
  logout() {
    this.$session.destroy()
    this.$store.dispatch('logout')
    this.handleScroll ()
  },
  handleScroll () {
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
  }
},

computed: {
  isLoggedIn() {
    return this.$store.getters.isLoggedIn;
  }
},
created () {
  window.addEventListener('scroll', this.handleScroll);
},
destroyed () {
  window.removeEventListener('scroll', this.handleScroll);
}
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
  color: #2f2f2f;
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
  background: #88D8B0;
  transition: width 0.15s ease-in 0s, right 0.3s ease 0s;
  width: 0;
}
.nav-link:hover, a:hover {
	color:  #2f2f2f !important;
}
.fa-bars {
  color: gray
}
</style>