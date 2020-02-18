<template>
  <div class="w-100 justify-content-center pt-4" >
    <h3 class="align-self-start p-0 py-lg-5" style="font-weight:100; font-size: calc(16px + 1.2vw)"><fa icon="search-location" />  동네별 카페 바로찾기</h3>
    <div class="popular row justify-content-center mx-auto">
      <router-link v-for="neighbor in neighborList" :to="`/searched?q=${neighbor}`" :key="neighbor" class="cube col-12 col-md-4 col-lg-2">
        <div class="flippety">
          <p class="glow w-100" style="z-index: 5;background: rgba(0">{{ neighbor }}</p> 
          <img :src="require(`@/assets/img/${neighbor}-min.jpg`)" style="width: 100%; height: 100%; position:absolute;filter: brightness(0.6)">
          
        </div>
        <div class="flop border">
          <p style="font-size: calc(1rem + 0.5vw)">{{ neighbor }} 카페 <br><span style="color:#89B0AE"><fa icon="search-location" /> 바로 검색</span> </p> 
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
  font-weight: 400;
  font-size: calc(1rem + 1vw);
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