<template>
  <div id="postDetail">
	<NavBar :iswhite="true" />
	<div class="container">
		<h1 class="display-3 mx-5" style="padding-top:6rem;font-weight:700">{{ post.title }}</h1>
		<h5>{{post.created_at.slice(0,10) }}</h5>
		<h1 class="border-bottom pb-4"><span class="text-muted small">written by</span> {{ post.writer_name }} </h1>
		<p v-html="post.content"></p>
		<button v-if="isWriter" @click="updatePost">수정</button>
		<button v-if="isWriter" @click="deletePost">삭제</button>

		<h4 class="mt-5 pt-5">Comments</h4>
		<div class="container comments">
			댓글 갯수: {{ comments.length }}
		</div>
		
	</div>
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
			comments: [],
			error: null,
			isWriter: false
		}
	},
	methods: {
		getPost () {
			axios.get(`${this.$store.state.constants.SERVER}/post/${this.postId}`)
					.then(response => {
						this.post = response.data
					})
		},
		getComment () {
			axios.get(`${this.$store.state.constants.SERVER}/comments/${this.postId}`)
					.then(response => {
						this.comments = response.data
					})
		},
		updatePost() {
			console.log('수정 시작')
			this.$router.push('/posts/create')
		},
		deletePost() {
			axios.delete(`${this.$store.state.constants.SERVER}/post/${this.postId}`, {headers: {'Authorization': "Bearer " + this.$session.get('jwt')}})
				.then(response => {
					console.log(response)
					this.$router.push('/posts')
				})
		}
	},
	created () {
		axios.get(`${this.$store.state.constants.SERVER}/post/${this.postId}`)
					.then(response => {
						console.log(response)
						this.post = response.data
						console.log(this.post)
						if (this.post.writer_name === this.$session.get('username')) {
							this.isWriter = true
						}
					})
	},
	mounted() {
		setTimeout(() => {
			window.scrollBy(0,1);
		},120)
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
.comments {
	min-height: 10rem;
	width: 80%;
	margin-bottom: 5rem;
	box-shadow: 3px 3px 10px gray;
}
</style>