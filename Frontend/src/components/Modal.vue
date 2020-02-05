<template>
  <div class="modal fade modal2" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header border-0 pb-0">
          <div class="text-muted w-100" id="staticBackdropLabel">
            <img src="../assets/logo_icon.png" width="50px"><span>LATTE <span style="color:violet">=</span> HORSE</span>
            <div class="text-left pl-4 border-bottom" style="border-left: 10px solid lavender">
              <h3 v-if="showLogin">LOGIN</h3>
              <h3 v-else>SIGN UP</h3>
            </div>
          </div>
          <button type="button" id="modalCloseButton" class="close" data-dismiss="modal" aria-label="Close" @click="initStatus">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div v-if="fail.occured" id="authFailAlert" class="alert alert-warning mb-0" role="alert">
          <h6 class="mb-0">{{ fail.content }}</h6>
        </div>
        <div class="modal-body">
          <login-form v-if="showLogin" @login="login">
            <span class="switchText"  @click="switchModal">회원가입하기</span>
          </login-form>
          <signup-form v-else @fail-event="failMessage">
            <span class="switchText" @click="switchModal">로그인하러가기</span>
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
      props: {
        loginFailed: {
          type: Boolean,
          default: false
        }
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
            this.fail = {}
          },
          initStatus() {
            this.showLogin = true
            this.fail.occured = false
          },
          failMessage(fail) {
            this.fail = fail
            // console.log('모달에서 저장', this.fail)
          }
      },
      watch: {
        loginFailed: function() {
          // console.log('loginFailed', this.loginFailed)
            this.fail = {
              occured: true,
              content: '이메일 또는 비밀번호를 잘못 입력하셨습니다'
          }
        }
      }
  }
</script>


<style>
#staticBackdrop {
  overflow: hidden;
}
#modalCloseButton {
  display: flex;
  justify-content: center;
  align-items: center;
  padding:0;
  border: 0;
  width: 45px;
  height: 45px;
  margin-right: 3px;
  margin-top: 3px;
  border-radius: 50%;
  background: #ffffff;
  box-shadow:  5px 5px 9px #d9d9d9, 
              -5px -5px 9px #ffffff;
}

#modalCloseButton:hover {
  box-shadow:  inset 5px 5px 9px #d9d9d9, 
              inset -5px -5px 9px #ffffff;
}
.modal-dialog{
  align-items: center;
  position: relative;
  margin: auto !important;
  height: 100vh !important;
  display: flex;
}

.modal-content {
  border-radius: 21px;
  background: none;
}

.loginbtn {
  display: flex;
  cursor: pointer;
  border-radius: 10px;
  margin: 1rem auto 1rem auto;
  justify-content: center;
  color: #3f3f3f;
  height: 3rem;
  width: 70%;
  border:0;
  box-shadow:  5px 5px 9px #d9d9d9, 
              -5px -5px 9px #ffffff;
  transition: 155ms ease-in-out;
}
.loginbtn:hover {
  box-shadow: inset 5px 5px 9px #d9d9d9, 
            inset -5px -5px 9px #ffffff;
  color: #88D8B0;
  
}

.switchText {
  color: #88D8B0;
  cursor: pointer;
}
.switchText:hover {
  color: violet;
}
@media screen and (max-width: 768px) {
    .modal-backdrop {
      background: lavender !important;
      opacity: 1 !important;
    }
}
</style>