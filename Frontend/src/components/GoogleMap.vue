<template>
  <div>
    <gmap-map ref="gmap" 
              :center="center" 
              :zoom="zoom_level"
              @zoom_changed="zoomChanged" 
              :options="{
                          zoomControl: false, //zoom 컨트롤바 생성
                          mapTypeControl: false,
                          scaleControl: true,
                          streetViewControl: false,
                          rotateControl: false,
                          fullscreenControl: true,
                          disableDefaultUi: false,
                          gestureHandling: 'greedy', //Ctrl + 화면을 사용하려면 Google지도 확대 / 축소를 사용 비활성화
                          minZoom: 7, //최소 줌 레벨
                          maxZoom: 17 //최대 줌 레벨
                        }"
                style="width:100%;  height: 640px;">
      <gmap-marker 
          :key="index" 
          v-for="(m, index) in markers" 
          :position="m.position"
          :clickable="true" 
          @click="center=m.position">
      </gmap-marker>
    </gmap-map>
    <!-- <br>
    <div>
      <h2>Search and add a pin</h2>
      <label>
        <gmap-autocomplete @place_changed="setPlace"></gmap-autocomplete>
        <button @click="addMarker">Add</button>
      </label>
      <br/>

    </div> -->
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "GoogleMap",
  // props: {
  //   width: {
  //     type: String,
  //     required: false,
  //     default: '100%',
  //   },
  //   height: {
  //     type: String,
  //     required: false,
  //     default: '640px',
  //   },
  // },
  data() {
    return {
      // default to Montreal to keep it simple
      // change this to whatever makes sense
      center: { lat: 45.508, lng: -73.587 },
      markers: [],
      places: [],
      zoom_level: 14,
      currentPlace: null
    };
  },
  watch: {
    zoom_level: {
      
    },
  },
  mounted() {
    this.geolocate()
  },

  methods: {
    //Zoom In/Out 할 때 사용되는 callback func
    zoomChanged(){
      //  console.log(this.$refs.gmap.$mapObject.getZoom())
       axios.post("http://192.168.31.111:3000/v1/map/",{
                            'longitude': this.center.lng,
                            'latitude': this.center.lat,
                            'level': this.$refs.gmap.$mapObject.getZoom()
                    })
                    .then(res => {
                        //받아온 카페 리스트의 개수가 1개 이상일 때
                        // let cafePositions = []
                        // const imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"
                        if(res.data.constructor == Array){
                            // this.markers = []
                            console.log(res.data)
                            this.addCafeMarkers(res.data)
                            // res.data.forEach(function(cafe){
                            //     cafePositions.push({
                            //         title: cafe.cafe_name,
                            //         latlng: new Map.daum.maps.LatLng(cafe.latitude, cafe.longitude)
                            //     })
                            //     console.log(cafe.cafe_id)
                            //     console.log(cafe.cafe_name + '\n' +
                            //                 "latitude : " + cafe.latitude + '\n' +
                            //                 "longitude : " + cafe.longitude)
                            // })
                        }else{
                          this.markers = this.markers.splice(0, 1)
                        }
                    })
                    .catch(err => {
                        console.log(err)
                    });
    },
    addCafeMarkers(cafes){
        // console.log(cafes.constructor == Array)
        this.markers = this.markers.splice(0, 1)
        cafes.forEach((cafe) => {
           const marker = {
            lat: cafe.latitude,
            lng: cafe.longitude
          }
          this.markers.push({ position: marker })
        })
    },
    // receives a place object via the autocomplete component
    setPlace(place) {
      this.currentPlace = place;
    },
    addMarker() {
      if (this.currentPlace) {
        const marker = {
          lat: this.currentPlace.geometry.location.lat(),
          lng: this.currentPlace.geometry.location.lng()
        };
        this.markers.push({ position: marker });
        this.places.push(this.currentPlace);
        this.center = marker;
        this.currentPlace = null;
      }
    },
    geolocate: function() {
      //Use Geolocation to get current user's location
      navigator.geolocation.getCurrentPosition(position => {
        //Make a marker
        const marker = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        }
        this.markers.push({position: marker})
        //Set Map center position by user's current location
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        }
        
      })
    }
  }
};
</script>