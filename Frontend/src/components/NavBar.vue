<template>
  <nav class="navbar navbar-expand-lg fixed-top" id="topnav">
  <router-link to="/" ><img src="../assets/img/navlogo2.png"  style="border-radius:3px" width="120"></router-link>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav ml-auto">
      <li v-if="!isAuthenticated" class="nav-item">
        <p class="nav-link" data-toggle="modal" data-target="#staticBackdrop">
          <i class="fa fa-key"></i> 로그인
        </p>
      </li>
      <li v-else class="nav-item">
        <a class="nav-link" type="button" data-toggle="modal" data-target="#staticBackdrop">
          <i class="fa fa-key"></i> 로그아웃
        </a>
      </li>
      <li class="nav-item" >
        <router-link class="nav-link" to="about"><i class="fa fa-user"></i> About Us</router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="map"><i class="fa fa-map"></i> 내 주변 보기</router-link>
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
    isAuthenticated: false
  };
},
methods: {
  handleScroll () {
    this.scrolled = window.scrollY > 0;
    const topnav = document.getElementById('topnav')
    const navitem = document.querySelectorAll('.nav-link')
    if (this.scrolled) {
      topnav.style.background = "whitesmoke"
      topnav.style.boxShadow = "2px 2px 12px gray"
      for (var i=0;i<navitem.length;i++){
        navitem[i].style.color = "#2f2f2f"
      }
    }
    else {
      topnav.style.background = "none"
      topnav.style.backgroundImage = "unset"
      topnav.style.boxShadow = "0px 0px 0px"
      for (i=0;i<navitem.length;i++){
        navitem[i].style.color = "white"
      }
    }
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
}
.nav-link {
  color: white;
  margin:auto
}

a {
	transition: 0.5s ease-in-out;
}
a:hover {
	color:  #88D8B0 !important;
}
.navbar-brand:hover {
	color: #FFCC5C !important;
}
</style>