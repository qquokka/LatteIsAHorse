<template>
  <div class="container-fluid p-0">
    <loading
      :active.sync="locmeLoading"
      :can-cancel="false"
      :is-full-page="true"
      loader="bars"
      color="violet"
    ></loading>

    <nav-bar class="mapnav" />

    <div id="fixcont" class="row p-0 m-0" :style="`height: ${avheight}px;background:#f2f2f2`">
      <div class="d-fixed d-md-flex col-md-1 menucol-1" style="font-size:1vw">
        <div class="menu-icon">
          <router-link to="/">
            <fa icon="home" size="3x" />
          </router-link>
          <p class="menu-tex">돌아가기</p>
        </div>

        <div class="menu-icon">
          <fa icon="search-location" size="3x" />
          <p class="menu-tex">검색</p>
        </div>

        <div class="menu-icon">
          <fa icon="filter" size="3x" />
          <p class="menu-tex">필터적용</p>
        </div>

        <div class="menu-icon" @click="locateMe">
          <fa icon="map-marked-alt" size="3x" />
          <p class="menu-tex">내 위치</p>
        </div>
      </div>

      <google-map
        v-if="center"
        class="col-12 col-md-8 p-0"
        :prop_center="center"
        @cafe_change_event="cafeChange"
      />
    </div>
          <div class="d-none d-md-block col-md-3 bg-white px-0 infocol" v-if="cafe.cafeinfo">
                      <loading
          :active.sync="detailLoading"
          :can-cancel="false"
          :is-full-page="false"
          loader="bars"
          color="violet"
        ></loading>
        <div class="info-header">

          <h1 class="font-weight-bold pt-3">{{ cafe.cafeinfo.cafe_name }}</h1>
          <small>Cafe</small>
          <p>대표자: {{ cafe.cafeinfo.owner }}</p>
          <p class="small">{{ cafe.cafeinfo.cafe_phone }}</p>
        </div>
        <div class="container px-1">
          <div class="row align-content-center">
            <div
              v-for="i in 6"
              :key="'t' + i"
              class="frame col-4"
              :style="`background-image: ${thumb[i-1]}`"
            ></div>
          </div>
          <router-link :to="`/cafe/${cafe.cafeinfo.cafe_id}`">
            <button class="btn btn-block btn-outline-dark mt-2">자세히 보기</button>
          </router-link>
        </div>
        <div class="container">
          <p class="py-2">리뷰 : {{ cafe.post.length }}</p>
          <div v-for="review in cafe.post" :key="review.id">
            <p>{{ review.title }}<span class="ml-3 text-info">@{{ review.writer_name }}</span></p>
            <p class="border shadow p-2">{{ review.content }}</p>
          </div>
          
        </div>
      </div>

  </div>
</template>

<script>
import GoogleMap from "@/components/GoogleMap.vue";
import NavBar from "@/components/NavBar.vue";
import Loading from "vue-loading-overlay";
import "vue-loading-overlay/dist/vue-loading.css";
import { library } from "@fortawesome/fontawesome-svg-core";
import axios from "axios"
import {
  faHome,
  faSearchLocation,
  faFilter,
  faMapMarkedAlt
} from "@fortawesome/free-solid-svg-icons";
library.add(faHome, faSearchLocation, faFilter, faMapMarkedAlt);

export default {
  name: "map_test",
  components: {
    GoogleMap,
    NavBar,
    Loading
  },
  data() {
    return {
      avheight: 0,
      center: { lat: parseFloat(37.5014281), lng: parseFloat(127.0385063) },
      locmeLoading: false,
      detailLoading: false,
      cafe: {},
      thumb: [
        'url(' + require('@/assets/noimage.png') + ')',
        'url(' + require('@/assets/noimage.png') + ')',
        'url(' + require('@/assets/noimage.png') + ')',
        'url(' + require('@/assets/noimage.png') + ')',
        'url(' + require('@/assets/noimage.png') + ')',
        'url(' + require('@/assets/noimage.png') + ')',
      ]
    };
  },
  computed: {},
  methods: {
    cafeChange(sc) {
      this.detailLoading = true
      axios
        .get(
          `${this.$store.state.constants.SERVER}/cafe/detail/${sc.cafe_id}`
        )
        .then(response => {
          this.cafe = response.data
          this.detailLoading = false
          
          for (let i=0;i<6;i++){
            if (i < response.data.post.length) {
              this.$set(this.thumb, i, `url(${response.data.post[i].thumbnail})`)
            } else {
              this.$set(this.thumb, i, `url(${require('@/assets/noimage.png')})`)
            }
          }
        })
        .catch(error => {
          console.log(error.data);
        });

    },
    locateMe() {
      this.locmeLoading = true;
      navigator.geolocation.getCurrentPosition(this.success, this.fail, {
        enableHighAccuracy: true
      });
    },
    success(position) {
      this.loadend();
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };
    },
    fail(error) {
      this.loadend();
      console.log(error);
    },
    loadend() {
      setTimeout(() => {
        this.locmeLoading = false;
      }, 500);
      
    }
  },
  beforeMount() {
    axios.get('https://13.125.168.55:3000/v1/cafe/detail/1')
    .then(r=>{
      this.cafe = r.data
      this.center = {
        lat: r.data.cafeinfo.latitude,
        lng: r.data.cafeinfo.longitude
      };  
      
    })
  },
  mounted() {
    this.avheight = window.innerHeight - 75;
    setTimeout(() => {
      for (let i=0;i<6;i++){
        this.$set(this.thumb, i, `url(${this.cafe.post[i].thumbnail})`)
      }
    }, 500);

  }
};
</script>

<style>
#fixcont {
  position: fixed;
  top: 75px;
  width: 100%;
}
.mapnav {
  position: fixed;
}
.menucol-1 {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding:0;
  height: 100%;
  padding: 3rem 0;
}
.menu-tex {
  color: #2f2f2f;
  margin: auto;
  font-weight: 600;
  font-size: 1rem;
  text-align: center;
}
.info-header {
  background: lavender;
  border-bottom: 1px solid lightsteelblue;
  width: 100%;
}
.menu-icon > i {
  color: transparent !important;
  -webkit-text-stroke: 1px #2f2f2f;
}
.menu-icon {
  cursor: pointer;
  padding: 5px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  transition: 0.2s;
  border-radius: 21px;
  background: #f2f2f2;
}
.menu-icon:hover {
  border-radius: 21px;
  background: linear-gradient(145deg, #ffffff, #dadada);
  box-shadow: 9px 9px 18px #cecece, -9px -9px 18px #ffffff;
}
.menu-icon:hover i {
  color: lavender !important;
}
.menu-icon:hover .menu-tex {
  color: #2f2f2f;
}
.frame {
  height: 150px;
  overflow: hidden;
  background-size: cover;
}
.infocol {
  margin-left: auto;
  min-height: 100vh;
  overflow-x:hidden;
  z-index: 99;
  display: flex;
  background: lavender;
  position:relative;
  overflow-y: scroll;
  border: 1px solid lightsteelblue
}
</style>