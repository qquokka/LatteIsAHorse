<template>
  <div id="aboutus">
    <nav-bar style="position:fixed;left:0;width:100%;" />
    <div id="background-aboutus" />
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
#background-aboutus {
    position: absolute !important;
    top: 0;
    left: 0;
    z-index: 0;
    width: 100vw;
    height: 30rem;
    background-color: lavenderblush
}
nav {
  background: transparent;
}
</style>