<template>
  <div>
    <loading
      :active.sync="mapLoading"
      :can-cancel="false"
      :is-full-page="true"
      loader="bars"
      color="violet"
    ></loading>
    <gmap-map
      ref="gmap"
      :center="prop_center"
      :zoom="17"
      :options="{
        zoomControl: true,
        mapTypeControl: false,
        scaleControl: false,
        streetViewControl: true,
        rotateControl: false,
        fullscreenControl: true,
        disableDefaultUi: false,
        minZoom: 12,
        maxZoom: 18
      }"
      :style="`height: 107%`"
    >
      <gmap-marker
        :position="prop_center"
        :clickable="true"
        :icon="require('../assets/icons/myloc.png')"
      ></gmap-marker>

      <gmap-marker
        :key="'marker' + cafe.cafe_id"
        v-for="(cafe, idx) in filteredCafes"
        :position="{ lat: +cafe.latitude, lng: +cafe.longitude }"
        :clickable="true"
        @click="infoWindow(idx)"
      ></gmap-marker>
      <gmap-info-window
        v-for="(cafe, idx) in filteredCafes"
        :key="idx"
        :ref="'info'+idx"
        @closeclick="window_open[idx]=false"
        :opened="window_open[idx]"
        :position="{ lat: +cafe.latitude, lng: +cafe.longitude }"
        :options="{
          pixelOffset: {
            width: 0,
            height: -35
          }
        }"
      >
        <h5>{{ cafe.cafe_name }}</h5>
        <small class="m-0">{{ cafe.cafe_address }}</small>
        <h6 class="mt-3 font-weight-bold">
          <fa icon="phone-alt" />
          {{ cafe.cafe_phone }}
        </h6>
      </gmap-info-window>
    </gmap-map>
  </div>
</template>

<script>
import Loading from "vue-loading-overlay";
import "vue-loading-overlay/dist/vue-loading.css";
import axios from 'axios'

export default {
  name: "GoogleMap",
  components: {
    Loading
  },
  props: {
    prop_center: { //지도의 중심 위치
      type: Object,
      required: true
    },
    filtername: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      center: {},
      zoom_level: 16,
      mapLoading: false,
      window_open: [],
      selected_cafe: {},
      avheight: 0,
      hashtags: []
    };
  },
  beforeMount() {
    this.avheight = Math.min(window.innerHeight - 52.78, window.innerWidth * 1.3);
    for (var lc=0;lc<1000;lc++) {
      this.window_open.push(false);
    }
  },
  methods: {
    infoWindow(id) {
      this.window_open.splice(id, 1, !this.window_open[id])
      this.selected_cafe = this.filteredCafes[id];
      this.$emit("cafe_change_event", this.selected_cafe);
    },
    getHashtagsByCafeIds(cafe_ids){
      
      console.log("cafe_ids : " + cafe_ids)
      console.log(typeof cafe_ids)
      axios
        .post(`${this.$store.state.constants.SERVER}/map/hashtags`, {
          cafe_ids: cafe_ids
        })
        .then(res => {
          if(res.status === 200){
            console.log(res.data)
            this.hashtags = res.data.map_hashtags
            this.$emit("hashtag_get_event", this.hashtags)
            console.log(this.hashtags)
          }
        })
        .catch(err => {
          console.log(err)
        });
    },
    geolocate() {
      axios
        .post(`${this.$store.state.constants.SERVER}/map/`, {
          latitude: this.prop_center.lat,
          longitude: this.prop_center.lng,
          level: 12
        })
        .then(res => {
          console.log(res.data)
          this.cafes = res.data.filter(ccc => ccc.cafe_name.includes(this.filtername));
          //cafes 리스트에서 cafe_id만 추출하여 새로운 리스트 만듬
          const cafe_ids = res.data.map((cafe) => { return cafe.cafe_id})
          //카페 ID로 해시태그 가져오기
          this.getHashtagsByCafeIds(cafe_ids)
          this.$forceUpdate()
          setTimeout(() => {
            this.mapLoading = false;
          }, 1);
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  computed: {
    filteredCafes() {
        return this.$store.getters.filteredCafes(this.filtername)
      },
  }
};
</script>