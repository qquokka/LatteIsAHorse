<template>
  <div class="container-fluid p-0">
    <!-- global component -->
    <loading :active.sync="isLoading" :can-cancel="false" :is-full-page="fullPage" loader="bars" color="violet"></loading>
    <div v-if="selectedImage" id="imgView">
      <img :src="selectedImage" @click.stop="selectedImage = null" />
    </div>
    <router-link id="reviewWriteBtn" :to="`/cafe/${cafeId}/posts/create`" >리뷰 쓰러가기</router-link>
    <!-- global end-->
    <nav-bar />
    <div class="row m-0 mt-0 mt-lg-5 mx-lg-3 border-0" v-if="info">
      <single-cafe-map :cafe="info" :isOpen="isOpen" class="col-12 col-lg-5 shadow p-0" />
      <div class="col px-2">
        <div class="align-items-center d-none d-lg-flex">
          <h1 class="cafe-name-detail">{{ info.cafe_name }}</h1>
          <p v-if="isOpen" class="openbdg ml-2" style="font-size: 1rem;">영업중</p>
          <p v-else style="font-size: 1rem;" class="closebdg ml-2">준비중</p>
        </div>
        <div class="row mt-3 mt-lg-1 justify-content-between px-4">
          <h6 class="text-left">
            <fa style="color:gold;margin-right:0.4rem" icon="crown" />대표자명:
            <span class="text-muted">미등록</span>
          </h6>

          <h6 class="text-left">
            <fa style="color:turquoise;margin-right:0.5rem;margin-left:0.2rem" icon="phone-square" />전화번호:
            <span class="text-muted">{{ info.cafe_phone }}</span>
          </h6>

          <h6 class="text-left">
            <fa style="color:crimson;margin-right:0.5rem" icon="shopping-basket" />인기메뉴:
            <span class="text-muted">구현 예정</span>
          </h6>
        </div>

        <div class="row justify-content-center my-4 justify-content-lg-center pl-lg-5">
          <h3 class="px-2 mr-lg-5">
            <fa class="mr-2 bouncer" style="color: royalblue" icon="heartbeat" />
            <ICountUp
              style="font-family:monospaced"
              :delay="delay"
              :endVal="29593"
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
            v-if="reviews[0]"
            class="col-12 col-md-4 col-lg-3 cafe-preview"
            @click="zoom(reviews[0].thumbnail)"
            :style="`background:url('${reviews[0].thumbnail}')`"
          />
          <div
            v-if="reviews[1]"
            class="col-12 col-md-4 col-lg-3 cafe-preview"
            @click="zoom(reviews[1].thumbnail)"
            :style="`background:url('${reviews[1].thumbnail}')`"
          />
          <div
            v-if="reviews[2]"
            class="col-12 col-md-4 col-lg-3 cafe-preview"
            @click="zoom(reviews[2].thumbnail)"
            :style="`background:url('${reviews[2].thumbnail}')`"
          />
        </div>

        <div class="row">
          <div class="col border-right">
            <p class="border-bottom weekday">
              <fa icon="calendar" />
            </p>
            <p>open</p>
            <p>close</p>
          </div>
          <div v-for="i in info.time.length - 1" :key="i" class="col" :id="i">
            <p class="weekday">{{ info.time[i][2] }}</p>
            <p>{{ info.time[i][0].slice(11, 16) }}</p>
            <p>{{ info.time[i][1].slice(11, 16) }}</p>
          </div>
          <div class="col" id="0">
            <p
            class="weekday"
              style="color:crimson;font-size:1.2rem;font-weight:800;border-bottom: 1px solid crimson;"
            >{{ info.time[0][2] }}</p>
            <p>{{ info.time[0][0].slice(11, 16) }}</p>
            <p>{{ info.time[0][1].slice(11, 16) }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="container mt-5 px-0 px-lg-5" v-if="menus">
      <h4 class="my-5">
        <fa icon="mug-hot" style="color:violet" />MENU
      </h4>
      <hr />
      <div  v-for="menu in menus" :key="menu.mid" class="row">
        <div class="col-3 row justify-content-between">
          <p>{{ menu.product }}</p>
        </div>
        <div class="col-1">
          <p
            style="cursor:pointer"
            @click="pushLikeMenu(menu.mid, menu.user_like)">
            <fa
              :icon="menu.user_like?['fas', 'heart']:['far', 'heart']"
              style="cursor: pointer; color: red;"
              :style="menu.user_like?{animation: 'bounce 1s infinite'}:{}"/>
            {{ menu.like_count? menu.like_count : 0 }}
          </p>
        </div>
        <div class="col-6">
          <p class="text-muted">메뉴에 관한 설명이 들어갈 자리</p>
        </div>

        <div class="col-2">
          <p>
            {{ menu.price }}
            <fa icon="money-bill" style="color:green" />
          </p>
        </div>
      </div>
    </div>

    <hr />
    <h4 class="my-5">
      <fa icon="envelope-open-text" style="color:orange" />REVIEW
    </h4>
    <div class="row px-0 px-lg-4">
      <div
        class="container my-3 overflow-hidden col-12 col-lg-4"
        v-for="review in reviews.slice().reverse()"
        :key="review.id"
      >
        <router-link :to="`/cafe/${cafeId}/review/${review.id}/`">
          <div
            class="justify-content-center px-2"
            style="background: lavender;;border:1px solid lightgray"
          >
            <h1 class="py-2 font-weight-bold text-left text-truncate" style="font-size: 3rem">
              {{ review.title }}
            </h1>
            <h5 class="text-right">
              <fa class="text-muted" icon="user-circle" />
              {{ review.writer_name }}
            </h5>
            <p style="font-size:0.8rem;text-align:right">작성일시: {{ review.updated_at.slice(0,11) }}</p>
          </div>
          <div class="border">
            <div class="row mt-2 px-1 justify-content-center">
              <img
                :src="review.thumbnail"
                class="col-12"
                style="height:300px"
                @error="imgPlaceholder"
              />
            </div>
            <div class="row p-1 justify-content-center line-clamp" style="height: 225px;">
              <span class="col-12 my-5 text-left" v-html="review.content"></span>
            </div>
            <div style="background: lavender !important;">- {{ review.id }} -</div>
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
  faHeart as fasHeart,
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
  fasHeart, farHeart
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
  methods: {
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
          `${this.$store.state.constants.SERVER}/cafe/detail/${this.cafeId}`,{}, config
        )
        .then(response => {
          this.isLoading=false
          console.log("카페 데이터 ");
          console.log(response.data);
          this.info = response.data.cafeinfo;
          this.reviews = response.data.post;
          this.menus = response.data.menu;

          // 리뷰 작성시간이 12시간 이내이면 '3시간 전' 이런 식으로 나오게 하고, 12시간 이전이면 날짜 시간 다 표시
          let now = Date.now();
          this.reviews.forEach(review => {
            review.updated_at = review.updated_at.slice(0, 19);
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
        .catch(error => {
          console.log(error.data);
        });
      // reponse.data.like.forEach(elem => {
      //   menuselem.menuId
      // })
    },
    // deleteReview(reviewId) {
    // 	axios.delete(`${this.$store.state.constants.SERVER}/post/${reviewId}`, {headers: {'Authorization': "Bearer " + this.$session.get('jwt')}})
    // 		.then(response => {
    // 			console.log(response)
    // 		})
    // },
    pushLikeMenu(menuId,likeornot) {
      if (!this.$store.getters.isLoggedIn) {
        alert("plz login");
        return;
      }
      if (likeornot) {
        axios
          .post(
            `${this.$store.state.constants.SERVER}/userslikemenu/${menuId}`,{headers: { Authorization: "Bearer " + this.$session.get("jwt") }}
          )
          .then(()=>{
            this.getData()
          })
          .catch(e=> {
            console.log(e.response);
          })
      } else {
        axios
          .delete(
            `${this.$store.state.constants.SERVER}/userslikemenu/${menuId}`,{headers: { Authorization: "Bearer " + this.$session.get("jwt") }}
          )
          .then(()=>{
            this.getData()
          })
      }
    }
  },
  mounted() {
    this.getData();
    window.scrollTo(0, 0);
    setTimeout(() => {
      let week = ["0", "1", "2", "3", "4", "5", "6"];
      let dayofweek = week[new Date().getDay()];
      let todayCal = document.getElementById(dayofweek);
      todayCal.style.backgroundColor = "lavender";
    }, 250);
  },
};
</script>

<style>
#reviewWriteBtn {
  position: fixed;
  bottom: 15px;
  right: 15px;
  color: black;
  background: gold;
  padding: 0.7rem 0.4rem;
  border-radius: 15px;
  z-index: 9999;
  box-shadow: 0 0 10px lightgray
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
  border-left: 15px solid lavender;
  padding-left: 1rem;
}
.bouncer {
  animation: bounce ease infinite 3s;
}
.weekday {
  font-weight: 650;
  font-size: 1.2rem;
  border-bottom: 1px solid lightgray;
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
@media only screen and (max-width: 991px) {
  .menutxt > div > p {
    font-size: 2vw;
  }
}
</style>