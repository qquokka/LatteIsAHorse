<template>
  <div class='container' id="app">
    <h1 class="bg-danger text-white mx-5"><i class="fab fa-youtube"></i>Youtube 검색기</h1>
    <div class="container mt-5">
      <search-bar @input-change-event="onInputChange" />
    </div>
    <div class="row">
      <video-detail class="col-8" :video="selected"/>
      <video-list class='col' @video-select-event="selectedVideo" :videos="videos" />
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import SearchBar from './components/SearchBar.vue'
  import VideoList from './components/VideoList.vue'
  import VideoDetail from'./components/VideoDetail.vue'
  const API_KEY = process.env.VUE_APP_API_KEY

  export default {
    name: 'app',
    components: {
      SearchBar,
      VideoList,
      VideoDetail
    },
    data() {
      return {
        videos: [],
        selected: {},
      }
    },
    methods: {
      onInputChange(value) {
        console.log('==App==')
        console.log(value)
        axios.get('https://www.googleapis.com/youtube/v3/search', {
            params: {
              key: API_KEY,
              part: 'snippet',
              q: value,

            }
          }).then(response => {
            this.videos = response.data.items
          })
          .catch(error => {
            console.log(error)
          })
      },
      selectedVideo(v){
        this.selected = v
      },
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
  .related{
    height: 2000px;
  }
</style>