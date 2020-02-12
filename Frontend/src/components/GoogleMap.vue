<template>
  <div>
    <loading :active.sync="mapLoading" :can-cancel="false" :is-full-page="true" loader="bars" color="violet"></loading>
    <gmap-map ref="gmap" 
              :center="center" 
              :zoom="zoom_level"
              :options="{
                          zoomControl: false, //zoom 컨트롤바 생성
                          mapTypeControl: false,
                          scaleControl: false,
                          streetViewControl: false,
                          rotateControl: false,
                          fullscreenControl: true,
                          disableDefaultUi: false,
                          gestureHandling: 'greedy', //Ctrl + 화면을 사용하려면 Google지도 확대 / 축소를 사용 비활성화
                          minZoom: 12, //최소 줌 레벨
                          maxZoom: 18 //최대 줌 레벨
                        }"
      style="width:100%;  height: 100%;"
    >
      <gmap-marker
        :position="prop_center"
        :clickable="true"
        :icon="require('../assets/icons/myloc.png')"
        
      ></gmap-marker>
      <gmap-marker
        :key="'m-' + idx"
        v-for="(cafe, idx) in cafes"
        :position="{ lat: +cafe.latitude, lng: +cafe.longitude }"
        :clickable="true"
        @click="infoWindow(idx)"
      ></gmap-marker>
        <gmap-info-window
        v-for="(cafe, idx) in cafes"
        :key="idx"
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
import axios from "axios";
import Loading from "vue-loading-overlay";
import "vue-loading-overlay/dist/vue-loading.css";
export default {
  name: "GoogleMap",
  components: {
    Loading
  },
  props: {
    prop_center: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      cafes: null,
      center: null,
      markers: [],
      places: [],
      zoom_level: 15,
      currentPlace: null,
      mapLoading: false,
      window_open: [],
      selected_cafe: {}
    };
  },
  watch: {
    prop_center: {
      handler() {
        this.center = this.prop_center
        axios
          .post(`${this.$store.state.constants.SERVER}/map/`, {
            longitude: this.prop_center.lng,
            latitude: this.prop_center.lat,
          })
          .then(res => {
            if (res.data.constructor === Array) {
              this.cafes = res.data;
            }
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
  },
  beforeMount() {
    this.center = this.prop_center
                for (var ix=0;ix < 1000;ix++) {
              this.window_open.push(false)
            }
  },
  mounted() {
    this.mapLoading = true
    this.geolocate();
    
  },
  methods: {
    infoWindow(idx) {
      // this.window_open[idx] = !this.window_open[idx]
      this.$set(this.window_open, idx, !this.window_open[idx])
      this.selected_cafe = this.cafes[idx]
      this.$emit('cafe_change_event', this.selected_cafe)
    },
    // receives a place object via the autocomplete component
    setPlace(place) {
      this.currentPlace = place;
    },
    geolocate() {
        axios
          .post(`${this.$store.state.constants.SERVER}/map/`, {
            longitude: this.prop_center.lng,
            latitude: this.prop_center.lat,
            level: 13,
          })
          .then(res => {

            setTimeout(() => {
              this.mapLoading = false
            }, 1000);   
            this.cafes = res.data;
          })
          .catch(err => {
            console.log(err);
          });
        
        
      }
  }
}
</script>