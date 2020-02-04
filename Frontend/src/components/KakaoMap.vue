<template>
  <div :id="elementId" :style="{ width, height }" v-on:scroll="handleScroll('event')">
    <!-- daum kakao map -->
  </div>
</template>

<script>
import Map from '@/services/map/index'
import debounce from 'lodash/debounce'
// ...

export default {

  props: {
    elementId: {
      type: String,
      required: true,
    },
    markers: {
      type: Array,
      default () {
        return []
      },
      required: true,
    },
    width: {
      type: String,
      required: false,
      default: '100%',
    },
    height: {
      type: String,
      required: false,
      default: '640px',
    },
  },
  data () {
    return {
      map: null,
      isUserScrolling: 0
    }
  },
  watch: {
    markers: {
      handler () {
        if (typeof window === 'undefined') return // SSR
        this.initMap(this.markers)
      },
      immediate: true,
    },
  },
  methods: {
    handleScroll(event){
      alert(event)
      this.isUserScrolling = (window.scrollY > 0);
      console.log('calling handleScroll');
    },
    async initMap (markers) {
      if (!this.map) {
        const map = new Map()
        await map.mount(this.elementId)

        map.addMarkerClusters([
          {
            key: 'cluster1',
            color: '#222529',
            zIndex: 0,
            singleIconURL: 'ClusterIcon1',
          },
          {
            key: 'cluster2',
            color: '#209cee',
            zIndex: 1,
            singleIconURL: 'ClusterIcon2',
          },
        ])

        this.map = map
      } else {
        this.map.clearMarkers()
      }

      this.map.addMarkers(
        markers.map(
          (marker) => {
            const { name, type, location: { lat, lng } } = marker
            return {
              lat,
              lng,
              clusterKey: type,
              title: name,
              onClick: () => {
                this.$emit('click-marker', marker)
              },
            }
          }
        )
      )
    },
    setCenter (lat, lng) {
      this.map && this.map.setCenter({ lat, lng, maxLevel: 10 })
    },
  },
  created() {
    this.handleDebouncedScroll = debounce(this.handleScroll, 100);
    window.addEventListener('scroll', this.handleDebouncedScroll);
  },
  beforeDestroy() {
    // I switched the example from `destroyed` to `beforeDestroy`
    // to exercise your mind a bit. This lifecycle method works too.
    window.removeEventListener('scroll', this.handleDebouncedScroll);
  }
}
</script>