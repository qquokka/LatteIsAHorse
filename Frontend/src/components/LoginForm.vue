<template>
  <div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="staticBackdropLabel">
            <strong v-if="showLogin">LOGIN</strong>
            <strong v-else>SIGN UP</strong>
          </h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">
          <div v-if="showLogin">
            <form @submit.prevent="login" class="p-3">
                <div class="form-group">
                    <input type="email" class="form-control" id="inputId" placeholder="ID (your_id@example.com)" v-model="credentials.username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="inputPW" placeholder="PASSWORD" v-model="credentials.password">
                </div>
                <b>
                  <small>처음이시라면? <span style="color: #88D8B0 !important;cursor:pointer" @click="switchToSignup()">회원가입</span></small>
                </b>
                <button type="submit" class="btn btn-block btn-outline-warning font-weight-bolder">로그인</button>
            </form>
          </div>

          <div v-else>
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
                <b><small>저희 회원이신가요? <span style="color: #88D8B0 !important;cursor:pointer" @click="switchToSignup()">로그인</span></small></b>
                <button type="submit" class="btn btn-block btn-outline-warning font-weight-bolder">회원가입</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
      name: 'LoginForm',
      // props: {
      //   showLoginInitial: {
      //     showLoginInitial: Boolean,
      //     required: true
      //   }
      // },
      data() {
          return {
              credentials: {},
              password2: '',
              lengthLimit: {},
              lengthCheck: {},
              showLogin: true,
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
          },
          switchToSignup() {
            this.showLogin = !this.showLogin
          },
          signup() {
            if (this.credentials.password === this.password2) {
              console.log(this.credentials)
              axios.post('http://192.168.31.111:8080/api/auth/signup', this.credentials)
                .then(response => {
                console.log('회원가입 성공')
                console.log(response.data.success)
                console.log(response.data.message)
                this.credentials = {}
                })
                .catch(error => {
                  console.log(error)
                })
            } else {
              console.log('비밀번호 불일치')
            }
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


/* warning notification 만드는 중: https://css-tricks.com/ease-out-in-ease-in-out/ */
.coming-down {
  height: 100px;
  background: red;
  -webkit-transition: height 2s; /* Safari */
  transition: height 0.2s;
  -webkit-transition-timing-function: ease-in;
  transition-timing-function: ease-in;
  /* transform: translateY(130%); */
}

/* For Safari 3.1 to 6.0 */
#div1 {-webkit-transition-timing-function: linear;}
#div2 {-webkit-transition-timing-function: ease;}
#div3 {-webkit-transition-timing-function: ease-in;}
#div4 {-webkit-transition-timing-function: ease-out;}
#div5 {-webkit-transition-timing-function: ease-in-out;}

/* Standard syntax */
#div1 {transition-timing-function: linear;}
#div2 {transition-timing-function: ease;}
#div3 {transition-timing-function: ease-in;}
#div4 {transition-timing-function: ease-out;}
#div5 {transition-timing-function: ease-in-out;}

.coming-down:hover {
  height: 150px;
}
/* warning notification 만드는 중: https://css-tricks.com/ease-out-in-ease-in-out/ */

</style>