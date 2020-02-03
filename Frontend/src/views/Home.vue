<template>
  <div class="container-fluid p-0">
    <div id="background" >

    </div>
    <nav-bar style="position:fixed;left:0;width:100%" blackOrWhite="true" />
    <h2 style="margin-top: 8rem;margin-bottom:2rem;position:relative">Find your ideal cafe</h2>
    <search-bar class="mx-auto"  />
    <hash-tags class="position-relative mb-5 pb-5" />
    <div class="main-section" style="margin-top:15rem;">
      <h2 class="article-header">가까운 카페</h2>
      <recom-list/>
    </div>
    <div class="main-section" >
      <h2 class="article-header">EDITOR's PICK</h2>
      <post-list limits="3" />
    </div>
    <Footer />
  </div>
</template>

<script>
  import { mapGetters } from 'vuex' 
  import SearchBar from '@/components/SearchBar.vue'
  import NavBar from '@/components/NavBar.vue'
  import RecomList from '@/components/RecomList.vue'
  import PostList from '@/views/section/PostList.vue'
  import HashTags from '@/components/HashTags.vue'
  import Footer from '@/views/section/Footer.vue'

  export default {
    name: 'home',
    components: {
			SearchBar,
      RecomList,
      PostList,
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
      isLogin() {
        this.$session.start()
        if (!this.$session.has('jwt')) {
          this.$router.push('/login')
        } else {
          // 로그인 되어 있다면, vuex token 업데이트
          this.$store.dispatch('login', this.$session.get('jwt'))
        }
      },
    },
    mounted() {
    }
  }
  
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Noto+Sans+KR:900&display=swap');


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
.main-section {
  position: relative;
  margin: 0 auto 3rem auto;
  width: 85%;
  min-width: 450px;
  border-radius: 50px;
}
.list-container {
  background-color: rgba(255, 255, 255, 0.95);
}
.article-header {
  color: #3f3f3f;
  text-align: left;
  margin-top: 2rem;
  border-radius: 20px;
  text-shadow:  26px 26px 51px #d9d9d9, 
              -26px -26px 51px #ffffff;
  width: fit-content;
}
#background {
    position: absolute !important;
    top: 0;
    left: 0;
    z-index: 0;
    width: 100vw;
    height: 30rem;
    background-color: lavender
}
</style>