<template>
  <div class="container-fluid p-0">
    <nav-bar class="position-relative" />
    <div class="row p-0 m-0" :style="`height: ${avheight}px;background:#f2f2f2`">
      <div class="d-none d-md-flex col-md-1 menucol-1 py-5" style="font-size:1vw">
        <div class="menu-icon">
          <router-link to="/">
            <i class="fa fa-home fa-4x"></i>
          </router-link>
          <p class="menu-tex">돌아가기</p>
        </div>

        <div class="menu-icon">
          <i class="fa fa-search fa-4x"></i>
          <p class="menu-tex">검색</p>
        </div>

        <div class="menu-icon">
          <i class="fas fa-filter fa-4x"></i>
          <p class="menu-tex">필터적용</p>
        </div>

        <div class="menu-icon">
          <i class="fa fa-map-marked-alt fa-4x"></i>
          <p class="menu-tex">내 위치</p>
        </div>
      </div>

      <div class="d-none d-md-block col-md-3 bg-white px-0 py-2 infocol">
        <div class="info-header">
          <h1 class="font-weight-bolder">카페 데일리</h1>
          <small>Cafe' Daily</small>
          <p>대표자: 김태우</p>
          <p class="small">영업시간: 10 A.M. ~ 12 P.M.</p>
        </div>
        <div class="container">
          <div class="row align-content-center">
            <div
              class="frame col-4"
              :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`"
            ></div>
            <div
              class="frame col-4"
              :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`"
            ></div>
            <div
              class="frame col-4"
              :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`"
            ></div>
          </div>
          <div class="row">
            <div
              class="frame col-4"
              :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`"
            ></div>
            <div
              class="frame col-4"
              :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`"
            ></div>
            <div
              class="frame col-4"
              :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`"
            ></div>
          </div>
          <a href>#스터디</a>
          <a href>#시나몬라떼</a>
          <a href>#2층이상</a>
          <a href>#대학가</a>
          <a href>#저렴이</a>
          <button class="btn btn-block btn-info mt-2">자세히 보기</button>
        </div>
        <div class="container h-25">
          <p class="py-2 shadow">베스트 리뷰</p>

          <p class="border shadow p-2">아메리카노 한잔 시키고 11시간씩 있어도 눈치 안 주세요! 사장님이 안오고 알바만 있거든요!</p>
        </div>
      </div>

      <kakao-map
        class="col-12 col-md-8 my-auto shadow pl-1"
        width="100%"
        elementId="35989832"
      />
    </div>
    <!-- <Footer class="d-none d-sm-block" style="bottom:0 !important!" /> -->
  </div>
</template>

<script>
import KakaoMap from "@/components/KakaoMap.vue";
// import Footer from '@/views/section/Footer.vue'
import NavBar from "@/components/NavBar.vue";

export default {
  name: "Kakao-map",
  components: {
    KakaoMap,
    NavBar
  },
  data() {
    return {
      avheight: 0
    };
  },
  computed: {},
  methods: {},
  mounted() {
    this.avheight = window.innerHeight - 76
  }
};
</script>

<style>
.menucol-1 {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.menu-tex {
  color: #2f2f2f;
  margin: auto;
  font-weight: 600;
  font-size: 1rem;
  text-align: center;
}
.info-header {
  padding: 1rem;
	border-radius: 21px;
	background: linear-gradient(145deg, #dadada, #ffffff);
	box-shadow:  41px 41px 82px #cecece, 
							-41px -41px 82px #ffffff;
}
.menu-icon > i {
  color: transparent !important;
  -webkit-text-stroke: 1px #2f2f2f;
}
.menu-icon {
  cursor: pointer;
  padding: 5px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  transition: 0.2s;
  border-radius: 21px;
  background: #f2f2f2;
  box-shadow: 9px 9px 18px #cecece, -9px -9px 18px #ffffff;
}
.menu-icon:hover {
  border-radius: 21px;
  background: linear-gradient(145deg, #ffffff, #dadada);
  box-shadow: 9px 9px 18px #cecece, -9px -9px 18px #ffffff;
}
.menu-icon:hover i {
  color: lavender !important;
}
.menu-icon:hover .menu-tex {
  color: #2f2f2f;
}
.frame {
  height: 120px;
  overflow: hidden;
  box-shadow: 1px 1px 10px black;
  padding: 5px !important;
  background-size: 100%;
}
.infocol {
  background: #f2f2f2;
  box-shadow: inset 15px 15px 42px #f2f2f2, inset -15px -15px 42px #ffffff;
	margin-bottom: 5px
}
</style>