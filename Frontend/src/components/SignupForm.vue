<template>
  <form @submit.prevent="signup" class="p-3">
    <div class="form-group">
      <p v-show="showText('email')" class="text-left text-danger mb-1"><small>이메일을 'your_id@example.com' 형식으로 입력해주세요</small></p>
      <input type="email" class="form-control" id="email" placeholder="ID (your_id@example.com)" v-model="email"
      :style="{ 'background-color': bgColor.email }">
    </div>
    <div class="form-group">
      <p v-show="showText('nickname')" class="text-left text-danger mb-1"><small>닉네임을 3 ~ 15자로 입력해주세요</small></p>
      <input type="text" class="form-control" id="nickname" placeholder="닉네임(3~15자리)을 입력해주세요" v-model="nickname"
      :style="{ 'background-color': bgColor.nickname }">
    </div>
    <div class="form-group">
      <p v-show="showText('password')" class="text-left text-danger mb-1"><small>비밀번호를 6 ~ 20자로 입력해주세요</small></p>
      <input type="password" class="form-control" id="password" placeholder="비밀번호" v-model="pw.password"
      :style="{ 'background-color': bgColor.password }">
    </div>
    <div class="form-group">
      <p v-show="showText('passwordCheck')" class="text-left text-danger mb-1"><small>위에 입력한 비밀번호와 동일하게 입력해주세요</small></p>
      <input type="password" class="form-control" id="passwordCheck" placeholder="비밀번호를 확인" v-model="pw.passwordCheck"
      :style="{ 'background-color': bgColor.passwordCheck }">
    </div>
    <b><small>저희 회원이신가요? <slot></slot></small></b>
    <button id="signupButton" type="submit" class="btn btn-block btn-outline-warning font-weight-bolder" disabled>회원가입</button>
  </form>
</template>
<script>
import axios from 'axios'
// import router from '../router'

export default {
  name: 'SignupForm',
  data() {
    return {
      email: '',
      pw: {
        password: '',
        passwordCheck: ''
      },
      nickname: '',
      isOkay: {
        email: false,
        password: false,
        passwordCheck: false,
        nickname: false
      },
      okay: false,
      bgColor: {
        email: '',
        password: '',
        passwordCheck: '',
        nickname: ''
      },
      warningColor: 'rgba(255, 0, 0, 0.100) !important'
    }
  },
  methods: {
    signup() {
      if (this.okay) {
        console.log('회원가입 가능')
        const credentials = {
          'email': this.email,
          'password': this.pw.password,
          'username': this.nickname
        }
        axios.post(`${this.$store.state.constants.SERVER}/signup`, credentials)
          .then(response => {
              console.log('회원가입 성공')
              console.log(response)
              console.log(response.data.success)
              console.log(response.data.message)
              // 로그인 시켜주기
            })
          .catch(error => {
            console.log('메세지', error.response.data.message)
            if (!error.response.data.success){
              if (error.response.data.message[0] === 'E') {  // Email Address already in use!
                const error = {type:'이메일', content: '이미 가입되어있는 이메일입니다'}
              } else if (error.response.data.message[0] === 'U') {  // Username is already taken!
                const error = {type:'닉네임', content: '이미 사용중인 닉네임입니다'}
              }
              this.$emit('error', error)
            }
          })
      } else {
        console.log('회원가입 실패')
      }
    },
    showText(elem) {
      return this.bgColor[elem] === this.warningColor
    },
    checkEmail() {
      let email = this.email
      let indexAt = email.indexOf('@')
      let indexDot = email.lastIndexOf('.')
      if (indexAt > 2 && indexDot > 6 && indexDot + 2 < email.length) {
        this.isOkay.email = true
        this.bgColor.email = ''
      } else {
        this.isOkay.email = false
        this.bgColor.email = this.warningColor
      }
    },
    checkPassword() {
      let pwLen = this.pw.password.length
      // console.log('this.pw.password', this.pw.password)
      if (5 < pwLen && pwLen < 21) {
        this.isOkay.password = true
        this.bgColor.password = ''
      } else if (pwLen === 0) {
        this.isOkay.password = false
        this.bgColor.password = ''
      } else {
        this.isOkay.password = false
        this.bgColor.password = this.warningColor
      }
      // console.log(this.pw.passwordCheck)
      if (this.pw.passwordCheck === '') {
        this.isOkay.passwordCheck = false
        this.bgColor.passwordCheck = ''
      } else if (this.pw.password === this.pw.passwordCheck) {
        this.isOkay.passwordCheck = true
        this.bgColor.passwordCheck = ''
      } else {
        this.isOkay.passwordCheck = false
        this.bgColor.passwordCheck = this.warningColor
      }
    },
    checkNickname() {
      let nickLen = this.nickname.length
      // console.log('nickname', this.nickname)
      if (2 < nickLen && nickLen < 16) {
        this.isOkay.nickname = true
        this.bgColor.nickname = ''
      } else if (nickLen === 0) {
        this.isOkay.nickname = false
        this.bgColor.nickname = ''
      } else {
        this.isOkay.nickname = false
        this.bgColor.nickname = this.warningColor
      }
    },
    checkIsOkay() {
      this.okay = this.isOkay.email && this.isOkay.password && this.isOkay.passwordCheck && this.isOkay.nickname
      if (this.okay) {
        document.querySelector('#signupButton').removeAttribute('disabled')
      } else {
        document.querySelector('#signupButton').setAttribute('disabled', 'true')
      }
    }
  },
  watch: {
    email: function() {
      this.checkEmail()
    },
    pw: {
      deep: true,
      handler() {
        this.checkPassword()
      }
    },
    nickname: function() {
      this.checkNickname()
    },
    isOkay: {
      deep: true,
      handler() {
        this.checkIsOkay()
      }
    }
  }
}
</script>

<style>

</style>