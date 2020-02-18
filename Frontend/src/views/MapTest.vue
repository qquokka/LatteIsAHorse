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
      <div style="font-size:1vw;position:fixed;z-index:99">

        <div class="btn-group dropright mt-2 ">
          <div class="menu-icon" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fa icon="search-location" size="2x" />
            <p class="menu-tex">검 색</p>
          </div>

          <div class="dropdown-menu" >
            <div class="p-0">
              <input type="text" id="namefilter" placeholder="카페이름으로 검색" v-model="filtername">
            </div>
          </div>
        </div>
        <br>
        <div class="btn-group dropright">
          <div class="menu-icon" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fa icon="filter" size="2x" />
            <p class="menu-tex">필 터</p>
          </div>

          <div class="dropdown-menu">
            <div v-for="hashtag in hashtags" :key="hashtag.hashtags_id"  class="p-0">
              {{hashtag.name}}
            </div>
            <!-- Dropdown menu links -->
          </div>
        </div>

        <div class="menu-icon" @click="locateMe">
          <fa icon="map-marked-alt" size="2x" />
          <p class="menu-tex">내위치</p>
        </div>
      </div>

      <google-map
        v-if="center"
        class="col-12 col-md-9 p-0"
        :prop_center="center"
        :filtername="filtername"
        :key="filtername"
        @cafe_change_event="cafeChange"
      />
      <input @hashtag_get_event="getHashtags" type = "hidden">
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
          <div style="background-color:#BEE3DB" class="partenerbdg" v-if="cafe.cafeinfo.owner"><fa icon="crown" style="color: gold;margin-right: 4px" />파트너 카페</div>
          <div style="background-color:lightgray" class="partenerbdg" v-else><fa icon="mug-hot" style="color: royalblue;margin-right: 4px" />일반 카페</div>
          <h2 class="pt-3">{{ cafe.cafeinfo.cafe_name }}</h2>
          <p v-if="cafe.cafeinfo.owner">{{ cafe.cafeinfo.owner }}님의 카페</p>
          <p class="text-muted" v-else>(미등록)</p>
          <p class="small"><fa icon="phone-alt" class="mr-2" />{{ cafe.cafeinfo.cafe_phone }}</p>
          <div class="border tag-zone">
            <p class="tag-zone-header">TAGS</p>
            <p v-if="cafe.cafeinfo.tags">{{ cafe.tags }}</p>
            <p v-else>(이 카페엔 등록된 태그가 없습니다.)</p>
          </div>
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
          <div class="border" v-for="review in cafe.post.reverse()" :key="review.id">
            <h4 class="border-bottom text-left mx-2">{{ review.title }}<span style="font-size:0.8rem;" class="ml-3 text-info">@{{ review.writer_name }}</span></h4>
            <p class="text-left small p-1 rcontent" v-html="review.content"></p>
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
      filtername: '',
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
      ],
      hashtags: []
    };
  },
  computed: {},
  methods: {
    getHashtags(hashtags){
      alert('asdfadfads')
      this.hashtags = hashtags
    },
    cafeChange(sc) {
      this.detailLoading = true
      axios
        .get(
          `${this.$store.state.constants.SERVER}/cafe/detail/${sc.cafe_id}`
        )
        .then(response => {
          console.log(response.data)
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
      this.$forceUpdate()
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
    axios.get(`${this.$store.state.constants.SERVER}/cafe/detail/1`)
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

  },
};
</script>

<style>
img {
  max-width: 100% !important;
}
.tag-zone {
  height: 3rem;
  margin: 1rem;
  font-weight: 400;
}
.tag-zone-header {
  text-align: left;
  margin: -0.7rem 0 0 1rem;
  background: white;
  width: fit-content;
  padding: 0 1rem;
}
.partenerbdg {
  width: 8rem;
  border-radius: 10px;
  font-weight: 400;
  margin: 5px
}
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
  border-bottom: 1px solid lightgray;
  width: 100%;
}
.menu-icon > i {
  color: transparent !important;
  -webkit-text-stroke: 1px #2f2f2f;
}
.menu-icon {
  cursor: pointer;
  padding: 5px 1.3rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  transition: 0.2s;
  background: #f2f2f2;
  margin-bottom: 1rem;
  border-radius: 10px;
}
.menu-icon:hover {
  background: #BEE3DB
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
  border: 1px solid lightgray
}
</style>