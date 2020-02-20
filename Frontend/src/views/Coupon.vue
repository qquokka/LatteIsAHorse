<template>
  <div>
    <div v-if="isGeneratedCoupon">
      <img id="qrCode" alt="QRCode" :src="qrcode" width="200" height="300"/>
      <br/>
      <p>발급된 쿠폰 개수 : {{ couponNum }}</p>
      <br/>
      <button type="button" class="couponbtn font-weight-bolder" 
      v-on:click="reGenerateCoupon">재발급</button>
      <br/>
      <p>{{uri}}</p>
    </div>
    <div v-else>
      <p>쿠폰 발급 개수</p>
      <input type="number" min="0" max="10" v-model="couponNum"/>
      <button type="button" class="couponbtn font-weight-bolder" 
      v-on:click="generate">생성</button>
    </div>
  </div>
</template>

<script>
import axios from "axios"

export default {
  name: 'coupon',
  components:{
  },
  data () {
    return {
       isGeneratedCoupon : false,
       couponNum: 0,
       qrcode: null,
       uri: null,
    } 
  },
  props: {
    cafe_id: Number
  },
  computed: {
    isLogged() {
      return this.$store.getters.isLoggedIn
    }
  },
  methods: {
    reGenerateCoupon(){
      this.isGeneratedCoupon = false
    },
    generate(){
      const payload = {
        //"cafe_id": this.cafe_id,
        "cafe_id": 1,
        "count": this.couponNum
      }
      axios.post(`${this.$store.state.constants.SERVER}/qrcode/`, payload)
           .then(res => {
             console.log(res.data)
             this.qrcode = 'data:image/png;base64,' + res.data.qrcode
             this.uri = res.data.uri
             console.log(res.data.message)
             this.isGeneratedCoupon = true
           })
           .catch(err => {
             console.log(err)
           })
    }
  },
  created() {
    window.addEventListener('scroll', this.handleScroll);
  },
  destroyed() {
    window.removeEventListener('scroll', this.handleScroll);
  },
}
</script>

<style>
</style>