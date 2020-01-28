<template>
  <form @submit.prevent="signup" class="p-3">
    <div class="form-group">
      <input type="email" class="form-control" id="signupEmail" placeholder="ID (your_id@example.com)" v-model="credentials.email">
    </div>
    <div class="form-group">
      <input type="password" class="form-control" id="signupPW" placeholder="비밀번호(6~20자리)를 입력해주세요" v-model="credentials.password">
      <!-- :style="{ 'background-color': bgColor.password }" -->
    </div>
    <div class="form-group">
      <input type="password" class="form-control" id="signupPW2" placeholder="비밀번호를 다시 입력해주세요" v-model="password_check">
    </div>
    <div class="form-group">
      <input type="text" class="form-control" id="name" placeholder="이름(4~40자리)을 입력해주세요" v-model="credentials.name">
    </div>
    <div class="form-group">
      <input type="text" class="form-control" id="nickname" placeholder="닉네임(3~15자리)을 입력해주세요" v-model="credentials.username">
    </div>
    <b><small>저희 회원이신가요? <slot></slot></small></b>
    <button type="submit" class="btn btn-block btn-outline-warning font-weight-bolder">회원가입</button>
  </form>
</template>

<script>
import axios from 'axios'
// import router from '../router'

export default {
  name: 'SignupForm',
  data() {
    return {
      credentials: {
        email: '',
        password: '',
        name: '',
        username: ''
      },
      password_check: '',
      isOkay: {
        // email: false,
        password: false,
        name: false,
        username: false
      // },
      // bgColor: {
      //   password: this.isOkay.password? 'white' : 'red',
      //   name: this.isOkay.name? 'white' : 'red',
      //   username: this.isOkay.username? 'white' : 'red'
      }
    }
  },
  methods: {
    signup(credentials) {
      if (this.isOkay.password && this.isOkay.username && this.isOkay.name) {
        console.log(credentials)
        axios.post('http://192.168.31.111:8080/api/auth/signup', credentials)
          .then(response => {
              console.log('회원가입 성공')
              console.log(response.data.success)
              console.log(response.data.message)
            })
      } else {
        console.log('회원가입 실패')
      }
    }
  },
  watch: {
    credentials: {
      deep: true,
      handler() {
        let pw_len = this.credentials.password.length
        if (5 < pw_len || pw_len < 21 && this.credentials.password === this.password_check) {
          this.isOkay.password = true;
        } else {
          console.log('this.isOkay.password', this.isOkay.password)
          this.isOkay.password = false;
        }
        let nick_len = this.credentials.username.length
        if (2 < nick_len || nick_len < 16) {
          this.isOkay.username = true;
        } else {
          this.isOkay.username = false;
        }
        let name_len = this.credentials.name.length
        if (3 < name_len || name_len < 41) {
          this.isOkay.name = true;
        } else {
          this.isOkay.name = false;
        }
      }
    }
  }
}
</script>

<style>
  .warning-red {
    background-color: red !important;
  }
</style>