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
							<p>이메일: {{ profile.email }} </p>
							<p id="myNickname">닉네임: <input  v-model="nicknameToChange" type="text" placeholder="3 ~ 15자로 입력해주세요"><button @click="checkNickname">중복확인</button></p>
							<p>이름: <input type="text" v-model="profile.name"></p>
							<p id="myPhone">전화번호: <input type="text" v-model="profile.phone" placeholder="010-0000-0000 형식으로 입력해주세요"></p>
							<p>회원등급: {{ roles[role] }}</p>
							<button id="saveMyDataButton" @click="saveMyData">변경</button>
						</div>
						<div>
							<h5>비밀번호 변경</h5>
							<!-- <input v-model="password" type="password" placeholder="변경할 비밀번호를 6 ~ 20자로 입력해주세요">
							<input v-model="password2" type="password" placeholder="위에 입력한 비밀번호를 다시 입력해주세요">
							<button @click="saveMyPW">변경</button> -->
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
			profile: {},
			nicknameToChange: '',
			role: 4,
			roles: {
				4: '일반회원',
				7: '에디터',
				6: '사장님',
				5: '관리자'
			},
			nicknameCheck: false,
			// pw: {
			// 	password: '',
			// 	password2: ''
			// }
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
			if (this.nicknameToChange.length < 16) {
				axios.get(`${this.$store.state.constants.SERVER}/user/checkusername/${this.nicknameToChange}`)
					.then(response => {
						console.log(response.data)
						notification.innerText = '가능합니다'
						myNickname.childNodes[1].style.backgroundColor = "white"
						this.nicknameCheck = true
					})
					.catch(error => {
						console.log(error)
						notification.innerText = '이미 존재하는 닉네임입니다'
						myNickname.childNodes[1].style.backgroundColor = "rgba(255, 0, 0, 0.100)"
						this.nicknameCheck = false
					})
			} else {
				notification.innerText = '3 ~ 15자로 입력해주세요'
				myNickname.childNodes[1].style.backgroundColor = "rgba(255, 0, 0, 0.100)"
				this.nicknameCheck = false
			}
		},
		saveMyData() {
			const phone = this.profile.phone
			const myPhone = document.getElementById('myPhone')
			let notification = document.getElementById('notification')
			let notification2 = document.getElementById('notification2')
			if (notification2 === null) {
				notification2 = document.createElement('small')
				notification2.id = 'notification2'
				notification2.color = 'red'
				myPhone.parentElement.insertBefore(notification2, myPhone)
			}
			if (this.nicknameToChange !== this.profile.username && this.nicknameCheck === false) {
				console.log('중복체크해야함')
			}
			if (phone === '' ||
				11 < phone.length && phone.length < 14 && phone[3] === '-' && phone[phone.length-5] === '-') {
					console.log('전화번호 가능')
					notification.hidden = true
					notification2.hidden = true
					myPhone.childNodes[1].style.backgroundColor = "white"
			} else {
				notification2.innerText = '010-0000-0000 형식으로 입력해주세요'
				myPhone.childNodes[1].style.backgroundColor = "rgba(255, 0, 0, 0.100)"
			}
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
</style>