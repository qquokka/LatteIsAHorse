<template>
<div id="post-list-main" class="container-fluid">
  <div class="row px-2">
    <div v-for="review in reviewData.slice(0, limits)" :key="review.id" class="post-list col-12 col-sm-6 col-lg-4 p-3">
      <router-link :to="`/cafe/${review.cafe_id}/review/${review.id}/`" class="text-decoration-none">
        <div class="postbody overflow-hidden">
          <img :src="review.thumbnail" width="100%" height="300px" @error="imgPlaceholder">
          <h3 class="p-2 posttitle">{{ review.title }}</h3>
          <p class="" style="font-size:0.8rem"><i class="fas fa-thumbs-up"> {{ review.like_count }}</i></p>
          <p class="mb-0"> {{ review.writer_name }}</p>
          <p class="postcreate" style="top:1rem;left:3rem"> {{ review.created_at.slice(0,10) }} </p>
        </div>
      </router-link>
    </div>
    <button class="btn btn-light btn-block position-relative" @click="loadmore">더보기</button>
  </div>
</div>
</template>

<script>
export default {
  name: 'ReviewList',
  data() {
    return {
    }  
  },
  props: {
      limits: {
          limits: Number,
          default: 12,
      },
      reviewData: {
        default: [{id:"null",title:"NO Review"}]
      }
  },

  methods: {
    loadmore() {
      this.limits += 6  
    },
    imgPlaceholder(e) {
      e.target.src = require("../../assets/noimage.png")
    }
  }
}
</script>


<style>

.post-preview {
    overflow: hidden;  
    display: -webkit-box;  
    -webkit-line-clamp: 5;  
    -webkit-box-orient: vertical    
}

.postbody {
  color: #2f2f2f;
  border-radius: 50px;
  background: linear-gradient(145deg, #dadada, #ffffff);
  transition: 250ms ease-in-out;
}
.postbody:hover {
  box-shadow: 3px 3px 1px lavender;
}
.postbody:hover .posttitle {
  color: white;
  text-shadow: 1px 1px 1px #2f2f2f;
  background: lavender;
}

.posttitle {
  background: #f2f2f2;
  border: 0px lavender;
  transition:ease-in-out;
}

.postcreate {
  position: absolute;
  top: 2rem;
  left: 2rem;
  padding: 0.3rem;
  background: linear-gradient(145deg, #dadada, #ffffff);
  opacity: 0.7;
}
</style>