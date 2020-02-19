<template>
  <div id="postDetail">
    <NavBar />
    <div class="container">
      <div class="row justify-content-center py-2">LOGO</div>
      <div class="container border">
        <div class="row align-content-center text-left border-bottom py-2" style="line-height:100%">
            <fa icon="user-circle" class="ml-1" style="color:#89B0AE" size="2x" />
            <p class="ml-1 my-auto text-info">{{ review.writer_name }}<br ><small class="text-muted">{{ displayTime(review.time) }}</small></p>
        </div>
        <div class="row">
          <h5 class="text-muted px-1">#{{ review.id }}</h5>
        </div>
        <div class="review-detail-header" :style="`background: url('${review.thumbnail}')`">
          <h1 class="review-detail-title">{{ review.title }}</h1>
        </div>
        <div class="container mt-3 text-left">
          <p style="font-weight:400" v-html="review.content"></p>
        </div>
        <div class="row justify-content-end" v-if="isWriter">
          <button class="detailbtn mr-1" @click="updateReview">수정</button>
          <button class="detailbtn" @click="deleteReview">삭제</button>
        </div>
      </div>
    </div>
    <div class="container mt-3">
      <div class="container border">

        <form class="row my-2" v-if="isLogined" @submit.prevent="addComment">
          <div style="width: 10%" class="small text-center my-auto">
            @{{ $session.get('username') }}
          </div>
          <input class="inputcomm" type="text" v-model="addCommentContent" />
          <button role="submit" style="width: 8%;background-color:#89B0AE; color:white;border:0">댓글쓰기</button>
      </form>
        <div class="row py-2" style="background-color: #f2f2f2">
          <div style="font-weight: 400;margin-left:0.3rem">댓글 : {{ comments.length }}</div>
        </div>
        
        <div v-for="comment in comments" :key="comment.id" class="row align-items-center">
          <div class="col-1 border text-left">
            <small> <fa icon="user-circle" /> {{ comment.writer_name }}</small>
          </div>
          <div class="col text-left border" style="font-weight:400">{{ comment.content }} <span class="float-right small" style="">{{ displayTime(comment.created_at.slice(0,20)) }}</span> </div>
          <a 
            class="col-1 bg-danger text-white"
            v-if="comment.writer_name === $session.get('username')"
            @click.prevent="deleteComment(comment.id)"
          >
            <fa icon="trash-alt" />
          </a>
        </div>
      </div>

    </div>
    </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import axios from "axios";
import moment from "moment";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faTrashAlt } from "@fortawesome/free-solid-svg-icons";
library.add(faTrashAlt);

export default {
  name: "ReviewDetail",
  components: {
    NavBar
  },
  props: ["reviewId", "cafeId"],
  data() {
    return {
      loading: false,
      review: {},
      time: "",
      comments: [],
      isWriter: false,
      addCommentContent: null,
      isLogined: false
    };
  },
  methods: {
    updateReview() {
      console.log("수정 시작");
      this.$router.push(`/post/${this.reviewId}`);
    },
    deleteReview() {
      axios
        .delete(`${this.$store.state.constants.SERVER}/post/${this.reviewId}`, {
          headers: { Authorization: "Bearer " + this.$session.get("jwt") }
        })
        .then(response => {
          console.log(response);
          this.$router.back();
        });
    },
    getComment() {
      axios
        .get(`${this.$store.state.constants.SERVER}/comments/${this.reviewId}`)
        .then(response => {
          this.comments = response.data;
        });
    },
    addComment() {
      if (this.addCommentContent === null || this.addCommentContent === "") {
        alert("댓글 내용을 작성해주세요!");
        return;
      }
      let comment = {
        content: this.addCommentContent,
        post_id: this.reviewId
      };
      axios
        .post(`${this.$store.state.constants.SERVER}/comments`, comment, {
          headers: { Authorization: "Bearer " + this.$session.get("jwt") }
        })
        .then(response => {
          console.log(response);
          this.getComment();
          this.addCommentContent = "";
        })
        .catch(error => {
          console.log(error);
        });
    },
    deleteComment(commentId) {
      axios
        .delete(`${this.$store.state.constants.SERVER}/comments/${commentId}`, {
          headers: { Authorization: "Bearer " + this.$session.get("jwt") }
        })
        .then(response => {
          console.log(response);
          this.getComment();
        })
        .catch(error => {
          console.log(error);
        });
    },
    displayTime(time) {
      let now = Date.now();
      if (now - Date.parse(time) <= 43200000) {
        return moment(time)
          .locale("ko")
          .fromNow();
      } else {
        return moment(time)
          .locale("ko")
          .format("llll");
      }
    }
  },
  mounted() {
    axios
      .get(`${this.$store.state.constants.SERVER}/post/${this.reviewId}`)
      .then(response => {
        this.review = response.data;
        if (this.review.writer_name === this.$session.get("username")) {
          this.isWriter = true;
        }
        console.log(this.review.content);
      });
    axios
      .get(`${this.$store.state.constants.SERVER}/comments/${this.reviewId}`)
      .then(response => {
        console.log(response.data);
        this.comments = response.data;
      })
      .catch(error => {
        console.log(error);
      });
    this.isLogined = this.$session.has("jwt");
  }
};
</script>
<style scoped>
div >>> img {
  max-width: 100% !important;
}
</style>
<style>
.detailbtn {
  background: #BEE3DB;
  color: #555B6E;
  width: 20%;
  border: 0;
  border-radius: 6px;
}
.detailbtn:hover {
  background: #FFD6BA;
}
.review-detail-header {
  height: 50vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-size: cover !important;
}
.review-detail-title {
  font-size: calc(2rem + 1vw);
  color: white;
  text-shadow: 0 0 9px black;

}
.inputcomm {
  width: 50% ;
  transition: 150ms;
  text-align: right;
}
.inputcomm:focus {
  background: #BEE3DB
}

</style>