<template>
  <form @submit.prevent="login" class="p-3">
    <div class="form-group">
      <input type="email" class="form-control" id="inputId" placeholder="ID (your_id@example.com)" v-model="credentials.username">
    </div>
    <div class="form-group">
      <input type="password" class="form-control" id="inputPW" placeholder="PASSWORD" v-model="credentials.password">
    </div>
    <b>
      <small>처음이시라면? <span style="color: #88D8B0 !important;cursor:pointer">
        <slot></slot></span></small>
    </b>
    <button type="submit" class="btn btn-block btn-outline-warning font-weight-bolder">로그인</button>
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
    switchModal() {
      this.$emit('switchModal')
    }
  }
}
</script>

<style>

</style>