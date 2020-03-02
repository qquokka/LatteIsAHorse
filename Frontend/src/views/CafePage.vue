<template>
  <div id="cafe-page-container" class="container-fluid p-0">
    <!-- global component -->
    <loading :active.sync="isLoading" :can-cancel="false" :is-full-page="fullPage" loader="bars" color="violet"></loading>
    <div v-if="selectedImage" id="imgView">
      <img :src="selectedImage" @click.stop="selectedImage = null" />
    </div>
    <router-link id="reviewWriteBtn" :to="`/cafe/${cafeId}/posts/create`" ><fa icon="pencil-alt" size="2x" style="filter: drop-shadow(0 0 1px white)" /></router-link>
    <router-link class="d-fixed d-md-none" id="couponAddBtn" :to="`/cafe/${cafeId}/coupon`" ><fa icon="ticket-alt" size="2x" style="filter: drop-shadow(0 0 1px white)" /></router-link>
    <!-- global end-->
    <nav-bar />
      <div id="topbar" class="justify-content-between align-items-center">
        <div class="ml-2" @click="$router.back()"><fa icon="undo" /></div>
        <div v-if="info">{{ info.cafe_name }}</div>
        <div v-else>ERROR</div>
        <div class="mr-2"><fa icon="coffee" /></div>
      </div>
      
    <div class="row m-0 mt-0 mt-lg-5 border-0" v-if="info">

      <single-cafe-map :cafe="info" :isOpen="isOpen" class="col-12 col-lg-5 p-0" />
      <div class="col px-2">
        <div class="align-items-center d-none d-lg-flex">
          <h1 class="cafe-name-detail">{{ info.cafe_name }}</h1>
          <p v-if="isOpen" class="openbdg ml-2" style="font-size: 1rem;">영업중</p>
          <p v-else style="font-size: 1rem;" class="closebdg ml-2">준비중</p>
        </div>
        <div class="row mt-3 mt-lg-1 justify-content-between px-4">
          <h6 class="text-left shortinfo">
            <fa style="color:gold;margin-right:0.4rem" icon="crown" />대표자명:
            <span class="text-muted">{{ info.owner_name || '미등록' }}</span>
          </h6>

          <h6 class="text-left shortinfo">
            <fa style="color:turquoise;margin-right:0.5rem;margin-left:0.2rem" icon="phone-square" />전화번호:
            <span class="text-muted">{{ info.cafe_phone }}</span>
          </h6>

          <h6 class="text-left shortinfo">
            <fa style="color:crimson;margin-right:0.5rem" icon="shopping-basket" />인기메뉴:
            <span class="text-muted">{{ mostPopular() }}</span>
          </h6>
        </div>

        <div class="row justify-content-center my-4 justify-content-lg-center m-0">
          <h3 class="px-2 mr-lg-5">
            <fa class="mr-2 bouncer" style="color: royalblue" icon="heartbeat" />
            <ICountUp
              style="font-family:monospaced"
              :delay="delay"
              :endVal="info.like_count"
              :options="options"
            />
            <span style="font-size:1rem">LIKED</span>
          </h3>

          <h3 class="ml-lg-5">
            <fa class="mr-2" style="color: orange" icon="envelope-open-text" />
            <ICountUp
              style="font-family:monospaced"
              :delay="delay"
              :endVal="reviews.length"
              :options="options"
            />
            <span style="font-size:1rem;margin-left:0.5rem">REVIEWED</span>
          </h3>
        </div>

        <div class="row justify-content-around p-3">
          <div
            class="col-12 col-md-4 col-lg-3 cafe-preview"
            @click="zoom(info.thumbnail)"
            :style="`background:url('${info.thumbnail}')`"
          />
          <div
            v-if="reviews[lastreview]"
            class="col-12 col-md-4 col-lg-3 cafe-preview"
            @click="zoom(reviews[lastreview].thumbnail)"
            :style="`background:url('${reviews[lastreview].thumbnail}')`"
          />
          <div
            v-if="reviews[lastreview-1]"
            class="col-12 col-md-4 col-lg-3 cafe-preview"
            @click="zoom(reviews[lastreview-1].thumbnail)"
            :style="`background:url('${reviews[lastreview-1].thumbnail}')`"
          />
          <div
            v-if="reviews[lastreview-2]"
            class="col-12 col-md-4 col-lg-3 cafe-preview"
            @click="zoom(reviews[lastreview-2].thumbnail)"
            :style="`background:url('${reviews[lastreview-2].thumbnail}')`"
          />
        </div>
        <div class="my-2" style="font-size: calc(1rem + 0.5vw)">영업 시간 </div>
        <div class="row weekrow">
          
          <div class="col wcol border-right">
            <p class="border-bottom weekday">
              <fa icon="calendar" />
            </p>
            <p>open</p>
            <p>close</p>
          </div>
          <div v-for="i in info.time.length - 1" :key="'time' + i" class="col wcol" :id="i">
            <p class="weekday">{{ info.time[i][2] }}</p>
            <p>{{ info.time[i][0].slice(11, 16) }}</p>
            <p>{{ info.time[i][1].slice(11, 16) }}</p>
          </div>
          <div class="col wcol" id="0">
            <p
            class="weekday"
              style="color:crimson;border-bottom: 1px solid crimson;"
            >{{ info.time[0][2] }}</p>
            <p>{{ info.time[0][0].slice(11, 16) }}</p>
            <p>{{ info.time[0][1].slice(11, 16) }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="container px-4" v-if="menus" >
      <h4 class="my-5 cafe-page-section-name">
        <fa icon="mug-hot" style="color: brown;margin-right:0.5rem" />MENU
      </h4>
      <hr />
      <div  v-for="menu in menus" :key="menu.mid * 1121" class="row menurow">
        <div class="col-4 justify-content-between">
          <p class="text-truncate text-left menutitle">{{ menu.product }}</p>
        </div>
        <div class="col-1 ">
          <p
            style="cursor:pointer;"
            class="menutitle text-left"
            @click="pushLikeMenu(menu.mid, menu.user_like)">
            <fa
              :icon="menu.user_like?['fas', 'heart']:['far', 'heart']"
              style="cursor: pointer; color: violet;display:inline" />
            {{ menu.like_count? menu.like_count : 0 }}
          </p>
        </div>
        <div class="col-5 text-truncate text-left">
          <p class="text-muted menutitle">{{ menu.description || '(아직 설명이 없는 메뉴에요)' }}</p>
        </div>

        <div class="col-2 menutitle">
            {{ menu.price }} ₩
        </div>
      </div>
    </div>

    <hr />
    <h4 class="my-5 cafe-page-section-name">
      <fa icon="envelope-open-text" style="color:orange;margin-right:0.5rem" />REVIEW
    </h4>
    <div class="row m-0 px-0 container mx-auto">
      <div
        class="container my-3 overflow-hidden col-12 review-link p-0"
        v-for="review in reviews.slice().reverse()"
        :key="review.id"

      >
        <router-link :to="`/cafe/${cafeId}/review/${review.id}/`">
          <div
            class="justify-content-center px-2 p-0 border"
            style="background: #f3f3f3;"
          >
            <h1 class="py-2 text-center text-truncate" style="font-size: calc(1.5rem + 1vw)">
              {{ review.title }}
            </h1>
            <h5 class="text-left bg-white py-1">
              <fa class="text-muted" icon="user-circle" />
              {{ review.writer_name }}
              <span  style="font-size:0.8rem;text-align:left">{{ review.updated_at.slice(0,12) }}</span>
            </h5>
            
          </div>
          <div class="border">
            <div class="m-2 border">
              <div class="row m-0 mt-2 justify-content-center">
                <div
                  :style="`background: url(${review.thumbnail || require('../assets/img/loginbg.jpg')});height: 60vh;padding:0;background-size: cover !important;`"
                  class="col-11"
                  @error="imgPlaceholder"
                />
              </div>
              <div class="p-3 p-lg-5 line-clamp" style="height: 225px;">
                <div class="imghtml text-left" v-html="review.content"></div>
              </div>
              <div style="font-size: calc(2rem+2vw)" class="row justify-content-center">
                댓글 : (...)
              </div>
            </div>
            <div style="">- {{ review.id }} -</div>
          </div>
        </router-link>
      </div>
    </div>
    </div>
</template>

<script>
import axios from "axios";
import ICountUp from "vue-countup-v2";
import moment from "moment";
import Loading from "vue-loading-overlay";
import "vue-loading-overlay/dist/vue-loading.css";
import NavBar from "@/components/NavBar.vue";
import SingleCafeMap from "@/components/SingleCafeMap.vue";
import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faCrown,
  faPhoneSquare,
  faShoppingBasket,
  faHeartbeat,
  faEnvelopeOpenText,
  faCalendar,
  faMugHot,
  faMoneyBill,
  faThumbsUp,
  faUserCircle,
  faPencilAlt,
  faHeart as fasHeart,
  faTicketAlt
} from "@fortawesome/free-solid-svg-icons";
import { faHeart as farHeart } from "@fortawesome/free-regular-svg-icons";
library.add(
  faCrown,
  faPhoneSquare,
  faShoppingBasket,
  faHeartbeat,
  faEnvelopeOpenText,
  faCalendar,
  faMugHot,
  faMoneyBill,
  faThumbsUp,
  faUserCircle,
  fasHeart, farHeart,
  faPencilAlt,
  faTicketAlt
);

export default {
  name: "CafePage",
  components: {
    NavBar,
    SingleCafeMap,
    ICountUp,
    Loading
  },
  props: ["cafeId"],
  data() {
    return {
      info: null,
      menus: [],
      reviews: [],
      isOpen: false,
      isLoading: false,
      fullPage: true,
      today: 0,
      isDesktop: true,
      selectedImage: null,
      delay: 400,
      options: {
        useEasing: true,
        useGrouping: true,
        separator: ",",
        decimal: ".",
        prefix: "",
        suffix: ""
      }
    };
  },
  computed: {
    lastreview() {
      return this.reviews.length - 1
    },
  },
  methods: {
    mostPopular() { 
      let bestMenu = [0, '(없음)']
      this.menus.forEach(function(elem) {
        bestMenu[1] = elem.like_count > bestMenu[0] ? elem.product : bestMenu[1]
        bestMenu[0] = elem.like_count > bestMenu[0] ? elem.like_count : bestMenu[0]
      })
      return bestMenu[1]
    },
    imgPlaceholder(e) {
      e.target.src = require("../assets/noimage.png");
    },
    zoom(url) {
      this.selectedImage = url;
    },
    getData() {
      this.isLoading = true
      const config = {
        headers: { Authorization: "Bearer " + this.$session.get("jwt") }
      };
      axios
        .get(
          `${this.$store.state.constants.SERVER}/cafe/detail/${this.cafeId}`, config
        )
        .then(response => {
          this.isLoading=false
          this.info = response.data.cafeinfo;
          this.reviews = response.data.post;
          this.menus = response.data.menu;

          // 리뷰 작성시간이 12시간 이내이면 '3시간 전' 이런 식으로 나오게 하고, 12시간 이전이면 날짜 시간 다 표시
          let now = Date.now();
          this.reviews.forEach(review => {
            review.updated_at = review.updated_at.slice(0, 20);
            let date = new Date(review.updated_at);
            if (now - Date.parse(date) <= 43200000) {
              review.updated_at = moment(review.updated_at)
                .locale("ko")
                .fromNow();
            } else {
              review.updated_at = moment(review.updated_at)
                .locale("ko")
                .format("llll");
            }
          });

          // 현재 운영중인지 표시
          now = new Date(now);
          this.today = now.getDay();
          let openTime = new Date(
            this.info.time[this.today][0].slice(0, 19)
          );
          let closeTime = new Date(
            this.info.time[this.today][1].slice(0, 19)
          );
          let nowTime = now.getHours() * 100 + now.getMinutes();

          let closeHour = closeTime.getHours();
          // 새벽 1시에 끝나면 25시에 끝난다고 생각
          if (openTime.getDate() !== closeTime.getDate()) {
            closeHour += 24;
          }
          if (
            openTime.getHours() * 100 + openTime.getMinutes() <= nowTime &&
            nowTime < closeHour * 100 + closeTime.getMinutes()
          ) {
            this.isOpen = true;
          } else {
            this.isOpen = false;
          }
          let days = "일월화수목금토";

          for (let i = 0; i < 7; i++) {
            this.info.time[i].push(days[i]);
          }
        })
    },
    pushLikeMenu(menuId,likeornot) {
      if (!this.$store.getters.isLoggedIn) {
        alert("plz login");
        return;
      }
      const config = {
        headers: { Authorization: "Bearer " + this.$session.get("jwt") }
      };
      if (!likeornot) {
        console.log('likey');
        const likeybody = {"menu_id": menuId}
        console.log(likeybody);
        axios
          .post(
            `${this.$store.state.constants.SERVER}/userslikemenu`,likeybody, config)
          .then(()=>{
            this.getData()
            this.$forceUpdate()
          })
          .catch(e=> {
            console.log(e.response);
          })
      } else {
        console.log('dislikey');
        axios
          .delete(
            `${this.$store.state.constants.SERVER}/userslikemenu/${menuId}`, config)
          .then(()=>{
            this.getData()
            this.$forceUpdate()
          })
      }
    }
  },
  beforeMount() {
    if (window.innerWidth < 991) {
      this.isDesktop = false
    }
  },
  mounted() {
    this.getData();
    window.scrollTo(0, 0);
    setTimeout(() => {
      let week = ["0", "1", "2", "3", "4", "5", "6"];
      let dayofweek = week[new Date().getDay()];
      try {
      let todayCal = document.getElementById(dayofweek);
      todayCal.style.backgroundColor = "#BEE3DB";
      } catch (e) {
        console.log(e);
        
      }

    }, 500);
  },
};
</script>

<style>
.imghtml > div > img {
  display: none !important;
}
.shortinfo {
  font-size: calc(0.7rem + 0.7vw)
}
.cafe-page-section-name {
  font-size: calc(1.2rem + 1vw)
}
.review-link {
  max-width: 1440px !important;
}
.review-link:hover {
  box-shadow: 0 0 15px lightgray;
}
#cafe-page-container {
  font-weight: 400
}
  #topbar {
    display: none;
  }
.menurow {
  padding: 0 0.3rem
}
.menurow > div {
  padding: 0;
}
#couponAddBtn {
  position: fixed;
  bottom: 25%;
  right: 19px;
  background: violet;
  opacity: 0.9;
  padding: calc(0.7rem + 1vw) calc(0.7rem + 1vw);
  color: white !important;
  border-radius: 50%;
  z-index: 9999;
  box-shadow: 2px 3px 3px lightgray
}
#reviewWriteBtn {
  position: fixed;
  bottom: 13%;
  right: 19px;
  background: gold;
  opacity: 0.9;
  padding: calc(0.5rem + 1vw) calc(0.7rem + 1vw);
  border-radius: 50%;
  z-index: 9999;
  box-shadow: 2px 3px 3px lightgray
}
#reviewWriteBtn:hover {
  animation: bounce 1s infinite;
}
#imgView {
  position: fixed;
  display: flex;
  z-index: 2000;
  width: 100vw !important;
  height: 100vh !important;
  background: rgba(0, 0, 0, 0.7);
  justify-content: center;
  align-items: center;
  animation: fadein 250ms;
}
#imgView > img {
  width: 60vw;
}
.cafe-preview {
  height: 280px;
  background-size: cover !important;
  cursor: all-scroll;
  transition: 150ms;
  box-shadow: 0 0 3px gray;
}

.cafe-preview:hover {
  z-index: 99;
  transform: scale(1.2);
}

.cafe-name-detail {
  text-align: left;
  border-left: 15px solid #BEE3DB;
  padding-left: 1rem;
}
.bouncer {
  animation: bounce ease infinite 3s;
}
.weekrow {
  max-width: 100vw;
}
.weekday {
  font-weight: 650;
  margin-bottom: 0;
  border-bottom: 1px solid lightgray;
}
.wcol {
  font-size: calc(0.7rem + 0.5vw);
  padding: 0 !important
}
.wcol > p {
  margin-bottom: 0.5rem;
}
@keyframes bounce {
  0%, 50%, 80%, 100% {
    transform: translateY(0);
  }
  40% {
    transform: translateY(-8px);
  }
  60% {
    transform: translateY(-3px);
  }
}
.bg-image {
  filter: blur(8px);
  -webkit-filter: blur(8px);
  height: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
.bg-content {
  background-color: rgb(0, 0, 0);
  background-color: rgba(0, 0, 0, 0.4);
  color: white;
  font-weight: bold;
  border: 3px solid #f1f1f1;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 2;
  width: 80%;
  padding: 20px;
  text-align: center;
}
.menutxt {
  width: 100%;
  margin: 0 !important;
  justify-content: center;
}
.line-clamp {
  display: -webkit-box;
  -webkit-line-clamp: 7;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
  .menutitle { 
    font-size: calc(0.5rem + 0.7vw)
  }
@media only screen and (max-width: 991px) {
  .menutxt > div > p {
    font-size: 2vw;
  }
  #topbar {
    height: 7vh !important;
    display: flex;
  }
}
</style>