<template>
  <div id="post-list-main" class="container-fluid">
    <div class="row px-2">
      <div
        v-for="review in reviewData.slice(0, limits)"
        :key="review.id"
        class="post-list col-12 col-sm-6 col-lg-4"
      >
        <router-link :to="`/cafe/${review.cafe_id}/review/${review.id}/`">
          <div class="postbody overflow-hidden border">
            <img :src="review.thumbnail" width="100%" height="300px" @error="imgPlaceholder" />

            <h3 class="p-2 posttitle text-truncate">{{ review.title }}</h3>
            <p style="font-size:0.8rem;">
              <fa icon="thumbs-up" style="color: skyblue" />
              {{ review.like_count }}
            </p>
            <p class="mb-0">
              <fa icon="user-circle" />
              {{ review.writer_name }}
            </p>
            <p
              class="postcreate"
              style="top:2rem;left:3rem;border-radius:15px"
            >{{ review.created_at.slice(0,10) }}</p>
          </div>
        </router-link>
      </div>
      <button
        v-if="limits > 7"
        class="btn btn-light btn-block position-relative"
        @click="loadmore"
      >더보기</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ReviewList",
  data() {
    return {
      reviewId: null
    };
  },
  props: {
    limits: {
      limits: Number,
      default: 12
    },
    reviewData: {
      default: [{ id: "null", title: "NO Review" }]
    }
  },

  methods: {
    loadmore() {
      this.limits += 6;
    },
    imgPlaceholder(e) {
      e.target.src = require("../../assets/noimage.png");
    }
  }
};
</script>


<style>
.post-preview {
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 5;
  -webkit-box-orient: vertical;
}

.postbody {
  color: #2f2f2f;
  border-radius: 50px;
  transition: 250ms ease-in-out;
}
.postbody:hover .posttitle {
  color: white;
  text-shadow: 1px 1px 1px #2f2f2f;
  background: #89b0ae;
}

.posttitle {
  background: #f2f2f2;
  transition: ease-in-out;
}

.postcreate {
  position: absolute;
  top: 2rem;
  left: 2rem;
  padding: 0.3rem;
  opacity: 0.7;
}
.post-list {
  padding: 0 !important;
}
</style>