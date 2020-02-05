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
    elementId: null,
    markers: {
      type: Array,
      default () {
        return []
      }
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
    level: {
      type: Number,
      required: false,
    }
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
    async initMap () {
      if (!this.map) {
        const map = new Map()
        await map.mount(this.elementId, this.curLatitude, this.curLongitude)



        this.map = map
        
      } else {
        this.map.clearMarkers()
      }
<<<<<<< HEAD


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

=======
      const happymap = this.map
      console.log(happymap.getLevel())
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
    getLevel() {
      console.log(this.map.level)
    }
  },
  mounted() {
    this.getLevel()
>>>>>>> b55562412104aa72952e30448291132ea37b5a30
  }
}
</script>