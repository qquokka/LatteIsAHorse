<template>

  <nav class="navbar navbar-expand-lg bg-transparent sticky-top">
    
  <a class="navbar-brand" href="/">라떼는 말이야</a>
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
      <li class="nav-item">
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


</script>

<style>
a {
	color: white !important;
	transition: 0.5s ease-in-out;
}
a:hover {
	color:  #88D8B0 !important;
}
.navbar-brand:hover {
	color: #FFCC5C !important;
}
</style>