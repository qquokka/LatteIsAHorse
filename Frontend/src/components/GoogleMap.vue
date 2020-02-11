<template>
  <div>
    <loading :active.sync="mapLoading" :can-cancel="false" :is-full-page="true" loader="bars" color="violet"></loading>
    <gmap-map ref="gmap" 
              :center="center" 
              :zoom="zoom_level"
              @zoom_changed="zoomChanged" 
              :options="{
                          zoomControl: false, //zoom 컨트롤바 생성
                          mapTypeControl: false,
                          scaleControl: false,
                          streetViewControl: false,
                          rotateControl: false,
                          fullscreenControl: true,
                          disableDefaultUi: false,
                          gestureHandling: 'greedy', //Ctrl + 화면을 사용하려면 Google지도 확대 / 축소를 사용 비활성화
                          minZoom: 13, //최소 줌 레벨
                          maxZoom: 16 //최대 줌 레벨
                        }"
      style="width:100%;  height: 640px;"
    >
      <gmap-marker
        :key="index"
        v-for="(m, index) in markers"
        :position="m.position"
        :clickable="true"
        @click="center=m.position"
      ></gmap-marker>
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
      center: null,
      markers: [],
      places: [],
      zoom_level: 15,
      currentPlace: null,
      mapLoading: false
    };
  },
  watch: {
    prop_center: {
      handler() {
        this.center = this.prop_center
        axios
          .post(`${this.$store.state.constants.SERVER}/map/`, {
            longitude: this.center.lng,
            latitude: this.center.lat,
          })
          .then(res => {
            if (res.data.constructor === Array) {
              this.addCafeMarkers(res.data);
            } else {
              this.markers = this.markers.splice(0, 1)
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
  },
  mounted() {
    this.mapLoading = true
    this.geolocate();
    
  },
  methods: {
    addCafeMarkers(cafes) {
      cafes.forEach(cafe => {
        const marker = {
          lat: +cafe.latitude,
          lng: +cafe.longitude
        };
        this.markers.push({ position: marker });
      });
    },
    zoomChanged(e) {
      this.zoom_level = e;
    },
    // receives a place object via the autocomplete component
    setPlace(place) {
      this.currentPlace = place;
    },
    geolocate() {
      let position = this.center
        const marker = {
          lat: position.lat,
          lng: position.lng
        };
      
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
            
            if (res.data.constructor === Array) {
              this.addCafeMarkers(res.data);
              this.markers.push({ position: marker });
            }
            
            
          })
          .catch(err => {
            console.log(err);
          });
        
        
      }
  }
}
</script>