<template>
<div>
  <form @submit.prevent="login" class="px-3 pt-3">
    <div class="form-group">
      <input type="email" class="form-control" id="inputId" placeholder="ID (your_id@example.com)" v-model="credentials.usernameOrEmail">
    </div>
    <div class="form-group">
      <input type="password" class="form-control" id="inputPW" placeholder="PASSWORD" v-model="credentials.password">
    </div>
    <b>
      <small>처음이시라면? <slot></slot></small>
    </b>
    <button type="submit" class="btn btn-block btn-outline-warning font-weight-bolder">로그인</button>
    <hr>
  </form>
  <div class="container px-2">
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
      credentials: {}
    }
  },
  methods: {
    login() {
      // console.log('emit-login in loginform')
      this.$emit('login', this.credentials)
      this.credentials = {}
    }
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
</style>