<template>
  <div class="row mx-auto my-4">
   <div>
     <p>현재 보유중인 쿠폰 개수 : {{numberOfCoupon}}</p>
     <p>쿠폰 사용은 메뉴당 하나씩만 가능합니다.</p>
   </div>
   <button v-on:click="useCoupon(1, 1)">쿠폰 사용</button>
   <div>
      <VueGoodTable
         :columns="columns"
         :rows="rows"
         @on-cell-click="useCoupon"
      />
    </div>
  </div>
</template>

<script>
import 'vue-good-table/dist/vue-good-table.css'
import { VueGoodTable } from 'vue-good-table'
import axios from "axios"

export default {
  name: "CouponUseRequestPage",
  components: { VueGoodTable },
  data() {
    return {
      numberOfCoupon: null, //보유중인 쿠폰 개수
      columns: [
        {
          label: '제품명',
          field: 'product'
        },
        {
          label: '가격',
          field: 'price',
          type: 'number',
        },
        {
          label: '제품 설명',
          field: 'description',
        },
        {
          label: '개당 쿠폰 차감수',
          field: 'num_coupon',
          type: 'number'
        },
        {
          label: '쿠폰 사용',
          field: 'coupon_btn',
          html: true
        }
      ],
      rows: [],
      rowIndex: null,
    };
  },
  props: {
    cafe_id: {
      type: Number,
      default: 2
    }
  },
  methods: {
   async useCoupon(params){
     //보유 쿠폰 차감
      this.rowIndex = params.rowIndex
      const row = params.row
      const payload = {
        cafe_id: this.cafe_id,
        num_coupon: row.num_coupon
      }
      await axios.patch(`${this.$store.state.constants.SERVER}/coupon`, payload) //${cafe_id}
      .then(response => {
        console.log(response.data)
        this.requestCouponUse(row.num_coupon, row.mid)
      })
      .catch(err => {
        console.log(err.response);
      });
   },
   requestCouponUse(count, mid){
     //응답 성공 후 쿠폰 사용 리스트에 등록
      const payload = {
        cafe_id: this.cafe_id,
        count: count,
        mid: mid
      }
      axios.post(`${this.$store.state.constants.SERVER}/coupon`, payload) //${cafe_id}
      .then(response => {
        console.log(response.data.message)
        this.numberOfCoupon = this.numberOfCoupon - count
        this.deleteCouponUsedMenu()
      })
      .catch(err => {
        console.log(err.response);
      });
   },
   deleteCouponUsedMenu(){
    //  document.getElementById('coupon-use-btn' + mid).remove()
     let frontRows = this.rows.slice(0, this.rowIndex)
     let rearRows = this.rows.slice(this.rowIndex + 1, this.rows.length)
     let newRows = []
     frontRows.forEach(element => {
       newRows.push(element)
     })
     rearRows.forEach(element => {
       newRows.push(element)
     })

     this.rows = newRows

    //  this.rows = 
    //  this.rows.forEach(element => {
    //    console.log(element)
    //    if(element.mid === mid){
    //      this.rows.remove(element)
    //    }
    //  })
   },
   getMyCouponCount(){
     axios.get(`${this.$store.state.constants.SERVER}/coupon/${2}`) //${cafe_id}
      .then(response => {
        console.log(response.data)
        this.numberOfCoupon = response.data.numberOfCoupon;
      })
      .catch(err => {
        console.log(err.response);
      });
   },
  },
  beforeMount(){
      axios.get(`${this.$store.state.constants.SERVER}/menus/${1}`) //${cafe_id}
      .then(response => {
        console.log(response.data)
        this.rows = response.data.menus;
        this.rows.forEach(element => {
          element.coupon_btn = `<button id='coupon-use-btn${element.mid}'>쿠폰 사용</button>`
        });
      })
      .catch(err => {
        console.log(err.response);
      });
  },
  mounted() {
    this.getMyCouponCount()
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