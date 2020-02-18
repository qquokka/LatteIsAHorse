<template>
   <div>
    <p class="decode-result">
      Last result: <b>{{ result }}</b>
    </p>
    <br/>
    <p class="error" v-if="errorOccur">Error Message : <b>{{error}}</b></p>

    <qrcode-drop-zone @decode="onDecode" @init="logErrors">
      <qrcode-stream
        :camera="camera" 
        :key="_uid" 
        @decode="onDecode" 
        @init="onInit" 
        > <!--:track="paintGreenText" -->

        <div v-if="validationSuccess" class="validation-success">
          쿠폰 등록 성공 (^o^)
        </div>

        <div v-if="validationFailure" class="validation-failure">
          쿠폰 등록 실패 (^ㅠ_ㅠ^) <br/> 등록 유효기간 만료...
        </div>

        <div v-if="validationPending" class="validation-pending">
          쿠폰 등록 중...
        </div>

        <div v-if="validationCoupon" class="validation-failure">
          유효하지 않은 쿠폰입니다!
        </div>
      </qrcode-stream>
    </qrcode-drop-zone>
    
    <!-- 카메라 미지원일 때 파일 업로드로 QR 코드 인식 -->
    <qrcode-capture v-if="noStreamApiSupport" @decode="onDecode" />
  </div>
</template>

<script>
import axios from "axios"
import 'vue-qrcode-reader/dist/vue-qrcode-reader.css'
import { QrcodeStream, QrcodeDropZone, QrcodeCapture } from 'vue-qrcode-reader'

export default {
  name: 'qrcode-reader',
  components:{
    QrcodeStream,  //https://gruhn.github.io/vue-qrcode-reader/demos/CustomTracking.html
    QrcodeDropZone,
    QrcodeCapture
  },
  data () {
    return {
      paused: false, //첫번째 결과에만 관심이 있다면 카메라 스트림 멈춤
      result: null,
      error:'',
      errorOccur: false, //에러 발생 했을 때 에러 메세지 표시
      isValid: undefined, //0 : validCoupon, 1 : couponAddSuccess, -1 : couponAddFail
      camera: 'auto',
      noStreamApiSupport: false,
      isNotACoupon: false
    } 
  },
  props: {
    cafe_id: Number
  },
  computed: {
    isLogged() { return this.$store.getters.isLoggedIn },
    validationCoupon () { return this.isValid === 0},
    validationPending () {
      return this.isValid === undefined
        && this.camera === 'off'
    },
    validationSuccess () { return this.isValid === 1 },
    validationFailure () { return this.isValid === -1 }
  },
  methods: {
    deleteQRCode(code, config){
      axios.delete(`${this.$store.state.constants.SERVER}/qrcode`,{
        data:{
          cafe_id: this.cafe_id,
          code: code
        }
      }, config) //쿠폰 등록
             .then(res => {
               if(res.status === 200){
                  console.log(res.data.message)
               }
             })
             .catch(err => {
               console.log(err)
             })
    },
    goBack() { this.$router.go(-1) }, //뒤로가기(아마도 카페 페이지?)
    async onDecode (content) {
      
      this.result = content
      this.turnCameraOff()

      // pretend it's taking really long
      await this.timeout(2000) //stop time in ms
      
      if(content.startsWith(`${this.$store.state.constants.SERVER}/coupon/`)){ //발급된 쿠폰인지 체크
         const req = "coupon/"
         const lastIdx = content.lastIndexOf(req)
        //  console.log("last index : " + lastIdx)
        //  const url = content.substring(0, lastIdx + req.length);
        //  console.log("url : " + url)
         const code = content.substring(lastIdx + req.length, content.length);
        //  console.log("code : " + code)
         const config = {
            headers: { Authorization: "Bearer " + this.$session.get("jwt") }
         }
        console.log(typeof code)
        await axios.put(`${this.$store.state.constants.SERVER}/coupon/`,{
                code : code
              }, config) //쿠폰 등록
             .then(res => {
               console.log(res.data)
               if(res.status === 200){
                  this.isValid = 1
                  
               }
             })
             .catch(err => {
               this.isValid = -1
               console.log(err.name)
               console.log(err)
             })
             
        //DB에 등록된 QR코드 삭제
        this.deleteQRCode(code, config)

      }else{
        this.isValid = 0
        
      }
      await this.timeout(1500)
      if(this.isValid === -1 || this.isValid === 0){
        this.turnCameraOn()
      }else{
        console.log("should go back")
        //  this.goBack()
      }

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
              .then(this.resetValidationState)
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
          this.noStreamApiSupport = true
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
</style>