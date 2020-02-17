<template>
  <div class="w-100 justify-content-center pt-4" >
    <h3 class="align-self-start p-0 py-lg-5" style="font-weight:100; font-size: calc(16px + 1.2vw)"><fa icon="search-location" />  동네별 카페 바로찾기</h3>
    <div class="popular row justify-content-center mx-auto">
      <router-link v-for="neighbor in neighborList" :to="`/searched?q=${neighbor}`" :key="neighbor" class="cube col-12 col-md-4 col-lg-2">
        <div class="flippety" style="background: #89B0AE">
          <p class="glow">{{ neighbor }}</p> 
        </div>
        <div class="flop">
          <h3 class="dong">{{ neighbor }}</h3>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
import { library } from '@fortawesome/fontawesome-svg-core'
import { faSearchLocation } from '@fortawesome/free-solid-svg-icons'
library.add(faSearchLocation)

export default {
    data() {
    return {
      neighborList: ['전민동', '덕명동', '둔산동', '궁동', '탄방동', '오류동']
    };
  },
};
</script>

<style>
.popular {
  width: 100%;
  min-width: 375px;
  text-align: left;
  padding: 0;
  border: 1px solid transparent;
  transition: 120ms linear;
  border-radius: 12px;
}
.popular:hover {
  background: 
}
.cube {
	text-align: center;
  height: 360px;
  transition: transform 0.33s;
  transform-style: preserve-3d;
	cursor: pointer;
}

.flippety,
.flop {
  height: 360px;
	align-items: center !important;
	justify-content: center !important;
	display: flex;
	border-radius: 5px;;
  border: 1px solid lightgray
}

.flippety {
  transform: translateZ(180px);
}

.flop {
  transform: rotateX(-90deg) translateZ(-180px);
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
@media only screen and (max-width: 991px) {
  .popular {
    padding: 0;
  }
  .cube {
    margin: 1rem 0;
    padding: 0 !important;
  }
}
</style>