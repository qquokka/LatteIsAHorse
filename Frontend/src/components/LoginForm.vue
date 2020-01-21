<template>
<div class="modal fade m-auto" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Login</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form @submit.prevent="login">
            <div class="form-group">
                <input type="email" class="form-control" id="inputId" placeholder="Email" v-model="credentials.username">
                <small id="idHelp" class="form-text text-muted">이메일을 입력하라</small>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="inputPW" placeholder="password" v-model="credentials.password">
            </div>
            <button type="submit" class="btn btn-outline-warning shadow">로그인</button>
        </form>
      </div>
    </div>
  </div>
</div>
</template>

<script>
    export default {
        data() {
            return {
                credentials: {
                }
            }
        },
        methods: {
            loginLog() {
                console.log('함수가 실행되었습니다!!!!!!!!!!!!!!')
                console.log(this.id)
                console.log(this.password)
            },
            login() {
                console.log('emit')
                this.$emit('login-event', this.credentials)
                this.credentials = {}
            }
        }
    }
</script>

<style>

</style>