<template>
  <div>
    <div v-if="isGeneratedCoupon">
      <img id="qrCode" alt="QRCode" :src="qrcode" width="200" height="300"/>
      <br/>
      <p>발급된 쿠폰 개수 : {{couponNum}}</p>
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