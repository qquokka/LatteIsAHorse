<template>
<div>
  <form @submit.prevent="login" class="px-3 pt-3">
    <div class="form-group">
      <input type="email" class="logininput" id="inputId" placeholder="사용자이름" v-model="credentials.usernameOrEmail">
    </div>
    <div class="form-group">
      <input type="password" class="logininput" id="inputPW" placeholder="비밀번호" v-model="credentials.password">
    </div>
    <label class="small mr-3" for="remeber">아이디 기억하기</label>
    <input type="checkbox" name="remeber" v-model="rememberme" id="">
    <p class="mt-3">처음이시라면? <slot></slot></p>
    <button type="submit" class="loginbtn font-weight-bolder">로그인</button>
  </form>
  <div class="container px-2 d-none">
    <div class="row px-3">
      <div class="col-md-6 mx-0 my-1 px-1">
      <button id="kakaoLoginButton" class="btn btn-block btn-outline-danger font-weight-bolder"><i class="fas fa-comment"/> 카카오 로그인</button>
      </div>
      <div class="col-md-6 mx-0 my-1 px-1">
      <button id="googleLoginButton" class="btn btn-block btn-outline-warning font-weight-bolder"><i class="fab fa-google"/> 구글 로그인</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'LoginForm',
  data() {
    return {
      credentials: {},
      rememberme: false,
    }
  },
  methods: {
    login() {
      this.$emit('login', this.credentials)
      this.credentials = {}
      if (this.rememberme) {
        let iid = document.getElementById('inputId').value
        localStorage.setItem('rid', iid)
      } else {
        localStorage.removeItem('rid')
      }
    },
    ridCheck() {
      let rememberedID = localStorage.getItem('rid')
      if (rememberedID !== undefined) {
        this.credentials.usernameOrEmail = rememberedID
        this.rememberme = true
      }
    }
  },
  created() {
    this.ridCheck()
  }
}
</script>

<style>
#kakaoLoginButton {
  border: 1px solid rgba(255, 203, 92) !important;
  color: #88D8B0 !important
}

#kakaoLoginButton:hover {
  background: rgba(255, 203, 92) !important;
  color: #45343F !important
}

#googleLoginButton {
  border: 1px solid rgba(255, 110, 105) !important;
  color: #88D8B0 !important
}

#googleLoginButton:hover {
  background: rgba(255, 110, 105) !important;
  color: whitesmoke !important
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