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
          
        }
      ],
      rows: []
    };
  },
  props: {
    cafe_id: {
      type: Number,
      default: 2
    }
  },
  methods: {
    refreshList(){
      this.getCouponUseRequests()
    },
    getCouponUseRequests(){
      axios.get(`${this.$store.state.constants.SERVER}/coupons/${this.cafe_id}`) //${cafe_id}
      .then(response => {
        console.log(response.data.requests)
        this.rows = response.data.requests;
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
</style>