<template>

  <div class="position-relative" id="aboutus">
    <nav-bar />
  <div style="background-color: lavender; height: 100vh;">
      <h1 style="margin-top: 0; padding-top: 20px;">Scroll down ⬇</h1>
      </div>
      <div style="position: relative; z-index: 9999; background-color: #fff;">
        <h1 style="margin:0;">Parallax Effect</h1>
        <parallax>
          <img src="@/assets/img/aboutus1.jpg">
        </parallax>
      </div>
      <div style="background-color: #fff; height: 100vh;"></div>
      <h1>Parallax fixed position </h1>

      <div style="position: relative;">
        <parallax :fixed="true">
          <img src="static/img/pexels-photo-196416.jpeg">
        </parallax>
      </div>

      <div style="background-color: #fff; height: 100vh;"></div>
    </div>
</template>

<script>
  import NavBar from '@/components/NavBar.vue'
  import Parallax from 'vue-parallaxy'

  export default {
    name: 'about',
    components: {
      NavBar,
      Parallax
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
}
#background-aboutus {
    position: relative !important;
    top: 0;
    left: 0;
    z-index: 0;
    width: 100vw;
    background-color: white
}
nav {
  background: transparent;
}

</style>