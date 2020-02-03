<template>
  <div class="container-fluid">
		<nav-bar />
    <h1 class="display-1">HI</h1>
		<h1 v-for="cafe in cafes" :key="cafe.id">{{ cafe.cafe_name }}</h1>
  </div>
</template>

<script>
import axios from "axios"
import NavBar from "@/components/NavBar.vue"

export default {
	name: 'Search-Result-View',
	components: {
		NavBar,
	},
  data() {
    return {
			queryset: [],
			cafes: [],
    }
	},
	methods: {
		searchNow(query) {
		axios.get(`${this.$store.state.constants.SERVER}/search/${query}`)
			.then(response => {
				this.cafes = response.data.cafes
			})
			.catch(error => {
				console.log(error.response)
			})
		}
	},
	mounted() {
		let queries = this.$route.query.q.split(' ')
		this.searchNow(queries)
		this.queryset = queries
	}
}
</script>

<style></style>
