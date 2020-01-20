<template>
  <div class="home container">
    <image-header />
    <div class="banner-quote col-12 col-md-4 offset-md-2 mt-5">
      <h2>라떼는 말이야...</h2>
      <h2>까페 대신 다방을 갔어</h2>
    </div>
    <div class="row justify-content-center mt-5">
      <search-bar @todoCreate-event="todoCreate" />
    </div>
    <div id="main-raise" class="container shadow">
      <h1 class="display-1 text-white">무언가를 넣을 훌륭한 자리가 매우 많이 비어 있으니 여러분의 많은 협조 바랍니다.</h1>
      <h2>Recommendation List</h2>
      <recom-list/>
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

  export default {
    name: 'home',
    components: {
			SearchBar,
      ImageHeader,
      RecomList,
    },
    data() {
      return {
        todos: [],
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
      todoCreate(title) {
        const datas = {
          title: title,
          user: this.user
        }
        // const formData = new FormData()
        // formData.append('title', title)
        // formData.append('user', 1)
        axios.post('http://127.0.0.1:8000/api/v1/todos/', datas, this.options)
          .then(response => {
            console.log(response)
            this.getTodos()
          })
          .catch(error => {
            console.log(error)
          })
      },
      getTodos() {
        axios.get(`http://127.0.0.1:8000/api/v1/users/${this.user}`, this.options)
          .then(response => {
            console.log(response)
            this.todos = response.data.todo_set
          })
      },
      todoUpdate(){
        this.getTodos()
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
      this.getTodos()
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
  background: rgba(0,0,0,0.5);
  height: 2000px;
}
</style>