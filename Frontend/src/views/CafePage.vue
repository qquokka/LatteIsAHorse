<template>
	<div class="container-fluid px-3">
		<img :src="info.thumbnail" :alt="info.cafe_name">
		<h1>
			{{ info.cafe_name }}
			<span v-if="isOpen" class="badge badge-primary">영업중</span>
			<span v-else class="badge badge-secondary">준비중</span>
		</h1>
		<hr>
		<p><button click="pushLike">좋아요</button>{{ info.like_count }}</p>
		<p>태그: {{ info.tag }}</p>
		<hr>
		<h2>정보</h2>
		<div class="row">
			<div class="col-2"><p>주소</p></div>
			<div class="col-10"><p>{{ info.cafe_address }}</p></div>
			<div class="col-2"><p>전화번호</p></div>
			<div class="col-10"><p>{{ info.cafe_phone }}</p></div>
		</div>
		<h5>영업시간</h5>
		<div class="row">
			<div class="col-2"><p>요일</p></div>
			<div class="col-5"><p>여는 시간</p></div>
			<div class="col-5"><p>닫는 시간</p></div>
		</div>
		<div v-for="i in 7" :key="time[i - 1][2]" class="row" :class="{isToday: today === i - 1}">
			<div class="col-2"><p>{{ time[i - 1][2] }}요일</p></div>
			<div class="col-5"><p>{{ time[i - 1][0] }}</p></div>
			<div class="col-5"><p>{{ time[i - 1][1] }}</p></div>
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
		<h2>리뷰 ({{ reviews.length }}개)</h2>
		<hr>
		<h3><router-link :to="`/cafe/${cafeId}/posts/create`" v-if="isLogined">리뷰 쓰기</router-link></h3>
		<hr>
		<div v-for="review in reviews" :key="review.id">
			<div class="card my-3">
				<img :src="review.thumbnail" class="card-img-top" :alt="review.title" style="width:300px;">
				<div class="card-body">
					<h5 class="card-title">{{ review.title }}</h5>
					<p>작성자: {{ review.writer_name }} | {{ review.updated_at }} 작성</p>
					<button v-if="review.writer_name === $session.get('username')" click="deleteReview(review.id)">삭제</button>
				</div>
				<div class="card-body">
					<p v-html="review.content" />
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import axios from 'axios'
import moment from 'moment'

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
			time: [],
			isOpen: false,
			isLogined: false,
			today: 0
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
						this.time = response.data.time

						// 리뷰 작성시간이 12시간 이내이면 '3시간 전' 이런 식으로 나오게 하고, 12시간 이전이면 날짜 시간 다 표시
						let now = Date.now()
						this.reviews.forEach(review => {
							review.updated_at = review.updated_at.slice(0,19)
							let date = new Date(review.updated_at)
							if (now - Date.parse(date) <= 43200000) {
								review.updated_at = moment(review.updated_at).locale('ko').fromNow()
							} else {
								review.updated_at = moment(review.updated_at).locale('ko').format('llll')
							}
						})

						now = new Date(now)
						this.today = now.getDay()
						let openTime = new Date(response.data.time[this.today][0].slice(0,19))
						let closeTime = new Date(response.data.time[this.today][1].slice(0,19))
						let nowTime = now.getHours() * 100 + now.getMinutes()
			
						let closeHour = closeTime.getHours()
						// 새벽 1시에 끝나면 25시에 끝난다고 생각
						if (openTime.getDate() !== closeTime.getDate()) {
							closeHour += 24
						}
						if (openTime.getHours() * 100 + openTime.getMinutes() <= nowTime && nowTime < closeHour * 100 + closeTime.getMinutes()) {
							this.isOpen = true
						} else {
							this.isOpen = false
						}
						let days = '일월화수목금토'
						for (let i = 0; i < 7; i++) {
							for (let j = 0; j < 2; j++) {
								this.time[i][j] = this.time[i][j].slice(11, 16)
							}
							this.time[i].push(days[i])
						}
					})
					.catch(error => {
						console.log(error.data)
					})
		},
		// deleteReview(reviewId) {
		// 	axios.delete(`${this.$store.state.constants.SERVER}/post/${reviewId}`, {headers: {'Authorization': "Bearer " + this.$session.get('jwt')}})
		// 		.then(response => {
		// 			console.log(response)
		// 		})
		// },
		pushLike() {
			
		}
	},
	created() {
		this.getData()
	},
	mounted() {
		this.isLogined = this.$session.has('jwt')
	}
}
</script>

<style>
.isToday {
	background-color: rgba(255, 238, 0, 0.438);
}
</style>