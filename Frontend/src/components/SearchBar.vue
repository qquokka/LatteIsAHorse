<template>
  <form class="search-form row mb-3" @submit.prevent="goSearch()">
    <input
      v-model="query"
      id="title"
      type="text"
      class="col-10 morph-text p-0"
      placeholder=" 지역명, 카페 이름 등🔍"
      autofocus
      autocomplete
    />
    <div class="col h-100 p-0">
      <div class="search-btn d-flex justify-content-center align-items-center" type="submit" >
        <p class="m-0">검색</p>
      </div>
    </div>
  </form>
</template>


<script>
export default {
  name: "SearchBar",
  data() {
    return {
      query: ""
    };
	},
	props: {

	},
  methods: {
		goSearch() {
			this.$router.push(`/searched?q=${this.query}`)
		}
	},
	mounted() {
    if (this.$router.query) {
      this.query = this.$route.query.q.split(" ")
    }
	}
}
</script>

<style scoped>
#title {
  height: 3rem;
  font-size: 3vw;
}

.search-btn {
  background: gold;
  transition: 0.2s;
  font-size: 3vw;
  width: 100%;
  height: 3rem;
}
.search-btn:hover {
  background: gold;
  font-weight: 900;
}

.search-form {
  width: 50%;
}

input[type="text"] {
  border: 0;
  background: #ffffff;
}
</style>
