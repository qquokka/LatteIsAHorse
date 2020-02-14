<template>
  <div class="container-fluid p-0">
		<nav-bar blackOrWhite="true" />
		<h1 class="my-5">내 정보</h1>
		<!-- 내 쿠폰 -->
		<div class="card text-center">
			<div class="card-header">
				<ul class="nav nav-tabs card-header-tabs">
					<li class="li-menu-tab nav-item">
						<a class="nav-link active" @click="showContent(0)"><fa icon="home"/><span class="menu-tab"> 내 카페</span></a>
					</li>
					<li class="li-menu-tab nav-item">
						<a class="nav-link active" @click="showContent(1)"><fa icon="coffee"/><span class="menu-tab"> 내가 좋아하는 카페</span></a>
					</li>
					<li class="li-menu-tab nav-item">
						<a class="nav-link active" @click="showContent(2)"><fa icon="pen"/><span class="menu-tab"> 내가 쓴 리뷰</span></a>
					</li>
					<li class="li-menu-tab nav-item">
						<a class="nav-link active" @click="showContent(3)"><fa icon="user-edit"/><span class="menu-tab"> 내 정보 수정</span></a>
					</li>
				</ul>
			</div>
			<div class="card-body">
				<div v-if="contentNum === 0">
						내 카페
					</div>
					<div v-if="contentNum === 1">
						<cafe-list :cafeData="myFavoriteCafes" />
					</div>
					<div v-if="contentNum === 2">
						<review-list limits="3" :reviewData="myReviews"/>
					</div>
					<div v-if="contentNum === 3">
						<h5 class="card-title">내 정보 변경하기</h5>
						<p class="card-text">이메일을 제외한 정보를 변경할 수 있습니다.</p>
						<hr>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">이메일</span>
							</div>
							<p class="form-control text-left">{{ profile.email }}</p>
						</div>
						<div id="myNickname" class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">닉네임</span>
							</div>
							<input v-model="nicknameToChange" type="text" class="form-control" placeholder="3 ~ 15자로 입력해주세요">
							<div class="input-group-append">
								<button @click="checkNickname" type="button" class="btn btn-outline-secondary">중복확인</button>
							</div>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">이름</span>
							</div>
							<input v-model="profile.name" type="text" class="form-control">
						</div>
						<div id="myPhone" class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">핸드폰</span>
							</div>
							<input v-model="profile.phone" type="text" class="form-control" placeholder="010-0000-0000 형식으로 입력해주세요">
						</div>
						<button id="saveMyDataButton" @click="saveMyData" type="button" class="btn btn-primary btn-lg btn-block">저장</button>
					</div>

					
			</div>
		</div>
  </div>
</template>

<script>
import axios from 'axios'
import NavBar from "@/components/NavBar.vue"
import CafeList from '../components/CafeList.vue'
import ReviewList from "@/views/section/ReviewList.vue"

import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faHome, faCoffee, faPen, faUserEdit
} from "@fortawesome/free-solid-svg-icons";
library.add(
  faHome, faCoffee, faPen, faUserEdit
);

export default {
	name: 'Profile',
	components: {
		NavBar,
		CafeList,
		ReviewList
	},
	data() {
		return {
			myReviews: [],
			myFavoriteCafes: [],
			contentNum: 0,
			profile: {},
			nicknameToChange: '',
			role: 4,
			roles: {
				4: '회원', // ROLE_USER
				7: '에디터', // ROLE_EDITOR
				6: '사장', // ROLE_OWNER
				5: '관리자' // ROLE_ADMIN
			},
			nicknameCheck: false
		}
	},
	methods: {
		checkNickname() {
			const myNickname = document.getElementById('myNickname')
			let notification = document.getElementById('notification')
			if (notification === null) {
				notification = document.createElement('small')
				notification.id = 'notification'
				notification.color = 'red'
				myNickname.parentElement.insertBefore(notification, myNickname)
			}
			notification.hidden = false
			axios.get(`${this.$store.state.constants.SERVER}/user/checkusername/${this.nicknameToChange}`)
				.then(response => {
					console.log(response.data)
					notification.innerText = '변경 가능합니다'
					myNickname.childNodes[1].style.backgroundColor = "white"
					this.nicknameCheck = true
				})
				.catch(error => {
					console.log(error)
					notification.innerText = '닉네임이 이미 존재하거나 너무 깁니다'
					myNickname.childNodes[1].style.backgroundColor = "rgba(255, 0, 0, 0.100)"
					this.nicknameCheck = false
				})
		},
		saveMyData() {
			const phone = this.profile.phone
			const myNickname = document.getElementById('myNickname')
			const myPhone = document.getElementById('myPhone')
			let notification = document.getElementById('notification')
			if (notification === null) {
				notification = document.createElement('small')
				notification.id = 'notification'
				notification.color = 'red'
				myNickname.parentElement.insertBefore(notification, myNickname)
			}
			let notification2 = document.getElementById('notification2')
			if (notification2 === null) {
				notification2 = document.createElement('small')
				notification2.id = 'notification2'
				notification2.color = 'red'
				myPhone.parentElement.insertBefore(notification2, myPhone)
			}
			if (this.nicknameToChange !== this.profile.username && !this.nicknameCheck) {
				console.log('중복체크해야함')
				notification.innerText = '닉네임 중복 체크 해주세요'
				notification.hidden = false
				myNickname.childNodes[1].style.backgroundColor = "rgba(255, 0, 0, 0.100)"
				return
			}
			if (phone === '' ||
				11 < phone.length && phone.length < 14 && phone[3] === '-' && phone[phone.length-5] === '-') {
					console.log('전화번호 가능')
					notification.hidden = true
					notification2.hidden = true
					myPhone.childNodes[1].style.backgroundColor = "white"
					axios.patch(`${this.$store.state.constants.SERVER}/userinfo`,
					{username: this.nicknameToChange, name: this.profile.name, phone: this.profile.phone},
					{headers: {Authorization: "Bearer " + this.$session.get("jwt")}})
						.then(response => {
							console.log(response)
						})
						.catch(error => {
							console.log(error)
						})
			} else {
				notification2.hidden = false
				notification2.innerText = '010-0000-0000 형식으로 입력해주세요'
				myPhone.childNodes[1].style.backgroundColor = "rgba(255, 0, 0, 0.100)"
			}
		},
		showContent(num) {
			this.contentNum = num
			if (num === 1) {
				axios.get(`${this.$store.state.constants.SERVER}/cafe/my`, {headers: {Authorization: "Bearer " + this.$session.get("jwt")}})
					.then(response => {
						console.log(response.data)
						this.myFavoriteCafes = response.data
					})
					.catch(error => {
						console.log(error)
					})
			} else if (num === 2) {
				axios.get(`${this.$store.state.constants.SERVER}/post/my`, {headers: {Authorization: "Bearer " + this.$session.get("jwt")}})
					.then(response => {
						console.log(response.data)
						this.myReviews = response.data.reverse()
					})
					.catch(error => {
						console.log(error)
					})
			}	else if (num === 3) {
				axios.get(`${this.$store.state.constants.SERVER}/user/my`, {headers: {Authorization: "Bearer " + this.$session.get("jwt")}})
				.then(response => {
					console.log(response.data)
					this.role = response.data.roles[0].id
					this.profile = response.data
					this.nicknameToChange = response.data.username
					// this.$set(this.isOkay, nickname, false)
					this.nicknameCheck = false
				})
				.catch(error => {
					console.log(error)
				})
			}
		}
	}
}
</script>

<style>
@media only screen and (max-width: 600px) {
	.menu-tab {
		display: none
	}
}
.li-menu-tab {
	margin: 0.15rem
}
</style>