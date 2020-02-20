<template>
  <div class="container-fluid p-0">
		<nav-bar />
		<h4 class="pt-5">{{ getUserName() }}님</h4>
		<p>반갑습니다. 현재 [{{ getUserRole() }}]으로 로그인하셨습니다.</p>
		<!-- 내 쿠폰 -->
		<div class="card text-center">
			<div class="card-header">
				<ul class="nav nav-tabs card-header-tabs row">
					<li v-if="isOwner" class="li-menu-tab nav-item col-3">
						<div id="tab1" class="dtabs nav-link d-flex align-items-center justify-content-center text-center" @click="showContent(0)">
							<p><fa icon="coffee"/></p>
							<p class="mobile-display-none">내 카페 관리</p>
						</div>
					</li>
					<li class="li-menu-tab nav-item" :class="{'col-3': isOwner, 'col-4': !isOwner}">
						<div id="tab2" class="dtabs nav-link d-flex align-items-center justify-content-center text-center" @click="showContent(1)">
							<p><fa icon="heart"/></p>
							<p class="mobile-display-none">내가 좋아하는 카페</p>
						</div>
					</li>
					<li class="li-menu-tab nav-item" :class="{'col-3': isOwner, 'col-4': !isOwner}">
						<div id="tab3" class="dtabs nav-link d-flex align-items-center justify-content-center text-center" @click="showContent(2)">
							<p><fa icon="pen"/></p>
							<p class="mobile-display-none">내가 쓴 리뷰</p>
						</div>
					</li>
					<li class="li-menu-tab nav-item" :class="{'col-3': isOwner, 'col-4': !isOwner}">
						<div id="tab0" class="dtabs nav-link d-flex align-items-center justify-content-center text-center" @click="showContent(3)">
							<p><fa icon="user-edit"/></p>
							<p class="mobile-display-none">내 정보 수정</p>
						</div>
					</li>
				</ul>
			</div>
			<div class="card-body">
				<div v-if="contentNum === 0">
					<h5 class="card-title mobile-display-block">내 카페 관리</h5>
					<div v-if="myCafe">

						<p class="card-text">내 카페 정보를 수정할 수 있습니다.</p>
						<hr>
						<cafe-list :cafeData="myCafe" />
					</div>
					<router-link to="/editcafe" v-else>
						<p class="card-text">내 카페 페이지를 생성할 수 있습니다.</p>
						<hr>
						<div class="card m-auto" :style="`border: 4px dashed lightgray; width: ${l}px; height: ${l < 400? l : 400}px;`">
							<div class="card-body p-0" style="cursor: pointer;">
								<h1 style="margin: 0" :style="`line-height: ${l < 400? l : 400}px; font-size: 70px`">
									<fa icon="plus-circle" style="color:lightgray"/>
								</h1>
							</div>
						</div>
					</router-link>
				</div>
				<div v-if="contentNum === 1">
					<h5 class="card-title mobile-display-block">내가 좋아하는 카페</h5>
					<cafe-list :cafeData="myFavoriteCafes" />
				</div>
				<div v-if="contentNum === 2">
					<h5 class="card-title mobile-display-block">내가 쓴 리뷰</h5>
					<review-list limits="3" :reviewData="myReviews"/>
				</div>
				<div v-if="contentNum === 3">
					<h5 class="card-title mobile-display-block">내 정보 수정</h5>
					<p class="card-text">이메일을 제외한 정보를 변경할 수 있습니다.</p>
					<hr>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">이메일</span>
						</div>
						<p class="form-control text-left">{{ profile.email }}</p>
					</div>
					<div id="myNickname" class="input-group border mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">닉네임</span>
						</div>
						<input v-model="nicknameToChange" type="text" class="form-control" placeholder="3 ~ 15자로 입력해주세요">
						<div class="input-group-append">
							<button @click="checkNickname" type="button" class="btn btn-outline-secondary">중복확인</button>
						</div>
					</div>
					<div class="input-group border mb-3">
						<div class="input-group-prepend text-center">
							<span style="width:71.125px;" class="input-group-text justify-content-center">이름</span>
						</div>
						<input v-model="profile.name" type="text" class="form-control">
					</div>
					<div id="myPhone" class="input-group border mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">핸드폰</span>
						</div>
						<input v-model="profile.phone" type="text" class="form-control" placeholder="010-0000-0000 형식으로 입력해주세요">
					</div>
					<button id="saveMyDataButton" @click="saveMyData" type="button" class="btn btn-warning btn-lg btn-block">저장</button>
					<div style="height: 40px"></div>
					<!-- Withdrawal modal trigger -->
					<p class="text-right" data-toggle="modal" data-target="#withdrawalModal">회원탈퇴</p>
					<!-- Withdrawal modal -->
					<div class="modal fade" id="withdrawalModal" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="withdrawalModalTitle">회원탈퇴</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<p>라떼는말이야 서비스를 탈퇴하시겠습니까?<br>확인을 위해 이메일을 입력한 후 탈퇴 버튼을 눌러주세요.</p>
									<input type="text" v-model="withdrawalMessage">
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
									<button type="button" class="btn btn-danger" :disabled="withdrawalMessage !== profile.email" @click="withdrawal">탈퇴</button>
								</div>
							</div>
						</div> 
					</div>
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
  faHeart, faCoffee, faPen, faUserEdit, faPlusCircle
} from "@fortawesome/free-solid-svg-icons";
library.add(
  faHeart, faCoffee, faPen, faUserEdit, faPlusCircle
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
			l: window.innerWidth * 0.8,
			active: 0,
			myCafe: [],
			myReviews: [],
			myFavoriteCafes: [],
			contentNum: 1,
			profile: {},
			nicknameToChange: '',
			role: 4,
			roles: {
				4: '회원', // ROLE_USER
				7: '에디터', // ROLE_EDITOR
				6: '사장', // ROLE_OWNER
				5: '관리자' // ROLE_ADMIN
			},
			nicknameCheck: false,
			isOwner: this.getUserRole() === '관리자' || this.getUserRole() === '사장',
			withdrawalMessage: ''
		}
	},
	methods: {
		getUserName() {
			return this.$session.get("username")
		},
		getUserRole() {
			const roles = {
				ROLE_USER: '회원',
				ROLE_EDITOR: '에디터',
				ROLE_OWNER: '사장',
				ROLE_ADMIN: '관리자'
			}
			return roles[this.$session.get("roles")[0]] || '회원'
		},
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
				.then(function() {
					notification.innerText = '변경 가능합니다'
					myNickname.childNodes[1].style.backgroundColor = "white"
					this.nicknameCheck = true
				})
				.catch(function() {
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
				notification.innerText = '닉네임 중복 체크 해주세요'
				notification.hidden = false
				myNickname.childNodes[1].style.backgroundColor = "rgba(255, 0, 0, 0.100)"
				return
			}
			if (phone === '' ||
				11 < phone.length && phone.length < 14 && phone[3] === '-' && phone[phone.length-5] === '-') {
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
		withdrawal() {
			axios.delete(`${this.$store.state.constants.SERVER}/withdrawal`, {headers: {Authorization: "Bearer " + this.$session.get("jwt")}})
				.then(response => {
					console.log(response)
				})
				.catch(error => {
					console.log(error)
				})
		},
		showContent(num) {
			this.contentNum = num
			if (num === 0) {
				this.active = 0
				axios.get(`${this.$store.state.constants.SERVER}/mycafe`, {headers: {Authorization: "Bearer " + this.$session.get("jwt")}})
					.then(response => {
						this.myCafe = [response.data.cafe]
					})
					.catch(error => {
						console.log(error)
					})
			} else if (num === 1) {
				this.active = 1
				axios.get(`${this.$store.state.constants.SERVER}/cafe/my`, {headers: {Authorization: "Bearer " + this.$session.get("jwt")}})
					.then(response => {
						this.myFavoriteCafes = response.data
					})
					.catch(error => {
						console.log(error)
					})
			} else if (num === 2) {
				this.active = 2
				axios.get(`${this.$store.state.constants.SERVER}/post/my`, {headers: {Authorization: "Bearer " + this.$session.get("jwt")}})
					.then(response => {
						this.myReviews = response.data.reverse()
					})
					.catch(error => {
						console.log(error)
					})
			}	else if (num === 3) {
				this.active = 3
				axios.get(`${this.$store.state.constants.SERVER}/user/my`, {headers: {Authorization: "Bearer " + this.$session.get("jwt")}})
				.then(response => {
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
			let notification = document.getElementById('notification')
			let notification2 = document.getElementById('notification2')
			notification.hidden = true
			notification2.hidden = true
		}
	},
	mounted() {
		if (this.isOwner) {
			this.showContent(0)
		} else {
			this.showContent(1)
		}
	}
}
</script>

<style>
.dtabs {
	border: 1px solid lightgray !important;
	border-bottom: 0 !important;
	background: mediumaquamarine;
	color: white !important; 
}
.dtabs:active {
	background: white !important;
}
.mobile-display-block {
	display: none
}
.mobile-display-none {
	display: block
}
@media only screen and (max-width: 991px) {
	.mobile-display-none {
		display: none
	}
	.mobile-display-block {
		display: block
	}
}
.li-menu-tab {
	padding: 0 0.25rem !important;
	cursor: pointer;
}
.li-menu-tab:active {
	background: white;
}
.card-header-tabs p {
	margin: 0 0.3rem;
}
</style>