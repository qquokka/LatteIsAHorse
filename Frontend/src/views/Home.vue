<template>
  <div class="container-fluid p-0">
    <nav-bar bgcolor="#FFD6BA" />
    <div class="home-header align-items-center d-flex flex-column justify-content-center">
      <div>
        <h2
          class="d-none d-md-block brand"
        >Find your ideal cafe <i class="fas fa-mug-hot"></i></h2>
      </div>
      <div>
        <h3 class="d-block d-md-none">
          LATTE
          <span style="color:white">=</span> HORSE
        </h3>
      </div>
        <search-bar class="mx-auto mt-4" />
    </div>
    

    <popular-list />
    <div class="main-section" style="margin-top:8rem;">
      <h2 class="article-header">가까운 카페</h2>
      <cafe-list :cafeData="cafeData" />
    </div>
    <word-cloud class="overflow-hidden" />
    <div class="main-section">
      <h2 class="article-header">EDITOR's PICK</h2>
      <review-list limits="3" :reviewData="reviewData" />
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
import PopularList from "@/components/PopularList.vue"
import BSection from "@/views/section/BuisinessSection.vue"
import Footer from "@/views/section/Footer.vue";
import axios from "axios";
import WordCloud from "@/components/WordCloud.vue"

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
    WordCloud,
  },
  data() {
    return {
      answers: [],
      username: "",
      i: 0,
      isAuthenticated: this.$store.state.token !== null,
      cafeData: [],
      reviewData: [],
    };
  },
  computed: {
    ...mapGetters(["options", "user", "colors"])
  },
  methods: {
  },
  beforeMount() {
    axios.get(`${this.$store.state.constants.SERVER}/cafe`)
      .then(response => {
        console.log(response.data)
        this.cafeData = response.data.slice(0, 6);
      })
      .catch(err => {
        console.log(err.response);
      });

    axios.get(`${this.$store.state.constants.SERVER}/post`)
      .then(response => {
        this.reviewData = response.data;
      });
    }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Noto+Sans+KR:900&display=swap");

.home-header {
  background: #FFD6BA;
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
    color: #FAF9F9;
  }
  100% {
    opacity: 1;
    color: #555B6E;
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
  margin: 2rem auto 2rem auto;
  border-radius: 20px;
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