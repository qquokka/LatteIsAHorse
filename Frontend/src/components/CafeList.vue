<template>
  <div class="row mx-auto my-4">
    <div v-for="cafe in cafeData" :key="cafe.cafe_id" class="col-12 col-sm-6 col-lg-4 mx-0">
      <router-link :to="`/cafe/${cafe.cafe_id}`">
        <div class="newmorph my-2 mx-0" :style="{ backgroundImage: `url('${cafe.thumbnail}')` }">
          <div class="card-body">
            <h4 class="cafe-name mx-auto">
              {{ cafe.cafe_name }}
              <p class="text-truncate" style="font-size: 0.6rem">{{ cafe.cafe_address }}</p>
            </h4>
          </div>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "CafeList",
  components: {},
  data() {
    return {};
  },
  props: {
    cafeData: []
  },
  methods: {},
  mounted() {}
};
</script>

<style>
.cafe-name {
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
.newmorph:hover .cafe-name {
  margin-top: 0% !important;
  width: 100%;
  border-radius: 0;
}
</style>