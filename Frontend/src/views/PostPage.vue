<template>
  <div id="post-page-main">
    <nav-bar style="position:fixed;left:0;width:100%;" />
    <h1 class="mt-5">{{ this.$store.state.constants.SERVER }}</h1>
    <post-list />
  </div>
</template>

<script>
// import axios from 'axios'
import NavBar from '@/components/NavBar.vue'
import PostList from '@/views/section/PostList.vue'

  export default {
    name: 'posts',
    components: {
      NavBar,
      PostList
    },
    data() {
      return {

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

nav {
  background: transparent;
}
</style>