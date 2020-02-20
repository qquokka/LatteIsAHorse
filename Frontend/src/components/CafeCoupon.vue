<template>
  <div class="container-fluid m-0 p-0 min-vh-100">
    <nav-bar />
    <h1 class="bg-white py-5 font-weight-light">{{ myCafe[0].cafe_name }}</h1>
    <h4>{{ $session.get('username') }} 님</h4>

    <div class="container">
      <div v-if="!isOwner">쿠폰 몇개~</div>
      <div v-else>
        <div v-if="!isGeneratedCoupon" class="container">
          <p>고객님께 몇 장의 쿠폰을 제공하실 예정인가요</p>
          <input
            style="width: 50%;text-align:center;height: 10vh;font-size:8vw"
            type="number"
            min="0"
            max="10"
            placeholder="0-10매"
            v-model="couponNum"
          />
        <button type="button" class="couponbtn font-weight-bolder" v-on:click="generate">생성</button>
        <div class="container p-0 pt-3">
          <ul class="small text-left">
            <li>
              생성한 QR코드는 10분 후 사용 기한이 만료됩니다.
              <br />그 이전에 고객님이 촬영할 수 있도록 해주세요.
            </li>
            <li>한번 발급완료 된 쿠폰은 삭제가 어렵습니다.</li>
            <li>담당자에게 직접 연락주시면 조치드리겠습니다.</li>
          </ul>
        </div>
			</div>
    <div class="container" v-else>
      <img id="qrCode" alt="QRCode" :src="qrcode" width="200" height="300" />
      <br />
      <p>발급된 쿠폰 개수 : {{ couponNum }}</p>
      <br />
      <button type="button" class="couponbtn font-weight-bolder" v-on:click="reGenerateCoupon">재발급</button>
      <br />
    </div>
		</div>
  </div>
</div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import axios from "axios";
export default {
  components: {
    NavBar
  },
  data() {
    return {
      cafeId: this.$route.params.cafeid,
      myCafe: [],
      isOwner: false,
      couponNum: 0,
      qrcode: null,
      uri: null,
      isGeneratedCoupon: false
    };
  },
  methods: {
    getCafeInfo() {
      axios
        .get(`${this.$store.state.constants.SERVER}/mycafe`, {
          headers: { Authorization: "Bearer " + this.$session.get("jwt") }
        })
        .then(response => {
          this.myCafe = [response.data.cafe];
          this.isOwner = true;
        })
        .catch(() => {
          axios
            .get(`${this.$store.state.constants.SERVER}/mycafe`)
            .then(response => {
              this.myCafe = [response.data.cafe];
              this.isOwner = false;
            });
        });
    },
    generate() {
      const payload = {
        cafe_id: this.cafeId,
        count: this.couponNum
      };
      axios
        .post(`${this.$store.state.constants.SERVER}/qrcode/`, payload)
        .then(res => {
          this.qrcode = "data:image/png;base64," + res.data.qrcode;
          this.uri = res.data.uri;
          this.isGeneratedCoupon = true;
        });
		},
    reGenerateCoupon(){
      this.isGeneratedCoupon = false
    },
  },
  mounted() {
    this.getCafeInfo();
  }
};
</script>

<style>
.couponbtn {
  width: 46%;
  background: #bee3db;
  border: 0;
}
</style>