<template>
  <div :id="elementId" :style="{ width, height }">
    <!-- daum kakao map -->
  </div>
</template>

<script>
import Map from '@/services/map/index'
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
      gettingLocation: false,
      errorStr: null,
      curLatitude: 33.450701, 
      curLongitude: 126.570667
    }
  },
  watch: {
    markers: {
      handler () {
        if (typeof window === 'undefined') return // SSR
        //this.initMap(this.markers)
      },
      immediate: true,
    },
  },
  methods: {
    async initMap (markers) {
      if (!this.map) {
        const map = new Map()
        await map.mount(this.elementId, this.curLatitude, this.curLongitude)

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
    }
  },
  created(){
    //Geolocation is supported?
    if(!("geolocation" in navigator)){
      this.errorStr = 'Geolocation is not available'
      return
    }

    this.gettingLocation = true

    //get current location
    navigator.geolocation.getCurrentPosition(pos => {
      this.gettingLocation = false
      this.curLatitude = pos.coords.latitude
      this.curLongitude = pos.coords.longitude
      this.initMap(this.markers)
    }, err => {
      this.gettingLocation = false
      this.errorStr = err.message
    })

  }
}
</script>