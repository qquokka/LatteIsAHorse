<template>
  <div class="position-relative" id="aboutus">
    <nav-bar />
    <div class="row w-100 p-0" id="background-aboutus">
      <div v-rellax="{speed:-10,}" class="col-6 p-0" > SLOW AND SMOOTH</div>
      <div class="col-6 p-0" style="background: black;" />
    </div>
    <h1 class="display1">HBH</h1>
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
    height: 100vh;
    background-color: lavenderblush
}
nav {
  background: transparent;
}
</style>