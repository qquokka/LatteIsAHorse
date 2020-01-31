<template>
  <div id="post-page-main">
    <img id="post-background" src="../assets/img/postlist.jpeg">
    <nav-bar style="position:fixed;left:0;width:100%;" />
    <hash-tags class="position-relative mt-5 py-2" />
    <router-link v-if="isAuthenticated" to="posts/create/" class="position-relative"><button class="btn-danger btn">글쓰기</button></router-link>
    <post-list />
  </div>
</template>

<script>
// import axios from 'axios'
import NavBar from '@/components/NavBar.vue'
import PostList from '@/views/section/PostList.vue'
import HashTags from '@/components/HashTags.vue'

  export default {
    name: 'posts',
    components: {
      NavBar,
      PostList,
      HashTags
    },
    data() {
      return {
        isAuthenticated: this.$session.exists('jwt'),
      }
    },
    computed: {
    },
    methods: {
    },
    mounted() {

    }
  }
  
</script>

<style>
#post-background {
  width: 99.6vw;
  position: absolute;
  left: 0;
  top:0;
  background-repeat: repeat-y
}
nav {
  background: transparent;
}
</style>