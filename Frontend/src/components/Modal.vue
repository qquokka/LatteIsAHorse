<template>
  <div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="staticBackdropLabel">
            <strong v-if="showLogin">LOGIN</strong>
            <strong v-else>SIGN UP</strong>
          </h4>
          <button type="button" id="modalCloseButton" class="close" data-dismiss="modal" aria-label="Close" @click="initStatus">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div v-if="fail.occured" class="alert alert-warning mb-0" role="alert" style="border-radius: 20px 20px !important;">
          <h6 class="mb-0">{{ fail.content }}! 다른 {{ fail.type }}을 사용해주세요</h6>
        </div>
        <div class="modal-body">
          <login-form v-if="showLogin" @login="login">
            <span style="color: #88D8B0 !important;cursor:pointer" @click="switchModal">회원가입</span>
          </login-form>
          <signup-form v-else @fail-event="failMessage">
            <span style="color: #88D8B0 !important;cursor:pointer" @click="switchModal">로그인</span>
          </signup-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import LoginForm from './LoginForm.vue';
  import SignupForm from './SignupForm.vue';

  export default {
      name: 'Modal',
      components: {
        LoginForm, SignupForm
      },
      data() {
          return {
            showLogin: true,
            fail: {}
          }
      },
      methods: {
          login(credentials) {
            this.$emit('login', credentials)
          },
          switchModal() {
            this.showLogin = !this.showLogin
            this.credentials = {}
          },
          initStatus() {
            this.showLogin = true
            this.fail.occured = false
          },
          failMessage(fail) {
            this.fail = fail
            console.log('모달에서 저장', this.fail)
          }
      },
  }
</script>


<style>
#staticBackdrop {
  overflow: hidden;
}
.modal-dialog{
  align-items: center;
  position: relative;
  margin: auto !important;
  height: 100vh !important;
  display: flex;
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