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
      :zoom="16"
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
      :style="`height: ${avheight}px;`"

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
      required: true
    }
  },
  data() {
    return {
      center: {},
      cafes: null,
      markers: [],
      places: [],
      zoom_level: 16,
      mapLoading: false,
      window_open: [],
      selected_cafe: {},
      avheight: 0,
    };
  },
  beforeMount() {
    this.avheight = Math.min(window.innerHeight - 75, window.innerWidth * 1.3)
    for (var ix = 0; ix < 1000; ix++) {
      this.window_open.push(false);
    }
  },
  mounted() {
    this.mapLoading = true;
    this.geolocate();
    
  },
  methods: {
    infoWindow(idx) {
      this.$set(this.window_open, idx, !this.window_open[idx]);
      this.selected_cafe = this.cafes[idx];
      this.$emit("cafe_change_event", this.selected_cafe);
    },
    geolocate() {
      axios
        .post(`${this.$store.state.constants.SERVER}/map/`, {
          latitude: this.prop_center.lat,
          longitude: this.prop_center.lng,
          level: 15
        })
        .then(res => {
          this.cafes = res.data;
          setTimeout(() => {
            this.mapLoading = false;
          }, 1);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>