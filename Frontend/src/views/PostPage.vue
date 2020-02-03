<template>
  <div id="post-page-main">
    <div id="background-post" />

    <nav-bar />
    <search-bar class="mx-auto"  style="margin-top:9rem" />
    <router-link v-if="isAuthenticated" to="posts/create/" class="position-relative"><button class="btn-danger btn">글쓰기</button></router-link>
    <post-list style="margin-top: 8rem;" class="" />
  </div>
</template>

<script>
// import axios from 'axios'
import NavBar from '@/components/NavBar.vue'
import PostList from '@/views/section/PostList.vue'
import SearchBar from '@/components/SearchBar.vue'

  export default {
    name: 'posts',
    components: {
      NavBar,
      PostList,
      SearchBar
    },
    data() {
      return {
        isAuthenticated: this.$session.exists('jwt')
      }
    },
    computed: {
    },
    methods: {
    },
    mounted() {
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