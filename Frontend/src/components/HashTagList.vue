<template>
  <div id="tag-result">
    <nav-bar />
    <div class="container border" style="background: #BEE3DB">
      <h1 style="color: #855B6E;">
        #{{ tagname }}
        <span class="small">태그로</span>
      </h1>
      <h4>{{ cafeData.length }}개의 카페를 찾았어요.</h4>
      <div class="btn-group btn-group-toggle" data-toggle="buttons">
        <label class="btn btn-outline disabled">
          <input type="radio" name="options" /> 정렬방식:
        </label>
        <label class="btn btn-info active" style="border-radius: 5px; cursor: pointer">
          <input type="radio" name="options" @click="order=false" checked /> 거리순
        </label>
        <label class="btn btn-info" style="border-radius: 5px; cursor: pointer">
          <input type="radio" name="options" @click="order=true" /> 인기순
        </label>
      </div>
    </div>
    <div class="container border shadow">
      <cafe-list :cafeData="order?cafeData:likeData" />
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import axios from "axios";
import CafeList from "@/components/CafeList.vue";

export default {
  name: "TagResult",
  components: {
    NavBar,
    CafeList
  },
  data() {
    return {
      cafeData: [],
      likeData: [],
      order: false,
    };
	},
	props:["tagname"],
  methods: {
    getCafeByHashtag() {
      axios
        .get(`${this.$store.state.constants.SERVER}/search/hashtag/${this.tagname}`)
        .then(response => {
          this.cafeData = response.data.hashtags
          this.likeData = response.data.hashtags.sort(this.compare)
          
        });
    },
    compare(a, b) {
        if (a.like_count < b.like_count) {
          return -1;
        }
        if (a.like_count > b.like_count) {
          return 1;
        }
        return 0;
      }
    },
  mounted() {
    this.getCafeByHashtag();
  }
};
</script>

<style>
#tag-result {
  min-height: 100vh;
}
</style>
