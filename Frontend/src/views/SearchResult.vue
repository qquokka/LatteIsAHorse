<template>
  <div id="search-result">
    <nav-bar />
    <div class="container">
      <div class="sresultheader">
        <h3>"{{ $route.query.q }}" 에 대해 검색하여</h3>
        <h4>{{ cafeData.length }} 개의 카페를 찾았어요</h4>
        <search-bar class="mx-auto my-3 pb-4" />
    </div>
    <div class="container border">
    <div class="post-container">
      <review-list reviewData="reviewData" />
    </div>

    <div class="cafe-container">
      <cafe-list :cafeData="cafeData" />
    </div>
    </div>
  </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue"
import SearchBar from "@/components/SearchBar.vue"
import axios from "axios"
import ReviewList from "@/views/section/ReviewList.vue"
import CafeList from "@/components/CafeList.vue"

export default {
  name: "SearchResult",
  components: {
    NavBar,
    SearchBar,
    ReviewList,
    CafeList
  },
  data() {
    return {
			cafeData: [],
			reviewData: []
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
					this.reviewData = response.data.posts
        })
    }
	},
	mounted() {
		let queries = this.$route.query.q.split(' ')
		this.searchNow(queries.join('|'))
	}
}
</script>

<style>
.sresultheader {
  background: #89B0AE;
  border-radius: 10px 10px 0 0;
  border: 1px solid lightgray;  
}
</style>
