<template>
  <form @submit.prevent="signup" class="p-3">
    <div class="form-group-lg text-danger text-left">
      <p v-show="showText('email')"><small>이메일을 'your_id@example.com' 형식으로 입력해주세요</small></p>
      <input type="email" class="  logininput" id="email" placeholder="ID (id@example.com)" v-model="email"
      :style="{ 'background-color': bgColor.email }">
      <p v-show="showText('nickname')"><small>닉네임을 15자 이하로 입력해주세요</small></p>
      <input type="text" class="  logininput" id="nickname" placeholder="닉네임(3~15자리)을 입력해주세요" v-model="nickname"
      :style="{ 'background-color': bgColor.nickname }">
      <p v-show="showText('password')"><small>비밀번호를 6 ~ 20자로 입력해주세요</small></p>
      <input type="password" class="  logininput" id="password" placeholder="비밀번호" v-model="pw.password"
      :style="{ 'background-color': bgColor.password }">
      <p v-show="showText('passwordCheck')"><small>위에 입력한 비밀번호와 동일하게 입력해주세요</small></p>
      <input type="password" class="  logininput" id="passwordCheck" placeholder="비밀번호를 확인" v-model="pw.passwordCheck"
      :style="{ 'background-color': bgColor.passwordCheck }">
      <div class="container text-right mb-3">
        <label class="agree-label mt-3 ml-2" for="개인정보동의">라떼는말이야의 <router-link to="#">이용약관</router-link>과 <router-link to="#">개인정보처리방침</router-link>에 동의합니다.</label>
        <input type="checkbox" v-model="agreementInfo" name="개인정보동의" id="">
        <label class="agree-label mt-1 ml-2" for="위치정보동의">서비스를 위한<router-link to="#">위치정보제공</router-link>에 동의합니다.</label>
        <input type="checkbox" v-model="agreementLoc" name="위치정보동의" id="">
      </div>
    </div>
    <b><p>기존에 가입하셨다면 <slot></slot></p></b>
    <button id="signupButton" type="submit" class="loginbtn font-weight-bolder" disabled>회원가입</button>
  </form>
</template>
<script>
import axios from 'axios'

export default {
  name: 'SignupForm',
  data() {
    return {
      agreementInfo: false,
      agreementLoc: false,
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
      fail: {
        occured: false
      },
      warningColor: 'rgba(255, 0, 0, 0.100) !important'
    }
  },
  methods: {
    signup() {
      this.fail = {}
      this.$emit('fail-event', {})
      if (this.okay) {
        // console.log('회원가입 가능')
        const credentials = {
          'email': this.email,
          'password': this.pw.password,
          'username': this.nickname
        }
        console.log(credentials)
        axios.post(`${this.$store.state.constants.SERVER}/signup`, credentials)
          .then(response => {
              console.log(response.data.message)
              document.querySelector('#modalCloseButton').click()
              const credentialsLogin = {
                'usernameOrEmail': this.email,
                'password': this.pw.password
              }
              axios.post(`${this.$store.state.constants.SERVER}/signin`, credentialsLogin)
                .then(response => {
                    const token = response.data.accessToken
                    this.$session.start()
                    this.$session.set('jwt', token)
                    this.$store.dispatch('login', token)
                    this.$store.commit('setToken', token)
                })
          })
          .catch(error => {
            // console.log(error.response)
            // console.log('메세지', error.response.data.message)
            if (!error.response.data.success){
              this.pw.password = ''
              this.pw.passwordCheck = ''
              // console.log('에러메세지 저장')
              this.fail.occured = true
              if (error.response.data.message[0] === 'E') {  // Email Address already in use!
                this.fail.content = '이미 가입되어있는 이메일입니다! 다른 이메일을 사용해주세요'
              } else if (error.response.data.message[0] === 'U') {  // Username is already taken!
                this.fail.content = '이미 사용중인 닉네임입니다! 다른 닉네임을 사용해주세요'
              }
              // console.log(this.fail)
              this.$emit('fail-event', this.fail)
            }
          })
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
      if (nickLen === 0) {
        this.isOkay.nickname = false
        this.bgColor.nickname = ''
      } else if (nickLen < 16) {
        this.isOkay.nickname = true
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
.signupform {
  height: 3rem !important;
}
.agree-label {
  color: #2f2f2f;
  font-size: 0.8rem;
}
.text-danger > p {
  text-align: center;
  border-radius: 6px;
  background: #ffffff;
  box-shadow:  17px 17px 34px #d9d9d9, 
              -17px -17px 34px #ffffff;
}

.logininput {
  background: #ffffff;
  width: 100%;
  height: 3rem;
  margin: 0.2rem;
  padding: 1rem;
  border:0;
  box-shadow:  inset 5px 5px 9px #f1f1f1, 
            inset -5px -5px 9px #ffffff;
}

.logininput:focus {
    box-shadow:  5px 5px 9px #f1f1f1, 
              -5px -5px 9px #ffffff; 
    outline: none;
    border-bottom: 3px solid #88D8B0
}
.logininput:focus ::placeholder {
  color: rosybrown !important;
}

</style>