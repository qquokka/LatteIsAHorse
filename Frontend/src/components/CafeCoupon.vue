<template>
  <div class="container">
    <nav-bar />
    <h1 class="display-1">하이하이</h1>
    <h1>{{ $route.params.cafeid }}</h1>
    <h1></h1>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
    components: {
        NavBar,
    }
};
</script>

<style>
</style>