<template>
  <form class="search-form input-group mb-3" @submit.prevent="goSearch()">
    <input
      v-model="query"
      id="title"
      type="text"
      class="form-control morph-text"
      placeholder=" 지역명, 카페 이름 등🔍"
      aria-describedby="button-addon2"
      autofocus
      autocomplete
    />
    <div class="input-group-append">
      <button class="btn" type="submit" id="button-addon2">검색</button>
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
		this.query = this.$route.query.q.split(" ")
	}
}
</script>

<style scoped>
#title {
  height: 4rem;
  border-radius: 20px 0 0 20px;
  font-size: 1.2rem;
}

.btn {
  border-radius: 50px;
  width: 5rem;
  background: #e6e6fa;
  box-shadow: 26px 26px 51px #c4c4d5, -26px -26px 51px #ffffff;
  transition: 0.2s;
}
.btn:hover {
  border-radius: 50px;
  background: #e6e6fa;
  box-shadow: inset 26px 26px 51px #c4c4d5, inset -26px -26px 51px #ffffff;
  font-weight: 900;
}

.search-form {
  width: 60%;
  min-width: 450px;
}

input[type="text"] {
  border-radius: 50px;
  border: 0;
  background: #ffffff;
  box-shadow: 6px 16px 31px #d9d9d9, -6px -16px 31px #ffffff;
}
</style>
