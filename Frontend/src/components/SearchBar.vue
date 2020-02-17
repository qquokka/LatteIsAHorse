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
      <button class="search-btn d-flex justify-content-center align-items-center" type="submit">
        <p class="m-0">검색</p>
      </button>
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
  props: {},
  methods: {
    goSearch() {
      this.$router.push(`/searched?q=${this.query}`);
    }
  },
  mounted() {
    if (this.$router.query) {
      this.query = this.$route.query.q.split(" ");
    }
  }
};
</script>

<style>
#title {
  padding: 1rem;
  font-size: 1.2rem;
}

.search-btn {
  background: #BEE3DB;
  transition: 0.2s;
  font-size: 1.2rem;
  width: 100%;
  padding: 1rem;
}
.search-btn:hover {
  background: #89B0AE;
  font-weight: 900;
}

.search-form {
  width: 50%;
  min-width: 325px;
}

input[type="text"] {
  border: 0;
  background: #ffffff;
}
@media only screen and (max-width: 991px) {
  ::-webkit-input-placeholder,
  ::-moz-placeholder,
  :-ms-input-placeholder,
  :-moz-placeholder {
    color: transparent;
  }
  #title,
  .search-btn {
    font-size: 4vw;
    padding: 2vw !important;
  }
}
</style>
