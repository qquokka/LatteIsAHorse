<template>
<div id="post-list-main" class="container-fluid" >
  <div class="row">
    <div v-for="post in postData.slice(0, limits)" :key="post.id" class="post-list col-4 p-3 shadow">
      <router-link :to="`post/${post.id}/`" style="text-decoration:none;color:#3f3f3f">
        <div class="postbody">
          <img :src="post.thumbnail" width="100%" height="300px" style="border-radius:15px 15px 0 0">
          <h3 class="p-2">{{ post.title }}</h3><span><i class="fas fa-thumbs-up"> {{ post.like_count }}</i></span>
          <p class="mb-0"> {{ post.writer_name }} 님이 </p>
          <p class=""> {{ post.created_at }} 에 쓴 글 </p>
          
          <!-- <p class="post-preview">{{ post.content }}</p> -->
        </div>
      </router-link>
    </div>
    <button class="btn btn-light btn-block position-relative" @click="loadmore">더보기</button>
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
      limits: 12,
    }  
  },
  mounted() {
    axios
      .get(`${this.$store.state.constants.SERVER}/post`)
      .then(response => {
        this.postData = response.data  
      })
  },
  methods: {
    loadmore() {
      this.limits += 6  
    },
    // deletePost(postId) {
    //   console.log('삭제시작')
    //   axios.delete(`${this.$store.state.constants.SERVER}/post/${postId}`, {headers: {'Authorization': "Bearer " + this.$session.get('jwt')}})
    //       .then(response => {
    //         console.log(response)
    //       })
  }
}
</script>


<style>
#post-list-main {
}

.postbody {
  background: rgba(255,255,255,0.95);  
  border-radius: 15px  
}

.postbody>h3:hover {
  text-decoration: none;
}

.post-preview {
    overflow: hidden;  
    display: -webkit-box;  
    -webkit-line-clamp: 5;  
    -webkit-box-orient: vertical    
}
</style>