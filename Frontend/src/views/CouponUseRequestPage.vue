<template>
  <div class="row mx-auto my-4">
   
  </div>
</template>

<script>
export default {
  name: "CouponUseRequestPage",
  components: {},
  data() {
    return {
    };
  },
  props: {
    
  },
  methods: {
   
  },
  mounted() {
    
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