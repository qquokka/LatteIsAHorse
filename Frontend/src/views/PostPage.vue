<template>
  <div class="container-fluid">
    <div id="background-post" />
    <nav-bar />
    <search-bar class="mx-auto"  style="margin-top:9rem" />
    <router-link v-if="isAuthenticated" to="posts/create/" class="position-relative"><button class="btn-danger btn">글쓰기</button></router-link>
    <review-list style="margin-top: 8rem" :postData="postData" />
    <Footer />
  </div>
</template>

<script>
import axios from 'axios'
import NavBar from '@/components/NavBar.vue'
import ReviewList from '@/views/section/ReviewList.vue'
import SearchBar from '@/components/SearchBar.vue'
import Footer from '@/views/section/Footer.vue'

  export default {
    name: 'posts',
    components: {
      NavBar,
      ReviewList,
      SearchBar,
      Footer
    },
    data() {
      return {
        isAuthenticated: this.$session.exists('jwt'),
        postData: []
      }
    },
    computed: {
    },
    methods: {
    },
    mounted() {
      axios
        .get(`${this.$store.state.constants.SERVER}/post`)
        .then(response => {
          this.postData = response.data  
        })
    },
  }
  
</script>

<style>
#background-post {
    position: absolute !important;
    top: 0;
    left: 0;
    z-index: 0;
    width: 100vw;
    height: 30rem;
    background-color: whitesmoke
}
nav {
  background: transparent;
}
</style>