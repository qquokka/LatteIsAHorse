<template>
  <div class="home container">
    <image-header />
    <div class="banner-quote col-12 col-md-4 offset-0 offset-sm-2 mt-5">
      <h2>라떼는 말이야...</h2>
      <h2>까페 대신 다방을 갔어</h2>
    </div>
    <div class="row justify-content-center mt-5">
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
  import router from 'vue-router'
	import SearchBar from '@/components/SearchBar.vue'
  import ImageHeader from '@/views/section/ImageHeader.vue'
  import RecomList from '@/components/RecomList.vue'
  import EditorPickList from '@/components/EditorPickList.vue'

  export default {
    name: 'home',
    components: {
			SearchBar,
      ImageHeader,
      RecomList,
      EditorPickList
    },
    data() {
      return {
        answers: [],
        username: '',
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
          user: this.user
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
          router.push('/login')
        } else {
          // 로그인 되어 있다면, vuex token 업데이트
          this.$store.dispatch('login', this.$session.get('jwt'))
        }
      }
    },
    mounted() {
      this.isLogin()
    }
  }
</script>

<style>
body {
  height: fit-content;
}
.banner-quote {
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
</style>