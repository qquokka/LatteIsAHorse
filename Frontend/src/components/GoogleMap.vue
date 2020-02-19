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
export default {
  name: "GoogleMap",
  components: {
    Loading
  },
  props: {
    prop_center: {
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
  },
  computed: {
    filteredCafes() {
        return this.$store.getters.filteredCafes(this.filtername)
      },
  }
};
</script>