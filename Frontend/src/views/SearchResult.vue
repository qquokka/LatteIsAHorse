<template>
  <div id="search-result">
    <nav-bar />
    <search-bar class="mx-auto" style="margin-top:9rem" />
    <!-- Post List -->
    <div class="row px-2">
      <div v-for="post in posts" :key="post.id" class="post-list col-4 p-3">
        <router-link :to="`post/${post.id}/`" class="text-decoration-none">
          <div class="postbody overflow-hidden">
            <img :src="post.thumbnail" width="100%" height="300px" />
            <h3 class="p-2 posttitle">{{ post.title }}</h3>
            <p class style="font-size:0.8rem">
              <i class="fas fa-thumbs-up">{{ post.like_count }}</i>
            </p>
            <p class="mb-0">{{ post.writer_name }}</p>
            <p class="postcreate" style="top:1rem;left:3rem">{{ post.created_at.slice(0,10) }}</p>
          </div>
        </router-link>
      </div>
    </div>
    <!-- Cafe List -->
    <div class="row px-2">
      <div v-for="cafe in cafes" :key="cafe.cafe_id" class="post-list col-4 p-3">
        <router-link :to="`cafe/${cafe.cafe_id}/`" class="text-decoration-none">
          <div class="postbody overflow-hidden">
            <img :src="cafe.thumbnail" width="100%" height="300px" />
            <h3 class="p-2 posttitle">{{ cafe.cafe_name }}</h3>
            <p class style="font-size:0.8rem">
              <i class="fas fa-thumbs-up">{{ cafe.like_count }}</i>
            </p>
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import SearchBar from "@/components/SearchBar.vue";
import axios from "axios";
export default {
  name: "SearchResult",
  components: {
    NavBar,
    SearchBar
  },
  data() {
    return {
      cafes: [],
      posts: []
    };
  },
  methods: {
    goToCafePage(cafe_id) {
      axios
        .get(`${this.$store.state.constants.SERVER}/cafe/${cafe_id}`)
        .then(response => {
          console.log(response);
        })
        .catch(error => {
          console.log(error.response);
        });
    },
    searchNow(query) {
      axios
        .get(`${this.$store.state.constants.SERVER}/search/${query}`)
        .then(response => {
          this.cafes = response.data.cafes;
          this.posts = response.data.posts;
        });
    }
  },
  mounted() {
    let queries = this.$route.query.q.split(" ");
    this.searchNow(queries.join("|"));
  }
};
</script>

<style></style>