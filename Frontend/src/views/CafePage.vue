<template>
  <div class="container-fluid p-0">
    <div v-if="selectedImage" id="imgView">
      <img :src="selectedImage" @click.stop="selectedImage = null" />
    </div>
    <nav-bar />
    <div class="row pt-5 px-lg-3 border-0">
      <single-cafe-map :cafe="cafe" :isOpen="isOpen" class="col-12 col-lg-5 shadow" width="100%" />

      <div class="col">
        <div class="d-flex align-items-center">
          <h1 class="cafe-name-detail">{{ cafe.cafe_name }}</h1>
          <p v-if="isOpen" class="openbdg ml-2" style="font-size: 1rem;">영업중</p>
          <p v-else style="font-size: 1rem;" class="closebdg ml-2">준비중</p>
        </div>
        <div class="row my-1 justify-content-sm-around">
          <h6 class="text-left">
            <fa style="color:gold;margin-right:0.5rem" icon="crown" />대표자명:
            <span class="text-muted">미등록</span>
          </h6>

          <h6 class="text-left">
            <fa style="color:turquoise;margin-right:0.5rem" icon="phone-square" />전화번호:
            <span class="text-muted">{{ cafe.cafe_phone }}</span>
          </h6>

          <h6 class="text-left">
            <fa style="color:crimson;margin-right:0.5rem" icon="shopping-basket" />인기메뉴:
            <span class="text-muted">구현 예정</span>
          </h6>
        </div>

        <div class="row justify-content-center">
          <h3 class="mr-5">
            <fa class="mr-2 bouncer" style="color: royalblue" icon="heartbeat" />
            <ICountUp
              style="font-family:monospaced"
              :delay="delay"
              :endVal="2958293"
              :options="options"
            />
            <span style="font-size:1rem;margin-left:0.5rem">LIKED</span>
          </h3>

          <h3 class="ml-5">
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
            class="col-3 cafe-preview"
            @click="zoom(cafe.thumbnail)"
            :style="`background:url('${cafe.thumbnail}')`"
          />
          <div
            v-if="reviews[0]"
            class="col-3 cafe-preview"
            @click="zoom(reviews[0].thumbnail)"
            :style="`background:url('${reviews[0].thumbnail}')`"
          />
          <div
            v-if="reviews[1]"
            class="col-3 cafe-preview"
            @click="zoom(reviews[1].thumbnail)"
            :style="`background:url('${reviews[1].thumbnail}')`"
          />
          <div
            v-if="reviews[2]"
            class="col-3 cafe-preview"
            @click="zoom(reviews[2].thumbnail)"
            :style="`background:url('${reviews[2].thumbnail}')`"
          />
        </div>

        <div class="row px-3">
          <div class="col border-right">
            <p class="border-bottom weekday">
              <fa icon="calendar" />
            </p>
            <p>open</p>
            <p>close</p>
          </div>
          <div v-for="i in time.length - 1" :key="i" class="col" :id="i">
            <p class="weekday">{{ time[i][2] }}</p>
            <p>{{ time[i][0] }}</p>
            <p>{{ time[i][1] }}</p>
          </div>
          <div class="col" :id="0">
            <p
              style="color:crimson;font-size:1.2rem;font-weight:800;border-bottom: 1px solid crimson;"
            >{{ time[0][2] }}</p>
            <p>{{ time[0][0] }}</p>
            <p>{{ time[0][1] }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="container mt-5 px-5">
      <h4>
        <fa icon="mug-hot" style="color:violet" />MENU
      </h4>
      <hr />
      <div v-for="menu in menus" :key="menu.id" class="row">
        <div class="col-3 row justify-content-between">
          <p>{{ menu.product }}</p>
        </div>
        <div class="col-1">
          <p>
            <fa icon="thumbs-up" style="color: skyblue" />
            {{ menu.like_count }}
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
    <hr>
    <div class="dropdown">
      <button class="btn btn-block dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        리뷰 쓰기
      </button>
      <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <create-post class="dropdown-item" />
      </div>
    </div>
      <router-link :to="`/cafe/${cafeId}/posts/create`" v-if="isLogined">리뷰 쓰기</router-link>
    <hr />
    <div v-for="review in reviews" :key="review.id">
      <router-link :to="`/cafe/${cafeId}/review/${review.id}/`">
        <div class="card my-3">
          <img
            :src="review.thumbnail"
            class="card-img-top"
            :alt="review.title"
            style="width:300px;"
          />
          <div class="card-body">
            <h5 class="card-title">{{ review.title }}</h5>
            <p>작성자: {{ review.writer_name }} | {{ review.updated_at }} 작성</p>
            <button
              v-if="review.writer_name === $session.get('username')"
              click="deleteReview(review.id)"
            >삭제</button>
          </div>
          <div class="card-body">
            <p v-html="review.content" />
          </div>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ICountUp from "vue-countup-v2";
import moment from "moment";
import NavBar from "@/components/NavBar.vue";
import SingleCafeMap from "@/components/SingleCafeMap.vue";
import CreatePost from "@/views/CreatePost.vue"
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
  faThumbsUp
} from "@fortawesome/free-solid-svg-icons";
library.add(
  faCrown,
  faPhoneSquare,
  faShoppingBasket,
  faHeartbeat,
  faEnvelopeOpenText,
  faCalendar,
  faMugHot,
  faMoneyBill,
  faThumbsUp
);

export default {
  name: "CafePage",
  components: {
    NavBar,
    SingleCafeMap,
    ICountUp,
    CreatePost
  },
  props: ["cafeId"],
  data() {
    return {
      cafe: {},
      menus: [],
      reviews: [],
      time: [[]],
      likeMenu: [],
      isOpen: false,
      isLogined: false,
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
      const config = {
        headers: { 'Authorization': "Bearer " + this.$session.get('jwt') }
      }
      axios
<<<<<<< HEAD
        .get(`${this.$store.state.constants.SERVER}/cafe/detail/${this.cafeId}`)
=======
        .get(`${this.$store.state.constants.SERVER}/cafe/detail/${this.cafeId}`, config)
>>>>>>> 0236d6ae7db54212d11b35cfc60f54d573e6e252
        .then(response => {
          console.log("카페 데이터 ");
          console.log(response.data);
          this.cafe = response.data.cafeinfo;
          this.reviews = response.data.post;
          this.menus = response.data.menu;
          this.time = response.data.time;
          this.likeMenu = response.data.like;

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
            response.data.time[this.today][0].slice(0, 19)
          );
          let closeTime = new Date(
            response.data.time[this.today][1].slice(0, 19)
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
            for (let j = 0; j < 2; j++) {
              this.time[i][j] = this.time[i][j].slice(11, 16);
            }
            this.time[i].push(days[i]);
          }

          // 메뉴별로 좋아요 표시되어 있으면 menus 배열에 userLiked(Boolean)랑 likeCount(Num) 넣어주기
          response.data.like.forEach(elem => {
            for (let i = 0; i < this.menus.length; i++) {
              if (this.menus[i].mid === elem.menu_id) {
                this.menus[i].userLiked = true
                this.menus[i].likeCount = elem.like_count
                break
                }
            }
          })
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
<<<<<<< HEAD
    pushLike() {}
=======
    pushLikeMenu(menuId, ifUserLikesMenu) {  // 좋아요 버튼을 누르는 경우엔 true, 취소할 경우엔 false
      const config = {
        headers: { 'Authorization': "Bearer " + this.$session.get('jwt') }
      }
      console.log(config, menuId)
      if (ifUserLikesMenu) {
        // 좋아요 누를 때
        console.log('좋아요')

        // axios.get(`${this.$store.state.constants.SERVER}/userslikemenu/${menuId}`, config)
        //   .then(response => {
        //     console.log(response.data)
        //   })
      } else {
        // 좋아요 취소할 때
        console.log('싫어요');
        
        // axios.get()
      }
      
    }
>>>>>>> 0236d6ae7db54212d11b35cfc60f54d573e6e252
  },
  beforeMount() {
    this.getData();
  },
  mounted() {
    this.isLogined = this.$session.has("jwt");
    let week = ['0','1','2','3','4','5','6'];
    let dayofweek = week[new Date().getDay()]
    let todayCal = document.getElementById(dayofweek);
    console.log(todayCal);
    todayCal.style.background = "gold !important";
  }
};
</script>

<style>
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
  0%,
  20%,
  50%,
  80%,
  100% {
    transform: translateY(0);
  }
  40% {
    transform: translateY(-8px);
  }
  60% {
    transform: translateY(-3px);
  }
}
</style>