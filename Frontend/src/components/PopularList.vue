<template>
  <div class="w-100 justify-content-center pt-4" >
    <h3 class="align-self-start" style="font-weight:100;"><fa icon="search-location" />  동네별 카페 바로찾기</h3>
    <div class="popular row justify-content-center mx-auto">
      
      
			<!-- component화 예정 -->
      <router-link to="/searched?q=전민동" class="cube col-12 col-md-4 col-lg-2">
        <div class="flippety" style="background: lavender">
          <p class="glow">전민동</p> 
        </div>
        <div class="flop">
          <h3 class="dong">전민동</h3>
        </div>
      </router-link>

      <router-link to="/searched?q=둔산동" class="cube col-12 col-md-4 col-lg-2">
        <div class="flippety" style="background: lavender">
          <p class="glow">둔산동</p> 
        </div>
        <div class="flop">
          <h3 class="dong">둔산동</h3>
        </div>
      </router-link>


      <router-link to="/searched?q=궁동" class="cube col-12 col-md-4 col-lg-2">
        <div class="flippety" style="background: lavender">
          <p class="glow">궁동</p> 
        </div>
        <div class="flop">
          <h3 class="dong">궁동</h3>
        </div>
      </router-link>


      <router-link to="/searched?q=탄방동" class="cube col-12 col-md-4 col-lg-2">
        <div class="flippety" style="background: lavender">
          <p class="glow">탄방동</p> 
        </div>
        <div class="flop">
          <h3 class="dong">탄방동</h3>
        </div>
      </router-link>


      <router-link to="/searched?q=오류동" class="cube col-12 col-md-4 col-lg-2">
        <div class="flippety" style="background: lavender">
          <p class="glow">오류동</p> 
        </div>
        <div class="flop">
          <h3 class="dong">오류동</h3>
        </div>
      </router-link>


      <router-link to="/searched?q=덕명동" class="cube col-12 col-md-4 col-lg-2">
        <div class="flippety" style="background: lavender">
          <p class="glow">덕명동</p> 
        </div>
        <div class="flop">
          <h3 class="dong">덕명동</h3>
        </div>
      </router-link>


    </div>
  </div>
</template>

<script>
import { library } from '@fortawesome/fontawesome-svg-core'
import { faSearchLocation } from '@fortawesome/free-solid-svg-icons'
library.add(faSearchLocation)

export default {};
</script>

<style>
.popular {
  width: 80%;
  min-width: 375px;
  text-align: left;
  padding: 3rem;
  background: white;
  border: 1px solid transparent;
  transition: 120ms linear;
  border-radius: 12px;
}
.popular:hover {
  width: 80%;
  min-width: 375px;
  text-align: left;
  padding: 3rem;
  background: white;
  border: 1px solid gray;
}
.cube {
  width: 150px;
	text-align: center;
  height: 150px;
  transition: transform 0.33s;
  transform-style: preserve-3d;
	cursor: pointer;
}

.flippety,
.flop {
  height: 150px;
	align-items: center !important;
	justify-content: center !important;
	display: flex;
	border-radius: 5px;
	background: #ffffff;
}

.flippety {
  transform: translateZ(75px);
}

.flop {
  transform: rotateX(-90deg) translateZ(-75px);
	background-size: cover !important;
}

.cube:hover {
  -webkit-transform: rotateX(90deg);
  transform: rotateX(90deg);
}

.dong {
	font-size: 1.4rem;
}

.glow {
  text-shadow: 0px 0px 4px #2f2f2f;
  color: white;
  font-size: 1.2rem;
}
</style>