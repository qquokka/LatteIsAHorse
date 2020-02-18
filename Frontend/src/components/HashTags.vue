<template>
  <div class="container-fluid border tag-box mt-5" style="border-radius: 10px">
    <h2
      style="margin: calc(-1.2rem - 0.5vw) auto 0 auto; background-color:#FAF9F9;width:calc(2rem + 12vw);font-size:calc(0.8rem + 1vw)"
    >TODAY #</h2>
    <div class="row m-0 mt-3">
      <router-link to="/hashtag/스터디" class="col-12 col-lg-4 hashimg">
        <p style="z-index:5;position:absolute;font-size:calc(1rem + 1vw);color:white;background:rgba(0,0,0,0.5);padding:0.2rem">#스터디</p>
        <img class="hashpop" :src="require(`../assets/img/study.jpg`)" />
      </router-link>
      <router-link to="/hashtag/브런치" class="col-12 col-lg-4 hashimg">
        <p style="z-index:5;position:absolute;font-size:calc(1rem + 1vw);color:white;background:rgba(0,0,0,0.5);padding:0.2rem">#브런치</p>
        <img class="hashpop" :src="require(`../assets/img/brunch.jpg`)" />
      </router-link>
      <router-link to="/hashtag/로맨틱" class="col-12 col-lg-4 hashimg">
        <p style="z-index:5;position:absolute;font-size:calc(1rem + 1vw);color:white;background:rgba(0,0,0,0.5);padding:0.2rem">#로맨틱</p>
        <img class="hashpop" :src="require(`../assets/img/romantic.jpg`)" />
      </router-link>
    </div>
    <div class="row justify-content-center text-dark my-4">
      <router-link
        class="hash"
        v-for="tag in tags"
        :key="tag.name"
        :to="`hashtag/${tag.name}`"
      >#{{ tag.name }}</router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      tags: []
    };
  },
  methods: {
    bts() {
      axios.get(`${this.$store.state.constants.SERVER}/hashtags`).then(res => {
        this.tags = res.data.hashtags.slice(0, 11);
      });
    }
  },
  mounted() {
    this.bts();
  }
};
</script>

<style>
.hashimg {
  overflow: hidden;
  height: 24vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
.hashpop {
  filter: brightness(80%);
  transition: 350ms;
}
.hashimg:hover .hashpop {
  filter: brightness(40%);
}
.tag-box {
  position: relative;
  max-width: 1440px;
}
.hash {
  cursor: pointer;
  font-weight: 400;
  border: 1px solid lightgray;
  font-size: calc(12px + 0.5vw);
  padding: 0.4rem;
  margin-right: 0.7rem;
  border-radius: 15px;
}
.hash:hover {
  color: royalblue !important;
}
</style>