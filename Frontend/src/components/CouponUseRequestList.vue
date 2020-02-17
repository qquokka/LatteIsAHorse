<template>
  <div class="row mx-auto my-4">
    <div>
      <button v-on:click="refreshList">Refresh</button>
    </div>
    <div>
      <VueGoodTable
         :columns="columns"
         :rows="rows"
      />
    </div>
  </div>
</template>

<script>
import 'vue-good-table/dist/vue-good-table.css'
import { VueGoodTable } from 'vue-good-table'
import axios from "axios"

export default {
  name: "CouponUseRequestList",
  components: {
    VueGoodTable
  },
  data() {
    return {
      columns: [
        {
          label: '고객 닉네임',
          field: 'username',
        },
        {
          label: '고객 연락처',
          field: 'phone',
          // type: 'number',
        },
        {
          label: '고객 이메일',
          field: 'email',
          // type: 'number',
        },
        {
          label: '메뉴 이름',
          field: 'product',
          // type: 'number',
        },
        {
          label: '요청일',
          field: 'created_at',
          type: 'date',
          dateInputFormat: 'yyyy-MM-dd',
          dataOutputFormat: 'yyyy-MM-dd'
          // dateOutputFormat: 'MMM Do yy',
        }
      ],
      rows: []
    };
  },
  props: {
    cafe_id: Number
  },
  methods: {
    refreshList(){
      this.getCouponUseRequests()
    },
    getCouponUseRequests(){
      axios.get(`${this.$store.state.constants.SERVER}/coupons/${1}`) //${cafe_id}
      .then(response => {
        console.log(response.data)
        this.rows = response.data;
      })
      .catch(err => {
        console.log(err.response);
      });
    }

  },
  mounted() {
    this.getCouponUseRequests()
  }
};
</script>

<style>
.coupon-name {
  background: rgba(255, 255, 255, 0.7);
  transition: 255ms ease-in-out;
  padding: 1rem;
  margin-top: 40% !important;
  width: fit-content;
  border-radius: 50px;
}
.newmorph {
  overflow: hidden;
  height: 0;
  width: 100%;
  padding-bottom: 100%;
  background-size: cover !important;
  border-radius: 50px;
  background: linear-gradient(145deg, #e6e6e6, #ffffff);
  box-shadow: 26px 26px 51px #d9d9d9, -26px -26px 51px #ffffff;
  transition: 125ms ease-in-out;
}

.newmorph:hover {
  cursor: pointer;
}
.newmorph:hover .coupon-name {
  margin-top: 0% !important;
  width: 100%;
  border-radius: 0;
}
</style>