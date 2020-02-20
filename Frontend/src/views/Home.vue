<template>
  <div class="container-fluid p-0">
    <nav-bar bgcolor="#FFD6BA" />
                  <loading
                :active.sync="geoloading"
                :can-cancel="false"
                :is-full-page="true"
                loader="bars"
                color="crimson"
              ></loading>
    <div class="home-header align-items-center d-flex flex-column justify-content-center">
      <img
        class="d-none d-lg-block"
        style="position:absolute;height: 100vh;right:-16vw;bottom:15vh; opacity: 0.08;filter: grayscale(100%)"
        :src="require('../assets/navlogo.png')"
        alt
      />
      <div>
        <h2 class="d-none d-md-block brand">
          Find your ideal cafe
          <i class="fas fa-mug-hot"></i>
        </h2>
      </div>
      <div class="d-block d-md-none brand">
        <img :src="require('../assets/navlogo.png')" style="margin: -13vh 0" />
      </div>
      <search-bar class="mx-auto mt-4" />
      <div class="d-block d-md-none small w-50 text-left">{{ getMyung }}</div>
    </div>

    <div class="container">
      <hash-tags class="mt-4" />
      <popular-list />
      <div class="main-section" style="margin-top:8rem;">
        <div class="text-center">
          <h2 class="article-header">
            <fa icon="road" />가까운 인기 카페
          </h2>
          <div
            style="cursor:pointer; color: crimson;font-size: calc(5px + 0.5vw)"
            @click.once="geoPermission()"
          >

            <fa icon="crosshairs" />위치정보이용동의
          </div>
        </div>
        <cafe-list :cafeData="cafeData" />
      </div>

      <div class="main-section">
        <h2 class="article-header my-5">
          <fa icon="pen-nib" />EDITOR's PICK
        </h2>
        <review-list limits="6" :reviewData="reviewData" />
      </div>
    </div>
    <b-section />
    <Footer />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import SearchBar from "@/components/SearchBar.vue";
import NavBar from "@/components/NavBar.vue";
import CafeList from "@/components/CafeList.vue";
import ReviewList from "@/views/section/ReviewList.vue";
import PopularList from "@/components/PopularList.vue";
import BSection from "@/views/section/BuisinessSection.vue";
import Footer from "@/views/section/Footer.vue";
import HashTags from "@/components/HashTags.vue";
import axios from "axios";
import Loading from "vue-loading-overlay";
import "vue-loading-overlay/dist/vue-loading.css";
import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faRoad,
  faCrosshairs,
  faPenNib
} from "@fortawesome/free-solid-svg-icons";
library.add(faRoad, faCrosshairs, faPenNib);
export default {
  name: "home",
  components: {
    SearchBar,
    CafeList,
    ReviewList,
    NavBar,
    PopularList,
    BSection,
    Footer,
    HashTags,
    Loading
  },
  data() {
    return {
      center: null,
      answers: [],
      username: "",
      i: 0,
      isAuthenticated: this.$store.state.token !== null,
      cafeData: [],
      reviewData: [],
      geoloading: false,
      myungun: [
        "성공한 모든 여성 뒤에는 많은 양의 커피가 있다. -스테파니 파이로",
        "내게 정신을 차리게 만드는 것은 진한 커피, 아주 진한 커피이다. 커피는 내게 온기를 주고, 특이한 힘과 기쁨과 쾌락이 동반된 고통을 불러 일으킨다.- 나폴레옹",
        "커피를 마시기 전까지 나는 절대 웃지 않는다. -클라크 게이블",
        "내게 커피를 주시오, 아니면 죽음을 주시오! -패트릭 헨리",
        "내가 좋아하는 것은 향기다. 집 근처에서 커피콩을 볶을 때면 나는 서둘러 창문을 열어 그 향기를 모두 받아 들인다. - 장자크 루소",
        "커피는 사람을 재치 있게 만든다. -몽테스키외",
        "좋은 커피 옆에는 언제나, 에티오피아 인의 친절한 미소가 있다. -에티오피아 속담",
        "(설탕)과 (진한 크림)이 들어간 것이 진짜 커피다. -마가렛 미첼",
        "이해력을 높이고 싶다면 커피를 마셔라. 커피는 지적인 음료다. -시드니 스미스"
      ]
    };
  },
  
  computed: {
    ...mapGetters(["options", "user", "colors"]),
    getMyung() {
      return this.myungun[Math.floor(Math.random() * this.myungun.length)];
    }
  },
  methods: {
    geoPermission() {
      this.geoloading = true
      navigator.geolocation.getCurrentPosition(this.success, this.fail);
    },
    success(position) {
      axios
        .post(`${this.$store.state.constants.SERVER}/map/limit`, {
          latitude: position.coords.latitude,
          longitude: position.coords.longitude,
          level: 12,
          limit: 6,
        })
        .then(r=> {
          this.cafeData = r.data
          this.geoloading = true
        })
    }
  },
  beforeMount() {
    axios.get(`${this.$store.state.constants.SERVER}/cafe`).then(response => {
      this.cafeData = response.data.slice(0, 6);
    });
    axios.get(`${this.$store.state.constants.SERVER}/post`).then(response => {
      this.reviewData = response.data;
    });
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Noto+Sans+KR:900&display=swap");

.home-header {
  background: #ffd6ba;
  height: 50vh;
  width: 100%;
}
.brand {
  opacity: 1;
  animation: fadein 1.5s;
}

@keyframes fadein {
  0% {
    opacity: 0;
    color: #faf9f9;
  }
  100% {
    opacity: 1;
    color: #555b6e;
  }
}

.main-section {
  position: relative;
  margin: 0 auto 3rem auto;
  width: 100%;
  border-radius: 50px;
}
.list-container {
  background-color: rgba(255, 255, 255, 0.95);
}
.article-header {
  text-align: center;
  margin: 1rem auto 0.4rem auto;
  text-shadow: 26px 26px 51px #d9d9d9, -26px -26px 51px #ffffff;
  width: fit-content;
}
#background {
  position: absolute !important;
  top: 0;
  left: 0;
  z-index: 0;
  width: 100vw;
  height: 60vh;
  background-color: lavender;
}
</style>