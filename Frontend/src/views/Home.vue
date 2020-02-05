<template>
  <div class="container-fluid p-0">
    <div id="background"></div>
    <nav-bar blackOrWhite="true" />
    <h2
      class="d-none d-md-block brand"
      style="margin-top: 8rem;margin-bottom:2rem;position:relative"
    >Find your ideal cafe <i class="fas fa-mug-hot"></i></h2>
    <h3 class="d-block d-md-none" style="margin-top: 1rem;margin-bottom:2rem;position:relative">
      LATTE
      <span style="color:violet">=</span> HORSE
    </h3>
    <search-bar class="mx-auto" />
    <hash-tags class="position-relative mb-5 pb-5" />
    <popular-list />

    <div class="main-section" style="margin-top:8rem;">
      <h2 class="article-header">가까운 카페</h2>
      <cafe-list :cafeData="cafeData" />
    </div>
    <div class="main-section">
      <h2 class="article-header">EDITOR's PICK</h2>
      <post-list limits="3" :postData="postData" />
    </div>
    <Footer />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import SearchBar from "@/components/SearchBar.vue";
import NavBar from "@/components/NavBar.vue";
import CafeList from "@/components/CafeList.vue";
import PostList from "@/views/section/PostList.vue";
import PopularList from "@/components/PopularList.vue"
import HashTags from "@/components/HashTags.vue";
import Footer from "@/views/section/Footer.vue";
import axios from "axios";

export default {
  name: "home",
  components: {
    SearchBar,
    CafeList,
    PostList,
    NavBar,
    HashTags,
    PopularList,
    Footer
  },
  data() {
    return {
      answers: [],
      username: "",
      i: 0,
      isAuthenticated: this.$store.state.token !== null,
      cafeData: [],
      postData: [],
    };
  },
  computed: {
    ...mapGetters(["options", "user"])
  },
  methods: {
    isLogin() {
      this.$session.start();
      if (!this.$session.has("jwt")) {
        this.$router.push("/login");
      } else {
        this.$store.dispatch("login", this.$session.get("jwt"));
      }
    }
  },
  beforeMount() {
    axios
      .get(`${this.$store.state.constants.SERVER}/cafe`)
      .then(response => {
        this.cafeData = response.data.slice(0, 6);
      })
      .catch(err => {
        console.log(err.response);
      });

    axios.get(`${this.$store.state.constants.SERVER}/post`).then(response => {
      this.postData = response.data;
    });
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Noto+Sans+KR:900&display=swap");

.brand {
  opacity: 1;
  animation: fadein 1.5s;
}

@keyframes fadein {
  0% {
    opacity: 0;
    color: lavender;
  }
  100% {
    opacity: 1;
    color: #2f2f2f;
  }
}



.main-section {
  position: relative;
  margin: 0 auto 3rem auto;
  width: 85%;
  min-width: 450px;
  border-radius: 50px;
}
.list-container {
  background-color: rgba(255, 255, 255, 0.95);
}
.article-header {
  color: #3f3f3f;
  text-align: left;
  margin-top: 2rem;
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
  height: 30rem;
  background-color: lavender;
}
</style>