<template>
  <div id="postDetail">
    <NavBar />
    <div class="container">
      <h1 class="display-3 mx-5" style="padding-top:6rem;font-weight:700">{{ review.title }}</h1>
      <h5>{{ review.created_at }}</h5>
      <h1 class="border-bottom pb-4">
        <span class="text-muted small">written by</span>
        {{ review.writer_name }}
      </h1>
      <p v-html="review.content"></p>
      <button v-if="isWriter" @click="updateReview">수정</button>
      <button v-if="isWriter" @click="deleteReview">삭제</button>

      <h4 class="mt-5 pt-5">Comments</h4>
      <div class="container comments">
        댓글 갯수: {{ comments.length }}
        <li v-for="comment in comments" :key="comment.id">
          {{ comment.writer_name }} : {{ comment.content }} at {{ comment.created_at }}
          <a
            v-if="comment.writer_name === $session.get('username')"
            @click.prevent="deleteComment(comment.id)"
          >[X]</a>
        </li>
      </div>
      <form v-if="isLogined" @submit.prevent="addComment">
        <input type="text" v-model="addCommentContent" />
        <button>등록</button>
      </form>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import axios from "axios";
import moment from "moment";

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
      comments: [],
      isWriter: false,
      addCommentContent: null,
      isLogined: false
    };
  },
  methods: {
    getReview() {
      axios
        .get(`${this.$store.state.constants.SERVER}/post/${this.reviewId}`)
        .then(response => {
          this.review = response.data;
          let now = Date.now();

          let dateCreatedAt = new Date(this.review.created_at.slice(0, 19));
          if (now - Date.parse(dateCreatedAt) <= 43200000) {
            this.review.created_at = moment(this.review.created_at)
              .locale("ko")
              .fromNow();
          } else {
            this.review.created_at = moment(this.review.created_at)
              .locale("ko")
              .format("llll");
          }

          let dateUpdatedAt = new Date(this.review.updated_at.slice(0, 19));
          if (now - Date.parse(dateUpdatedAt) <= 43200000) {
            this.review.updated_at = moment(this.review.updated_at)
              .locale("ko")
              .fromNow();
          } else {
            this.review.updated_at = moment(this.review.updated_at)
              .locale("ko")
              .format("llll");
          }
        });
    },
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
    }
    // displayTime(time) {
    // 	let now = Date.now()
    // 	let dateCreatedAt = new Date(time.slice(0, 19))
    // 	if (now - Date.parse(dateCreatedAt) <= 43200000) {
    // 		this.review.created_at = moment(this.review.created_at).locale("ko").fromNow()
    // 	} else {
    // 		this.review.created_at = moment(this.review.created_at).locale("ko").format("llll")
    // 	}
    // }
  },
  mounted() {
    axios
      .get(`${this.$store.state.constants.SERVER}/post/${this.reviewId}`)
      .then(response => {
        this.review = response.data;
        if (this.review.writer_name === this.$session.get("username")) {
          this.isWriter = true;
        }
      });
    axios
      .get(`${this.$store.state.constants.SERVER}/comments/${this.reviewId}`)
      .then(response => {
        this.comments = response.data;
      })
      .catch(error => {
        console.log(error);
      });
    this.isLogined = this.$session.has("jwt");
  }
};
</script>

<style>
.comments {
  min-height: 10rem;
  width: 80%;
  margin-bottom: 5rem;
  box-shadow: 3px 3px 10px gray;
}
</style>