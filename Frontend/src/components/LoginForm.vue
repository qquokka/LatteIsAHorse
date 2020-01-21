<template>
<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="staticBackdropLabel"><strong>LOGIN</strong></h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form @submit.prevent="login" class="p-3">
            <div class="form-group">
                <input type="email" class="form-control" id="inputId" placeholder="ID (your_id@example.com)" v-model="credentials.username">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="inputPW" placeholder="PASSWORD" v-model="credentials.password">
            </div>
            <b><small>처음이시라면? <a href="" style="color: #88D8B0 !important">회원가입</a></small></b>
            <button type="submit" class="btn btn-block btn-outline-warning font-weight-bolder">로그인</button>
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
.modal-content {
  margin-top: 50%;
  margin-bottom: 50%;
}
.modal-header {
  background: #88D8B0;
  border-radius: 20px 20px;
}
.modal-body {
  border-radius: 15px 15px !important;
}
.btn-outline-warning {
  border: 1px solid #88D8B0 !important;
  color: #88D8B0 !important
}
.btn-outline-warning:hover {
  background: #88D8B0 !important;
  color: #1f1f1f !important
}
</style>