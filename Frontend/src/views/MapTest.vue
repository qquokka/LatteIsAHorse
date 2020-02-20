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
        <div class="btn-group dropright mt-2">
          <div class="menu-icon" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fa icon="search-location" size="2x" />
            <p class="menu-tex">검 색</p>
          </div>

          <div class="dropdown-menu">
            <form class="p-0" @submit.prevent="changeNameFilter">
              <input
                type="text"
                id="namefilter"
                placeholder="카페이름으로 검색"
                
              />
            </form>
          </div>
        </div>
        <br />
        <div class="btn-group dropright">
          <div class="menu-icon" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fa icon="filter" size="2x" />
            <p class="menu-tex">필 터</p>
          </div>

          <div class="dropdown-menu">
            <!-- Dropdown menu links -->
          </div>
        </div>

        <div class="menu-icon" @click="locateMe">
          <fa icon="map-marked-alt" size="2x" />
          <p class="menu-tex">내위치</p>
        </div>
      </div>

      <google-map
        class="col-12 col-md-9 p-0 border"
        style="margin-top: -2rem"
        :prop_center="center"
        :filtername="filtername"
        @cafe_change_event="cafeChange"
      />
    </div>
    <div class="d-none d-lg-block col-lg-3 bg-white px-0 infocol" v-if="cafe.cafeinfo">
      <loading
        :active.sync="detailLoading"
        :can-cancel="false"
        :is-full-page="false"
        loader="bars"
        color="violet"
      ></loading>
      <div class="info-header p-0">
        <div style="background-color:#BEE3DB" class="partenerbdg" v-if="cafe.cafeinfo.owner">
          <fa icon="crown" style="color: gold;margin-right: 4px" />파트너 카페
        </div>
        <div style="background-color:lightgray" class="partenerbdg" v-else>
          <fa icon="mug-hot" style="color: royalblue;margin-right: 4px" />일반 카페
        </div>
        <h2>{{ cafe.cafeinfo.cafe_name }}</h2>
        <p v-if="cafe.cafeinfo.owner">{{ cafe.cafeinfo.owner }}님의 카페</p>
        <p class="text-muted" v-else>(미등록)</p>
        <p class="small">
          <fa icon="phone-alt" class="mr-2" />
          {{ cafe.cafeinfo.cafe_phone }}
        </p>
        <div class="border tag-zone">
          <p class="tag-zone-header">TAGS</p>
          <div class="row mx-auto" v-if="tags">
            <router-link class="m-2 hashlink" v-for="tag in tags" :key="tag.hashtag_id + 'hash'"  :to="`/hashtag/${tag.name}`">
              <span>
                #{{ tag.name }}
              </span>
            </router-link>
          </div>
          <div class="row" v-else>(이 카페엔 등록된 태그가 없습니다.)</div>
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
      <div class="container p-0">
        <p class="py-2">리뷰 : {{ cafe.post.length }}</p>
        <div class="border" v-for="review in cafe.post.slice().reverse()" :key="review.id">
          <h4 class="border-bottom text-left mx-2">
            {{ review.title }}
            <span style="font-size:0.8rem;" class="ml-3 text-info">@{{ review.writer_name }}</span>
          </h4>
          <p class="text-left small p-1 rcontent" v-html="review.content"></p>
        </div>
      </div>
    </div>
    <div class="d-none col-lg-3 text-center bg-white px-0 infocol d-flex" v-else>
      <h4 class="my-auto" style="margin: auto">지도에서 마커를 눌러주세요</h4> 
    </div>
  </div>
</template>

<script>
import GoogleMap from "@/components/GoogleMap.vue";
import NavBar from "@/components/NavBar.vue";
import Loading from "vue-loading-overlay";
import "vue-loading-overlay/dist/vue-loading.css";
import { library } from "@fortawesome/fontawesome-svg-core";
import axios from "axios";
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
      filtername: "",
      center: {},
      locmeLoading: false,
      detailLoading: false,
      cafe: {},
      tags: [],
      thumb: [
        "url(" + require("@/assets/noimage.png") + ")",
        "url(" + require("@/assets/noimage.png") + ")",
        "url(" + require("@/assets/noimage.png") + ")",
        "url(" + require("@/assets/noimage.png") + ")",
        "url(" + require("@/assets/noimage.png") + ")",
        "url(" + require("@/assets/noimage.png") + ")"
      ]
    };
  },
  methods: {
    changeNameFilter() {
      this.filtername = document.getElementById('namefilter').value
    },
    cafeChange(sc) {
      this.detailLoading = true;
      axios
        .get(`${this.$store.state.constants.SERVER}/cafe/detail/${sc.cafe_id}`)
        .then(response => {
          this.cafe = response.data;
          axios
          .post(`${this.$store.state.constants.SERVER}/map/hashtags`,{"cafe_ids": [response.data.cafeinfo.cafe_id]})
          .then(tagresponse=> [
            this.tags = tagresponse.data.map_hashtags
          ])
          .catch(e => {
            console.log(e.response);
            
          })
          this.detailLoading = false;
          for (let i = 0; i < 6; i++) {
            if (i < response.data.post.length) {
              this.$set(
                this.thumb,
                i,
                `url(${response.data.post[i].thumbnail})`
              );
            } else {
              this.$set(
                this.thumb,
                i,
                `url(${require("@/assets/noimage.png")})`
              );
            }
          }
        });
    },
    locateMe() {
      this.locmeLoading = true;
      navigator.geolocation.getCurrentPosition(this.success, this.fail, {
        enableHighAccuracy: true,
        timeout: 20000,
        maximumAge: 0
      });
    },
    success(position) {
      this.loadend();
      this.center = {
        lat: +position.coords.latitude,
        lng: +position.coords.longitude
      }
      axios
        .post(`${this.$store.state.constants.SERVER}/map/`, {
          latitude: parseFloat(position.coords.latitude),
          longitude: parseFloat(position.coords.longitude),
          level: 12
        })
        .then(res => {
          this.$store.state.nearme.cafes = res.data;
        })
    },
    fail() {
      this.loadend();
    },
    loadend() {
      setTimeout(() => {
        this.locmeLoading = false;
      }, 500);
    }
  },
  mounted() {
    this.avheight = window.innerHeight - 75;
    this.locateMe()
    setTimeout(() => {
      for (let i = 0; i < 6; i++) {
        this.$set(this.thumb, i, `url(${this.cafe.post[i].thumbnail})`);
      }
    }, 500);
  }
};
</script>

<style>
img {
  max-width: 100% !important;
}
.tag-zone {
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
  margin: 0 5px;
}
#fixcont {
  position: fixed;
  top: 75px;
  width: 100%;
}
.mapnav {
  position: fixed;
}
.hashlink:hover {
  color: royalblue !important;
  border-radius: 10px;
  padding: 5px;
  border: 1px solid royalblue !important;
}
.menucol-1 {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 0;
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
  background: #bee3db;
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
  overflow-x: hidden;
  z-index: 99;
  background: lavender;
  position: relative;
  overflow-y: scroll;
  border: 1px solid lightgray;
}

@media only screen and (max-width: 991px) {
  .infocol {
    display: none !important;
    min-height: 0;
  }
}
</style>