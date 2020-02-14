<template>
   <div>
    <p class="decode-result">
      Last result: <b>{{ result }}</b>
    </p>
    <br/>
    <p class="error" v-if="errorOccur">Error Message : <b>{{error}}</b></p>

    <qrcode-stream
      :camera="camera" 
      :key="_uid" 
      :track="paintGreenText" 
      @decode="onDecode" 
      @init="onInit" 
      :paused="paused">

      <div v-if="validationSuccess" class="validation-success">
        This is a URL
      </div>

      <div v-if="validationFailure" class="validation-failure">
        This is NOT a URL!
      </div>

      <div v-if="validationPending" class="validation-pending">
        Long validation in progress...
      </div>
    </qrcode-stream>
  </div>
</template>

<script>
import axios from "axios"
import 'vue-qrcode-reader/dist/vue-qrcode-reader.css'
import { QrcodeStream } from 'vue-qrcode-reader'

export default {
  name: 'qrcode-reader',
  components:{
    QrcodeStream  //https://gruhn.github.io/vue-qrcode-reader/demos/CustomTracking.html
  },
  data () {
    return {
      paused: false, //첫번째 결과에만 관심이 있다면 카메라 스트림 멈춤
      result: null,
      error:'',
      errorOccur: false,
      isValid: undefined,
      camera: 'auto',
    } 
  },
  props: {
  },
  computed: {
    isLogged() {
      return this.$store.getters.isLoggedIn
    },
    validationPending () {
      return this.isValid === undefined
        && this.camera === 'off'
    },
    validationSuccess () {
      return this.isValid === true
    },
    validationFailure () {
      return this.isValid === false
    }
  },
  methods: {
    async onDecode (content) {
      // console.log(request)
      // this.result = request
      //쿠폰 등록
      axios.post(content)
           .then(res => {
             if(res.status === 200){
               console.log(res.data)
             }
           })
           .catch(err => {
             console.log(err.name)
             console.log(err.data)
           })
  
      this.result = content
      this.turnCameraOff()

      // pretend it's taking really long
      await this.timeout(3000) //stop time in ms
      this.isValid = content.startsWith('http') //validation 내용

      // some more delay, so users have time to read the message
      await this.timeout(2000)

      //결과 정상 처리 후 페이지 전환
      this.turnCameraOn()
    },
    paintGreenText (location, ctx) {
      const {
        topLeftCorner,
        topRightCorner,
        bottomLeftCorner,
        bottomRightCorner
      } = location

      const pointArray = [
        topLeftCorner,
        topRightCorner,
        bottomLeftCorner,
        bottomRightCorner
      ]

      const centerX = pointArray.reduce((sum, { x }) => x + sum, 0) / 4
      const centerY = pointArray.reduce((sum, { y }) => y + sum, 0) / 4

      ctx.font = "bold 24px sans-serif"
      ctx.textAlign = "center"

      ctx.lineWidth = 3
      ctx.strokeStyle = '#35495e'
      ctx.strokeText(this.result, centerX, centerY)

      ctx.fillStyle = '#5cb984'
      ctx.fillText(this.result, centerX, centerY)
    },
    logErrors (promise) {
      //에러 메세지 표시 not console
      promise.catch(console.error)
    },
    async onInit (promise) {
      try {
        await promise
              .catch(console.error)
              .then(this)
      } catch (error) {
        this.errorOccur = true;
        if (error.name === 'NotAllowedError') {
          this.error = "ERROR: you need to grant camera access permisson"
        } else if (error.name === 'NotFoundError') {
          this.error = "ERROR: no camera on this device"
        } else if (error.name === 'NotSupportedError') {
          this.error = "ERROR: secure context required (HTTPS, localhost)"
        } else if (error.name === 'NotReadableError') {
          this.error = "ERROR: is the camera already in use?"
        } else if (error.name === 'OverconstrainedError') {
          this.error = "ERROR: installed cameras are not suitable"
        } else if (error.name === 'StreamApiNotSupportedError') {
          this.error = "ERROR: Stream API is not supported in this browser"
        }
      }
    },
    resetValidationState () { this.isValid = undefined },
    turnCameraOn () { this.camera = 'auto' },
    turnCameraOff () { this.camera = 'off' },
    timeout (ms) {
      return new Promise(resolve => {
        window.setTimeout(resolve, ms)
      })
    }
  }
}
</script>

<style>
.error {
  font-weight: bold;
  color: red;
}
.validation-success,
.validation-failure,
.validation-pending {
  position: absolute;
  width: 100%;
  height: 100%;

  background-color: rgba(255, 255, 255, .8);
  text-align: center;
  font-weight: bold;
  font-size: 1.4rem;
  padding: 10px;

  display: flex;
  flex-flow: column nowrap;
  justify-content: center;
}
.validation-success {
  color: green;
}
.validation-failure {
  color: red;
}
nav {
  transition: 0.25s ease-in-out;
  top: 0;
}
i {
  color: inherit !important;
}
.nav-link {
  margin:auto;
  cursor:pointer;
  transition: 0.25s ease-in-out;
  position:relative
}
.nav-link:hover:after {
  width: 100%;
  left: 0;
}
.nav-link:after {
  background: none repeat scroll 0 0 transparent;
  bottom: 0;
  content: "";
  display: block;
  height: 3px;
  right: 0;
  position: absolute;
  background: violet;
  transition: width 0.15s ease-in 0s, right 0.3s ease 0s;
  width: 0;
}

.fa-bars {
  color: gray
}
@media only screen and (max-width: 991px) {
  #topnav {
    background: rgba(255,255,255,0.7) !important;
    width: fit-content;
    border-radius: 15px;
    justify-content: start;
    text-align: left;
    position: fixed;
  }
}
</style>