<template>
  <div id="footer" class="container-fluid">
    <a href="https://github.com/HyejinYang/LatteIsAHorse/" target="_blank">
      <fa
        @mouseover="githover(1)"
        @mouseleave="githover(0)"
        class="m-4 gitlink"
        :icon="github"
        size="3x"
        style="color: lavender"
      />
    </a>
    <div class="row m-0 mx-auto text-white-50 justify-content-around align-items-center" style="max-width: 750px;">
			<p style="color:violet;">|</p>
      <p>류시호</p>
      <p style="color:violet;">|</p>
      <p>이경호</p>
      <p style="color:violet;">|</p>
      <p>조현호</p>
      <p style="color:violet;">|</p>
      <p>양혜진</p>
      <p style="color:violet;">|</p>
      <p>현진원</p>
      <p style="color:violet;">|</p>
    </div>
    <div class="row m-0 text-white-50 justify-content-center align-items-center">
      <p>
        <img src="../../assets/ssafy_logo.png" height="50px" />
      </p>
      <p>
        SSAFY 2기
        <span style="color:violet;">|</span> 대전 캠퍼스 1반
        <span style="color:violet;">|</span> 웹 / 모바일 프로젝트 팀 HBH
      </p>
    </div>
    <div class="row m-0 text-white-50 justify-content-center align-items-center">
      <p>
        Copyright HBH
        <fa icon="copyright" />All rights reserved.
      </p>
    </div>

    <div class="row align-items-center m-0">
      <div class="d-none d-md-flex col-md-5">
        <p class="footer-brand">
          LATTE
          <span style="color:violet;">=</span>HORSE
        </p>
        <small class="text-muted my-auto ml-2">
          <span @click="$router.push('/hangman/is/the/perfect/game/of/the/whole/human/history')">라떼는</span> 말이야
        </small>
      </div>
      <div class="col offset-md-3">
        <div class="row justify-content-around">
          <router-link to="#" class="footer-right">이용 약관</router-link>
          <router-link to="#" class="footer-right">개인 정보 취급 방침</router-link>
          <router-link to="about" class="p-3 text-muted">© 2020 Team HBH</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { library } from "@fortawesome/fontawesome-svg-core";
import brands from "@fortawesome/fontawesome-free-brands";
import { faAddressBook, faCopyright } from "@fortawesome/free-solid-svg-icons";

library.add(faAddressBook, faCopyright, brands);
export default {
  data() {
    return {
      github: ["fab", "github"]
    };
  },
  methods: {
    githover(dir) {
      if (dir) {
        this.github = ["fab", "github-alt"];
      } else {
        this.github = ["fab", "github"];
      }
    }
  }
};
</script>

<style>
#footer {
  background: #4f4f4f;
  align-items: center;
  position: relative;
  margin-top: auto !important;
}
.footer-brand {
  font-size: 2rem;
  font-weight: 900;
  color: lavender;
  font-family: sans-serif;
  user-select: none;
  margin: 0;
}
.footer-right {
  font-size: 1rem;
  color: lavender !important;
  text-align: left;
  padding: 1rem;
  text-decoration: none !important;
}
.footer-right:hover {
  color: violet !important;
}
.gitlink:hover {
  transition: cubic-bezier(0.39, 0.575, 0.565, 1);
  animation: bounce 1s infinite;
  color: violet !important;
}
</style>