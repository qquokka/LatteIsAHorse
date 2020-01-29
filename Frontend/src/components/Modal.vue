<template>
  <div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="staticBackdropLabel">
            <strong v-if="showLogin">LOGIN</strong>
            <strong v-else>SIGN UP</strong>
          </h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="initShowLogin">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <login-form v-if="showLogin" @login="login">
            <span style="color: #88D8B0 !important;cursor:pointer" @click="switchModal">회원가입</span>
          </login-form>
          <signup-form v-else>
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
          }
      },
      methods: {
          login() {
            this.$emit('login', this.credentials)
          },
          switchModal() {
            this.showLogin = !this.showLogin
            this.credentials = {}
          },
          initShowLogin() {
            this.showLogin = true
          }
      },
//       watch: {
//         credentials: {
//           deep: true,
//           handler() {
//             for (var info in this.credentials) {

// for(var n in roles){
//   console.log('object => ', n, 'value => ', roles[n]);
// }


//               if (info > 5 && this.credentials.password < 21) {
//                 this.lengthCheck.password = true
//                 console.log(this.lengthCheck)
//               } else {
//                 this.lengthCheck.password = false
//                 console.log(this.lengthCheck)
//               }
//             }
//           }
//         }
//       }
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