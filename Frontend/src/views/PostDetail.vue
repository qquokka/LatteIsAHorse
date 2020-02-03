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
			<li v-for="comment in comments" :key="comment.id">{{ comment.writer_name }} : {{ comment.content }} at {{ comment.created_at }}
				<a v-if="comment.writer_name === $session.get('username')" @click.prevent="deleteComment(comment.id)">[X]</a>
			</li>
		</div>
		<form v-if="isLogined" @submit.prevent="addComment">
			<input type="text" v-model="addCommentContent">
			<button>등록</button>
		</form>
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
			post: {},
			comments: [],
			error: null,
			isWriter: false,
			addCommentContent: null,
			isLogined: false
		}
	},
	methods: {
		getPost () {
			axios.get(`${this.$store.state.constants.SERVER}/post/${this.postId}`)
					.then(response => {
						this.post = response.data
					})
		},
		updatePost() {
			console.log('수정 시작')
			this.$router.push(`/post/${this.postId}/edit`)
		},
		deletePost() {
			axios.delete(`${this.$store.state.constants.SERVER}/post/${this.postId}`, {headers: {'Authorization': "Bearer " + this.$session.get('jwt')}})
				.then(response => {
					console.log(response)
					this.$router.push('/posts')
				})
		},
		getComment () {
			axios.get(`${this.$store.state.constants.SERVER}/comments/${this.postId}`)
					.then(response => {
						this.comments = response.data
					})
		},
		addComment() {
			if(this.addCommentContent === null || this.addCommentContent === ''){
				alert('댓글 내용을 작성해주세요!')
				return
			}
			let comment = {
				content: this.addCommentContent,
				post_id: this.postId
			}
			axios.post(`${this.$store.state.constants.SERVER}/comments`, comment, {headers: {'Authorization': "Bearer " + this.$session.get('jwt')}})
				.then(response => {
					console.log(response)
					this.getComment()
				})
				.catch(error => {
					console.log(error)
				})
		},
		deleteComment(commentId) {
			axios.delete(`${this.$store.state.constants.SERVER}/comments/${commentId}`, {headers: {'Authorization': "Bearer " + this.$session.get('jwt')}})
					.then(response => {
						console.log(response)
						this.getComment()
					})
					.catch(error => {
						console.log(error)
					})
		}
	},
	mounted() {
		setTimeout(() => {
			window.scrollBy(0,1);
		},120)
		axios.get(`${this.$store.state.constants.SERVER}/post/${this.postId}`)
					.then(response => {
						this.post = response.data
						console.log(this.post.title)
						if (this.post.writer_name === this.$session.get('username')) {
							this.isWriter = true
						}
					})
		axios.get(`${this.$store.state.constants.SERVER}/comments/${this.postId}`)
				.then(response => {
					this.comments = response.data
				})
				.catch(error => {
					console.log(error)
				})
		this.isLogined = this.$session.has('jwt')}
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