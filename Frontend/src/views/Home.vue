<template>
  <div class="container">
    <nav-bar style="position:fixed;left:0;width:100%" />
    <div id="background" :style="{'background-image': `url(${require('../assets/img/cafebg.jpg')})`}">
      <h1 style="margin-top:5rem !important;color:white" id="slogan"></h1>
    </div>
    
    <div class="row justify-content-center" style="margin-top:12rem">
      <search-bar @search-event="searchNow" />
    </div>
    <div>
      <h1 class="display-1">SOMETHING IN HERE</h1>
    </div>
    <div id="main-raise" class="container shadow">
      <h2>Recommendation List</h2>
      <recom-list/>
      <h2>Editor's Pick List</h2>
      <editor-pick-list/>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import { mapGetters } from 'vuex' 
  import SearchBar from '@/components/SearchBar.vue'
  import NavBar from '@/components/NavBar.vue'
  import RecomList from '@/components/RecomList.vue'
  import EditorPickList from '@/components/EditorPickList.vue'

  export default {
    name: 'home',
    components: {
			SearchBar,
      RecomList,
      EditorPickList,
      NavBar
    },
    data() {
      return {
        answers: [],
        username: '',
        i: 0,
        slogan: '커피가 맛있으면 뭐해 손님이 없는걸'
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
        const datas = {
          query: query,
          user: this.user,

        }
        axios.post('http://127.0.0.1:8000/api/v1/todos/', datas, this.options)
          .then(response => {
            console.log(response)
            this.getAnswers()
          })
          .catch(error => {
            console.log(error)
          })
      },
      getAnswers() {
        axios.get(`http://127.0.0.1:8000/api/v1/users/${this.user}`, this.options)
          .then(response => {
            console.log(response)
            this.answers = response.data
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
          console.log(this.slogan)
          document.getElementById("slogan").innerText += this.slogan.charAt(this.i);
          this.i++;
          setTimeout(this.typeWriter, 95);
        }
      }
    },
    mounted() {
      this.typeWriter()
    }
  }
  
</script>

<style>
body {
  height: fit-content;
}
#app {
  background: whitesmoke;
}
.banner-quote {
  padding-top: 50px;
  color: white;
  text-align: left;
}
#main-raise {
  position: relative;
  background: white;
  border-radius: 15px 15px;
  height: 2000px;
  padding: 2rem;
}
.list-container {
  background-color: rgba(255, 255, 255, 0.74);
}
#background {
    height: 65vh;
    width: 100% !important;
    position: absolute !important;
    top: 0;
    left: 0;
    z-index: 0;
    filter: brightness(50%);
    background-size: cover !important;
}
</style>