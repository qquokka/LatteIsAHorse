<template>
  <div>
		<NavBar/>
    <h3 class="border-bottom p-2">{{ post.title }}</h3><span><i class="fas fa-thumbs-up"> {{ post.like_count }}</i></span>
		<p>by {{ post.writer_name }} at {{ post.created_at }} </p>
		<img :src="post.thumbnail" width="100%" height="300px">
		<p class="post-preview">{{ post.content }}</p>
<!-- 
    <div v-if="post" class="content">
      <h2>{{ post.title }}</h2>
			<span><i class="fas fa-thumbs-up"> {{ post.like_count }}</i></span>
			<p>by {{ post.writer_name }} at {{ post.created_at }} </p>
      <p>{{ post.content }}</p>
    </div> -->
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import axios from 'axios'

export default {
	name: 'PostDetail',
	components: {
		NavBar
	},
	props: [
		'postId'
	],
	data() {
		return {
			loading: false,
      post: null,
      error: null
		}
	},
	method: {
		getPost () {
			axios.get(`${this.$store.state.constants.SERVER}/post/${this.postId}`)
					.then(response => {
						console.log(response)
						this.post = response.data
					})
    }
	},
	created () {
		axios.get(`${this.$store.state.constants.SERVER}/post/${this.postId}`)
					.then(response => {
						console.log(response)
						this.post = response.data
						console.log(this.post)
					})
  },
	watch: {
    // 라우트가 변경되면 메소드를 다시 호출됩니다.
    $route: function() {
			this.fetchData()
		}
  }
}
</script>

<style>

</style>