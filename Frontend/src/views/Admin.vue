<template>
  <div class="container-fluid">
		<nav-bar blackOrWhite="true" />
		<h1>사용자 관리 페이지</h1>
		<div>
			<h2>사용자</h2>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col"><p>Id</p></th>
						<th scope="col"><p>닉네임</p></th>
						<th scope="col"><p>이메일</p></th>
						<th scope="col"><p>이름</p></th>
						<th scope="col"><p>전화번호</p></th>
						<th scope="col"><p>Role</p></th>
						<th scope="col"><p>Active</p></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="user in users" :key="user.id">
						<th scope="row"><p>{{ user.id }}</p></th>
						<td><p>{{ user.username }}</p></td>
						<td><p>{{ user.email }}</p></td>
						<td><p>{{ user.name }}</p></td>
						<td><p>{{ user.phone }}</p></td>
						<td>
							<!-- 클릭했을 때 changeRole 함수가 실행이 안 됨 -->
							<p>{{ user.roles[0].id }}</p>
							<select v-model="user.roles[0].id">
								<option v-for="role in roles" @click.prevent="changeRole(user.id, role.id)" :value="role.id" :key="role.id">
									{{ role.name }}
								</option>
							</select>
						</td>
						<td>
							<button @click="toggleUserActive(user.id)" type="button" :v-model="user.active"
								class="btn" :class="{'btn-outline-primary': user.active, 'btn-secondary': !user.active}">
								<p>{{ user.active ? '' : '탈퇴한 ' }}회원</p>
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<hr>
		<div>
			<h2>카페</h2>
		</div>
		<div>
			<h2>리뷰</h2>
		</div>
		<div>
			<h2>댓글</h2>
		</div>
	</div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import axios from 'axios'

export default {
	name: 'Admin',
	components: {
		NavBar
	},
	data() {
		return {
			users: [],
			roles: [
				{ id: 4, name: '일반 회원' },
				{ id: 7, name: '에디터' },
				{ id: 6, name: '사장' },
				{ id: 5, name: '관리자' }
			]
		}
	},
	methods: {
		getUsers() {
			axios.get(`${this.$store.state.constants.SERVER}/users`)
				.then(response => {
					// {createdAt: "2020-01-23T01:38:06Z", updatedAt: "2020-01-23T01:38:06Z", id: 3, name: "Hyun Jin Won", username: "jwkim", …}
					console.log(response)
					this.users = response.data
				})
				.catch(error => {
					console.log(error)
				})
		},
		changeRole(userId, roleId) {
			let userSelected = this.users.filter(user => user.id === userId)[0]
			console.log(userSelected.roles[0])
			userSelected.roles[0] = this.roles.filter(role => role.id === roleId)[0]
			console.log(userSelected.roles[0])
		},
		toggleUserActive(userId) {
			let userSelected = this.users.filter(user => user.id === userId)[0]
			userSelected.active = !userSelected.active
		}
	},
	mounted() {
		this.getUsers()
	}
}
</script>

<style>

</style>