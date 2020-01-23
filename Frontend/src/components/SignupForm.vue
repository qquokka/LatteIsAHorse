<template>
  <form @submit.prevent="signup" class="p-3">
    <div class="form-group">
      <label for="signupEmail">이메일: </label>
      <input type="email" class="form-control" id="signupEmail" placeholder="ID (your_id@example.com)" v-model="credentials.email">
    </div>
    <div class="form-group">
      <label for="signupPW">비밀번호: </label>
      <input type="password" class="form-control" id="signupPW" placeholder="PASSWORD" v-model="credentials.password">
    </div>
    <div class="form-group">
      <label for="signupPW2">비밀번호 확인: </label>
      <input type="password" class="form-control" id="signupPW2" placeholder="PASSWORD" v-model="password2">
    </div>
    <div class="form-group">
      <label for="name">이름: </label>
      <input type="text" class="form-control" id="name" placeholder="NAME" v-model="credentials.name">
    </div>
    <div class="form-group">
      <label for="username">닉네임: </label>
      <input type="text" class="form-control" id="username" placeholder="USERNAME" v-model="credentials.username">
    </div>
    <b><small>저희 회원이신가요? <span style="color: #88D8B0 !important;cursor:pointer"><slot></slot></span></small></b>
    <button type="submit" class="btn btn-block btn-outline-warning font-weight-bolder">회원가입</button>
  </form>
</template>

<script>
export default {
  name: 'SignupForm',
  data() {
    return {
      credentials: {},
    }
  },
  methods: {
    switchModal() {
      this.$emit('switchModal')
    },
    check() {
      document.getElementById('signupPW2')
    }
  }

}
</script>

<style>

</style>