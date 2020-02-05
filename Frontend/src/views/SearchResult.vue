<template>
  <div id="search-result">
    <nav-bar />
    <search-bar class="mx-auto" style="margin-top:9rem" />
    <div class="post-container">
      <post-list postData="postData" />
    </div>

    <div class="cafe-container">
      <cafe-list :cafeData="cafeData" />
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue"
import SearchBar from "@/components/SearchBar.vue"
import axios from "axios"
import PostList from "@/views/section/PostList.vue"
import CafeList from "@/components/CafeList.vue"

export default {
  name: "SearchResult",
  components: {
    NavBar,
    SearchBar,
    PostList,
    CafeList
  },
  data() {
    return {
			cafeData: [],
			postData: []
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
					this.cafeData = response.data.cafes
					this.postData = response.data.posts
        })
    }
	},
	mounted() {
		let queries = this.$route.query.q.split(' ')
		this.searchNow(queries.join('|'))
	}
}
</script>

<style></style>
