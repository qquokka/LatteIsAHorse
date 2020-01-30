<template>
  <div id="aboutus" :style="{'background-image': `url(${require('../assets/img/tower.jpg')})`}">
    <nav-bar style="position:fixed;left:0;width:100%;" />
		<h1 class="display-1 mt-5 text-white">저희는</h1>

  </div>
</template>

<script>

  import NavBar from '@/components/NavBar.vue'

  export default {
    name: 'about',
    components: {
      NavBar
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
#aboutus {
  position: absolute;
  background-size: cover;
  width: 100vw;
  height: 5000px;
}
nav {
  background: transparent;
}
</style>