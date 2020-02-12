<template>
  <div class="container-fluid">
		<h1>내 정보</h1>
			<div class="row">
				<div class="d-inline col-3">내 카페 관리</div>
				<div class="d-inline col-3">
					<h4>내가 좋아하는 카페</h4>
					<!-- <cafe-list :cafeData="" /> -->
				</div>
				<div class="d-inline col-3" @click="showContent(2)">
					<h4>내가 쓴 리뷰</h4>
				</div>
				<div class="d-inline col-3" @click="showContent(3)">
					<h4>회원 정보 수정</h4>
				</div>
				<div class="container" style="height:50vh;background:lavender">
					<div v-if="contentNum === 3">
						<div>
							<p>이메일: {{ email }} </p>
							<p id="myNickname">닉네임: <input  v-model="nickname" type="text" placeholder="3 ~ 15자로 입력해주세요"><button @click="checkNickname">중복확인</button></p>
							<p>이름: <input type="text" v-model="name"></p>
							<p>전화번호: <input type="text" v-model="phone" placeholder="'-' 없이 입력해주세요"></p>
							<p>회원등급: {{ role }}</p>
							<button @click="saveMyData">변경</button>
						</div>
						<div>
							<h5>비밀번호 변경</h5>
							<input v-model="password" type="password" placeholder="변경할 비밀번호를 6 ~ 20자로 입력해주세요">
							<input v-model="password2" type="password" placeholder="위에 입력한 비밀번호를 다시 입력해주세요">
							<button @click="saveMyPW">변경</button>
						</div>
					</div>
				</div>
			</div>
		<!-- 내 쿠폰 -->
  </div>
</template>

<script>
import axios from 'axios'
// import CafeList from '../components/CafeList.vue'

export default {
	name: 'Profile',
	components: {
		// CafeList,
	},
	data() {
		return {
			contentNum: 0,
			role: '',
			email: '',
			nickname: '',
			isOkay: {nicknameCheck: true},
			name: '',
			phone: '',
			
			pw: {
				password: '',
				password2: ''
			}
		}
	},
	methods: {
		checkNickname() {
			const myNickname = document.querySelector('#myNickname')
			let notification = document.querySelector('#notification')
			if (notification === null) {
				notification = document.createElement('small')
				notification.id = 'notification'
				notification.color = 'red'
				myNickname.parentElement.insertBefore(notification, myNickname)
			}
			axios.get(`${this.$store.state.constants.SERVER}/user/checkusername/${this.nickname}`)
				.then(response => {
					console.log(response.data)
					notification.innerText = '변경 가능합니다'
					this.isOkay.nicknameCheck = true
				})
				.catch(error => {
					console.log(error)
					notification.innerText = '이미 존재하는 닉네임입니다'
					myNickname.childNodes[1].style.backgroundColor = "rgba(255, 0, 0, 0.100)"
					this.isOkay.nicknameCheck = false
				})
		},
		saveMyData() {
		},
		saveMyPW() {
		},
		showContent(num) {
			this.contentNum = num
			if (num === 2) {
				console.log(num)

			}
			else if (num === 3) {
				axios.get(`${this.$store.state.constants.SERVER}/user/my`, {headers: {Authorization: "Bearer " + this.$session.get("jwt")}})
				.then(response => {
					console.log(response.data)

					this.nickname = response.data.username
					this.name = response.data.name
					this.phone = response.data.phone
					this.role = response.data.roles[0].name
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
</style>