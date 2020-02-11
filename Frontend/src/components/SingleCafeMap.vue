<template>
  <div>
    <gmap-map
      ref="gmap"
      :center="{ lat: +cafe.latitude, lng:+cafe.longitude }"
      :zoom="zoom_level"
      :options="{
        zoomControl: false,
        mapTypeControl: false,
        scaleControl: true,
        streetViewControl: false,
        rotateControl: false,
        fullscreenControl: false,
        disableDefaultUi: false,
        gestureHandling: 'greedy', //Ctrl + 화면을 사용하려면 Google지도 확대 / 축소를 사용 비활성화
        minZoom: 7, //최소 줌 레벨
        maxZoom: 17 //최대 줌 레벨
      }"
      style="width:100%;  height: 70vh;"
    >
      <gmap-marker
        id="gmarker"
        :position="{ lat: +cafe.latitude, lng: +cafe.longitude }"
        :clickable="true"
        @click="infoWindow"
      ></gmap-marker>
      <gmap-info-window
        @closeclick="window_open = false"
        :opened="window_open"
        :position="{ lat: +cafe.latitude, lng: +cafe.longitude }"
        :options="{
          pixelOffset: {
            width: 0,
            height: -35
          }
        }"
      >
        <p v-if="isOpen" class="openbdg">OPEN</p>
        <p v-else class="closebdg">CLOSED</p>
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
import { library } from "@fortawesome/fontawesome-svg-core";
import { faPhoneAlt } from "@fortawesome/free-solid-svg-icons";

library.add(faPhoneAlt);
export default {
  name: "SingleCafeMap",
  props: {
    cafe: {
      type: Object,
      required: true
    },
    isOpen: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      places: [],
      zoom_level: 17,
      currentPlace: null,
      info_marker: null,
      window_open: true
    };
  },
  methods: {
    infoWindow() {
      this.window_open = !this.window_open;
    }
  },
  mounted() {}
};
</script>

<style>
.openbdg {
  margin: 0 auto 5px auto;
  width: fit-content;
  background: mediumaquamarine;
  padding: 3px;
  border-radius: 5px;
  color: white;
}
.closebdg {
  margin: 0 auto 5px auto;
  width: fit-content;
  background: purple;
  padding: 3px;
  border-radius: 5px;
  color: white;
}
</style>
