<template>
  <div class="home container">
    <search-bar @todoCreate-event="todoCreate" />
    <todo-list @todoDelete-event="todoUpdate" :todos="todos" />
    <recom-list/>
  </div>
</template>

<script>
  import axios from 'axios'
  import { mapGetters } from 'vuex' 
  import router from 'vue-router'
  import TodoList from '@/components/TodoList.vue'
  import SearchBar from '@/components/SearchBar.vue'
  import RecomList from '@/components/RecomList.vue'
  export default {
    name: 'home',
    components: {
      TodoList,
      SearchBar,
      RecomList
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