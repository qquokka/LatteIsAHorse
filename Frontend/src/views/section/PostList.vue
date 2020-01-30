<template>
<div id="post-list-main" class="container-fluid" >
  <div class="row">
    <div v-for="post in postData.slice(0, limits)" :key="post.id" class="post-list col-4 p-3">
      <div class="postbody">
        <h3 class="border-bottom p-2">{{ post.title }}</h3><span><i class="fas fa-thumbs-up"> {{ post.like_count }}</i></span>
        <p>by {{ post.writer_name }} at {{ post.created_at }} </p>
        <img :src="post.thumbnail" width="100%" height="300px">
        <p class="post-preview">{{ post.content }}</p>
      </div>
    </div>
    <button @click="loadmore" class="btn btn-light btn-block position-relative">더 보기</button>
  </div>
</div>
</template>

<script>
// import dummy from "!@/assets/dummy/post_dummy.json"
import axios from 'axios'
export default {
  data() {
    return {
      postData: [],
      limits: 6,
    };
  },
  mounted() {
    axios
      .get(`${this.$store.state.constants.SERVER}/post`)
      .then(response => {
        this.postData = response.data;
      })
      // .catch(error => {
      //   console.log("post data loaded from dummy");
      // })
  },
  methods: {
    loadmore() {
      this.limits += 6;
    },
    getRandomInt(min, max) {
      min = Math.ceil(min)
      max = Math.floor(max)
      return Math.floor(Math.random() * (max - min)) + min
    }
  }
}
</script>


<style>
#post-list-main {
  height: 1700px;
  overflow-y: scroll;
  
}

.postbody {
  background: rgba(255,255,255,0.95);
  border-radius: 15px;
}
.post-preview {
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 5;
    -webkit-box-orient: vertical;  
}
</style>