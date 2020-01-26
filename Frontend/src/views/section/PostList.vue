<template>
<div class="container-fluid p-5">
  <div class="row p-3">
    <div v-for="post in postData" :key="post.id" class="post-list col-3 shadow">
      <h3>{{ post.title }}</h3>
      <small>{{ post.created_at }}</small>
      <p class="post-preview">{{ post.body }}</p>
    </div>
  </div>
</div>
</template>

<script>
import dummy from "!@/assets/dummy/post_dummy.json"

export default {
  data() {
    return {
      postData: dummy,
    };
  },
  created() {
    // this.axios
    //   .get(`${this.$store.state.constants.SERVER}/posts`)
    //   .then(response => {
    //     this.postData = response.data;
    //     console.log(this.postData);
    //   });
  }
};
</script>


<style>
.post-list {
  height: 200px;
  width: 200px;
}
.post-preview {
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;  
}
</style>