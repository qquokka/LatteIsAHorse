import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import MapTest from '../views/MapTest.vue'
import AboutUs from '../views/AboutUs.vue'
import PostPage from '../views/PostPage.vue'
import CreatePost from '../views/CreatePost.vue'
import ReviewDetail from '../views/ReviewDetail.vue'
import SearchResult from '../views/SearchResult.vue'
import CafePage from '../views/CafePage.vue'
import HangMan from '../views/HangMan.vue'
import Admin from '../views/Admin.vue'
import Profile from '../views/Profile.vue'

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
    path: '/admin',
    name: 'admin',
    component: Admin
  },
  {
    path: '/posts',
    name: 'posts',
    component: PostPage
  },
  {
    path: '/cafe/:cafeId/posts/create',
    name: 'createpost',
    component: CreatePost,
    meta: { 
      requiresAuth: true
    },
    props: true
  },
  // {
  //   path: '/post/:postId/edit',
  //   name: 'edit-post',
  //   component: CreatePost,
  //   meta: {
  //     requiresAuth: true
  //   }
  // },
  {
    path: '/cafe/:cafeId/review/:reviewId',
    name: 'review-detail',
    component: ReviewDetail,
    props: true
  },
  {
    path: '/searched',
    name: 'search-result',
    component: SearchResult,
  },
  {
    path: '/cafe/:cafeId',
    name: 'cafePage',
    component: CafePage,
    props: true
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile
  },
  {
    path: '/hangman/is/the/perfect/game/of/the/whole/human/history',
    name: 'hangman',
    component: HangMan,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router