import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import MapTest from '../views/MapTest.vue'
import AboutUs from '../views/AboutUs.vue'
import PostPage from '../views/PostPage.vue'
import CreatePost from '../views/CreatePost.vue'
import PostDetail from '../views/PostDetail.vue'
import SerachResult from '../views/SearchResult.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/map',
    name: 'map',
    component: MapTest
  },
  {
    path: '/about',
    name: 'about',
    component: AboutUs
  },
  {
    path: '/posts',
    name: 'posts',
    component: PostPage
  },
  {
    path: '/posts/create',
    name: 'createpost',
    component: CreatePost,
    meta: { 
      requiresAuth: true
    }
  },
  {
    path: '/post/:postId',
    name: 'post-detail',
    component: PostDetail,
    props: true
  },
  {
    path: '/searched*',
    name: 'search-result',
    component: SerachResult,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router