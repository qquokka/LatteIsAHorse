<template>
  <div class="container-fluid p-0">
    
    <nav-bar style="position:fixed;left:0;width:100%" blackOrWhite="true" />
    <h2 class="brand" style="margin-top: 8rem;">믿고 한번 가봐...</h2>
    <h1 id="slogan"></h1>

    <video id="background"  width="100%" muted loop>
      <source src="../assets/indexmovie.webm" type="video/webm" >
    </video>
    <search-bar @search-event="searchNow" class="mx-auto"  />

    <hash-tags class="position-relative mb-5 pb-5" />
    <div class="main-raise">
      <h2 class="article-header">당신에게 꼭 맞는 오늘의 카페</h2>
      <recom-list/>
    </div>
    <div class="main-raise">
      <h2 class="article-header">EDITOR's PICK</h2>
      <editor-pick-list/>
    </div>
    <Footer />
  </div>
</template>

<script>
  import axios from 'axios'
  import { mapGetters } from 'vuex' 
  import SearchBar from '@/components/SearchBar.vue'
  import NavBar from '@/components/NavBar.vue'
  import RecomList from '@/components/RecomList.vue'
  import EditorPickList from '@/components/EditorPickList.vue'
  import HashTags from '@/components/HashTags.vue'
  import Footer from '@/views/section/Footer.vue'

  export default {
    name: 'home',
    components: {
			SearchBar,
      RecomList,
      EditorPickList,
      NavBar,
      HashTags,
      Footer
    },
    data() {
      return {
        answers: [],
        username: '',
        i: 0,
        slogan: '이게 다 너 잘먹으라고 하는 소리야.',
        isAuthenticated: this.$store.state.token !== null,
      }
    },
    computed: {
      ...mapGetters([
        'options',
        'user'
      ])
    },
    methods: {
      searchNow(query) {
        axios.get(`${this.$store.state.constants.SERVER}/search/${query}`)
          .then(response => {
            console.log(response)
          })
          .catch(error => {
            console.log(error.response)
          })
      },
      isLogin() {
        this.$session.start()
        if (!this.$session.has('jwt')) {
          this.$router.push('/login')
        } else {
          // 로그인 되어 있다면, vuex token 업데이트
          this.$store.dispatch('login', this.$session.get('jwt'))
        }
      },
      typeWriter() {
        if (this.i < this.slogan.length) {
          document.getElementById("slogan").innerText += this.slogan.charAt(this.i);
          this.i++;
          setTimeout(this.typeWriter, 125);
        }
      },
      playBackground() {
        const vid = document.getElementById("background")
        vid.play()
      }
    },
    mounted() {
      this.typeWriter()
      this.playBackground()
    }
  }
  
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Noto+Sans+KR:900&display=swap');

#slogan {
  font-family: 'Noto Sans KR', sans-serif;
  position: relative;
  z-index: 5;
  color: whitesmoke;
  text-shadow: 2px 2px 1px #3f3f3f;
  user-select: none;
}

.brand {
  font-family: 'Noto Sans KR', sans-serif;
  position: relative;
  z-index: 5;
  color: whitesmoke;
  text-shadow: 2px 2px 1px #3f3f3f;
  user-select: none;
}

.banner-quote {
  padding-top: 50px;
  color: white;
  text-align: left;
}
.main-raise {
  position: relative;
  border: 1px solid white;
  margin: 0 auto 3rem auto;
  background: rgba(255,255,255,0.95);
  border-radius: 15px 15px;
  width: 85%;
  min-width: 450px;
  padding: 2rem;
  box-shadow: 0 19px 38px rgba(0,0,0,0.30), 0 15px 12px rgba(0,0,0,0.22);
}
.list-container {
  background-color: rgba(255, 255, 255, 0.95);
}
.article-header {
  color: #3f3f3f;
  text-align: left;
  border-left: 15px solid pink;
  padding-left: 2rem;
  margin-top: 2rem;
}
#background {
    position: absolute !important;
    top: 0;
    left: 0;
    z-index: 0;
    filter: brightness(80%)
}
</style>