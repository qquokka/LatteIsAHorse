<template>
<div id="post-list-main" class="container-fluid" >
  <div class="row">
    <div v-for="post in postData.slice(0, limits)" :key="post.id" class="post-list col-4 p-3">
      <div class="postbody">
        <h3 class="border-bottom p-2">{{ post.title }}</h3><span><i class="fas fa-thumbs-up"> {{ getRandomInt(1, 5000) }}</i></span>
        <p>by {{ post.reporter_name }} at {{ post.created_at }} </p>
        <img :src="`https://picsum.photos/300/200?random=${post.id}`" width="100%">
        <p class="post-preview">{{ post.body }}</p>
      </div>
    </div>
    <button @click="loadmore" class="btn btn-light btn-block position-relative">더 보기</button>
  </div>
</div>
</template>

<script>
import dummy from "!@/assets/dummy/post_dummy.json"

export default {
  data() {
    return {
      postData: dummy,
      limits: 6,
    };
  },
  created() {
    // this.axios
    //   .get(`${this.$store.state.constants.SERVER}/posts`)
    //   .then(response => {
    //     this.postData = response.data;
    //     console.log(this.postData);
    //   });
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