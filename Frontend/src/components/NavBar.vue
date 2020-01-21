<template>

  <nav class="navbar navbar-expand-lg sticky-top" id="topnav">
  <a class="navbar-brand text-shadow" href="/" style="color:#fff;font-weight:900">라떼는 말이야</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav ml-auto">
      <li v-if="!isAuthenticated" class="nav-item">
        <a class="nav-link" type="button" data-toggle="modal" data-target="#staticBackdrop">
          <i class="fa fa-key"></i> 로그인
        </a>
      </li>
      <li v-else class="nav-item">
        <a class="nav-link" type="button" data-toggle="modal" data-target="#staticBackdrop">
          <i class="fa fa-key"></i> 로그아웃
        </a>
      </li>
      <li class="nav-item" >
        <a class="nav-link" href="#"><i class="fa fa-user"></i> About Us</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#"><i class="fa fa-map"></i> 내 주변 보기</a>
      </li>
    </ul>
  </div>
  
</nav>

</template>

<script>
export default {
  
  data () {
  return {
    scrolled: false
  };
},
methods: {
  handleScroll () {
    this.scrolled = window.scrollY > 0;
    const topnav = document.getElementById('topnav')
    if (this.scrolled) {
      topnav.style.background = "#7873f5"
      topnav.style.backgroundImage = "linear-gradient(to right, #ec77ab 0%, #7873f5 100%)"
      topnav.style.boxShadow = "2px 2px 12px black"
    }
    else {
      topnav.style.background = "none"
      topnav.style.backgroundImage = "unset"
      topnav.style.boxShadow = "0px 0px 0px"
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
.nav-item {
  color: white;
}
.nav-link {
  color: white;
  background-image: unset
}
a {
  color: white;
	transition: 0.5s ease-in-out;
}
a:hover {
	color:  #88D8B0 !important;
}
.navbar-brand:hover {
	color: #FFCC5C !important;
}
</style>