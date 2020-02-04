<template>
	<div class="container-fluid px-3">
		<img :src="info.thumbnail" :alt="info.cafe_name">
		<h1>{{ info.cafe_name }}</h1>
		<hr>
		<p>좋아요: {{ info.like_count }}</p>
		<p>태그: {{ info.tag }}</p>
		<hr>
		<h2>정보</h2>
		<div class="row">
			<div class="col-2"><p>주소</p></div>
			<div class="col-10"><p>{{ info.cafe_address }}</p></div>
			<div class="col-2"><p>전화번호</p></div>
			<div class="col-10"><p>{{ info.cafe_phone }}</p></div>
		</div>
		<hr>
		<h2>메뉴</h2>
		<div class="row">
			<div class="col-2"><p>메뉴</p></div>
			<div class="col-2"><p>가격</p></div>
		</div>
		<div v-for="menu in menus" :key="menu.id" class="row">
			<div class="col-2"><p>{{ menu.product }}</p></div>
			<div class="col-2"><p>{{ menu.price }}</p></div>
			<div class="col-8"><p>{{ menu.description }}</p></div>
		</div>
		<hr>
		<h2>리뷰</h2>
		<div v-for="review in reviews" :key="review.id">
			<router-link :to="`/post/${review.id}`">
				<div class="card my-3">
					<img class="card-img-top" :src="review.thumbnail" :alt="review.title">
					<div class="card-body">
						<h5 class="card-title">{{ review.title }}</h5>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item"><p class="my-0">{{ review.created_at }} 작성 | {{ review.updated_at }} 수정</p></li>
						<li class="list-group-item" v-html="review.content"></li>
					</ul>
				</div>
			</router-link>
		</div>
	</div>
</template>

<script>

import axios from 'axios'

export default {
	name: 'CafePage',
	props: [
		'cafeId'
	],
	data() {
		return {
			info: {},
			menus: [],
			reviews: [],
			isOpen: true
		}
	},
	methods: {
		getData() {
			axios.get(`${this.$store.state.constants.SERVER}/cafe/detail/${this.cafeId}`)
					.then(response => {
						console.log(response)
						this.info = response.data.cafeinfo
						this.reviews = response.data.post
						this.menus = response.data.menu
						
						this.reviews.forEach(review => {
							review.created_at = review.created_at.replace(/T|Z/g, " ")
							review.updated_at = review.updated_at.replace(/T|Z/g, " ")


						})
					})
					.catch(error => {
						console.log(error.data)
					})
		}
	},
	created() {
		this.getData()
	}
}
</script>

<style>
</style>