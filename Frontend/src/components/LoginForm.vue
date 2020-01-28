<template>
  <form @submit.prevent="login" class="p-3">
    <div class="form-group">
      <input type="email" class="form-control" id="inputId" placeholder="ID (your_id@example.com)" v-model="credentials.username">
    </div>
    <div class="form-group">
      <input type="password" class="form-control" id="inputPW" placeholder="PASSWORD" v-model="credentials.password">
    </div>
    <b>
      <small>처음이시라면? <slot></slot></small>
    </b>
    <button type="submit" class="btn btn-block btn-outline-warning font-weight-bolder" @click="login">로그인</button>
  </form>
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

</style>