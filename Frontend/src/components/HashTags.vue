<template>
  <div class="container border tag-box" style="border-radius: 10px">
    <h2 style="margin: -1.2rem auto 0 auto; background-color:#FAF9F9;width:12rem;">인기 태그</h2>
		<div class="row">
		</div>
    <div class="row mt-5 justify-content-center text-dark">
			<router-link class="hash" v-for="tag in tags" :key="tag.name" :to="`hashtag/${tag.name}`">#{{ tag.name }}</router-link>
		</div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      tags: []
    };
  },
  methods: {
    bts() {
      axios.get(`${this.$store.state.constants.SERVER}/hashtags`).then(res => {
        this.tags = res.data.hashtags.slice(0,11);
      });
    }
	},
	mounted() {
		this.bts()
	}
};
</script>

<style>
.tag-box {
  height: 10rem;
}
.hash {
  cursor: pointer;
	font-weight: 400;
	border: 1px solid lightgray;
	font-size: calc(12px + 0.5vw);
  margin: 5px;
  padding: 5px;
  border-radius: 15px;
}
.hash:hover {
	color: royalblue !important;
}
</style>